package seedu.stocker.commands;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import seedu.stocker.drugs.Inventory;
import seedu.stocker.drugs.Drug;

class FindCommandTest {

    @Test
    public void executeTest() {
        // Create an instance of FindCommand with a keyword
        FindCommand command = new FindCommand("Pa");

        // Create a new inventory
        Inventory inventory = new Inventory();
        Drug drug1 = new Drug("Paracetamol", "12/05/2024", 12L);
        inventory.addDrug(drug1);


        // Set the modified inventory for the command
        command.setData(inventory);


        // Define expected output
        String expectedOutput = "Listed all drugs with the keyword in the inventory.";

        CommandResult actualResult = command.execute();

        // Test the command's execute method with the modified inventory
        assertEquals(expectedOutput, actualResult.feedbackToUser);
    }
}
