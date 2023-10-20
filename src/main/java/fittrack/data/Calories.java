package fittrack.data;

import java.util.Objects;

public class Calories {
    private double calories;

    public Calories(double calories) {
        setCalories(calories);
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calories calories1 = (Calories) o;
        return Double.compare(calories, calories1.calories) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(calories);
    }

    @Override
    public String toString() {
        return calories + "kcal";
    }
}
