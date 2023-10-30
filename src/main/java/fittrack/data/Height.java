package fittrack.data;

import java.util.Objects;

public class Height {
    public final double value;

    public Height(double height) {
        assert height > 0;
        this.value = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Height height1 = (Height) o;
        return Double.compare(value, height1.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.format("%.1fcm", value);
    }

    public double calculateIdealWeight(){
        return 50 + (0.91 * (value - 152.4));
    }
}
