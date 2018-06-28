package tw.com.softleader.trainingexception.service;

import static tw.com.softleader.trainingexception.base.auth.ApproveAuth.EMPTY;
import static tw.com.softleader.trainingexception.base.auth.ApproveAuth.L52;
import static tw.com.softleader.trainingexception.base.validation.ValidationType.ABORT;
import static tw.com.softleader.trainingexception.base.validation.ValidationType.REVIEW;
import static tw.com.softleader.trainingexception.base.web.Msgs.VAL001;
import static tw.com.softleader.trainingexception.base.web.Msgs.VAL002;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import tw.com.softleader.trainingexception.base.security.User;
import tw.com.softleader.trainingexception.base.validation.Validation;
import tw.com.softleader.trainingexception.base.validation.ValidationResult;
import tw.com.softleader.trainingexception.web.TestForm;

@Component
public class VerifyComponent {

	/** Hello 不能為空 */
	public ValidationResult checkHellowRequird(TestForm testForm, User user) {
		final Validation validation = new Validation(ABORT, EMPTY.getLevel(), VAL001.build("Hello不能為空"));

		if (Strings.isNotBlank(testForm.getHello())) {
			validation.pass(user);
		}

		return new ValidationResult(validation);
	}

	/** World 不能為空，且有值時簽核層級提升到科主管 */
	public ValidationResult checkWorldRequird(TestForm testForm, User user) {
		final Validation validation1 = new Validation(ABORT, EMPTY.getLevel(), VAL001.build("World不能為空"));
		final Validation validation2 = new Validation(REVIEW, L52.getLevel(), VAL002.build("有輸入World，簽核層級提升到 5.2科主管"));

		if (Strings.isNotBlank(testForm.getWorld())) {
			validation1.pass(user);
		}
		if (user.getApproveAuth().getLevel() >= validation2.getApproveLevel()) {
			validation2.pass(user);
		}

		return new ValidationResult(validation1, validation2);
	}

}
