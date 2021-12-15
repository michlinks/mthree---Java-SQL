/*
 * @michaelalinks
 * */

package com.company.dao;

import com.company.dto.Coins;

public interface Change {

    static void poundsChange(float change){
        change = change * Coins.POUND.getValue();
        int pounds = (int) change / Coins.POUND.getValue();
        System.out.println("£s: " + pounds);
        double remainder1 = change % Coins.POUND.getValue();
        int fifties = (int) remainder1 / Coins.FIFTY.getValue();
        System.out.println("50ps: " + fifties);
        double remainder2 = remainder1 % Coins.FIFTY.getValue();
        int twenties = (int) remainder2 / Coins.TWENTY.getValue();
        System.out.println("20ps: " + twenties);
        double remainder3 = remainder2 % Coins.TWENTY.getValue();
        int tens = (int) remainder3 / Coins.TEN.getValue();
        System.out.println("10ps: " + tens);
        double remainder4 = remainder3 % Coins.TEN.getValue();
        int fives = (int) remainder4 / Coins.FIVE.getValue();
        System.out.println("5ps: " + fives);
        double remainder5 = remainder4 % Coins.FIVE.getValue();
        int twos = (int) remainder5 / Coins.TWO.getValue();
        System.out.println("2ps: " + twos);
        double remainder6 = remainder5 % Coins.TWO.getValue();
        int ones = (int) remainder6 / Coins.ONE.getValue();
        System.out.println("1ps: " + ones);

        // check calculated change is correct

        double cash = pounds * 1 + fifties * 0.5 + twenties * 0.2 + tens * 0.1 + fives * 0.05 + twos * 0.02 + ones * 0.01;

        System.out.println("\nChange given: £" + String.format("%.2f", cash));
    }

    static void eurosChange(float change){

        change = change * Coins.POUND.getValue();
        int twoEuro = (int) change / Coins.TWOEURO.getValue();
        System.out.println("2€s: " + twoEuro);
        double remainder0 = change % Coins.TWOEURO.getValue();
        int euro = (int) remainder0 / Coins.POUND.getValue();
        System.out.println("€s: " + euro);
        double remainder1 = remainder0 % Coins.POUND.getValue();
        int fifty = (int) remainder1 / Coins.FIFTY.getValue();
        System.out.println("50cs: " + fifty);
        double remainder2 = remainder1 % Coins.FIFTY.getValue();
        int twenty = (int) remainder2 / Coins.TWENTY.getValue();
        System.out.println("20cs: " + twenty);
        double remainder3 = remainder2 % Coins.TWENTY.getValue();
        int ten = (int) remainder3 / Coins.TEN.getValue();
        System.out.println("10cs: " + ten);
        double remainder4 = remainder3 % Coins.TEN.getValue();
        int five = (int) remainder4 / Coins.FIVE.getValue();
        System.out.println("5cs: " + five);
        double remainder5 = remainder4 % Coins.FIVE.getValue();
        int two = (int) remainder5 / Coins.TWO.getValue();
        System.out.println("2cs: " + two);
        double remainder6 = remainder5 % Coins.TWO.getValue();
        int one = (int) remainder6 / Coins.ONE.getValue();
        System.out.println("1cs: " + one);

        // check calculated change is correct

        double cash = twoEuro * 2 + euro * 1 + fifty * 0.5 + twenty * 0.2 + ten * 0.1 + five * 0.05 + two * 0.02 + one * 0.01;

        System.out.println("\nChange given: €" + String.format("%.2f", cash));
    }
}
