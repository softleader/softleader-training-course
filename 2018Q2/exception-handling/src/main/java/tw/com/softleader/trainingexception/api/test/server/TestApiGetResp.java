package tw.com.softleader.trainingexception.api.test.server;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;
import tw.com.softleader.trainingexception.base.web.Msg;
import tw.com.softleader.trainingexception.api.ApiResponse;

@Data
public class TestApiGetResp implements ApiResponse {

	private String rtnCode;
	private String desc;

	public TestApiGetResp(Msg msg) {
		this.rtnCode = msg.getCode();
		this.desc = StringUtils.trimToEmpty(msg.getDesc()) + ":" + StringUtils.trimToEmpty(msg.getDetail());
	}
}
