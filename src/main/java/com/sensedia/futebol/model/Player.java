package com.sensedia.futebol.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by renanpetronilho on 07/05/16.
 */
@Entity
@Table(name = "T_PLAYER")
@ApiModel(description = "Modelo para jogadores.")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "TXT_NAME", length = 500, unique = true)
	private String name;

	@NotNull
	@Column(name = "TXT_NICKNAME")
	private String nickname;


	@Temporal(value = TemporalType.DATE)
	@Column(name = "DT_BIRTHDATE")
	private Date birthdate;

	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private Team team;

	public Player(String name, String nickname, Date birthdate, Team team) {
		this.name = name;
		this.nickname = nickname;
		this.birthdate = birthdate;
		this.team = team;
	}

	public Player() {
	}

	@ApiModelProperty(value = "Id do jogador")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ApiModelProperty(value = "Nome do jogador")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ApiModelProperty(value = "Apelido do jogador")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@ApiModelProperty(value = "Data de nascimento do jogador")
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@ApiModelProperty(value = "Time do jogador.")
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "Player{" + "id=" + id + ", name='" + name + '\'' + ", nickname='" + nickname + '\'' + ", birthdate="
				+ birthdate + ", team=" + team + '}';
	}
}
