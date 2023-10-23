package seedu.stocker.commands;

import org.junit.jupiter.api.Test;
import seedu.stocker.drugs.Inventory;
import seedu.stocker.drugs.SalesList;
import seedu.stocker.drugs.Cart;

import static org.junit.jupiter.api.Assertions.assertEquals;

//test for list
class ListCommandTest {
    @Test
    public void executeTest() {
        ListCommand command = new ListCommand();
        command.setData(new Inventory(), new SalesList(), new Cart());
        String expectedOutput = "The inventory is empty.";
        assertEquals( new CommandResult(expectedOutput).feedbackToUser,command.execute().feedbackToUser);
    }
}
