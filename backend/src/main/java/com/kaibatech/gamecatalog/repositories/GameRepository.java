package com.kaibatech.gamecatalog.repositories;

import com.kaibatech.gamecatalog.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
