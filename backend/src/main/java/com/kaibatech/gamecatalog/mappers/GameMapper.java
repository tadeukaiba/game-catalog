package com.kaibatech.gamecatalog.mappers;

import com.kaibatech.gamecatalog.api.v1.resource.GameResource;
import com.kaibatech.gamecatalog.domain.Game;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MappingConfig.class)
public interface GameMapper {

    GameResource map(Game game);
    Game map(GameResource game);
    List<GameResource> map (List<Game> games);

}
