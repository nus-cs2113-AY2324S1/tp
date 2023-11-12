package seedu.financialplanner.commands;

import org.junit.jupiter.api.Test;
import seedu.financialplanner.goal.WishList;
import seedu.financialplanner.utils.Parser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
class SetGoalCommandTest {
    protected WishList wishList = WishList.getInstance();
    @Test
    void testIllegalArgumentException() {
        try {
            SetGoalCommand testEntry = new SetGoalCommand(Parser.parseRawCommand("set goal"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Goal must have an amount", e.getMessage());
        }
        try {
            SetGoalCommand testEntry = new SetGoalCommand(Parser.parseRawCommand("set goal /g /l car"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Amount must be specified", e.getMessage());
        }
        try {
            SetGoalCommand testEntry = new SetGoalCommand(Parser.parseRawCommand("set goal /g -1 /l car"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Amount must be positive", e.getMessage());
        }
        try {
            SetGoalCommand testEntry = new SetGoalCommand(
                    Parser.parseRawCommand("set goal /g 2222222222222222222222222222222222222 /l car"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Amount must be a valid integer", e.getMessage());
        }
    }

}
