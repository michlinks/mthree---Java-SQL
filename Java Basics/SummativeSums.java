/*
 * @michaelalinks
 */

package com.company;

import java.util.Scanner;
import java.util.*;

public class SummativeSums {
    public static int SummativeSums(int index){
        ArrayList<Integer> arr = new ArrayList<Integer>();
        int sum = 0;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the elements of array " + index + ": ");

        while(sc.hasNextInt()){ // appends to ArrayList until non-integer value entered
            int arrInput = sc.nextInt();
            System.out.println("Enter the elements of your array or enter a non-integer to finish: ");
            arr.add(arrInput);
        }

        for(int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
        }
        System.out.println("\n\n");
        return sum;
    } // end of function

    public static void main(String[] args) {
        int arr1 = SummativeSums(1), arr2 = SummativeSums(2), arr3 = SummativeSums(3);

        System.out.println("#1 Array Sum: " + arr1);
        System.out.println("#1 Array Sum: " + arr2);
        System.out.println("#1 Array Sum: " + arr3);
    } // end of main
}

