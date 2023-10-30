package fittrack.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("AssertBetweenInconvertibleTypes")
class BmiTest {

    @Test
    void getCategory_18o4_underweight() {
        assertEquals("Underweight", new Bmi(18.4).getCategory());
    }

    @Test
    void getCategory_21o4_normalWeight() {
        assertEquals("Normal weight", new Bmi(21.4).getCategory());
    }

    @Test
    void getCategory_29o4_overweight() {
        assertEquals("Overweight", new Bmi(29.4).getCategory());
    }

    @Test
    void getCategory_35o4_obese() {
        assertEquals("Obese", new Bmi(35.4).getCategory());
    }

    @Test
    void equals_same_true() {
        assertEquals(new Bmi(21.1), new Bmi(21.1));
        assertEquals(new Bmi(40.0), new Bmi(new Height(150), new Weight(90)));
    }

    @Test
    void equals_different_false() {
        assertNotEquals(null, new Bmi(21.1));
        assertNotEquals(new Calories(21.1), new Bmi(21.1));
        assertNotEquals(new Bmi(11.1), new Bmi(21.1));
    }

    @Test
    void toString_21o041_21o04() {
        assertEquals("21.04", new Bmi(21.041).toString());
    }
}