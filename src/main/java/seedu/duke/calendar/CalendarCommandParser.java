//@@author Brian030601

package seedu.duke.calendar;

import seedu.duke.calendar.command.AddEventCommand;
import seedu.duke.calendar.command.AddGoalEventCommand;
import seedu.duke.calendar.command.DeleteEventCommand;
import seedu.duke.calendar.command.EventCommand;
import seedu.duke.calendar.command.FindEventCommand;
import seedu.duke.calendar.command.DeleteAllEventsCommand;
import seedu.duke.calendar.command.ListCalendarEventsCommand;
import seedu.duke.calendar.command.UnknownCommand;

import seedu.duke.calendar.exceptions.AddEventException;
import seedu.duke.calendar.exceptions.DeleteEventException;
import seedu.duke.calendar.exceptions.DeleteAllException;
import seedu.duke.calendar.exceptions.FindEventException;
import seedu.duke.calendar.exceptions.ListEventException;

import java.util.Scanner;

public class CalendarCommandParser {

    //@@author Brian030601
    /**
     * The manageException method is used to throw exceptions if those exceptions have
     * been encountered. Each if case handles different exception errors. The method
     * has one parameter (String userInput), which is used to check if the input has
     * anything else after it.
     * @param userInput is taken to check if the condition matches the exception.
     * @throws AddEventException is thrown if the user only inputs add and nothing else.
     * @throws DeleteEventException is thrown if the user only inputs delete all and nothing else.
     * @throws DeleteAllException is thrown if the user only inputs delete and nothing else.
     * @throws FindEventException is thrown if the user only inputs find and nothing else.
     * @throws ListEventException is thrown if the user only inputs list and nothing else.
     */

    public static void manageException(String userInput) throws AddEventException, DeleteEventException,
            DeleteAllException, FindEventException, ListEventException {

        Scanner input = new Scanner(userInput);
        String command = input.next();

        if (command.equals("add") && !input.hasNext()) {
            throw new AddEventException();
        }
        if (command.equals("delete all") && !input.hasNext()) {
            throw new DeleteAllException();
        }
        if (command.equals("delete") && !input.hasNext()) {
            throw new DeleteEventException();
        }
        if (command.equals("find") && !input.hasNext()) {
            throw new FindEventException();
        }
        if (command.equals("list") && !input.hasNext()) {
            throw new ListEventException();
        }
    }

    //@@author Brian030601
    /**
     * The parseInput method is used to catch any exceptions that could occur. The method
     * has one parameter (String input). The input is used for asserting that it is not null.
     * If any exceptions are caught, then the corresponding messages are displayed.
     * Last resort unknown command will run if the command is not recognized.
     * @param input is used to check whether input is null or not.
     * @return runs the commands
     */

    public EventCommand parseInput(String input) {
        // using asser to check whether the input is null.
        assert input != null : "input is null";

        try {
            manageException(input);
            if (input.startsWith("add event")) {
                return new AddEventCommand(input);
            } else if (input.startsWith("add goal event")) {
                return new AddGoalEventCommand(input);
            } else if (input.startsWith("delete event")) {
                return new DeleteEventCommand(input);
            } else if (input.startsWith("list events")) {
                return new ListCalendarEventsCommand();
            } else if (input.startsWith("delete all events")) {
                return new DeleteAllEventsCommand();
            } else if (input.startsWith("find event")) {
                return new FindEventCommand(input);
            }

        } catch (AddEventException exception) {
            System.out.println("☹ OOPS!!! The description of an add cannot be empty.");
        } catch (DeleteAllException exception) {
            System.out.println("☹ OOPS!!! The description of a delete all cannot be empty.");
        } catch (DeleteEventException exception) {
            System.out.println("☹ OOPS!!! The description of a delete cannot be empty.");
        } catch (FindEventException exception) {
            System.out.println("☹ OOPS!!! The description of an find cannot be empty.");
        } catch (ListEventException exception) {
            System.out.println("☹ OOPS!!! The description of a list cannot.");
        }

        return new UnknownCommand();
    }
}
