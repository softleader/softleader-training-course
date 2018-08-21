package tw.com.softleader.trainingexception.api.exception;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ApiRetrunNotSuccessException extends RuntimeException {

	public ApiRetrunNotSuccessException(String rtnCode, Object request) {
		super("外部介接系統呼叫回傳錯誤代碼 代碼:" + rtnCode + ", 參數:" + ToStringBuilder.reflectionToString(request, ToStringStyle.SHORT_PREFIX_STYLE) + ".");
	}

}
