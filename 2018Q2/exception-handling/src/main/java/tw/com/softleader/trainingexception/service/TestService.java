package tw.com.softleader.trainingexception.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tw.com.softleader.trainingexception.base.security.User;
import tw.com.softleader.trainingexception.base.security.UserSupplier;
import tw.com.softleader.trainingexception.base.validation.Validations;
import tw.com.softleader.trainingexception.web.TestForm;

@Service
@Slf4j
public class TestService {

	@Autowired
	private VerifyComponent verifyComponent;

	public Validations verify(TestForm testForm) {
		log.info("verifing");
		final Validations validations = new Validations();

		final User user = UserSupplier.get();
		validations.addAll(verifyComponent.checkHellowRequird(testForm, user));
		validations.addAll(verifyComponent.checkWorldRequird(testForm, user));

		return validations;
	}

	public void confirm() {
		log.info("confirm");
//		throw new RuntimeException("確認發生錯誤");
	}

	public void sendReview() {
		log.info("send review");
	}

	public void apiGet() {

	}

}
