package fittrack;

import java.text.DecimalFormat;
import fittrack.data.Height;
import fittrack.data.Weight;
import fittrack.data.Calories;

public class UserProfile {
    private final DecimalFormat df = new DecimalFormat("0.00");
    private Height height;
    private Weight weight;
    private Calories dailyCalorieLimit;
    private double bmi;

    public UserProfile() {
    }

    public UserProfile(Height height, Weight weight, Calories dailyCalorieLimit) {
        setHeight(height);
        setWeight(weight);
        setDailyCalorieLimit(dailyCalorieLimit);
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public Calories getDailyCalorieLimit() {
        return dailyCalorieLimit;
    }

    public void setDailyCalorieLimit(Calories dailyCalorieLimit) {
        this.dailyCalorieLimit = dailyCalorieLimit;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi() {
        this.bmi = calculateBmi();
    }

    // TODO: change to private and call this method in the setter methods
    public double calculateBmi() {
        double heightInMetres = height.getHeight() / 100;
        bmi = Double.parseDouble(df.format(weight.getWeight() / Math.pow(heightInMetres, 2)));
        return bmi;
    }

    public String toString() {
        return "Height: " + height.toString() + "\n" +
                "Weight: " + weight.toString() + "\n" +
                "Daily calorie limit: " + dailyCalorieLimit.toString() + "\n" +
                "BMI: " + this.bmi;
    }
}
