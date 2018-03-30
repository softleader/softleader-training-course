package tw.com.softleader.sample;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.MinguoDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;

import tw.com.softleader.commons.function.Unchecked;
import tw.com.softleader.sample.report.ReportReducer;

public class MergeTxtsToTxtSampleApp {

	private static final DateTimeFormatter MINGUO_DATE_FORMATTER = DateTimeFormatter.ofPattern("y/MM/dd");
	private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

	/**
	 * 將resource/txts中所有的txt檔合併成單一一個txt檔
	 * 並且取代其中的產生日期與產生時間為當前時間
	 * 合併過程中，每一個txt檔之間以單一一行FormFeed進行區隔
	 */
	public static void main(String[] args) throws Exception {
		final LocalDateTime reportTime = LocalDateTime.now();

		final List<String> totalReport = Files.walk(Paths.get(MergeTxtsToTxtSampleApp.class.getResource("/txts").toURI()))
				.filter(p -> p.toFile().isFile())
				.filter(p -> p.toFile().getName().endsWith(".txt"))
				.map(Unchecked.apply(Files::readAllLines)) // 將檔案以純文字文件的方式讀成List<String>
				.peek(report -> handleReportTime(reportTime, report)) // 處理報表內的時間文字
				.reduce(new ReportReducer("FormFeed")) // 合併檔案，以FormFeed間隔
				.orElseGet(Lists::newArrayList);

		Files.write(Paths.get("D:/test.txt"), totalReport);
	}

	// report中的日期字串模式
	private static final Pattern DATE_PATTERN = Pattern.compile("產生日期：(\\d{2,3}/\\d{2}/\\d{2}| {8,9})?");
	// report中的時間字串模式
	private static final Pattern TIME_PATTERN = Pattern.compile("產生時間：(\\d{2}:\\d{2}:\\d{2}| {8})?");

	private static void handleReportTime(LocalDateTime reportTime, List<String> report) {
		final MinguoDate date = MinguoDate.from(reportTime);
		final LocalTime time = reportTime.toLocalTime();

		final String dateTx = "產生日期：" + MINGUO_DATE_FORMATTER.format(date);
		final String timeTx = "產生時間：" + TIME_FORMATTER.format(time);

		report.replaceAll(line -> {
			String newLine = line;

			// 處理日期取代
			final Matcher dateMatcher = DATE_PATTERN.matcher(newLine);
			if (dateMatcher.find()) {
				newLine = dateMatcher.replaceAll(dateTx);
			}

			// 處理時間取代
			final Matcher timeMatcher = TIME_PATTERN.matcher(newLine);
			if (timeMatcher.find()) {
				newLine = timeMatcher.replaceAll(timeTx);
			}

			// 如果記憶體地址不同, 代表有被replace過了, 因此這邊用==而不用equals
			if (newLine == line) {
				return line;
			} else {
				return newLine;
			}
		});
	}

}
