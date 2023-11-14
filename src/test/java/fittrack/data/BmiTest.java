package fittrack.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("AssertBetweenInconvertibleTypes")
class BmiTest {

    @Test
    void getCategory_bmi18o4_underweight() {
        Assertions.assertEquals("Underweight", new Bmi(18.4).getCategory());
    }

    @Test
    void getCategory_bmi21o4_normalWeight() {
        Assertions.assertEquals("Normal weight", new Bmi(21.4).getCategory());
    }

    @Test
    void getCategory_bmi29o4_overweight() {
        Assertions.assertEquals("Overweight", new Bmi(29.4).getCategory());
    }

    @Test
    void getCategory_bmi35o4_obese() {
        Assertions.assertEquals("Obese", new Bmi(35.4).getCategory());
    }

    @Test
    void equals_same_true() {
        Assertions.assertEquals(new Bmi(21.1), new Bmi(21.1));
        Assertions.assertEquals(new Bmi(40.0), new Bmi(new Height(150), new Weight(90)));
    }

    @Test
    void equals_different_false() {
        Assertions.assertNotEquals(null, new Bmi(21.1));
        Assertions.assertNotEquals(new Calories(21.1), new Bmi(21.1));
        Assertions.assertNotEquals(new Bmi(11.1), new Bmi(21.1));
    }

    @Test
    void toString_bmi21o041_str21o04() {
        Assertions.assertEquals("21.04", new Bmi(21.041).toString());
    }
}
