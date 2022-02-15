package com.spring.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.core.JsonParseException;
import com.spring.model.ResponObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class HandleExceptions {

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<errorMsg> sqlExeception(SQLIntegrityConstraintViolationException ex, WebRequest rq) {
		log.info("sqlInter");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(errorMsg.create("400", ex.getLocalizedMessage()));
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<errorMsg> elementEx(NoSuchElementException ex, WebRequest rq) {
		log.info("noSuchElement");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(errorMsg.create("400", ex.getLocalizedMessage()));
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<errorMsg> emptyResultData(EmptyResultDataAccessException ex, WebRequest rq) {
		log.info("EmptyData");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg.create("400",
				"no exsit " + rq.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, 0)));
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String noHandle(NoHandlerFoundException e, WebRequest rq) {
		log.info("404 not found");
		return "404 not found";
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<errorMsg> IllegalEx(IllegalArgumentException ex, WebRequest rq) {
		log.info("IllegalException");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg.create("400", ex.getMessage()));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<errorMsg> constraintEx(ConstraintViolationException ex, WebRequest rq) {
		log.info("run ex");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(errorMsg.create("400", ex.getConstraintViolations().toString()));
	}

	// validation exception fail
	@ExceptionHandler(BindException.class)
	public ResponseEntity<errorMsg> validateEx(BindException ex, WebRequest rq) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(errorMsg.create("400", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()));
	}

	@ExceptionHandler(JsonParseException.class)
	public ResponseEntity<errorMsg> jsonEx(JsonParseException ex, WebRequest rq) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg.create("400", ex.getOriginalMessage()));
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<errorMsg> jsonEx(HttpMessageNotReadableException ex, WebRequest rq) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg.create("400", "Required request body is missing"));
	} 
}

@Getter
@Setter
class errorMsg {
	private String status;
	private String error;

	static errorMsg create(String status, String error) {
		errorMsg e = new errorMsg();
		e.setError(error);
		e.setStatus(status);
		return e;
	}
}