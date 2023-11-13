//@@author Brian030601

package seedu.duke.calendar.command;

import seedu.duke.calendar.EventList;

import java.util.Scanner;

public class FindEventCommand extends DualEventCommand {

    //@@author Cheezeblokz

    public FindEventCommand(String input) {
        this.input = input;
        beginnerCommandLength = 2;
        expertCommandLength = 3;
        syntaxString = "find event EVENT_NAME";
    }

    //@@author Brian030601
    /**
     * The execute method is to search and find an event from the EventList. The method has two
     * parameters (Scanner , EventList). Scanner takes the name of the event that the user
     * wants to find. EventList is accessed to search the event from the list. If the given
     * input or part of it is not in the event list, "no such event" message will be displayed.
     * @param scanner is used to get user's input on what event to search for.
     * @param eventList is used to access EventList and find the specified event.
     */

    @Override
    protected void executeBeginnerMode(Scanner scanner, EventList eventList) {
        System.out.print("What event are you looking for?: ");
        String eventName = scanner.nextLine();

        int count = eventList.findEvent(eventName);

        if (count > 0) {
            System.out.println("    These events have been found");
        } else {
            System.out.println("    No such event found");
        }
    }


    //@@author Cheezeblokz

    @Override
    protected void executeExpertMode(Scanner scanner, EventList eventList) {
        String[] commandParts = input.split(" ");
        String eventName = commandParts[2];

        int count = eventList.findEvent(eventName);

        if (count > 0) {
            System.out.println("    These events have been found");
        } else {
            System.out.println("    No such event found");
        }
    }
}
