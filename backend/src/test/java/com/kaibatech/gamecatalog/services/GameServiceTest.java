package com.kaibatech.gamecatalog.services;

import com.kaibatech.gamecatalog.api.v1.resource.GameResource;
import com.kaibatech.gamecatalog.domain.Game;
import com.kaibatech.gamecatalog.mappers.GameMapper;
import com.kaibatech.gamecatalog.repositories.GameRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    private GameService gameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        GameMapper mapper = Mappers.getMapper(GameMapper.class);
        this.gameService = new GameServiceImpl(mapper, gameRepository);
    }

    @Test
    void getGame() {
        Game game = Game.builder().id(1L).build();
        when(gameRepository.findById(any(Long.class))).thenReturn(Optional.of(game));

        GameResource gameResource = this.gameService.getGame(1L);

        Assertions.assertThat(gameResource.getId()).isEqualTo(game.getId());
    }

    @Test
    void listAllGames() {
        gameService.listAllGames();
        Mockito.verify(gameRepository).findAll();
    }

    @Test
    void saveOrUpdateGame() {
        //given
        GameResource resource = GameResource.builder()
                .title("title").build();
        when(gameRepository.save(any(Game.class))).then(returnsFirstArg());

        //when
        GameResource savedGame = gameService.saveOrUpdateGame(resource);

        //then
        assertAll(
                () -> assertNotNull(savedGame),
                () -> assertEquals(savedGame.getTitle(), resource.getTitle())
        );
    }

}