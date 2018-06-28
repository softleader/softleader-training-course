package tw.com.softleader.trainingexception.api;

import tw.com.softleader.trainingexception.base.web.Msgs;

/**
 * 外部介接回傳共用介面.
 * 各自的 response 各自實作
 */
public interface ApiResponse {

	String getRtnCode();
	default String getDesc() {
		return getRtnCode();
	}

	default boolean isSuccesRtn() {
		return Msgs.MSG0000.name().equals(getRtnCode());
	}

}
