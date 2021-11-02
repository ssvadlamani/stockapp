package com.payconiq.stockapp.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class JsonResponse<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object data;

	private JsonError error;

	public JsonResponse() {

	}

	public JsonResponse(T data, JsonError error) {
		this.data = data;
		this.error = error;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public JsonError getError() {
		return error;
	}

	public void setError(JsonError error) {
		this.error = error;
	}

}
