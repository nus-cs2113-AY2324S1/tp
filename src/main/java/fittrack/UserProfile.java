package fittrack;

import java.text.DecimalFormat;
import fittrack.data.Height;
import fittrack.data.Weight;
import fittrack.data.Calories;
import fittrack.data.Bmi;

public class UserProfile {
    private final DecimalFormat df = new DecimalFormat("0.00");
    private Height height;
    private Weight weight;
    private Calories dailyCalorieLimit;
    private Bmi bmi;

    public UserProfile() {
        this(new Height(1), new Weight(0), new Calories(0));
    }

    public UserProfile(Height height, Weight weight, Calories dailyCalorieLimit) {
        this.height = height;
        this.weight = weight;
        this.dailyCalorieLimit = dailyCalorieLimit;
        updateBmi();
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
        updateBmi();
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
        updateBmi();
    }

    public Calories getDailyCalorieLimit() {
        return dailyCalorieLimit;
    }

    public void setDailyCalorieLimit(Calories dailyCalorieLimit) {
        this.dailyCalorieLimit = dailyCalorieLimit;
    }

    public Bmi getBmi() {
        return bmi;
    }

    public String getBmiCategory() {
        return bmi.getCategory();
    }

    private void updateBmi() {
        this.bmi = calculateBmi(height, weight);
    }

    public Bmi calculateBmi(Height height, Weight weight) {
        assert (height != null && height.value > 0 && weight != null);
        double heightInMetres = height.value / 100;
        return new Bmi(weight.value / heightInMetres / heightInMetres);
    }

    public String toString() {
        return "Height: " + height.toString() + "\n" +
                "Weight: " + weight.toString() + "\n" +
                "Daily calorie limit: " + dailyCalorieLimit.toString() + "\n" +
                "BMI: " + bmi.toString();
    }
}
