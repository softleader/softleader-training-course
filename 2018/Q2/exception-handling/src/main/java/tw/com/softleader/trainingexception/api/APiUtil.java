package tw.com.softleader.trainingexception.api;

import tw.com.softleader.trainingexception.api.exception.ApiRetrunNotSuccessException;

public class APiUtil {

	public static void requiredSuccess(ApiResponse response, Object request) {
		if (!response.isSuccesRtn())	{
			throw new ApiRetrunNotSuccessException(response.getRtnCode(), request);
		}
	}

}
