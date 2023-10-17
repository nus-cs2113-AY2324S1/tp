package seedu.financialplanner.reminder;
import java.util.ArrayList;
public class ReminderList {
    public static final ReminderList INSTANCE = new ReminderList();
    public ArrayList<Reminder> list = new ArrayList<>();
    private ReminderList() {
    }

    public void load(Reminder reminder) {
        list.add(reminder);
    }
    //TODO deleteReminder
}
