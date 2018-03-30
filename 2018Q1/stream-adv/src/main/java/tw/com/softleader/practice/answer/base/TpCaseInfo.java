package tw.com.softleader.practice.answer.base;

import lombok.Getter;
import lombok.Setter;
import tw.com.softleader.commons.function.Parses;
import tw.com.softleader.practice.CountTxtsByYearMonthPracticeApp;

@Getter
@Setter
public class TpCaseInfo extends CaseInfo {

	public TpCaseInfo(String caseNo) {
		final String dateStr = "1" + caseNo.substring(2, 8);

		this.caseNo = caseNo;
		this.date = Parses.tryMinguoDate(dateStr, CountTxtsByYearMonthPracticeApp.FORMATTER);
	}
}
