package com.kaibatech.gamecatalog.api.v1;

import com.kaibatech.gamecatalog.api.v1.resource.GameResource;
import com.kaibatech.gamecatalog.services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping(("/v1/games"))
public class GameController {

    private final GameService gameService;

    @GetMapping
    public ResponseEntity<List<GameResource>> listGames() {
        return ResponseEntity.ok().body(gameService.listAllGames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResource> getGame(@PathVariable Long id) {
        return ResponseEntity.ok().body(gameService.getGame(id));
    }

    @PostMapping
    public ResponseEntity<GameResource> saveGame(@Valid @RequestBody GameResource game) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.saveOrUpdateGame(game));
    }

}
