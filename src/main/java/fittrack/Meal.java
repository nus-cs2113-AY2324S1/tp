package fittrack;

public class Meal {
    private String mealName;
    private float calories;

    public Meal(String mealName, float calories) {
        this.mealName = mealName;
        this.calories = calories;
    }

    public float getCalories() {
        return this.calories;
    }

    public String getMealName() {
        return mealName;
    }

    @Override
    public String toString() {
        return("Meal name: " + this.mealName + "\nCalories: " + this.calories);
    }

}
