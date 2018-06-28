package tw.com.softleader.trainingexception.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tw.com.softleader.trainingexception.base.security.User;
import tw.com.softleader.trainingexception.base.security.UserSupplier;
import tw.com.softleader.trainingexception.base.validation.ValidationResult;
import tw.com.softleader.trainingexception.web.TestForm;

@Service
@Slf4j
public class TestService {

	@Autowired
	private VerifyComponent verifyComponent;

	public ValidationResult verify(TestForm testForm) {
		log.info("verifing");
		final ValidationResult validationResult = new ValidationResult();

		// 資料準備
		final User user = UserSupplier.get();

		// 進行檢核，並整理成檢核結果
		validationResult.addAll(verifyComponent.checkHellowRequird(testForm, user));
		validationResult.addAll(verifyComponent.checkWorldRequird(testForm, user));

		return validationResult;
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
