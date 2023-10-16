package fittrack;

public class UserProfile {
    private double height;
    private double weight;

    public UserProfile(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    public String toString() {
        return "Height: " + this.height + "\nWeight: " + this.weight;
    }

}
