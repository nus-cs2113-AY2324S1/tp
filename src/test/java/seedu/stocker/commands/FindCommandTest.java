package seedu.stocker.commands;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import seedu.stocker.drugs.Inventory;


//test for find
class FindCommandTest {

    @Test
    public void executeTest() {
        // Create an instance of FindCommand with a keyword
        FindCommand command = new FindCommand("Panadol");
        command.setData(new Inventory());



        String expectedOutput = "Listed all drugs with the keyword in the inventory.";
        assertEquals( new CommandResult(expectedOutput).feedbackToUser,command.execute().feedbackToUser);
    }

}
