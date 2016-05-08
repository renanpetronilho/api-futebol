package com.sensedia.futebol.repository;

import com.sensedia.futebol.model.Player;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by renanpetronilho on 08/05/16.
 */
public interface PlayerRepository extends CrudRepository<Player,Long> {
}
