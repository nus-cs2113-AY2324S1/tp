package seedu.stocker.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddDescriptionCommandTest {

    @Test
    public void executeTest() {
        AddDescriptionCommand command = new AddDescriptionCommand("Panadol", "Pain Relief");
        CommandResult<String> result = command.execute();
        String feedbackMessage = result.feedbackToUser;

        assertEquals("New drug description added for Panadol: Pain Relief", feedbackMessage);
    }
}
