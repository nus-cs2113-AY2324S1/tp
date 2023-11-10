package seedu.duke.storage;

import seedu.duke.calendar.Event;
import seedu.duke.calendar.Goal;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventStorageParser {

    private static Logger logger; // for logging


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
