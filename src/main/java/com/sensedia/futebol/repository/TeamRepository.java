package com.sensedia.futebol.repository;

import com.sensedia.futebol.model.Team;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by renanpetronilho on 07/05/16.
 */
public interface TeamRepository extends CrudRepository<Team, Long> {
}
