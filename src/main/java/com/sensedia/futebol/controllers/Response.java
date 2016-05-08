package com.sensedia.futebol.controllers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by renanpetronilho on 07/05/16.
 */
public class Response {

	public static Object build(Object key, Object value) {
		Map<Object, Object> response = new HashMap<Object, Object>();
		response.put(key, value);
		return response;
	}

}
