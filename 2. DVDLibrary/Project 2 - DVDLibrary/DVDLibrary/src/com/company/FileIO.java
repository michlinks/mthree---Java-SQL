package com.company;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileIO {

    private ArrayList<String> list = new ArrayList<String>();
    private String line;
    private int rows, cols;
    public String matrix[][] = new String [17][6]; // make dynamic
    public String paths;
    public String arr[];

    public FileIO (String path) throws IOException{
        try{
            BufferedReader myReader = new BufferedReader(new FileReader(path));
            while ((line = myReader.readLine()) != null) {
                rows++;
                cols = (line.split(",")).length;
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
            return arr;
        }
        catch (FileNotFoundException e){
            System.out.println("File not found.");
        }
    }

}
