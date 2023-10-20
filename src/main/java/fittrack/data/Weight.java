package fittrack.data;

import java.util.Objects;

public class Weight {
    private double weight;

    public Weight(double weight) {
        setWeight(weight);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Weight weight1 = (Weight) o;
        return Double.compare(weight, weight1.weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }

    @Override
    public String toString() {
        return weight + "kg";
    }
}
