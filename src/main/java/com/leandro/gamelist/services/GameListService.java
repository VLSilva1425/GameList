package com.leandro.gamelist.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leandro.gamelist.Projections.GameMinProjection;
import com.leandro.gamelist.dto.GameListDTO;
import com.leandro.gamelist.entities.GameList;
import com.leandro.gamelist.repositories.GameListRepository;
import com.leandro.gamelist.repositories.GameRepository;


@Service
public class GameListService {

    @Autowired
    GameListRepository gameListRepository;

    @Autowired
    GameRepository gameRepository;

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


    //ainda tenho que implentar nao esta funcional
    @Transactional
    public void move(Long listId, int sourceIndex, int targetIndex){
        List<GameMinProjection> list = gameRepository.searchByList(listId);

        GameMinProjection obj = list.remove(sourceIndex);
        list.add(targetIndex, obj);
        int min = sourceIndex < targetIndex ? sourceIndex : targetIndex;
        int max = sourceIndex < targetIndex ? targetIndex : sourceIndex;

        for(int i = min; i <= max; i++){
            gameListRepository.updateBelongingPosition(listId, list.get(i).getId() , i);
        }
    }
}
