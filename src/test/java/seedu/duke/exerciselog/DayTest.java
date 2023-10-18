package seedu.duke.exerciselog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DayTest {
    public static void main(String[] args) {
        DayTest test = new DayTest();
        test.containsExercisesTest();
        test.getExerciseTest();
        test.toStringTest();
    }

    public void containsExercisesTest() {
        Day day = new Day();
        assertFalse(day.containsExercises());
        day.addExercise("Swimming", 0);
        assert day.containsExercises();
        day.removeExercise("Swimming", 0);
        assert !day.containsExercises();
    }

    public void getExerciseTest() {
        Day day = new Day();
        day.addExercise("Basketball", 197);
        assertEquals(day.getExercise(1), new Exercise("Basketball", 197));
        assert day.getNumberOfExercises() == 1;
    }

    public void toStringTest() {
        Day day = new Day();
        assert day.toString().equals("\tNO EXCERCISES FOR THIS DAY!\n");
        System.out.println(day);
        day.addExercise("chicken", 28);
        System.out.println(day);
        assert day.toString().equals("\tExercise: chicken, Calories Burned: 28 Calories\n");
    }
}
