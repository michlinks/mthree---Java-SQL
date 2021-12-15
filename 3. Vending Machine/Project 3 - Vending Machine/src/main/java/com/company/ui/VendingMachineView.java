package com.company.ui;
import com.company.dao.Change;
import com.company.dao.FileIO;
import com.sg.classroster.ui.UserIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

@Component
public class VendingMachineView implements FileIO {

    private UserIO io;
    int currency, start;
    double EXCHANGE_RATE = 1.17;

    @Autowired
    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void currency(){
        String prompt = "\nEnter 1 for GBP or 2 for EUR: ";

        currency = io.readInt(prompt, 1,2);
    }

    public void machineMenu(String[][] array){
        System.out.println("\nVending machine options are: \n");

        /**
         * Print item options from Stock object array in the preferred currency
         * */

        if(currency == 1) {
            for (int i = 0; i < array.length - 1; i++) {
                System.out.println(i+1 + ". " + array[i][0] + " - £" + array[i][2]);
            }
        }
        else if(currency == 2){
            for (int j = 0; j < array.length - 1; j++) {
                String euroPrice = String.format("%.02f", Double.parseDouble(array[j][2])*EXCHANGE_RATE);
                System.out.println(j+1 + ". " + array[j][0] + " - €" + euroPrice);
            }
        }
    }


    public String[][] itemSelection(String[][] array) {
        int rowDims = (array.length) + 1;
        int selection, available;
        String[][] stockWithSelection = Arrays.copyOf(array, rowDims);

        try{
            String selectMessage = "\nPlease enter a number between 1 and 4 to select an item: ";

            selection = io.readInt(selectMessage, 1,4);
            available = Integer.parseInt(array[selection-1][1]);

            if(available == 0){
                throw new ItemUnavailableException("No stock left of this item. Sorry", io);
            }

        } catch (ItemUnavailableException e) {
            selection = e.itemAvailability(array);
        }

        /**
         * Pass item selection along with stock array
         * */

        String passSelection = String.valueOf(selection);
        String[] input = {passSelection, passSelection, passSelection};
        stockWithSelection[4] = input;

        return stockWithSelection;
    }

    public float payment(String[][] updatedStock) {
        float change, price = 0, payment = 0;
        int selection;
        int index = Integer.parseInt(updatedStock[4][0]) - 1;

        String paymentMessage;

        /**
        * Check sufficient payment received
        **/

        try{

            if(currency == 1){
                price = Float.parseFloat(updatedStock[index][2]);
                paymentMessage = "\nYou selected " + updatedStock[index][0] + " - £" + price + ". Please enter your payment amount: ";
                payment = io.readFloat(paymentMessage);
                System.out.println("Amount entered: £" + payment);
            }
            else if(currency == 2){
                price = (float) (Float.parseFloat(updatedStock[index][2])*EXCHANGE_RATE);
                BigDecimal priceRound = new BigDecimal(price).setScale(2, RoundingMode.HALF_DOWN);
                paymentMessage = "\nYou selected " + updatedStock[index][0] + " - €" + priceRound.doubleValue() + ". Please enter your payment amount: ";
                payment = io.readFloat(paymentMessage);
                System.out.println("Amount entered: €" + payment);
            }

            if (payment < price) {
                throw new InsufficientFundsException("Insufficient funds", io);
            }
        }
        catch(InsufficientFundsException ex){
            payment = ex.correctPayment(payment, price);
        }

        /**
         * Once sufficient payment received, update stock levels
         **/


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

        /**
         * Print change due
         **/

        change = Float.parseFloat(String.format("%.02f", payment - price)); // 2.d.p

        if(currency == 1){
            System.out.println("Change due: £" + change + "\n");
            Change.poundsChange(change);
        }
        else if(currency == 2){
            System.out.println("Change due: €" + change + "\n");
            Change.eurosChange(change);
        }

        return change;
    }

    public void fileFormat(String[][] updatedStock) {

        /**
         * Remove selection from array ready for writing back to file
         **/

        int rowDims = updatedStock.length - 2, colDims = updatedStock[0].length;
        String[][] formattedStock = new String[rowDims][colDims];

        for(int i=0; i<formattedStock.length; i++)
            System.arraycopy(updatedStock[i], 0, formattedStock[i], 0, formattedStock[i].length);

        writeFile(formattedStock); // write back to file with updated stock levels

    }

    public int vendAgain(){
        String exitMessage = "\nPress 1 to purchase another item or ENTER to terminate the service: ";
        String exit = io.readString(exitMessage);

        if(exit.equals("")){
            start = 0;
        }
        else if(Integer.parseInt(exit) == 1){
            start = 1;
        }
        else{
            start = 0;
        }
        return start;
    }

}
