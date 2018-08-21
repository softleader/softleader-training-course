package tw.com.softleader.practice.answer.base;

import java.util.function.Function;

/**
 * 處理不同的CaseNo成為各自的CaseInfo
 */
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
