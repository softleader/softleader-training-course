package tw.com.softleader.practice.answer.base;

import java.time.chrono.MinguoDate;

public class CaseInfo {

	protected String caseNo;
	protected MinguoDate date;

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public MinguoDate getDate() {
		return date;
	}

	public void setDate(MinguoDate date) {
		this.date = date;
	}
}
