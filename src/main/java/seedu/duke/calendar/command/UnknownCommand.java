package seedu.duke.calendar.command;

import seedu.duke.calendar.Calendar;

import java.util.Scanner;

public class UnknownCommand extends EventCommand{
    public void execute(Scanner scanner, Calendar calendar) {
        System.out.println("Unknown command!");
    }
}
