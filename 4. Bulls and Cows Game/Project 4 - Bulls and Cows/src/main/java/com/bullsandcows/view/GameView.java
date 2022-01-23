/*
* Bank of display functions that can be used in various other classes
* to print information from SQL tables to screen
*/


package com.bullsandcows.view;

import com.bullsandcows.entity.Game;
import com.bullsandcows.entity.Guesses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GameView {

    @Autowired
    UserIO io;

    public int displayMenu(){
        System.out.println("Welcome to Bulls and Cows. Please choose an option from the menu below\n");

        String[] menu = {"1. Play Game",
                         "2. Display All Game Stats",
                         "3. Display Guess Table",
                         "4. Display Guesses By Game",
                         "5. Display Specific Game Stats",
                         "6. Delete Game"};

        for (String item : menu)
        {
            System.out.println(item);
        }

        int selection = io.readInt("\nSelect Option: ", 1, 6);

        return selection;
    }

    public void displayGames(List<Game> games) {

        System.out.println("Game    Guesses Taken    Winning Code   Game Result");

        for (Game game : games) {
            StringBuilder sb = new StringBuilder();
            sb.append(game.getId());
            sb.append("              ");
            sb.append(game.getRounds());
            sb.append("             ");
            sb.append(game.getAnswer());
            sb.append("             ");
            sb.append(game.getResult());
            io.print(sb.toString());
        }
        io.print("");
    }

    public void displayGuesses(List<Guesses> guesses) {

        System.out.println("Guess Id   Guess No   Exacts   Partials   Game Id");

        for (Guesses guess : guesses) {
            StringBuilder sc = new StringBuilder();
            sc.append(guess.getId());
            sc.append("              ");
            sc.append(guess.getGuessNo());
            sc.append("        ");
            sc.append(guess.getExacts());
            sc.append("         ");
            sc.append(guess.getPartials());
            sc.append("        ");
            sc.append(guess.getGame());
            io.print(sc.toString());
        }
        io.print("");
    }

    public void displayGuessesByGame(List<Guesses> guesses) {

        System.out.println("Guess No   Exacts   Partials");

        for (Guesses guess : guesses) {
            StringBuilder sn = new StringBuilder();
            sn.append(guess.getGuessNo());
            sn.append("             ");
            sn.append(guess.getExacts());
            sn.append("        ");
            sn.append(guess.getPartials());
            io.print(sn.toString());
        }
        io.print("");
    }

    public void displaySpecificGame(Game game){
        System.out.println("Game    Guesses Taken    Winning Code   Game Result");


            StringBuilder sm = new StringBuilder();
            sm.append(game.getId());
            sm.append("             ");
            sm.append(game.getRounds());
            sm.append("             ");
            sm.append(game.getAnswer());
            sm.append("           ");
            sm.append(game.getResult());
            io.print(sm.toString());

        io.print("");
    }

}
