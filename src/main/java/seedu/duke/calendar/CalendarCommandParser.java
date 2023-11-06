//@@ kherlenbayasgalan & jingxizhu

package seedu.duke.calendar;

import seedu.duke.calendar.command.AddEventCommand;
import seedu.duke.calendar.command.DeleteAllEventsCommand;
import seedu.duke.calendar.command.DeleteEventCommand;
import seedu.duke.calendar.command.EventCommand;
import seedu.duke.calendar.command.UnknownCommand;
import seedu.duke.calendar.command.FindEventCommand;
import seedu.duke.calendar.command.ListCalendarEventsCommand;

import seedu.duke.calendar.Exceptions.AddEventException;
import seedu.duke.calendar.Exceptions.DeleteEventException;
import seedu.duke.calendar.Exceptions.DeleteAllException;
import seedu.duke.calendar.Exceptions.FindEventException;
import seedu.duke.calendar.Exceptions.ListEventException;

import java.util.Scanner;

public class CalendarCommandParser {
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

    public EventCommand parseInput(String input) {
        assert input != null : "input is null";

        try {
            manageException(input);
            if (input.startsWith("add event")) {
                return new AddEventCommand();
            } else if (input.startsWith("delete event")) {
                return new DeleteEventCommand();
            } else if (input.startsWith("list events")) {
                return new ListCalendarEventsCommand();
            } else if (input.startsWith("delete all events")) {
                return new DeleteAllEventsCommand();
            } else if (input.startsWith("find event")) {
                return new FindEventCommand();
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
