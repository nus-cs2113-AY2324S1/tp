package seedu.stocker.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListDescriptionsCommandTest {

    @Test
    public void executeTest() {
        AddDescriptionCommand addDescriptionCommand1 = new AddDescriptionCommand("Panadol", "Pain Relief");
        addDescriptionCommand1.execute();
        AddDescriptionCommand addDescriptionCommand2 = new AddDescriptionCommand("Dolo", "Stomache Discomfort");
        addDescriptionCommand2.execute();

        ListDescriptionsCommand listDescriptionsCommand = new ListDescriptionsCommand();

        CommandResult<String> result = listDescriptionsCommand.execute();
        String feedbackMessage = result.feedbackToUser;

        String expectedOutput = "List of Drug Descriptions:\nPanadol: Pain Relief\nDolo: Stomache Discomfort";

        assertEquals(expectedOutput, feedbackMessage);
    }
}
