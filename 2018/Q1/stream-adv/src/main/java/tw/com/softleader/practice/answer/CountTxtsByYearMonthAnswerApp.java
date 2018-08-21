package tw.com.softleader.practice.answer;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tw.com.softleader.practice.answer.base.CaseType;
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

}
