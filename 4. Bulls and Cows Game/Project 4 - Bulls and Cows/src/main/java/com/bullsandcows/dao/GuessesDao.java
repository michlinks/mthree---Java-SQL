package com.bullsandcows.dao;

import com.bullsandcows.entity.Game;
import com.bullsandcows.entity.Guesses;

import java.util.List;

public interface GuessesDao {
    List<Guesses> getAllGuesses();
    List<Guesses> getGuessesByGame(int id);
    void addGuess(Guesses guess);
    void updateGuess(Guesses guess);
    void deleteGuessById(int id);

    List<Guesses> getGuessesForGame(Game game);
}
