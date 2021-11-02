package com.payconiq.stockapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException() {
        super();
    }
    public StockNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public StockNotFoundException(String message) {
        super(message);
    }
    public StockNotFoundException(Throwable cause) {
        super(cause);
    }
}


