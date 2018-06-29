package tw.com.softleader.trainingexception.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tw.com.softleader.trainingexception.WebUtils;
import tw.com.softleader.trainingexception.base.security.DummyUsers;
import tw.com.softleader.trainingexception.base.security.UserSupplier;
import tw.com.softleader.trainingexception.base.validation.ValidationType;
import tw.com.softleader.trainingexception.base.validation.ValidationResult;
import tw.com.softleader.trainingexception.base.web.MsgBody;
import tw.com.softleader.trainingexception.base.web.Msgs;
import tw.com.softleader.trainingexception.service.TestService;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

	@Autowired
	private TestService testService;

	@GetMapping("/confirm")
	public ResponseEntity confirm(TestForm testForm) {
		try {
			UserSupplier.set(DummyUsers.DEPT);

			// 檢核
			final ValidationResult validationResult = testService.verify(testForm);
			final String result;

			// 根據檢核執行相對應的商業操作
			if (validationResult.isAllow(ValidationType.INFO)) {
				testService.confirm();
				result = "確認成功";
			} else if (validationResult.isAllow(ValidationType.REVIEW)) {
				testService.sendReview();
				result = "權限不足，已轉送覆核";
			} else {
				result = null;
			}

			return new ResponseEntity<>(
					new MsgBody<>(result, validationResult.unPassToMsg()),
					HttpStatus.OK
			);
		} catch (Exception e) {
			log.error("error catched", e);
			return new ResponseEntity<>(
					new MsgBody<>(Msgs.SYSERR.build(WebUtils.getMessage(e))),
					HttpStatus.INTERNAL_SERVER_ERROR
			);
		} finally {
			UserSupplier.clear();
		}
	}

}
