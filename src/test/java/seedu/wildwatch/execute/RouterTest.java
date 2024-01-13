//@@woodenclock
package seedu.wildwatch.execute;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import seedu.wildwatch.command.AddCommand;
import seedu.wildwatch.command.Command;
import seedu.wildwatch.command.DeleteCommand;
import seedu.wildwatch.command.ListCommand;
import seedu.wildwatch.entry.Entry;
import seedu.wildwatch.exception.InvalidInputException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.wildwatch.entry.EntryList.addEntry;
import static seedu.wildwatch.entry.EntryList.clearEntryList;

public class RouterTest {

    static final String ADD_INPUT = "add D/02-03-2023 S/Annam Leaf Turtle N/Ariel_2 R/Injured left flipper";

    @BeforeEach
    public void setUp() {
        // This is executed before each test.
        clearEntryList();
        addEntry(new Entry("28-10-2023", "Lion", "Simba", "This is Simba."));
    }

    @AfterEach
    public void tearDown() {
        // This is executed after each test.
        clearEntryList();
    }

    @Test
    public void route_addCommand_returnsAddCommandInstance() {
        Command result = assertDoesNotThrow(() -> Router.route(ADD_INPUT));
        assertTrue(result instanceof AddCommand);
    }

    @Test
    public void route_deleteCommandWithValidIndex_returnsDeleteCommandInstance() {
        Command result = assertDoesNotThrow(() -> Router.route("delete 1"));
        assertTrue(result instanceof DeleteCommand);
    }

    @Test
    public void route_deleteCommandWithInvalidIndex_throwsInvalidInputException() {
        String input = "delete -5"; // Negative numbers should be invalid.
        assertThrows(InvalidInputException.class, () -> Router.route(input));
    }

    @Test
    public void route_listCommand_returnsListCommandInstance() {
        String input = "list";
        Command result = assertDoesNotThrow(() -> Router.route(input));
        assertTrue(result instanceof ListCommand);
    }

    @Test
    public void route_invalidCommandInput_throwsInvalidInputException() {
        String input = "invalidCommand";
        assertThrows(InvalidInputException.class, () -> Router.route(input));
    }
}

