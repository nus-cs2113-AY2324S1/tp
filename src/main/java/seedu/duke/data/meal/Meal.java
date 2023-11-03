package seedu.duke.data.meal;

public class Meal {
    public String name;
    public int calories;
    // public DateTime time;

    public Meal(String name, int calories) throws Exception {
        this.name = name;
        this.calories = calories;
        // this.time = new DateTime(LocalDateTime.now().toString());
    }
}
