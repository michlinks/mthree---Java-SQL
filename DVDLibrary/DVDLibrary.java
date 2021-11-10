package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DVDLibrary {
    public String array[][];


    public DVDLibrary(String path) throws IOException{ // change exception
        FileIO library = new FileIO(path);
        array = library.getMatrix();
        menu(array);
     }
    public static String[] twoDimToOneDim(String array[][], int col){
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < array.length; i++) {
            list.add(array[i][col]);
        }

        String[] arr =new String[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static String[][] addDVDs(String array[][]){
        Scanner sc = new Scanner(System.in);

        String headings[] = {"title: ", "release date: ", "MPPA rating: ","director's name: ", "studio: ", "the user rating or note: "};
        int columnDims = headings.length;
        int rowDims = (array.length) + 1;
        String input [] = new String [columnDims];
        String newArray[][] = Arrays.copyOf(array, rowDims);

        for (int i=array.length; i< rowDims;i++) {
            for (int j = 0; j < headings.length; j++) {
                System.out.print("\nEnter " + headings[j]);
                input[j] = sc.nextLine();
            }
//            input[5] = sc.nextLine();
            newArray[i] = input;
        }

        displayLibrary(newArray);

        return newArray;
    }

    public static void displayLibrary(String array[][]){
        for(int i = 0; i < array.length; i++) {
            System.out.print(i+1 + ". ");
            for(int j = 0; j < array[0].length; j++){
                System.out.print(array[i][j] + ", ");
            }
            System.out.println("\n");
        }
    }
    public static void displayDVD(String array[][]){
        Scanner sc = new Scanner(System.in);
        int entries = array.length - 1;

        System.out.print("\nEnter a number between 0 and "+ entries + " to display the information of that entry: ");

        int index = sc.nextInt();

        if(index > 0 & index<=entries){
            System.out.println();

            for(int j=0; j < array[0].length; j++){
                System.out.print(array[index][j] + " ");
            }

            System.out.println();
        }
        else{
            System.out.println("Number out of range");
        }

    }

    public static String[][] removeDVD(String array[][]){
        Scanner sc = new Scanner(System.in);
        int rowDims = array.length-1, colDims = array[0].length;
        int entries = array.length;
        String[][] realCopy = new String[array.length-1][array[0].length];
        System.out.print("\nEnter a number between 1 and "+ entries + " for the DVD you would like to remove: ");
        int row = sc.nextInt();
        int editing = 0;

        if(row > 0 & row<=entries){
            editing = 1;
            System.out.println("");

            int count = 0;
            if(row==rowDims)
            {
                for(int r=0; r<rowDims; r++)
                {
                    for(int c=0; c<colDims; c++)
                    {
                        if(row==r)
                            r++;
                        realCopy[count][c]= array[r][c];
                    }
                    count++;
                }
            }
            else
            {
                for(int r=0; r<entries; r++)
                {
                    for(int c=0; c<colDims; c++)
                    {
                        if(row==r)
                            r++;
                        realCopy[count][c]= array[r][c];
                    }
                    count++;
                }
            }
            System.out.println("");
        }
        else{
            System.out.println("Number out of range");
        }

        if(editing == 1) {
            displayLibrary(realCopy);
        }

        return realCopy;
    }

    public static void searchByTitle(String array[], String matrix[][]){

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the title of the movie you'd like to search (as present in library): ");
        String input = sc.nextLine();
        int present = 0, index =0;
        System.out.println();

        for(int i=0; i<array.length; i++){
            if(Arrays.asList(array).contains(input)){
                index = i;
                present = 1;
            }
        }

        if(present == 1){
            for(int j = 0;j<matrix[0].length; j++){
                System.out.print(matrix[index][j] + ", ");
            }
            System.out.println();
        }
        else{
            System.out.println("Title not found");
        }
    }

    public static String[][] editLibrary(String array[][]){
        Scanner sc = new Scanner(System.in);
        int editing = 1;

        while(editing ==1) {
            System.out.print("\nPlease enter the row of the DVD entry you would like to edit: ");
            int row = sc.nextInt();

            System.out.print("\nPlease enter the column of the DVD entry you would like to edit: ");
            int col = sc.nextInt();

            Scanner s = new Scanner(System.in);

            System.out.print("\nEnter your edit: ");
            String input = s.nextLine();
            array[row][col] = input;

            System.out.print("\nPress one to continue editing: ");
            editing = sc.nextInt();
        }

        System.out.println("Updated library: ");

        displayLibrary(array);

        return array;
    }

    static void writeToFile(String array[][]){
        try {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < array.length; i++)//for each row
            {
                for (int j = 0; j < array[0].length; j++)//for each column
                {
                    builder.append(array[i][j] + "");//append to the output string
                    if (j < array.length - 1)//if this is not the last row element
                        builder.append(",");//then add comma (if you don't like commas you can use spaces)
                }
                builder.append("\n");//append new line at the end of the row
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter("UpdatedLibrary.txt"));
            writer.write(builder.toString());//save the string representation of the board
            writer.close();
            System.out.println("\nLibrary successfully saved to UpdatedLibrary.txt");
        }
        catch (IOException e){

        }
    }
    public static void menu(String array[][]){

        String titles[] = twoDimToOneDim(array, 0);

        Scanner sc =new Scanner(System.in);

        System.out.println("\nDVD Library Options:\n");
        String options[] = {"1. Add DVD to collection",
                "2. Remove DVDs from collection",
                "3. Edit existing DVD's information",
                "4. List all the DVDs in the collection",
                "5. Display specific DVD's information",
                "6. Search DVD by title"};

        for (int i=0; i < options.length; i++){
            System.out.println(options[i]);
        }
        System.out.print("\nEnter a number for the service you would like to use or type a letter to terminate the programme: ");


        while(sc.hasNextInt()) {
            int opt = sc.nextInt();

            if (opt >= 1 & opt <= 8) {
                if (opt == 1) {
                    array = addDVDs(array); // not tested
                } else if (opt == 2) {
                    array = removeDVD(array);
                } else if (opt == 3) {
                    array = editLibrary(array);
                } else if (opt == 4) {
                    displayLibrary(array);
                } else if (opt == 5) {
                    displayDVD(array);
                } else if (opt == 6) {
                    searchByTitle(titles, array);
                }
            }
            else{
                System.out.println("No option available for input\n");
            }

            System.out.println();

            for (int i=0; i < options.length; i++){
                System.out.println(options[i]);
            }

            System.out.print("\nEnter a number to continue accessing the DVD library or type a letter to terminate the programme: ");
        }
        writeToFile(array);
        System.out.println("\nPROGRAMME TERMINATING");
    }

}


