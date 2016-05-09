package com.sensedia.futebol.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by renanpetronilho on 07/05/16.
 */
@Entity
@Table(name = "T_TEAM")
@ApiModel(description = "Modelo para Times.")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "TXT_NAME", length = 255, unique = true)
	private String name;

	@NotNull
	@Column(name = "TXT_INITIALS", length = 3)
	private String initials;

	public Team(String name, String initials) {
		this.name = name;
		this.initials = initials;
	}

	public Team() {

	}

	@ApiModelProperty(value = "Id do Time.")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ApiModelProperty(value = "Nome do Time.")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ApiModelProperty(value = "Sigla do Time com 3 letras.")
	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	@Override
	public String toString() {
		return "Team{" + "id=" + id + ", name='" + name + '\'' + ", initials='" + initials + '\'' + '}';
	}
}
