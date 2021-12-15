/*
* @michaelalinks
* */

package com.company;

import com.company.controller.VendingMachineController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {

    static AnnotationConfigApplicationContext springDI(){
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com.company");
        appContext.refresh();
        return appContext;
    }

    public static void main(String[] args){

        AnnotationConfigApplicationContext appContext = springDI();

        VendingMachineController controller = appContext.getBean("vendingMachineController", VendingMachineController.class);
        controller.run();

    }

}
