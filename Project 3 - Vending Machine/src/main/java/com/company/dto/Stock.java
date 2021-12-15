package com.company.dto;

import com.company.dao.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Stock implements FileIO {
    String path = "/Users/michaelalinks/Google Drive/Coding/Java/VendingMachine/Michaela_Links_Vending_Machine_MVCSwSpring/src/main/java/com/company/stock.txt";

    private String[][] stock;
    private String[] snacks;
    private String[] units;
    private double[] prices;

    @Autowired
    public Stock() throws IOException {
        this.stock = readStock(path);
        this.snacks = setSnacks(stock);
        this.units = setUnits(stock);
        this.prices = setPrices(stock);
    }

    private String[][] readStock(String path) throws IOException{
            return readFile(path); // read in array from file using FileIO interface
    }

    public String[][] getStock() {
        return stock;
    }

    private String[] setSnacks(String[][] array){
        String[] temp1 = new String[array.length - 1];
        for (int i = 0; i < temp1.length; i++){
            temp1[i] = array[i][0];
        }
        return temp1;
    }

    public String[] getSnacks(){
        return snacks;
    }

    private String[] setUnits(String[][] array){
        String[] temp2 = new String[array.length - 1];
        for (int i = 0; i < temp2.length; i++){
            temp2[i] = array[i][1];
        }
        return temp2;
    }


    private double[] setPrices(String[][] array){
        double[] temp3 = new double[array.length - 1];
        for (int i = 0; i < temp3.length; i++){
            temp3[i] = Double.valueOf(array[i][2]);
        }
        return temp3;
    }
}
