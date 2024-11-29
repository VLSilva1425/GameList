package com.leandro.gamelist.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.leandro.gamelist.dto.GameListDTO;
import com.leandro.gamelist.entities.GameList;
import com.leandro.gamelist.repositories.GameListRepository;


@Service
public class GameListService {

    @Autowired
    GameListRepository gameListRepository;

    @Transactional
    public void create(GameListDTO gameList){
        GameList obj = new GameList();
        BeanUtils.copyProperties(gameList, obj);
        gameListRepository.save(obj);
    }

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll(){
        List<GameList> result = gameListRepository.findAll();

        return result.stream().map(x -> new GameListDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public GameListDTO findById (Long id){
        GameList result = gameListRepository.findById(id).get();
        GameListDTO dto = new GameListDTO(result);
        return dto;

    }
}
