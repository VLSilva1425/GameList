package com.leandro.gamelist.services;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leandro.gamelist.Projections.GameMinProjection;
import com.leandro.gamelist.dto.GameDTO;
import com.leandro.gamelist.dto.GameMinDTO;
import com.leandro.gamelist.entities.Game;
import com.leandro.gamelist.repositories.GameRepository;


@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    // CRIA UM GAME NO BANCO DE DADOS
    @Transactional
    public GameDTO create(GameDTO game){
        Game obj = new Game();
        BeanUtils.copyProperties(game, obj);
        gameRepository.save(obj);
        return game;
    }

    //LISTA TODOS OS GAMES
    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll(){
        List<Game> result = gameRepository.findAll();

        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }

    // PROCURA O GAME POR ID
    @Transactional(readOnly = true)
    public GameDTO findById (Long id){
        Game resultGame = gameRepository.findById(id).get();
        GameDTO dto = new GameDTO(resultGame);
        return dto;

    }

    //PROCURA TODOS OS JOGOS PELA LISTA QUE ELE ESTÁ
    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId){
        List<GameMinProjection> result = gameRepository.searchByList(listId);
        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }

    
}
