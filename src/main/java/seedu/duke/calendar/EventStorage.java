package seedu.duke.calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


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
     * load an event from certain format
     * Tokens includes attributes of Event
     * @param tokens is used to get event name
     * @return Event object
     */
    private Event loadEvent(String[] tokens){

        assert tokens.length == 3: "Token length should be 3";

        String name = tokens[0].trim();
        LocalDateTime from = LocalDateTime.parse(tokens[1].trim());
        LocalDateTime to = LocalDateTime.parse(tokens[2].trim());

        return new Event(name, from, to);
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

        while(s.hasNext()){
            String[] eventTokens = s.nextLine().split(" \\| ");
            eventList.addEvent(loadEvent(eventTokens));
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
                fw.write(String.format("%s | %s | %s \r\n",
                        event.getName(), event.getFrom(), event.getTo()));
            }
            fw.close();
        } catch (IOException e){
            //System.out.println("Failed to save.");
            logger.log(Level.WARNING, "problem: failed to save");
        }
    }





}
