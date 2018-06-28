package tw.com.softleader.trainingexception.api.exception;

import lombok.Getter;
import tw.com.softleader.trainingexception.base.web.Msg;

/** 任何 API server 於執行期間發生無法進行的情況的例外 */
public class ApiServerException extends RuntimeException {

	@Getter
	private Msg msg;

	public ApiServerException(Msg msg) {
		super(msg.toString());
		this.msg = msg;
	}

}
