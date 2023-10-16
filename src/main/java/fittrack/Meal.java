package fittrack;

public class Meal {
    private String name;
    private double calories;

    public Meal(String name, float calories) {
        this.name = name;
        this.calories = calories;
    }

    public double getCalories() {
        return calories;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Meal name: " + this.name + "\nCalories: " + this.calories;
    }

}
