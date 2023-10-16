package fittrack;

public class UserProfile {
    private String name;
    private double height;
    private double weight;
    private double dailyCalorieSurplusLimit;

    public UserProfile() {
        this(null, 0, 0, 0);
    }

    public UserProfile(String name, double height, double weight, double dailyCalorieSurplusLimit) {
        setName(name);
        setHeight(height);
        setWeight(weight);
        setDailyCalorieSurplusLimit(dailyCalorieSurplusLimit);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getDailyCalorieSurplusLimit() {
        return dailyCalorieSurplusLimit;
    }

    public void setDailyCalorieSurplusLimit(double dailyCalorieSurplusLimit) {
        this.dailyCalorieSurplusLimit = dailyCalorieSurplusLimit;
    }
}
