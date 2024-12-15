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

    // CRIA UMA LISTA
    @Transactional
    public GameListDTO create(GameListDTO gameList){
        GameList obj = new GameList();
        BeanUtils.copyProperties(gameList, obj);
        gameListRepository.save(obj);
        return gameList;
    }

    //LISTA TODAS AS LISTAS
    @Transactional(readOnly = true)
    public List<GameListDTO> findAll(){
        List<GameList> result = gameListRepository.findAll();

        // RETORNA O RESULTADO DA PESQUISA DE LISTAS E CONVERTE PARA UM DTO
        return result.stream().map(x -> new GameListDTO(x)).toList();
    }

    // PROCURA UMA LISTA POR ID
    @Transactional(readOnly = true)
    public GameListDTO findById (Long id){
        GameList result = gameListRepository.findById(id).get();
        GameListDTO dto = new GameListDTO(result);
        return dto;

    }


    //MOVE UM GAME DE UMA LISTA DE UMA POSIÇAO PARA A OUTRA
    @Transactional
    public void move(Long listId, int sourceIndex, int targetIndex){

        //CHAMA UMA LISTA COM JOGOS
        List<GameMinProjection> list = gameRepository.searchByList(listId);
        //REMOVE O JOGO ESPECIFICO DA LISTA
        GameMinProjection obj = list.remove(sourceIndex);
        //ADICIONA O JOGO PARA O INDEX DESEJADO
        list.add(targetIndex, obj);
        
        //VERIFICA QUAL O MENOR E O MAIOR INDEX
        int min = sourceIndex < targetIndex ? sourceIndex : targetIndex;
        int max = sourceIndex < targetIndex ? targetIndex : sourceIndex;

        //MOVE OS INDEX APOS A ADIÇAO PARA BAIXO
        /***NOTA* PRESUMO QUE ESSE FOR NÃO SEJA O MAIS ACONSELHAVEL PARA FAZER ISSO, POIS FAZ VARIAS REQUISIÇÕES NO BANCO, CREIO QUE PARA RESOLVER ISSO TENHA QUE MUDAR A QUERY
         PARA SER MAIS PRATICO***/
         for(int i = min; i <= max; i++){
            gameListRepository.updateBelongingPosition(listId, list.get(i).getId() , i);
        }
    }

}
