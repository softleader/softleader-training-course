package tw.com.softleader.practice.answer.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tw.com.softleader.commons.function.Parses;
import tw.com.softleader.practice.CountTxtsByYearMonthPracticeApp;

@Getter
@Setter
@AllArgsConstructor
public class HwCaseInfo extends CaseInfo {

	public HwCaseInfo(String caseNo) {
		final String dateStr = caseNo.substring(3, 10);

		this.caseNo = caseNo;
		this.date = Parses.tryMinguoDate(dateStr, CountTxtsByYearMonthPracticeApp.FORMATTER);
	}

}
