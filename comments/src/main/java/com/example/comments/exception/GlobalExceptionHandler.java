package com.example.comments.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CommentsException.class)
	public ResponseEntity<String> handleTodoAPIException(CommentsException exception) {

		return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
	}

}
