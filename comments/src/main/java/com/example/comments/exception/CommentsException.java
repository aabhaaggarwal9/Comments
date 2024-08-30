package com.example.comments.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentsException extends RuntimeException {

	private HttpStatus status;
	private String message;
}
