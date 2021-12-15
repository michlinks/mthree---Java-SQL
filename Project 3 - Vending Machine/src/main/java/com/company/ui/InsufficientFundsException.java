package com.company.ui;

import com.sg.classroster.ui.UserIO;

public class InsufficientFundsException extends Exception{
    UserIO io;

    InsufficientFundsException(String message, UserIO io){
        super(message);
        this.io = io;
    }

    public float correctPayment(float payment, float price){
        while(payment < price){
            String paymentMessage = "\nPlease enter a sufficient payment amount: ";
            payment = io.readFloat(paymentMessage);
        }
        return payment;
    }
}
