package com.sensedia.futebol.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object putPlayer(@PathVariable("id") Long id, @RequestBody Player player) {
		if (Objects.nonNull(repository.findOne(id))) {
			player.setId(id);
			repository.save(player);
			return ResponseEntity.status(HttpStatus.OK).body(Response.build("RESULT", "SUCCESS"));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.build("RESULT", "PLAYER NOT FOUND!"));
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Player> getPlayer() {
		return (List<Player>) repository.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getPlayer(@PathVariable("id") Long id) {
		Player player = repository.findOne(id);
		if(Objects.nonNull(player))
			return repository.findOne(id);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.build("RESULT", "PLAYER NOT FOUND ID "+id));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deletePlayer(@PathVariable("id") Long id) {
		if (Objects.nonNull(repository.findOne(id))) {
			repository.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body(Response.build("RESULT", "SUCCESS"));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.build("RESULT", "PLAYER NOT FOUND!"));
		}
	}
}
