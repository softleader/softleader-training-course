package tw.com.softleader.trainingexception.api.exception;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ApiRuntimeException extends RuntimeException {

	public ApiRuntimeException(Object request, Exception e) {
		super("外部介接系統呼叫失敗 參數:" + ToStringBuilder.reflectionToString(request, ToStringStyle.SHORT_PREFIX_STYLE), e);
	}

}
