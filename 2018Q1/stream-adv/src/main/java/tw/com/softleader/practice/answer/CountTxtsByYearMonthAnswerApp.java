package tw.com.softleader.practice.answer;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.YearMonth;
import java.time.chrono.MinguoDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

import tw.com.softleader.commons.function.Parses;
import tw.com.softleader.sample.MergeTxtsToZipSampleApp;

public class CountTxtsByYearMonthAnswerApp {
	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyMMdd");

	/**
	 * 將resource/txts中所有的txt檔的所屬資料夾為期案件編號
	 * 案件編號以HW開頭者為線上交易進件，TP開頭則代表為台北區的書面掃描進件
	 *
	 * 案件編號格式說明:
	 * 線上交易件 HW01061129000001, 其中1061129為進件日期, 格式(民國年)YYYMMDD
	 * 台北區的書面件 TP060821191749B0, 其中060821為進件日期, 只有民國100年以後的資料, 因此開頭須補1, 格式(民國年)YYYMMDD
	 *
	 * 請依照月份統計，蒐集每月案件數
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		final Map<String, CaseType> caseTypes = Stream.of(CaseType.values()).collect(Collectors.toMap(CaseType::name, Function.identity()));

		Files.walk(Paths.get(MergeTxtsToZipSampleApp.class.getResource("/txts").toURI()))
				.map(Path::toFile)
				.filter(File::isDirectory)
				.map(File::getName)
				// 根據不同的案件類型，初始化成不同的CaseInfo
				.map(caseNo -> Optional.ofNullable(caseTypes.get(caseNo.substring(0, 2))).map(ct -> ct.toCaseInfo(caseNo)).orElse(null))
				.filter(Objects::nonNull)
				// 算出年月份
				.collect(Collectors.groupingBy(ci -> YearMonth.from(ci.getDate())))
				.forEach((ym, caseInfos) -> System.out.println(ym + ": " + caseInfos.size()));

	}

	/**
	 * 不使用Stream的範例
	 * @throws Exception
	 */
	public static void nonStream() throws Exception {
		final URL txtsUrl = CountTxtsByYearMonthAnswerApp.class.getResource("/txts");
		final Path txtPath = Paths.get(txtsUrl.toURI());

		final Map<YearMonth, List<String>> result = new TreeMap<>();
		final File[] files = txtPath.toFile().listFiles();
		for (File file : files) {
			// txts下第一層一定是資料夾，不是資料夾的就略過
			if (file.isDirectory()) {
				// 資料夾的名稱即為案件編號
				final String caseNo = file.getName();
				final String dateStr;
				if (caseNo.startsWith("HW")) {
					dateStr = caseNo.substring(3, 10);
				} else if (caseNo.startsWith("TP")) {
					dateStr = "1" + caseNo.substring(2, 8);
				} else {
					// 非預期的開頭不處理
					continue;
				}

				// 處理民國年字串轉日期
				final MinguoDate date = Parses.tryMinguoDate(dateStr, FORMATTER);
				// 擷取年月資訊
				final YearMonth yearMonth = YearMonth.from(date);

				// 蒐集
				if (!result.containsKey(yearMonth)) {
					result.put(yearMonth, Lists.newArrayList());
				}
				result.get(yearMonth).add(caseNo);
			}
		}

		result.forEach((ym, caseNos) -> {
			System.out.println(ym + ": " + caseNos.size());
		});
	}

}
