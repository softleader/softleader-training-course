package tw.com.softleader.practice.answer;

import java.util.function.Function;

public enum CaseType {

	HW(HwCaseInfo::new),
	TP(TpCaseInfo::new);

	private Function<String, CaseInfo> mapper;

	CaseType(Function<String, CaseInfo> mapper) {
		this.mapper = mapper;
	}

	public CaseInfo toCaseInfo(String caseNo) {
		return mapper.apply(caseNo);
	}

}
