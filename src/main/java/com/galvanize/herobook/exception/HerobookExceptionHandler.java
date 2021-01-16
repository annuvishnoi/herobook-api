package com.galvanize.herobook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.galvanize.herobook.response.HerobookResponse;

@RestControllerAdvice
public class HerobookExceptionHandler {

	@ExceptionHandler(HerobookException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public HerobookResponse handleHeroNotFound(HerobookException exception) {
		HerobookResponse response = new HerobookResponse();
		response.addErrorMessage(exception.getMessage());
		return response;
	}

}


