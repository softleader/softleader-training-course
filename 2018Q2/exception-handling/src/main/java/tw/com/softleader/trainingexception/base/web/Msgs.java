package tw.com.softleader.trainingexception.base.web;

import java.text.MessageFormat;

import lombok.Getter;

@Getter
public enum Msgs {

	// 檢核類訊息
	VAL001("檢核不通過"),
	VAL002("檢核授權不足"),

	// API訊息
	MSG0000("API呼叫成功"),
	ERR0001("API參數錯誤"),
	ERR0002("API運行錯誤"),
	ERR9999("API發生預期外的錯誤"),

	SYSERR("預期外的錯誤");

	private String desc;

	Msgs(String desc) {
		this.desc = desc;
	}

	public Msg build(String detail, Object... objs) {
		return new Msg(this.name(), this.desc, MessageFormat.format(detail, objs));
	}
}
