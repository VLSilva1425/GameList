package com.leandro.gamelist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandro.gamelist.dto.GameListDTO;
import com.leandro.gamelist.dto.GameMinDTO;
import com.leandro.gamelist.services.GameListService;
import com.leandro.gamelist.services.GameService;;


@RestController
@RequestMapping("/lists")
public class GameListController {

    @Autowired
    GameListService gameListService;

    @Autowired
    GameService gameService;


    @GetMapping
     public List<GameListDTO> findAll(){
      List<GameListDTO> result = gameListService.findAll();
      
      return result;

     }

    @GetMapping("/{id}")
    public ResponseEntity<GameListDTO> findById (@PathVariable Long id){
        GameListDTO dto = gameListService.findById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    }

    @GetMapping("/{listId}/game")
    public ResponseEntity<List<GameMinDTO>> findByList (@PathVariable Long listId){
        List<GameMinDTO> dto = gameService.findByList(listId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    }

}
