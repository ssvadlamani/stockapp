package com.payconiq.stockapp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.payconiq.stockapp.response.JsonResponse;
import com.payconiq.stockapp.util.Utils;

@ControllerAdvice(basePackages="com.payconiq.stockapp")
public class StockExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {StockNotFoundException.class, IllegalArgumentException.class })
	protected ResponseEntity<Object> handleStockRuntimeException(IllegalArgumentException ex, WebRequest request) {
		JsonResponse response = Utils.buildResponse(null, "404","STOCK NOT FOUND! PLEASE TRY WITH VALID ID.");
		return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler(value = {RuntimeException.class})
	protected ResponseEntity<Object> handleStockNotFound(RuntimeException ex, WebRequest request) {
		JsonResponse response = Utils.buildResponse(null, "503",ex.getMessage());
		return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
}