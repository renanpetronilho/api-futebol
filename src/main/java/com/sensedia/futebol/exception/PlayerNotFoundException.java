package com.sensedia.futebol.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by renanpetronilho on 09/05/16.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlayerNotFoundException extends RuntimeException {

	public PlayerNotFoundException(Long id) {
		super("Could not find player " + id + "!");
	}
}
