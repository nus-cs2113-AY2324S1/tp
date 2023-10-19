package fittrack;

import java.text.DecimalFormat;

public class UserProfile {
    private double height;
    private double weight;
    private double dailyCalorieLimit;
    private static double bmi;
    public final DecimalFormat df = new DecimalFormat("0.00");

    public UserProfile() {
        this(0, 0, 0);
    }

    public UserProfile(double height, double weight, double dailyCalorieLimit) {
        setHeight(height);
        setWeight(weight);
        setDailyCalorieLimit(dailyCalorieLimit);
    }

    public static double getBmi() {
        return bmi;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getDailyCalorieLimit() {
        return dailyCalorieLimit;
    }

    public void setDailyCalorieLimit(double dailyCalorieLimit) {
        this.dailyCalorieLimit = dailyCalorieLimit;
    }

    public void calculateBmi() {
        double heightInMetres = this.height / 100;
        bmi = Double.parseDouble(df.format(this.weight / Math.pow(heightInMetres, 2)));
    }

    public String toString() {
        return "Height: " + this.height + "\n" +
                "Weight: " + this.weight + "\n" +
                "Daily calorie limit: " + this.dailyCalorieLimit + "\n" +
                "BMI: " + bmi;
    }
}
