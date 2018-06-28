package tw.com.softleader.trainingexception.api.test.client;

import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import tw.com.softleader.trainingexception.api.APiUtil;
import tw.com.softleader.trainingexception.api.exception.ApiRetrunNotSuccessException;
import tw.com.softleader.trainingexception.api.exception.ApiRuntimeException;

/**
 * 外部介接(Restful Service client)
 */
@Component
@Slf4j
public class TestApiClient {

	private final RestTemplate client;

	public TestApiClient() {
		this.client = new RestTemplate();
		SimpleClientHttpRequestFactory requestFactory = (SimpleClientHttpRequestFactory) client.getRequestFactory();
		requestFactory.setReadTimeout(180_000);
		requestFactory.setConnectTimeout(180_000);
	}

	/** client method 範例 */
	public void call() {
		final TestApiCallReq req = new TestApiCallReq();
		try {
			// request 資料準備
			req.setArg1("AAA");
			req.setArg2("BBB");
			req.setArg3("CCC");
			req.setArg4("DDD");

			// 呼叫外部API
			final ResponseEntity<TestApiCallResp> resp = client.getForEntity("dummy://this.is.a.pen/test", TestApiCallResp.class, req);

			// 是否允許空的回傳由各API自己決定，此處不允許
			Objects.requireNonNull(resp.getBody());
			APiUtil.requiredSuccess(resp.getBody(), req);

			// 即便是呼叫成功，仍然要 log 下來，因為無法保證對方不會亂寫 rtnCode
			log.info("Test:call success, resp:{}", resp);
		} catch (ApiRetrunNotSuccessException e) {
			throw e;
		} catch (Exception e) {
			// 所有預期外的 Exception 一樣保留輸入參數以利除錯
			throw new ApiRuntimeException(req, e);
		}
	}
}
