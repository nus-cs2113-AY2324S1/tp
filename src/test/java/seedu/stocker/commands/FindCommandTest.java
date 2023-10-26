package seedu.stocker.commands;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import seedu.stocker.drugs.Inventory;
import seedu.stocker.drugs.SalesList;
import seedu.stocker.drugs.Cart;
import seedu.stocker.drugs.Drug;
import static seedu.stocker.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

class FindCommandTest {

    @Test
    public void executeTestByName() {
        // Create an instance of FindCommand with a keyword
        FindCommand command = new FindCommand("Pa", "/n");

        // Create a new inventory
        Inventory inventory = new Inventory();
        Drug drug2 = new Drug("Panadol", "04/07/2030");
        inventory.addNewDrug("Panadol", drug2, 20L);

        // Set the modified inventory for the command
        command.setData(inventory, new SalesList(), new Cart());

        // Define expected output
        String expectedOutput = "1. Name: Panadol, Expiry date: 04/07/2030, Quantity: 20" + System.lineSeparator() +
                System.lineSeparator() +
                "Listed all drugs with the keyword in the inventory.";

        CommandResult actualResult = command.execute();

        // Test the command's execute method with the modified inventory
         assertEquals(expectedOutput, actualResult.getFeedbackToUserFindTest());
    }

    @Test
    public void executeTestByExpiryDate() {
        // Create an instance of FindCommand with a keyword for expiry date
        FindCommand command = new FindCommand("01/03/2027", "/d");

        // Create a new inventory
        Inventory inventory = new Inventory();
        Drug drug1 = new Drug("Paracetamol", "01/03/2027");
        inventory.addNewDrug("Paracetamol", drug1, 12L);

        // Set the modified inventory for the command
        command.setData(inventory, new SalesList(), new Cart());

        // Define expected output for drugs with matching expiry date
        String expectedOutput = "1. Name: Paracetamol, Expiry date: 01/03/2027, Quantity: 12" + System.lineSeparator() +
                System.lineSeparator() +
                "Listed all drugs with the keyword in the inventory.";

        CommandResult actualResult = command.execute();

        // Test the command's execute method with the modified inventory
        assertEquals(expectedOutput, actualResult.getFeedbackToUserFindTest());
    }

    @Test
    public void executeTestWithNullKeyword() {
        // Create an instance of FindCommand with a null keyword
        FindCommand command = new FindCommand("", "/n");

        // Create a new inventory
        Inventory inventory = new Inventory();

        // Set the modified inventory for the command
        command.setData(inventory, new SalesList(), new Cart());

        // Define expected output for invalid format
        String expectedOutput = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);

        CommandResult actualResult = command.execute();

        // Test the command's execute method with a null keyword
        assertEquals(expectedOutput, actualResult.getFeedbackToUserFindTest());
    }


}
