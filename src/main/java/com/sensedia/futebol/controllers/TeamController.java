package com.sensedia.futebol.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sensedia.futebol.model.Team;
import com.sensedia.futebol.repository.TeamRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by renanpetronilho on 07/05/16.
 */
@Controller
@RequestMapping("/teams")
@Api(value = "/teams", description = "Recurso responsavel por gerenciar operações relacionadas ao time")
public class TeamController {

	@Autowired
	private TeamRepository repository;

	@ApiOperation(value = "Retorna uma lista de times.", response = Team.class, responseContainer = "List")
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Team> getTeam() {
		return (List<Team>) repository.findAll();
	}

	@ApiOperation(value = "Retorna o time de acordo com ID.", response = Team.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getTeam(@PathVariable("id") Long id) {
		Team team = repository.findOne(id);
		if(Objects.nonNull(team))
			return team;
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.build("RESULT", "TEAM NOT FOUND ID "+id));
	}

	@ApiOperation(value = "Insere um novo time.", code = 201, response = Team.class)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object postTeam(@RequestBody Team team) {
		repository.save(team);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(team.getId()).toUri());
		return new ResponseEntity<>(team, httpHeaders, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Atualiza o time de acordo com id.")
	@io.swagger.annotations.ApiResponses(value = {
			@io.swagger.annotations.ApiResponse(code = 200, message = "SUCCESS"),
			@io.swagger.annotations.ApiResponse(code = 404, message = "TEAM NOT FOUND!") })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object putTeam(@PathVariable("id") Long id, @RequestBody Team team) {
		if (Objects.nonNull(repository.findOne(id))) {
			team.setId(id);
			repository.save(team);
			return ResponseEntity.status(HttpStatus.OK).body(Response.build("RESULT", "SUCCESS"));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.build("RESULT", "TEAM NOT FOUND!"));
		}
	}

	@ApiOperation(value = "Deleta o time de acordo com id.")
	@io.swagger.annotations.ApiResponses(value = {
			@io.swagger.annotations.ApiResponse(code = 200, message = "SUCCESS"),
			@io.swagger.annotations.ApiResponse(code = 404, message = "TEAM NOT FOUND!") })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteTeam(@PathVariable("id") Long id) {
		if (Objects.nonNull(repository.findOne(id))) {
			repository.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body(Response.build("RESULT", "SUCCESS"));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.build("RESULT", "TEAM NOT FOUND!"));
		}
	}

}
