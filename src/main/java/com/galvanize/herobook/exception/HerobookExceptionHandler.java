package com.galvanize.herobook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.galvanize.herobook.response.HerobookResponse;

@ControllerAdvice
public class HerobookExceptionHandler {

	@ExceptionHandler(HerobookException.class)
	public ResponseEntity<HerobookResponse> handleHeroNotFound(HerobookException exception) {
		HerobookResponse response = new HerobookResponse();
		response.addErrorMessage(exception.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
