package com.sensedia.futebol.controllers;

import java.util.List;
import java.util.Objects;

import com.sensedia.futebol.exception.PlayerNotFoundException;
import com.sensedia.futebol.model.Team;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sensedia.futebol.model.Player;
import com.sensedia.futebol.repository.PlayerRepository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Created by renanpetronilho on 08/05/16.
 */
@Controller
@RequestMapping("/players")
@Api(value = "/players", description = "Recurso responsavel por gerenciar operações relacionadas ao jogador")
public class PlayerController {

	@Autowired
	private PlayerRepository repository;

	@ApiOperation(value = "Insere um novo jogador.", code = 201, response = Player.class)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object postPlayer(@RequestBody Player player) {
		repository.save(player);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(player.getId()).toUri());
		return new ResponseEntity<>(player, httpHeaders, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Atualiza o jogador de acordo com id.", response = Player.class)
	@io.swagger.annotations.ApiResponses(value = {
			@io.swagger.annotations.ApiResponse(code = 200, message = "SUCCESS"),
			@io.swagger.annotations.ApiResponse(code = 404, message = "Could not find player id") })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object putPlayer(@PathVariable("id") Long id, @RequestBody Player player) {
		if (Objects.nonNull(repository.findOne(id))) {
			player.setId(id);
			repository.save(player);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(player.getId()).toUri());
			return new ResponseEntity<>(player, httpHeaders, HttpStatus.OK);
		} else {
			throw new PlayerNotFoundException(id);
		}
	}

	@ApiOperation(value = "Retorna uma lista de jogadores.", response = Player.class, responseContainer = "List")
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Player> getPlayer() {
		return (List<Player>) repository.findAll();
	}

	@ApiOperation(value = "Retorna o jogador de acordo com ID.", response = Player.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getPlayer(@PathVariable("id") Long id) {
		Player player = repository.findOne(id);
		if (Objects.nonNull(player))
			return repository.findOne(id);
		else
			throw new PlayerNotFoundException(id);
	}

	@ApiOperation(value = "Deleta o jogador de acordo com id.")
	@io.swagger.annotations.ApiResponses(value = {
			@io.swagger.annotations.ApiResponse(code = 200, message = "SUCCESS"),
			@io.swagger.annotations.ApiResponse(code = 404, message = "Could not find player id") })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deletePlayer(@PathVariable("id") Long id) {
		if (Objects.nonNull(repository.findOne(id))) {
			repository.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body(Response.build("RESULT", "SUCCESS"));
		} else {
			throw new PlayerNotFoundException(id);
		}
	}
}
