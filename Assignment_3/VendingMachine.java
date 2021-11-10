/*
 * @michaelalinks
 * */

package com.company;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Scanner;
import java.math.BigDecimal;

public class VendingMachine implements FileIO, Change {
    double EXCHANGE_RATE = 1.17;
    float change;
    String[][] stock;
    String[][] updatedStock;
    int start, currency;


    public VendingMachine() throws IOException {
        start = 1;

        while(start == 1) {
            this.stock = readStock();
            this.currency = currency();
            this.updatedStock = stockCheck();
            this.change = payment(updatedStock);
            this.updatedStock = fileFormat(updatedStock);
            FileIO.writeFile(updatedStock);
            this.start = vendAgain();
        }
    }

    private String[][] readStock() throws IOException {
        String path = "/Users/michaelalinks/Google Drive/Coding/Java/Michaela_Links_Assignment_3/src/com/company/stock.txt";
        return readFile(path); // read in array from file using FileIO interface
    }

    private int currency(){
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter 1 for GBP or 2 for EUR: ");

        int currency = sc.nextInt();
        return currency;
    }

    private String[][] stockCheck() {

        int rowDims = (stock.length) + 1;
        String[][] updatedStock = Arrays.copyOf(stock, rowDims);

        System.out.println("\nVending machine options are: \n");

        if(currency == 1) {
            for (int i = 0; i < updatedStock.length - 2; i++) {
                System.out.println(i+1 + ". " + updatedStock[i][0] + " - £" + updatedStock[i][2]);
            }
        }
        else if(currency == 2){
            for (int j = 0; j < updatedStock.length - 2; j++) {
                String euroPrice = String.format("%.02f", Double.parseDouble(updatedStock[j][2])*EXCHANGE_RATE);
                System.out.println(j+1 + ". " + updatedStock[j][0] + " - €" + euroPrice);
            }
        }


        Scanner sc = new Scanner(System.in);
        System.out.print("\nPlease enter a number between 1 and 4 to select an item: ");

        int selection = sc.nextInt();
        int available = Integer.parseInt(stock[selection-1][1]);

        // Check for stock availability

        while (available == 0) {
            System.out.println("No stock left of this item. Sorry");
            System.out.print("\nEnter another item number or enter a letter to terminate the service: ");
            selection = sc.nextInt();
            available = Integer.parseInt(stock[selection-1][1]);
        }

        // if item available, pass item selection with the updatedStck array

        String passSelection = String.valueOf(selection);
        String[] input = {passSelection, passSelection, passSelection};
        updatedStock[4] = input;

        return updatedStock;
    }

    private float payment(String[][] updatedStock) {
        float change = 0;
        int selection;
        int index = Integer.parseInt(updatedStock[4][0]) - 1;
        float price = 0;
        float payment = 0;

        Scanner s = new Scanner(System.in);

        if(currency == 1){
            price = Float.parseFloat(updatedStock[index][2]);
            System.out.print("\nYou selected " + updatedStock[index][0] + " - £" + price + ". Please enter your payment amount: ");
            payment = s.nextFloat();
            System.out.println("Amount entered: £" + payment);
        }
        else if(currency == 2){
            price = (float) (Float.parseFloat(updatedStock[index][2])*EXCHANGE_RATE);
            BigDecimal priceRound = new BigDecimal(price).setScale(2, RoundingMode.HALF_DOWN);
            System.out.print("\nYou selected " + updatedStock[index][0] + " - €" + priceRound.doubleValue() + ". Please enter your payment amount: ");
            payment = s.nextFloat();
            System.out.println("Amount entered: €" + payment);
        }

        if (payment < price) {
            System.out.println("Insufficient funds");
        } else {

            // Update stock levels
            selection = Integer.parseInt(updatedStock[4][0]);

            if (selection == 1) {
                updatedStock[0][1] = String.valueOf((Integer.parseInt(updatedStock[0][1]) - 1));
            } else if (selection == 2) {
                updatedStock[1][1] = String.valueOf((Integer.parseInt(updatedStock[1][1]) - 1));
            } else if (selection == 3) {
                updatedStock[2][1] = String.valueOf((Integer.parseInt(updatedStock[2][1]) - 1));
            } else if (selection == 4) {
                updatedStock[3][1] = String.valueOf((Integer.parseInt(updatedStock[3][1]) - 1));
            }

            System.out.println("Item vending...");

            // Print change due

            if(currency == 1){
                change = Float.parseFloat(String.format("%.02f", payment - price)); // 2.d.p
                System.out.println("Change due: £" + change + "\n");
                Change.poundsChange(change);
            }
            else if(currency == 2){
                change = Float.parseFloat(String.format("%.02f", payment - price)); // 2.d.p
                System.out.println("Change due: €" + change + "\n");
                Change.eurosChange(change);
            }
        }
        return change;
    }

    private String[][] fileFormat(String[][] updatedStock) {
        // remove selection from array ready for writing to file

        int rowDims = updatedStock.length - 2, colDims = updatedStock[0].length;
        String[][] formattedStock = new String[rowDims][colDims];

        for(int i=0; i<formattedStock.length; i++)
            for(int j=0; j<formattedStock[i].length; j++)
                formattedStock[i][j]=updatedStock[i][j];

        return formattedStock;
    }

    private int vendAgain(){
        Scanner s = new Scanner(System.in);
        System.out.print("\nPress 1 to purchase another item or any key to terminate the service: ");
        start = s.nextInt();
        return start;
    }


}// end of class
