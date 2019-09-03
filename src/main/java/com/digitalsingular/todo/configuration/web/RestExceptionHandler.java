package com.digitalsingular.todo.configuration.web;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ControllerAdvice
public class RestExceptionHandler {

	@ApiResponse(responseCode = "400", description = "Petición errónea")
	@ExceptionHandler(value = { EntityNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public String processNotFound(Exception ex) {
		return ex.getMessage();
	}

	@ApiResponse(responseCode = "403", description = "Acceso prohibido")
	@ExceptionHandler(value = { ConstraintViolationException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String processBadRequest(Exception ex) {
		return ex.getMessage();
	}

	@ApiResponse(responseCode = "404", description = "No encontrado")
	@ExceptionHandler(value = { AccessDeniedException.class })
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public String processForbidden(Exception ex) {
		return ex.getMessage();
	}
}
