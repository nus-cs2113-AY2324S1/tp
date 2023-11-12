package seedu.duke.storage;

import seedu.duke.calendar.Event;
import seedu.duke.calendar.Goal;
import seedu.duke.storage.exceptions.EventFileFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventStorageParser {

    private static Logger logger; // for logging

    /**
     * check the saved file format
     * token length should be 3 or 4
     * if token length is 3, format should be string, localdatetime, localdatetime
     * if token length is 4, format should be string, localdatetime, int, int
     * @param tokens is a split txt line
     * @throws EventFileFormatException
     */
    public static void eventFileChecker(String[] tokens) throws EventFileFormatException {
        if(tokens.length != 3 && tokens.length != 4) {
            throw new EventFileFormatException();
        }

        try {
            LocalDateTime.parse(tokens[1].trim());
            if(tokens.length == 3){
                LocalDateTime.parse(tokens[2].trim());
            }
            if(tokens.length == 4){
                Integer.parseInt(tokens[2].trim());
                Integer.parseInt(tokens[3].trim());
            }
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new EventFileFormatException();
        }
    }


    /**
     * load an event from certain format
     * Tokens includes attributes of Event
     * @param tokens is used to get event name
     * @return Event object
     */
    public static Event loadEvent(String[] tokens){

        logger = Logger.getLogger("event");
        logger.setLevel(Level.WARNING);

        assert tokens.length == 3 || tokens.length == 4: "Token length should be 3 or 4";

        if(tokens.length == 3) {
            String name = tokens[0].trim();
            LocalDateTime from = LocalDateTime.parse(tokens[1].trim());
            LocalDateTime to = LocalDateTime.parse(tokens[2].trim());

            logger.log(Level.INFO, "loaded event");

            return new Event(name, from, to);
        }
        String name = tokens[0].trim();
        LocalDateTime by = LocalDateTime.parse(tokens[1].trim());
        int goal = Integer.parseInt(tokens[2].trim());
        int completed = Integer.parseInt(tokens[3].trim());

        logger.log(Level.INFO, "loaded event");

        return new Goal(name, by, goal, completed);
    }
}
