package seedu.stocker.commands;

import org.junit.jupiter.api.Test;
import seedu.stocker.drugs.Drug;
import seedu.stocker.drugs.Inventory;
import seedu.stocker.drugs.SalesList;
import seedu.stocker.drugs.Cart;
import seedu.stocker.vendors.VendorsList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//test for list
class StockLevelCommandTest {

    @Test
    public void executeTestEmpty(){
        Inventory inventory = new Inventory();
        ShowStockLevelCommand command = new ShowStockLevelCommand();
        command.setData(inventory, new SalesList(), new Cart(), new VendorsList());
        // Execute the command
        CommandResult result = command.execute();
        String expectedOutput = "The inventory is empty.";
        assertEquals(expectedOutput, result.getFeedbackToUserFindTest());
    }

    @Test
    public void executeTest() {
        // Create a new inventory
        Inventory inventory = new Inventory();
        Drug drug1 = new Drug("Panadol", "04/07/2030", 19.90);
        inventory.addNewDrug("PAN947", drug1,120);
        Drug drug2 = new Drug("paracetamol", "01/07/2020", 12.90);
        inventory.addNewDrug("PARC347", drug2, 50);
        Drug drug3 = new Drug("histamine", "09/05/2070", 15.90);
        inventory.addNewDrug("HIS9447", drug3, 10);


        ShowStockLevelCommand command = new ShowStockLevelCommand();
        command.setData(inventory, new SalesList(), new Cart(), new VendorsList());

        // Execute the command
        CommandResult result = command.execute();


        String expectedOutput = "1. Name: histamine, Expiry date: 09/05/2070, Serial number: HIS9447, Quantity: 10" +
                System.lineSeparator() +
                "2. Name: paracetamol, Expiry date: 01/07/2020, Serial number: PARC347, Quantity: 50" +
                System.lineSeparator() +
                "3. Name: Panadol, Expiry date: 04/07/2030, Serial number: PAN947, Quantity: 120" +
                System.lineSeparator() +
                System.lineSeparator() +
                "Stock Level Report (Sorted by Quantity)";

        assertEquals(expectedOutput, result.getFeedbackToUserFindTest());

    }
}
