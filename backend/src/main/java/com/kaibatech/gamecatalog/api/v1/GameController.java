package com.kaibatech.gamecatalog.api.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(("/v1/games"))
public class GameController {

    @GetMapping
    public ResponseEntity<String> listGames() {
        return ResponseEntity.ok().body("Hi");
    }

}
