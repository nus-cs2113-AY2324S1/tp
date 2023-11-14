package seedu.stocker.commands;

import org.junit.jupiter.api.Test;
import seedu.stocker.drugs.Cart;
import seedu.stocker.drugs.Drug;
import seedu.stocker.drugs.Inventory;
import seedu.stocker.drugs.SalesList;
import seedu.stocker.vendors.VendorsList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListThresholdCommandTest {

    @Test
    public void executeTest() {
        // Create a new inventory
        Inventory inventory = new Inventory();
        Drug drug1 = new Drug("Panadol", "04/07/2030", 19.90);
        inventory.addNewDrug("PAN437", drug1, 120);
        Drug drug2 = new Drug("paracetamol", "01/07/2020", 20.90);
        inventory.addNewDrug("PARC578", drug2, 50);

        // Create the SetThresholdCommand
        ListThresholdCommand command = new ListThresholdCommand();

        // Set the modified inventory for the command
        command.setData(inventory, new SalesList(), new Cart(), new VendorsList());

        CommandResult initialResult = command.execute();

        String expectedInitialOutput = "1. Panadol: 100" +
                System.lineSeparator() +
                "2. paracetamol: 100" +
                System.lineSeparator() +
                System.lineSeparator() +
                "Listed all drugs by threshold level in the inventory.";

        // Test the command's execute method with a null keyword
        assertEquals(expectedInitialOutput, initialResult.getFeedbackToUserFindTest());
    }
}
