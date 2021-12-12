/*
* @michaelalinks
*/

package com.company;

import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {
    static void noTypeError(Scanner sc){
        while(!sc.hasNextInt()){ // prevents not integar inputs crashing programme
            System.out.print("Please enter a valid input: ");
            sc.next();
        }
    }

    static void RockPaperScissors() {
        int play = 1;

        while (play == 1){
            Scanner sc =new Scanner(System.in);
            System.out.print("Enter the number of rounds you'd like to play: ");

            noTypeError(sc);

            int rounds = sc.nextInt();

//          Gameplay

            if ( rounds >= 1 & rounds <= 10){ // input outside of range will terminate programme
                int min = 1, max = 3, tie = 0, playerWins = 0, computerWins = 0;

                for (int i = 0; i < rounds; i++){
                    System.out.print("\nEnter 1 for Rock, 2 for Paper or 3 for Scissors: ");

                    noTypeError(sc);

                    int player = sc.nextInt();
                    Random rd = new Random();
                    int computer = rd.nextInt((max - min)+1) + min;

                    if (player >= 1 & player <= 3){
                        if (player == computer) {
                            System.out.println("Computer: " + computer);
                            System.out.println("\nTie");
                            tie++;
                        }
                        else if (player == 1 & computer == 3){
                            System.out.println("Computer: " + computer);
                            System.out.println("\nPlayer wins");
                            playerWins++;
                        }
                        else if (player == 2 & computer == 1){
                            System.out.println("Computer: " + computer);
                            System.out.println("\nPlayer wins");
                            playerWins++;
                        }
                        else if (player == 3 & computer == 2){
                            System.out.println("Computer: " + computer);
                            System.out.println("Player wins");
                            playerWins++;
                        }
                        else{
                            System.out.println("Computer: " + computer);
                            System.out.println("Computer wins");
                            computerWins++;
                        }
                    }
                    else{
                        System.out.println("Input invalid, please type a number between 1 and 3");
                        i--;
                    }

                }

//              Result display

                System.out.println("\nTie: " + tie + ", Player: " + playerWins + ", Computer: " + computerWins);

                if (tie > playerWins & tie > computerWins | playerWins == computerWins){
                    System.out.println("It's a tie!");
                }
                else if (playerWins > tie & playerWins > computerWins | tie == playerWins){
                    System.out.println("You won!");
                }
                else if (computerWins > playerWins & computerWins > tie | tie == computerWins){
                    System.out.println("The computer won!");
                }

//              Replay or terminate game

                System.out.print("\nWould you like to play again? Press 1 for Yes or any number to terminate: ");

                noTypeError(sc);

                play = sc.nextInt();
            }
            else {
                System.out.println("Sorry, your input was invalid. Programme terminating");
                break;
            }
        } // end of while loop
    }

    public static void main(String[] args) {
        RockPaperScissors();
    }
} // end of class Main
