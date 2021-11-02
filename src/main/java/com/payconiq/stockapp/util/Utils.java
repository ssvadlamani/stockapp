package com.payconiq.stockapp.util;

import com.payconiq.stockapp.response.JsonError;
import com.payconiq.stockapp.response.JsonResponse;

public class Utils<T> {

	public static JsonResponse buildResponse(Object data, String errorCode, String error) {
		JsonResponse response = null;
		if (data != null) {
			response = new JsonResponse(data, null);
		} else {
			response = new JsonResponse(null, new JsonError(errorCode, error));
		}
		return response;
	}
	
}
