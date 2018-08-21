package tw.com.softleader.trainingexception.base.web;

import lombok.Data;

@Data
public class Msg {

	/** 代碼 */
	private String code;

	/** 說明 */
	private String desc;

	/** 訊息細節 */
	private String detail;

	public Msg(String code, String desc, String detail) {
		this.code = code;
		this.desc = desc;
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "(" + code + ")" + desc + ":" + detail;
	}
}
