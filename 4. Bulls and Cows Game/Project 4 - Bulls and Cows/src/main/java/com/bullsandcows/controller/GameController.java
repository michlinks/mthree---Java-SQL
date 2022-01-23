/*
* Controller pulls functions from View, Service Layer and Bulls and Cows Class to
* create user menu and ensure loose coupling. This class only orchestrates the sequence of
* functions to run
*
* */

package com.bullsandcows.controller;

import com.bullsandcows.controller.BullsAndCows;
import com.bullsandcows.service_layer.ServiceLayer;
import com.bullsandcows.view.GameView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameController {

    @Autowired
    GameView view;

    @Autowired
    ServiceLayer service;

    @Autowired
    BullsAndCows play;

    private int userSelection;

    public void run() {

        while (true) {
            userSelection = view.displayMenu();
            optionSelection(userSelection);
        }
    }

    private void optionSelection(int selection){
        if(selection == 1){
            play.gamePlay();
        }
        else if (selection == 2){
            service.listGames();
        }
        else if (selection == 3){
            service.listGuesses();
        }
        else if (selection == 4){
            service.listGuessesByGame();
        }
        else if (selection == 5){
            service.specificGameStats();
        }
        else if (selection == 6){
            service.deleteGame();
        }
    }

}
