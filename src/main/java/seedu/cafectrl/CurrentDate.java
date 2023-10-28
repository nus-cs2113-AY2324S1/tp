package seedu.cafectrl;

public class CurrentDate {
    protected int currentDay;

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

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }
}
