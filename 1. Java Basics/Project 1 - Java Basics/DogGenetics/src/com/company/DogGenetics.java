/*
 * @michaelalinks
 */

package com.company;

import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.*;
import java.util.stream.*;

public class DogGenetics {

    public static void main(String[] args) {
        int min = 0, max = 100;
        int[] randomNums = new int[5];
        double[] percent = new double[5];
        String[] dogBreeds = {"St. Bernard", "Chihuahua", "Dramatic RedNosed Asian Pug", "Common Cur", "King Doberman"};

        Scanner sc = new Scanner(System.in);
        System.out.print("What is your dog's name?: ");
        String dogName = sc.nextLine();
        System.out.print("Well then, I have this highly reliable report on " + dogName + "'s prestigious background right here.  \n");
        System.out.println("\n" + dogName + " is:\n");
        Random rd = new Random();

        for (int i = 0; i < randomNums.length; i++) {
            randomNums[i] = rd.nextInt(max - min + 1) + min; // generate and store random integers in an array
        }
        int sum = IntStream.of(randomNums).sum(); // obtain sum of random number array

        for (int i = 0; i < percent.length; i++) {
            percent[i] = (randomNums[i]*max)/sum; // obtain random percentages adding to 100
        }

        double percent_sum = DoubleStream.of(percent).sum();

        if (percent_sum != 100) {
            double difference = max - percent_sum;
            percent[0] = percent[0] + difference; // remove rounding error
        }
        for (int i = 0; i < percent.length; i++) {
            System.out.println(percent[i] + " % " + dogBreeds[i]);
        }

        System.out.println("\nWow, that's QUITE the dog!");
    }
}
