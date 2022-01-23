/*
* Bank of functions that enable SQL queries to Guesses table
* */

package com.bullsandcows.dao;

import com.bullsandcows.entity.Game;
import com.bullsandcows.entity.Guesses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GuessesDaoDB implements GuessesDao{

    @Autowired
    JdbcTemplate jdbc;


    @Override
    public List<Guesses> getAllGuesses() {
        final String SELECT_ALL_GUESSES = "SELECT * FROM guesses";

        return jdbc.query(SELECT_ALL_GUESSES, new GuessMapper());
    }

    @Override
    public List<Guesses> getGuessesByGame(int id) {
        try {
            final String SELECT_GUESS_BY_GAME_ID = "SELECT * FROM guesses WHERE gameId = ?";
            List<Guesses> guesses = jdbc.query(SELECT_GUESS_BY_GAME_ID, new GuessMapper(), id);
            return guesses;
        }  catch(DataAccessException ex) {
        return null;
    }
    }


    @Override
    public void addGuess(Guesses guess) {
        final String GET_LAST_ID = "SELECT MAX(id) FROM guesses";
        int index = jdbc.queryForObject(GET_LAST_ID, Integer.class);

        guess.setId(index + 1);

        final String INSERT_GUESS = "INSERT INTO guesses(id,guessNo, exacts, partials, gameId) VALUES(?, ?, ?, ?, ?)";
        jdbc.update(INSERT_GUESS, guess.getId(),guess.getGuessNo(), guess.getExacts(),guess.getPartials(), guess.getGame());
    }

    @Override
    public void updateGuess(Guesses guess) {

    }

    @Override
    public void deleteGuessById(int id) {
        final String DELETE_GUESS = "DELETE FROM guesses WHERE id = ?";
        jdbc.update(DELETE_GUESS, id);
    }

    @Override
    public List<Guesses> getGuessesForGame(Game game) {
        return null;
    }

    public static final class GuessMapper implements RowMapper<Guesses> {

        @Override
        public Guesses mapRow(ResultSet rs, int index) throws SQLException {
            Guesses guess = new Guesses();
            guess.setId(rs.getInt("id"));
            guess.setGuessNo(rs.getInt("guessNo"));
            guess.setExacts(rs.getInt("exacts"));
            guess.setPartials(rs.getInt("partials"));
            guess.setGame(rs.getInt("gameId"));
            return guess;
        }
    }
}
