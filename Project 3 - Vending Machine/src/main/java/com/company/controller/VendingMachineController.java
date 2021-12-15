package com.company.controller;

import com.company.dto.Stock;
import com.company.ui.ItemUnavailableException;
import com.company.ui.VendingMachineView;
import org.codehaus.plexus.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.io.IOException;

@Component
public class VendingMachineController {
    int start = 1;
    private final Stock inventory;
    private final VendingMachineView view;
    float change;
    String[][] tempArray;


    @Autowired
    public VendingMachineController(Stock inventory, VendingMachineView view) {
        this.inventory = inventory;
        this.view = view;
    }

    public void run(){
        while(start == 1) {
            view.currency();
            view.machineMenu(inventory.getStock());
            tempArray = view.itemSelection(inventory.getStock());
            change = view.payment(tempArray);
            view.fileFormat(tempArray);
            start = view.vendAgain();
        }
    }
}
