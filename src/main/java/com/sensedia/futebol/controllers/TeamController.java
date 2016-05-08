package com.sensedia.futebol.controllers;

import com.sensedia.futebol.model.Team;
import com.sensedia.futebol.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Created by renanpetronilho on 07/05/16.
 */
@Controller
@RequestMapping("/teams")
public class TeamController {

	@Autowired
	private TeamRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Team> getTeam() {
		return (List<Team>) repository.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getTeam(@PathVariable("id") Long id) {
		Team team = repository.findOne(id);
		if(Objects.nonNull(team))
			return team;
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.build("RESULT", "TEAM NOT FOUND ID "+id));
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object postTeam(@RequestBody Team team) {
		repository.save(team);
		return ResponseEntity.status(HttpStatus.CREATED).body(Response.build("id", team.getId()));
	}

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
