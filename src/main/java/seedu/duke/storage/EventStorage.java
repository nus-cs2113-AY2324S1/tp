package seedu.duke.storage;

import seedu.duke.calendar.Event;
import seedu.duke.calendar.EventList;
import seedu.duke.calendar.Goal;
import seedu.duke.storage.exceptions.EventFileFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.storage.EventStorageParser.eventFileChecker;


/**
 * storage for Events
 * One storage manages one file
 */
public class EventStorage {
    private static Logger logger; // for logging
    protected String path;

    public EventStorage(String path){
        this.path = path;
        logger = Logger.getLogger("event");
        logger.setLevel(Level.WARNING);
    }


    public boolean isStorageAvailable(){
        File f = new File(this.path);
        return f.exists();
    }

    /**
     * load list of events
     * from this.path
     * @return list of Events
     * @throws FileNotFoundException is used if the file is not in the path
     */
    public EventList loadEvents() throws FileNotFoundException{
        EventList eventList = new EventList(new ArrayList<>());
        File f = new File (this.path);
        Scanner s = new Scanner(f);

        try{
            while(s.hasNext()){
                String[] eventTokens = s.nextLine().split(" \\| ");
                eventFileChecker(eventTokens);
                eventList.addEvent(EventStorageParser.loadEvent(eventTokens));
            }
        } catch (EventFileFormatException e) {
            System.out.println("The flashcard save file is corrupted");
            System.out.println("Automatically making new file");
            eventList = new EventList(new ArrayList<>());
            saveEvents(eventList.getEvents());
        }


        logger.log(Level.INFO, String.format(
                "    There are currently %d events in the save file",
                eventList.getSize()));

        return eventList;

    }

    /**
     * saveEvents method
     * save all events to file
     * @param eventList is used to access the list
     */
    public void saveEvents(ArrayList<Event> eventList) {

        try {
            FileWriter fw = new FileWriter(path);

            for (Event event : eventList) {
                if (event.getClass() == Goal.class) {
                    fw.write(String.format("%s | %s | %s | %s \r\n",
                            event.getName(), event.getTo(), ((Goal) event).getGoal(), ((Goal) event).getCompleted()));
                } else {
                    fw.write(String.format("%s | %s | %s \r\n",
                            event.getName(), event.getFrom(), event.getTo()));
                }
            }
            fw.close();
        } catch (IOException e){
            //System.out.println("Failed to save.");
            logger.log(Level.WARNING, "problem: failed to save");
        }
    }





}
