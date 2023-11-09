package seedu.duke.calendar;

import java.time.LocalDateTime;

public class Goal extends Event{

    private String name;
    private LocalDateTime to;
    private int goal;
    private int completed;

    public Goal(String name, LocalDateTime to, int goal, int completed) {
        super(name, null, to);
        this.name = name;
        this.to = to;
        this.goal = goal;
        this.completed = completed;
    }

    public int getGoal() {
        return goal;
    }

    public Object getCompleted() {
        return completed;
    }

    public void flashcardCompleted() {
        if (LocalDateTime.now().isBefore(to)) {
            completed += 1;
        }
    }

    @Override
    public String getFrom() {
        return "null";
    }

    @Override
    public String toString() {
        return "Goal '" + name + '\'' + " review " + goal + " flashcards by: " + to + " (Reviewed: " + completed + ")";
    }

}
