package tw.com.softleader.trainingexception.base.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.com.softleader.trainingexception.base.security.User;
import tw.com.softleader.trainingexception.base.web.Msg;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Validation {

	/** 檢核類型 */
	private ValidationType type;

	/** 權責層級 */
	private int approveLevel;

	/** 訊息 */
	private Msg msg;

	/** 檢核通過否 */
	private boolean pass;

	/** 操作通過的人員 */
	private String userAcct;

	public Validation(ValidationType type, int approveLevel, Msg msg) {
		this.type = type;
		this.approveLevel = approveLevel;
		this.msg = msg;
	}

	public Validation pass(User user) {
		this.pass = true;
		this.userAcct = user.getAcct();
		return this;
	}
}
