package com.sensedia.futebol.controllers;

import com.sensedia.futebol.exception.PlayerNotFoundException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by renanpetronilho on 09/05/16.
 */
@ControllerAdvice
public class BookmarkControllerAdvice {

	@ResponseBody
	@ExceptionHandler(PlayerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndErrors playerNotFoundExceptionHandler(PlayerNotFoundException ex) {
		return new VndErrors("error", ex.getMessage());
	}
}
