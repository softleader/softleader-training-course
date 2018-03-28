package tw.com.softleader.sample;

import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import tw.com.softleader.sample.data.TimeClockDatas;
import tw.com.softleader.sample.data.TimeClockEntity;

public class CollectorSampleApp {

	public static void main(String[] args) {
		final List<TimeClockEntity> timeClocks = TimeClockDatas.get();

		grouping(timeClocks);
		groupingAndSumValue(timeClocks);
		toMapAndSumValue(timeClocks);
		toMapByIdentity(timeClocks);
		littleHardToRead(timeClocks);
	}

	/**
	 * 以人名分組
	 */
	public static void grouping(List<TimeClockEntity> timeClocks) {
		final Map<String, List<TimeClockEntity>> result = timeClocks.stream()
				.collect(Collectors.groupingBy(TimeClockEntity::getEmployeeName));

		System.out.println(result);
	}

	/**
	 * 以人名分組
	 * 並算出每個人的合計薪資
	 */
	public static void groupingAndSumValue(List<TimeClockEntity> timeClocks) {
		final Map<String, Double> result = timeClocks.stream()
				.collect(Collectors.groupingBy(
						TimeClockEntity::getEmployeeName,
						Collectors.summingDouble(TimeClockEntity::calcSalary))
				);

		System.out.println(result);
	}

	/**
	 * 以人名分組
	 * 並算出每個人合計薪資
	 * (因為沒有collect成list的動作，因此比上面快)
	 */
	public static void toMapAndSumValue(List<TimeClockEntity> timeClocks) {
		final Map<String, Double> result = timeClocks.stream().collect(Collectors.toMap(
				TimeClockEntity::getEmployeeName,
				TimeClockEntity::calcSalary,
				(s1, s2) -> s1 + s2
		));

		System.out.println(result);
	}

	/**
	 * 以人名分組
	 * 並取每個人最大時薪的那一筆紀錄
	 */
	public static void toMapByIdentity(List<TimeClockEntity> timeClocks) {
		final Map<String, TimeClockEntity> result = timeClocks.stream().collect(Collectors.toMap(
				TimeClockEntity::getEmployeeName,
				Function.identity(),
				(t1, t2) -> t1.getHourilyRate() > t2.getHourilyRate() ? t1 : t2));

		System.out.println(result);
	}

	/**
	 * 根據人名分組
	 * 並算出每人的周薪
	 */
	public static void littleHardToRead(List<TimeClockEntity> timeClocks) {
		final Map<String, Map<Integer, Double>> result = timeClocks.stream().collect(Collectors.groupingBy(
				TimeClockEntity::getEmployeeName,
				Collectors.groupingBy(
						t -> t.getTimeIn().get(WeekFields.ISO.weekOfYear()),
						Collectors.summingDouble(TimeClockEntity::calcSalary)
				)
		));

		System.out.println(result);
	}

}
