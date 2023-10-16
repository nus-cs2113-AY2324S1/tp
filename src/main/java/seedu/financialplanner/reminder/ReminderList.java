package seedu.financialplanner.reminder;
import java.util.ArrayList;
public class ReminderList {
    public static final ReminderList INSTANCE = new ReminderList();
    private ArrayList<Reminder> list = new ArrayList<>();
    private ReminderList() {
    }

    public void addReminder(String type, String date) {
        list.add(new Reminder(type, date));
    }

    public int size() {
        return list.size();
    }

    public Reminder get(int index) {
        return list.get(index);
    }
    public void load(Reminder reminder) {
        list.add(reminder);
    }
    //TODO deleteReminder
}
