package seedu.cafectrl.data;

public class CurrentDate {
    private int currentDay;

    public CurrentDate() {
        currentDay = 0;
    }

    public void nextDay() {
        currentDay += 1;
    }

    public void previousDay() {
        currentDay -= 1;
    }

    public int getCurrentDay() {
        return currentDay;
    }
}
