package seedu.duke.exerciselog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ExerciseTest {
    public static void main(String[] args) {
        ExerciseTest test = new ExerciseTest();
        test.initializeExerciseTest();
        test.initializeExerciseTest2();
        test.testEqualsMethod();
        test.testToStringMethod();
        test.setNewExerciseNameTest();
        test.setNewCaloriesBurnedTest();
        assert true;
    }

    public void initializeExerciseTest() {
        Exercise testExercise = new Exercise("Running", 280);
        assertEquals(testExercise.getExerciseName(), "Running");
        assertEquals(testExercise.getCaloriesBurned(), 280);
    }

    public void initializeExerciseTest2() {
        Exercise testExercise = new Exercise("Running", 280);
        assertNotEquals(testExercise.getExerciseName(), "running");
        assertNotEquals(testExercise.getCaloriesBurned(), 2800);
    }

    public void testEqualsMethod() {
        Exercise testExercise = new Exercise("Walking", 100);
        assertEquals(testExercise, new Exercise("Walking", 100));
        assertNotEquals(testExercise, new Exercise("Swimming", 100));
    }

    public void testToStringMethod() {
        Exercise testExercise = new Exercise("Golfing", 76);
        assertEquals(testExercise.toString(), "Exercise: Golfing, Calories Burned: 76 Calories\n");
    }

    public void setNewExerciseNameTest() {
        Exercise testExercise = new Exercise("Floorball", 143);
        testExercise.setExerciseName("hockey");
        assertEquals(testExercise.getExerciseName(), "hockey");
    }

    public void setNewCaloriesBurnedTest() {
        Exercise testExercise = new Exercise("Football", 309);
        testExercise.setCaloriesBurned(229);
        assert testExercise.getCaloriesBurned() == 229;
    }
}
