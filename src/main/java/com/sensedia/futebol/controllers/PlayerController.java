package com.sensedia.futebol.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensedia.futebol.model.Player;
import com.sensedia.futebol.repository.PlayerRepository;

/**
 * Created by renanpetronilho on 08/05/16.
 */
@Controller
@RequestMapping("/players")
public class PlayerController {

	@Autowired
	private PlayerRepository repository;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object postPlayer(@RequestBody Player player) {
		repository.save(player);
		return ResponseEntity.status(HttpStatus.CREATED).body(Response.build("id", player.getId()));
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Player> getPlayer() {
		return (List<Player>) repository.findAll();
	}
}
