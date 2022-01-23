package com.bullsandcows.controller;

import com.bullsandcows.dao.GameDao;
import com.bullsandcows.dao.GuessesDao;
import com.bullsandcows.entity.Game;
import com.bullsandcows.entity.Guesses;
import com.bullsandcows.service_layer.ServiceLayer;
import com.bullsandcows.view.GameView;
import com.bullsandcows.view.UserIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Component
public class BullsAndCows {

    @Autowired
    GameDao game;

    @Autowired
    GuessesDao guess;

    @Autowired
    UserIO io;

    @Autowired
    ServiceLayer service;

    @Autowired
    GameView view;


    int[] randArray;
    int [] playerInput;

    public int gamePlay(){
        Game gm = new Game();

        int playing = 1;

        randArray = generateGameArray(); // generates random int array
        gm.setAnswer(service.answerConvert(randArray)); // converts random array for SQL input type

        game.addGame(gm); // adds new game and answer to game table in DB

        for(int i = 1; i < 11; i++){  // allows player 10 guesses before terminating the game

            playerInput = playerGuesses();
            playing = guessResult(randArray,playerInput, i, gm.getId());

            if(playing == 0){ // game ends early if code correctly guessed
                endGame(gm, i, "Won");
                return playing;
            }

            if(i == 10){
                System.out.println("Game Over, you ran out of guesses!");
                endGame(gm, i, "Lost");
                return playing;
            }

        }
        return playing;
    }

    private int[] generateGameArray(){ // randomly generates array to be used for the game
        int[] randArray = new int[4];

        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0; i<9; i++) {
            list.add(i); // create array list of numbers 0-9
        }

        Collections.shuffle(list); // randomise order of arraylist

        for(int j = 0; j< randArray.length; j++){
            randArray[j] = list.get(j); // append first 4 numbers to get 4 unique random numbers
        }

        for (int k : randArray) { // print out randomly generated array for testing purposes
            System.out.print(k);
        }

        return randArray;
    }

    private int[] playerGuesses(){ // enables player input of a guess
        int[] playerGuesses = new int[4];

        Scanner sc = new Scanner(System.in);
        System.out.print("\nPlease enter 4 unique numbers between 0-9, SEPERATED BY COMMAS: ");
        String guess = sc.next();
        String[] result = guess.split(",");

        for(int i =0; i<playerGuesses.length; i++){
            playerGuesses[i] = Integer.parseInt(result[i]); // convert string input to int array
        }
        return playerGuesses;
    }


    private int guessResult(int[] randArray, int[] playerGuesses, int round, int gameId) {

        Guesses gs = new Guesses(); // new Guess object instantiated with every round

        int won = 1;
        int exact = 0, partial = 0;

        // work out exact matches

        for (int i = 0; i < randArray.length; i++) {
            if (randArray[i] == playerGuesses[i]) {
                exact++;
            }
        }

        // work out partial matches

        for (int index : randArray) {
            for (int z = 0; z < randArray.length; z++)
                if (index == playerGuesses[z]) {
                    partial++;
                }
        }
        partial = partial - exact;

        System.out.println("Number of exacts = " + exact + " and the number of partials is = " + partial);

        // Assign to guess object

        gs.setGuessNo(round);
        gs.setExacts(exact);
        gs.setPartials(partial);
        gs.setGame(gameId);

        guess.addGuess(gs); // output to guesses table in SQL

        // Ends game if correct combination entered

        if(exact == 4){
            System.out.println("\nCongratulations, you guessed the code! \n");
            won = 0;
        }
        return won; // value of won determines whether game keeps playing if player still guess left
    }

    private void endGame(Game gm, int round, String result){
        gm.setRounds(round);
        gm.setResult(result);
        game.updateGame(gm); // update game table in SQL


        // Print game and guess stats

        System.out.println("Game Results: ");
        view.displaySpecificGame(gm);
        System.out.println("Round Results: ");
        List<Guesses> guesses = guess.getGuessesByGame(gm.getId());
        view.displayGuessesByGame(guesses);
    }
}
