package fittrack;

public class Workout {
    private String name;
    private double calories;

    public Workout(String name, float calories) {
        this.name = name;
        this.calories = calories;
    }

    public double getCalories() {

        assert calories != 0;
        return calories;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Workout name: " + this.name + "\nCalories: " + this.calories;
    }

}
