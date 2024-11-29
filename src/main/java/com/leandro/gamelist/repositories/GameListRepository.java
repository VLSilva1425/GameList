package com.leandro.gamelist.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.leandro.gamelist.entities.GameList;

@Repository
public interface GameListRepository extends JpaRepository<GameList, Long>{

    

}
