package com.company.ui;
import com.sg.classroster.ui.UserIO;

public class ItemUnavailableException extends Exception{

    UserIO io;

    public ItemUnavailableException(String message,UserIO io) {
        super(message);
        this.io = io;
    }

    public int itemAvailability(String[][] array){
        String selectMessage = "\nPlease enter another item number: ";
        int selection = io.readInt(selectMessage, 1,4);
        int available = Integer.parseInt(array[selection-1][1]);

        while (available == 0) {
            String message = "\nPlease enter another item choice: ";
            selection = io.readInt(message, 1,4);
            available = Integer.parseInt(array[selection-1][1]);
        }
        return selection;
    }

}
