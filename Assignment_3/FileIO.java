/*
 * @michaelalinks
 * */

package com.company;

import java.io.*;
import java.util.ArrayList;

public interface FileIO {

    default String[][] readFile(String path) throws IOException {

        ArrayList<String> list = new ArrayList<String>();
        String line;
        String matrix[][] = new String [5][3]; // make dynamic
        String arr[] = new String[list.toArray().length];
        try{
            BufferedReader myReader = new BufferedReader(new FileReader(path));
            while ((line = myReader.readLine()) != null) {
                list.add(line);
            }

            myReader.close();

            String tempArray[] = new String[list.size()];
            tempArray = list.toArray(tempArray);

            for (int i = 0;i < tempArray.length ;i++){
                String temp2[] = tempArray[i].split(",");
                for (int j = 0;j < temp2.length ;j++){
                    matrix[i][j] = temp2[j];
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found.");
        }
        return matrix;
    } // end of readFile

    static String[][] writeFile(String[][] array){
        String path = "/Users/michaelalinks/Google Drive/Coding/Java/Michaela_Links_Assignment_3/src/com/company/stock.txt";
        try {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < array.length; i++)//for each row
            {
                for (int j = 0; j < array[0].length; j++)//for each column
                {
                    builder.append(array[i][j] + "");//append to the output string
                    if (j < array.length - 1)//if this is not the last row element
                        builder.append(",");
                }
                builder.append("\n");//append new line at the end of the row
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(builder.toString());//save the string representation of the board
            writer.close();
            System.out.println("\nStock successfully updated");
        }
        catch (IOException e){

        }
        return array;

    } // end of writeFile

} // end of FileIO
