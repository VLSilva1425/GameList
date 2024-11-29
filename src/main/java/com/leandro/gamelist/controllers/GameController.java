package com.leandro.gamelist.controllers;
import com.leandro.gamelist.dto.GameDTO;
import com.leandro.gamelist.dto.GameMinDTO;
import com.leandro.gamelist.services.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
     GameService gameService;

     @PostMapping
     public ResponseEntity<GameDTO> create(@RequestBody GameDTO game){
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.create(game));
     }

     @GetMapping
     public List<GameMinDTO> findAll(){
      List<GameMinDTO> result = gameService.findAll();
      
      return result;

     }

     @GetMapping("/{id}")
     public ResponseEntity<GameDTO> findById(@PathVariable("id") Long id){
      GameDTO dto = gameService.findById(id);
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
     }

}
