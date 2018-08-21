package tw.com.softleader.practice.answer;

import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import tw.com.softleader.sample.data.TimeClockDatas;
import tw.com.softleader.sample.data.TimeClockEntity;

public class CollectorAnswerApp {

	public static void main(String[] args) {
		final List<TimeClockEntity> timeClocks = TimeClockDatas.get();

		groupingByName(timeClocks);
		groupingByNameWithSumSalary(timeClocks);
		toMapByName(timeClocks);
		toMapByNameWithSumSalary(timeClocks);
		groupingByNameAndWeekWithSumSalary(timeClocks);
	}

	/**
	 * 以人名分組
	 */
	public static void groupingByName(List<TimeClockEntity> timeClocks) {
		final Map<String, List<TimeClockEntity>> result = timeClocks.stream()
				.collect(Collectors.groupingBy(TimeClockEntity::getEmployeeName));

		System.out.println(result);
	}

	/**
	 * 以人名分組
	 * 並算出每個人的合計薪資
	 */
	public static void groupingByNameWithSumSalary(List<TimeClockEntity> timeClocks) {
		final Map<String, Double> result = timeClocks.stream()
				.collect(Collectors.groupingBy(
						TimeClockEntity::getEmployeeName,
						Collectors.summingDouble(TimeClockEntity::calcSalary))
				);

		System.out.println(result);
	}

	/**
	 * 以人名分組
	 */
	public static void toMapByName(List<TimeClockEntity> timeClocks) {
		final Map<String, TimeClockEntity> result = timeClocks.stream()
				.collect(Collectors.toMap(
						TimeClockEntity::getEmployeeName,
						Function.identity(),
						(o1, o2) -> o1
				));

		System.out.println(result);
	}

	/**
	 * 以人名分組
	 * 並算出每個人合計薪資
	 * (因為沒有collect成list的動作，因此比上面快)
	 */
	public static void toMapByNameWithSumSalary(List<TimeClockEntity> timeClocks) {
			final Map<String, Double> result = timeClocks.stream()
				.collect(Collectors.toMap(
				TimeClockEntity::getEmployeeName,
				TimeClockEntity::calcSalary,
				(s1, s2) -> s1 + s2
		));

		System.out.println(result);
	}

	/**
	 * 根據人名分組，再根據每周分組
	 * 並算出每人每周的總薪水
	 */
	public static void groupingByNameAndWeekWithSumSalary(List<TimeClockEntity> timeClocks) {
		final Map<String, Map<Integer, Double>> result = timeClocks.stream()
				.collect(Collectors.groupingBy(
					TimeClockEntity::getEmployeeName,
					Collectors.groupingBy(
							t -> t.getTimeIn().get(WeekFields.ISO.weekOfYear()),
							Collectors.summingDouble(TimeClockEntity::calcSalary)
					)
		));

		System.out.println(result);
	}

}
