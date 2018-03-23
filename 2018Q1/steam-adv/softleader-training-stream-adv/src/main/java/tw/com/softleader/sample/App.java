package tw.com.softleader.sample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.chrono.MinguoDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;

import tw.com.softleader.commons.function.Unchecked;
import tw.com.softleader.sample.zip.ZipCollector;

public class App {

	public static final DateTimeFormatter MINGUO_DATE_FORMATTER = DateTimeFormatter.ofPattern("y/MM/dd");
	public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");


	public static void main(String[] args) throws Exception {
		mergeToOneTxt();
//		mergeToZip();
	}

	/**
	 * 將resource/txts中所有的txt檔加到zip中
	 */
	private static void mergeToZip() throws IOException, URISyntaxException {
		Files.walk(Paths.get(App.class.getResource("/txts").toURI()))
				.map(Path::toFile)
				.filter(File::isFile)
				.filter(f -> f.getName().endsWith(".txt"))
				.collect(new ZipCollector(new FileOutputStream("D:/test.zip"), f -> f.getParentFile().getName() + "_" + f.getName()))
				.build();
	}

	/**
	 * 將resource/txts中所有的txt檔合併成單一一個txt檔
	 * 並且取代其中的產生日期與產生時間為當前時間
	 * 合併過程中，每一個txt檔之間以單一一行FormFeed進行區隔
	 */
	private static void mergeToOneTxt() throws IOException, URISyntaxException {
		final MinguoDate date = MinguoDate.now();
		final LocalTime time = LocalTime.now();

		final String dateTx = "產生日期：" + MINGUO_DATE_FORMATTER.format(date);
		final String timeTx = "產生時間：" + TIME_FORMATTER.format(time);
		final Pattern datePattern = Pattern.compile("產生日期：(\\d{2,3}/\\d{2}/\\d{2}| {8,9})?");
		final Pattern timePattern = Pattern.compile("產生時間：(\\d{2}:\\d{2}:\\d{2}| {8})?");

		final List<String> totalReport = Files.walk(Paths.get(App.class.getResource("/txts").toURI()))
				.filter(p -> p.toFile().isFile())
				.filter(p -> p.toFile().getName().endsWith(".txt"))
				.map(Unchecked.apply(Files::readAllLines))
				.peek(report -> report.replaceAll(line -> {
					String newLine = line;

					// 處理日期取代
					final Matcher dateMatcher = datePattern.matcher(newLine);
					if (dateMatcher.find()) {
						newLine = dateMatcher.replaceAll(dateTx);
					}

					// 處理時間取代
					final Matcher timeMatcher = timePattern.matcher(newLine);
					if (timeMatcher.find()) {
						newLine = timeMatcher.replaceAll(timeTx);
					}

					// 如果記憶體地址不同, 代表有被replace過了, 因此這邊用==而不用equals
					if (newLine == line) {
						return line;
					} else {
						return newLine;
					}
				}))
				// 合併檔案
				.reduce((t1, t2) -> {
					// 依取得的案號，將各案號的變更明細表文字檔(F2B產生的)合併為一個檔案(以文字”FormFeed”分隔)
					t1.add("FormFeed");
					t1.addAll(t2);
					return t1;
				})
				.orElseGet(Lists::newArrayList);
		Files.write(Paths.get("D:/test.txt"), totalReport);
	}

}
