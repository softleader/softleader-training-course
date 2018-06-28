package tw.com.softleader.trainingexception.base.validation;

import lombok.Getter;

@Getter
public enum ValidationType {

	/** 不允許繼續流程 */
	ABORT(0),

	/** 需要使用者重複確認(改於頁面實作) */
	CHECK(100),

	/** 部分權限不足, 需覆核 */
	REVIEW(200),

	/** 顯示提示訊息 */
	INFO(300),

	/** 無任何檢核結果 */
	SUCCESS(999);

	/** 層級, 數字越低越嚴重 */
	private int level;

	public boolean isGe(ValidationType that) {
		return this.level >= that.level;
	}

	public boolean isLt(ValidationType that) {
		return this.level < that.level;
	}

	private ValidationType(int level) {
		this.level = level;
	}

}
