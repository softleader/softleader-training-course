package tw.com.softleader.practice;

import java.util.List;

import tw.com.softleader.sample.data.TimeClockDatas;
import tw.com.softleader.sample.data.TimeClockEntity;

public class CollectorPracticeApp {

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
		// TODO
	}

	/**
	 * 以人名分組
	 * 並算出每個人的合計薪資
	 */
	public static void groupingByNameWithSumSalary(List<TimeClockEntity> timeClocks) {
		// TODO
	}

	/**
	 * 以人名分組
	 */
	public static void toMapByName(List<TimeClockEntity> timeClocks) {
		// TODO
	}

	/**
	 * 以人名分組
	 * 並算出每個人合計薪資
	 * (因為沒有collect成list的動作，因此比上面快)
	 */
	public static void toMapByNameWithSumSalary(List<TimeClockEntity> timeClocks) {
		// TODO
	}

	/**
	 * 根據人名分組，再根據每周分組
	 * 並算出每人每周的總薪水
	 */
	public static void groupingByNameAndWeekWithSumSalary(List<TimeClockEntity> timeClocks) {
		// TODO
	}

}
