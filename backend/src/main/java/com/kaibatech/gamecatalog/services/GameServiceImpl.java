package com.kaibatech.gamecatalog.services;

import com.kaibatech.gamecatalog.api.v1.resource.GameResource;
import com.kaibatech.gamecatalog.domain.Game;
import com.kaibatech.gamecatalog.exceptions.CreateGameException;
import com.kaibatech.gamecatalog.exceptions.NotFoundException;
import com.kaibatech.gamecatalog.mappers.GameMapper;
import com.kaibatech.gamecatalog.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameMapper gameMapper;
    private final GameRepository gameRepository;

    @Override
    public GameResource getGame(Long id) {
        Game game = gameRepository.findById(id).orElseThrow(NotFoundException::new);
        return gameMapper.map(game);
    }

    @Override
    public List<GameResource> listAllGames() {
        return gameMapper.map(gameRepository.findAll());
    }

    @Override
    public GameResource saveOrUpdateGame(GameResource game) {
        try {
            Game savedGame = gameRepository.save(gameMapper.map(game));
            return gameMapper.map(savedGame);
        } catch (Exception e) {
            String message = "There was an error when trying to save the game";
            log.error(message, e);
            throw new CreateGameException(message);
        }
    }

}
