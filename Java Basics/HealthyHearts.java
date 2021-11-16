/*
 * @michaelalinks
 */

package com.company;

import java.util.Scanner;

public class HealthyHearts {
    static void noTypeError(Scanner sc){
        while(!sc.hasNextInt()){ // prevents not integar inputs crashing programme
            System.out.print("Please enter a valid input: ");
            sc.next();
        }
    }

    public static void main(String[] args) {
        int MAX_CONSTANT = 220;
        double MAX_PERCENT = 0.85, MIN_PERCENT = 0.5;
        Scanner sc = new Scanner(System.in);

        System.out.print("Please enter you age: ");
        noTypeError(sc);

        int age = sc.nextInt();

        if (age >= 0) {
            int maxHeartRate = MAX_CONSTANT - age;

            System.out.println("Your maximum heart rate should be " + maxHeartRate + " beats per minute");

            double upperTarget = maxHeartRate * MAX_PERCENT;
            double lowerTarget = maxHeartRate * MIN_PERCENT;

            int rounded_UT = (int) upperTarget;
            int rounded_LT = (int) lowerTarget;

            System.out.println("Your target HR is " + rounded_LT + " - " + rounded_UT + " beats per minute");
        }
        else{ // codes against negative input
            System.out.println("Input invalid. Programme terminating");
        }
    } // end of main method
}

