package tw.com.softleader.trainingexception.api.test.server;

import java.util.Optional;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tw.com.softleader.trainingexception.api.exception.ApiServerException;
import tw.com.softleader.trainingexception.base.web.Msg;
import tw.com.softleader.trainingexception.base.web.Msgs;
import tw.com.softleader.trainingexception.service.TestService;

/**
 * 外部介接(Restful Service server)
 */
@RestController
@RequestMapping("/test/api")
@Slf4j
public class TestApiServer {

	@Autowired
	private TestService testService;

	/** server method 範例 */
	@GetMapping("/get")
	public TestApiGetResp get(TestApiGetReq req) {
		// 接到來自外部的參數，第一件事先 log
		log.info("Test:get req:{}", ToStringBuilder.reflectionToString(req, ToStringStyle.SHORT_PREFIX_STYLE));
		Msg msg = Msgs.MSG0000.build("OK");

		try {
			// 進行檢核，若有問題則直接拋 ApiServerException，裡面隱含 Msg 物件
			Optional.ofNullable(req.getArg1())
					.orElseThrow(() -> new ApiServerException(Msgs.ERR0001.build("Arg1不應為空")));
			Optional.ofNullable(req.getArg2())
					.orElseThrow(() -> new ApiServerException(Msgs.ERR0001.build("Arg2不應為空")));
			Optional.ofNullable(req.getArg3())
					.orElseThrow(() -> new ApiServerException(Msgs.ERR0001.build("Arg3不應為空")));

			// 已經包裝好的商業邏輯，可以不處理交給最大的 catch 處理，或是獨立 catch 定義獨特的 rtnCode
			try {
				testService.apiGet();
			} catch (Exception e) {
				throw new ApiServerException(Msgs.ERR0002.build("呼叫Service失敗:" + e.getMessage()));
			}
		} catch (ApiServerException e) {
			// ApiServerException 大多是已知的錯誤，但仍然log下來以便系統間溝通
			msg = e.getMsg();
			log.error(msg.toString()); // 幾乎都是自己 throw 出來的所以就不需要 printStrack 了
		} catch (Exception e) {
			// 預期外的錯誤使用一個共通的回應代碼
			// 並蒐集輸入參數與 StrackTrace
			// 無論如何絕不能直接將 Exception 曝露給外部系統
			msg = Msgs.ERR9999.build("API Test:get failed, req:{0}", ToStringBuilder.reflectionToString(req, ToStringStyle.SHORT_PREFIX_STYLE));
			log.error(msg.toString(), e);
		}

		return new TestApiGetResp(msg);
	}

}
