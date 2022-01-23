/*
* Pulls DAOs, DTOs and View functions together to later be used in the Controller component to enable
* looser coupling
* */


package com.bullsandcows.service_layer;

import com.bullsandcows.dao.GameDao;
import com.bullsandcows.dao.GuessesDao;
import com.bullsandcows.entity.Game;
import com.bullsandcows.entity.Guesses;
import com.bullsandcows.view.GameView;
import com.bullsandcows.view.UserIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceLayer {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    GameDao game;

    @Autowired
    GuessesDao guess;

    @Autowired
    UserIO io;

    @Autowired
    GameView view;


    public void listGames() {
        System.out.println("\nAll games: \n");
        List<Game> games = game.getAllGames();;
        view.displayGames(games);
    }

    public void listGuesses() {
        System.out.println("\nAll guesses: \n");
        List<Guesses> guesses = guess.getAllGuesses();;
        view.displayGuesses(guesses);
    }

    private int lastIndex(){
        final String GET_LAST_ID = "SELECT MAX(id) FROM game";
        int index = jdbc.queryForObject(GET_LAST_ID, Integer.class);
        return index;
    }

    public void listGuessesByGame() {
        int max = lastIndex();
        int id = io.readInt("\nPlease enter the game number of the guesses you would like to review", 1,max);
        System.out.println("All guesses from Game " + id + ":");
        List<Guesses> guesses = guess.getGuessesByGame(id);
        view.displayGuessesByGame(guesses);
    }

    public void specificGameStats(){
        int max = lastIndex();
        int gameId = io.readInt("\nPlease enter the game number you would like to review", 1,max);
        Game gameStats = game.getGameById(gameId);
        view.displaySpecificGame(gameStats);
    }

    public void deleteGame(){
        listGames();

        int max = lastIndex();
        int id = io.readInt("\nEnter the game number you would like to remove:", 1,max); // need to limit to games in DB

        game.deleteGameById(id);

        System.out.println("Updated Game Table: \n");
        listGames();
    }

    public int answerConvert(int[] nums){
        StringBuilder strNum = new StringBuilder();

        for (int num : nums)
        {
            strNum.append(num);
        }
        int finalInt = Integer.parseInt(strNum.toString());
        return finalInt;
    }
}
