package com.bullsandcows.dao;

import com.bullsandcows.entity.Game;

import java.util.List;

public interface GameDao {
    List<Game> getAllGames();
    Game getGameById(int id);
    void addGame(Game game);
    void updateGame(Game game);
    void deleteGameById(int id);
}
