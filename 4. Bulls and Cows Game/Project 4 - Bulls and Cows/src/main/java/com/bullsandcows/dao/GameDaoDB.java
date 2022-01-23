/*
* * Bank of functions that enable SQL queries to Game table
* */

package com.bullsandcows.dao;

import com.bullsandcows.entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GameDaoDB implements GameDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Game> getAllGames() {
        final String SELECT_ALL_GAMES = "SELECT * FROM game";
        return jdbc.query(SELECT_ALL_GAMES, new GameMapper());
    }

    @Override
    public Game getGameById(int id) {
        try {
            final String SELECT_GAME_BY_ID = "SELECT * FROM game WHERE id = ?";
            return jdbc.queryForObject(SELECT_GAME_BY_ID, new GameMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public void addGame(Game game) {
        final String GET_LAST_ID = "SELECT MAX(id) FROM game";
        int index = jdbc.queryForObject(GET_LAST_ID, Integer.class);

        game.setId(index + 1);

        final String INSERT_GAME = "INSERT INTO game(id, answer) VALUES(?, ?)";
        jdbc.update(INSERT_GAME, game.getId(), game.getAnswer());
    }


    @Override
    public void updateGame(Game game) {
        final String UPDATE_GAME = "UPDATE game SET rounds = ?, result = ? WHERE id = ?";
        jdbc.update(UPDATE_GAME, game.getRounds(), game.getResult(), game.getId());
    }

    @Override
    public void deleteGameById(int id) {

        final String DELETE_GUESSES_BY_GAME = "DELETE FROM guesses WHERE gameId = ?";
        jdbc.update(DELETE_GUESSES_BY_GAME, id);

        final String DELETE_GAME = "DELETE FROM game WHERE id = ?";
        jdbc.update(DELETE_GAME, id);
    }

    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game gm = new Game();
            gm.setId(rs.getInt("id"));
            gm.setRounds(rs.getInt("rounds"));
            gm.setAnswer(rs.getInt("answer"));
            gm.setResult(rs.getString("result"));
            return gm;
        }
    }
}
