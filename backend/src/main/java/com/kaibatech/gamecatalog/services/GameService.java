package com.kaibatech.gamecatalog.services;

import com.kaibatech.gamecatalog.api.v1.resource.GameResource;

import java.util.List;

public interface GameService {

    GameResource getGame(Long id);
    List<GameResource> listAllGames();
    GameResource saveOrUpdateGame(GameResource game);

}
