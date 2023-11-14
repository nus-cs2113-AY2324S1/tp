package seedu.stocker.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.stocker.drugs.Inventory;
import seedu.stocker.drugs.SalesList;
import seedu.stocker.drugs.Cart;
import seedu.stocker.vendors.VendorsList;

class AddCommandTest {

    @Test
    public void executeTest() {
        AddCommand command = new AddCommand("Paracetamol", "12/05/2024", "12345",12L, 19.90);
        command.setData(new Inventory(), new SalesList(), new Cart(), new VendorsList());
        String expectedOutput = "New drug added in the inventory: Paracetamol";
        assertEquals(command.execute().feedbackToUser, new CommandResult(expectedOutput).feedbackToUser);
        assertEquals(command.execute().getRelevantElements(), new CommandResult(expectedOutput).getRelevantElements());
    }
}
