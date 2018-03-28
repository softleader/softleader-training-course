package tw.com.softleader.sample.report;

import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class ReportReducer implements BinaryOperator<List<String>> {

	private String formFeed;

	public ReportReducer() {
		this.formFeed = null;
	}

	public ReportReducer(String formFeed) {
		this.formFeed = formFeed;
	}

	@Override
	public List<String> apply(List<String> t1, List<String> t2) {
		// 將文字列表前後合併
		// 若存在分隔字串，則中間插入分隔字串行
		Optional.ofNullable(formFeed).ifPresent(t1::add);
		t1.addAll(t2);
		return t1;
	}
}
