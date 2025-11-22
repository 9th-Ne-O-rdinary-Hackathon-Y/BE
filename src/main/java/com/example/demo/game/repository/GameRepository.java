package com.example.demo.game.repository;

import com.example.demo.game.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    
    List<Game> findByContentNum(Integer contentNum);
    List<Game> findAllByOrderByContentNumAsc();
}