package com.leandro.gamelist.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.leandro.gamelist.dto.GameDTO;
import com.leandro.gamelist.dto.GameMinDTO;
import com.leandro.gamelist.entities.Game;
import com.leandro.gamelist.repositories.GameRepository;
import jakarta.transaction.Transactional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional
    public GameDTO create(GameDTO game){
        Game obj = new Game();
        BeanUtils.copyProperties(game, obj);
        gameRepository.save(obj);
        return game;
    }

    public List<GameMinDTO> findAll(){
        List<Game> result = gameRepository.findAll();

        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }

    public GameDTO findById (Long id){
        Game resultGame = gameRepository.findById(id).get();
        GameDTO dto = new GameDTO(resultGame);
        return dto;

    }
}
