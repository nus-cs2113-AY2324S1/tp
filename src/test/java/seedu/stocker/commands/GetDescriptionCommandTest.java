package seedu.stocker.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetDescriptionCommandTest {

    @Test
    public void executeTest() {
        AddDescriptionCommand addDescriptionCommand = new AddDescriptionCommand("Panadol", "Pain Relief");
        addDescriptionCommand.execute();

        GetDescriptionCommand command = new GetDescriptionCommand("Panadol");
        CommandResult<String> result = command.execute();
        String feedbackMessage = result.feedbackToUser;

        assertEquals("Pain Relief", feedbackMessage);
    }
}
