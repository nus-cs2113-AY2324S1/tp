package seedu.financialplanner.commands;

import org.junit.jupiter.api.Test;
import seedu.financialplanner.reminder.ReminderList;
import seedu.financialplanner.utils.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AddReminderCommandTest {
    protected ReminderList reminderList = ReminderList.getInstance();

    @Test
    void testIllegalArgumentException() {
        try {
            AddReminderCommand testEntry = new AddReminderCommand(Parser.parseRawCommand("addreminder"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Reminder must have a type", e.getMessage());
        }

        try {
            AddReminderCommand testEntry = new AddReminderCommand(Parser.parseRawCommand("addreminder /t debt"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Reminder must have a date", e.getMessage());
        }

        try {
            AddReminderCommand testEntry = new AddReminderCommand(
                    Parser.parseRawCommand("addreminder /t debt /d 11/12/2020"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Reminder date cannot be in the past", e.getMessage());
        }

        try {
            AddReminderCommand testEntry = new AddReminderCommand(
                    Parser.parseRawCommand("addreminder /t debt /d 2023/12/12"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Reminder date must be in the format dd/MM/yyyy", e.getMessage());
        }

        try {
            AddReminderCommand testEntry = new AddReminderCommand(
                    Parser.parseRawCommand("addreminder /t    /d 11/12/2023"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Reminder type cannot be empty", e.getMessage());
        }
    }

}
