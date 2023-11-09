//@@author wendelinwemhoener-reused

package seedu.duke.calendar.command;

import seedu.duke.calendar.EventList;

import java.util.Scanner;

public abstract class DualEventCommand extends EventCommand {
    protected int beginnerCommandLength;
    protected int expertCommandLength;
    protected String input;
    protected String syntaxString;

    protected abstract void executeBeginnerMode(Scanner scanner, EventList eventList);

    protected abstract void executeExpertMode(Scanner scanner,
                                              EventList eventList);

    public void execute(Scanner scanner, EventList eventList) {
        String[] commandParts = input.split(" ");

        if (commandParts.length == beginnerCommandLength) {
            executeBeginnerMode(scanner, eventList);
        } else if (commandParts.length == expertCommandLength) {
            executeExpertMode(scanner, eventList);
        } else {
            System.out.println("    Invalid syntax! The syntax is '" + syntaxString + "'");
            System.out.println("    Please try again.");
        }
    }
}
