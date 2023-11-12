//@@author Brian030601

package seedu.duke.calendar.command;

import seedu.duke.calendar.EventList;

import java.util.Scanner;

public class DeleteEventCommand extends DualEventCommand {

    public DeleteEventCommand(String input) {
        this.input = input;
        beginnerCommandLength = 2;
        expertCommandLength = 3;
        syntaxString = "delete event EVENT_NAME";
    }

    //@@author Brian030601
    /**
     * The execute method is used to delete a specified event from the EventList.
     * It takes two parameters (Scanner , EventList). The method takes in the event name,
     * then calls the deleteEvent function to search and delete the event.
     * If the event is not in the EventList, then "event doesn't exist" message will be displayed.
     * @param scanner is used to get user's desired event to be deleted.
     * @param eventList is used to delete the specified event from the EventList.
     */

    @Override
    protected void executeBeginnerMode(Scanner scanner, EventList eventList) {
        int size;

        System.out.print("Enter the event name: ");
        String eventName = scanner.nextLine();

        size = eventList.deleteEvent(eventName);
        if (size > eventList.getSize()) {
            System.out.println("    " + eventName + " has been deleted from your Calendar!");
        } else if (size != 0 && size == eventList.getSize()) {
            System.out.println("    " + eventName + " doesn't exist in your Calendar!");
        }
    }

    //@@author Cheezeblokz

    @Override
    protected void executeExpertMode(Scanner scanner, EventList eventList) {
        String[] commandParts = input.split(" ");
        String eventName = commandParts[2];

        int size = eventList.deleteEvent(eventName);
        if (size > eventList.getSize()) {
            System.out.println("    " + eventName + " has been deleted from your Calendar!");
        } else if (size != 0 && size == eventList.getSize()) {
            System.out.println("    " + eventName + " doesn't exist in your Calendar!");
        }
    }
}
