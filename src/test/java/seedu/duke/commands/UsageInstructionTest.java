package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link UsageInstructions} class.
 * This test class provides test cases to check whether each function can be call without error
 */
public class UsageInstructionTest {
    private UsageInstructions helpFunction;


    /**
     * Sets up the UsageInstructions object
     */
    @BeforeEach
    public void setup() throws KaChinnnngException {
        helpFunction = new UsageInstructions();
    }

    @Test
    public void executeAllCommands(){
        helpFunction.getHelp();         // Make sure all the function works well
        helpFunction.getHelpUpdateExchangeRateFunction();
        helpFunction.getHelpFindFunction();
        helpFunction.getHelpExitFunction();
        helpFunction.getHelpEditFunction();
        helpFunction.getHelpDeleteFunction();
        helpFunction.getHelpListFunction();
        helpFunction.getHelpBalanceFunction();
        helpFunction.getHelpAddFunction();
        helpFunction.getHelpClearFunction();
    }
}
