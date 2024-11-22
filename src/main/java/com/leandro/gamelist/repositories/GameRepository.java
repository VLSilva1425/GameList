package com.leandro.gamelist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leandro.gamelist.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>{

}
