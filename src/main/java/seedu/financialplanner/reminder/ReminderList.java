package seedu.financialplanner.reminder;

import java.util.ArrayList;
public class ReminderList {
    private static ReminderList reminderList = null;
    public final ArrayList<Reminder> list = new ArrayList<>();
    private ReminderList() {
    }
    public static ReminderList getInstance() {
        if (reminderList == null) {
            reminderList = new ReminderList();
        }
        return reminderList;
    }

    public void load(Reminder reminder) {
        list.add(reminder);
    }
    public void deleteReminder(int index) {
        int existingListSize = list.size();
        int listIndex = index - 1;
        assert listIndex >= 0  && listIndex < existingListSize;
        Reminder toRemove = list.get(listIndex);
        list.remove(listIndex);

    }

    public String toString() {
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            result += String.format("%d. %s\n", i + 1, list.get(i));
        }
        return result;
    }
}
