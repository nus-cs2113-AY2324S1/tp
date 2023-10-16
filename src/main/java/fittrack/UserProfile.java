package fittrack;

public class UserProfile {
    private double height;
    private double weight;
    private double dailyCalorieLimit;

    public UserProfile() {
        this(0, 0, 0);
    }

    public UserProfile(double height, double weight, double dailyCalorieLimit) {
        setHeight(height);
        setWeight(weight);
        setDailyCalorieLimit(dailyCalorieLimit);
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

    public String toString() {
        return "Height: " + this.height + "\n" +
                "Weight: " + this.weight + "\n" +
                "Daily calorie limit: " + this.dailyCalorieLimit;
    }
}
