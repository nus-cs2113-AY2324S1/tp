package fittrack.data;

public class Step {
    private final int steps;
    private final Date date;

    public Step(int steps, Date date) {
        assert steps >= 0 && date != null;

        this.steps = steps;
        this.date = date;
    }

    public String formatToFile() {
        return String.format("%s | %s", steps, date);
    }

    public int getSteps() {
        return steps;
    }

    public Date getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return String.format("[S] %s steps (%s)", steps, date);
    }
}
