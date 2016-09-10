package com.sensedia.futebol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sensedia.futebol.model.Team;

/**
 * Created by renanpetronilho on 07/05/16.
 */
public interface TeamRepository extends JpaRepository<Team, Long> {

	@Query("select t from Team t where t.name like %?1%")
	public List<Team> findLikeName(String name);
}
