package seedu.duke.exerciselog;

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
        assert testExercise.getExerciseName().equals("Running");
        assert testExercise.getCaloriesBurned() == 280;
    }

    public void initializeExerciseTest2() {
        Exercise testExercise = new Exercise("Running", 280);
        assert !testExercise.getExerciseName().equals("running");
        assert !(testExercise.getCaloriesBurned() == 2800);
    }

    public void testEqualsMethod() {
        Exercise testExercise = new Exercise("Walking", 100);
        assert testExercise.equals(new Exercise("Walking", 100));
        assert !(testExercise.equals(new Exercise("Swimming", 100)));
    }

    public void testToStringMethod() {
        Exercise testExercise = new Exercise("Golfing", 76);
        assert testExercise.toString().equals("Exercise: Golfing, Calories Burned: 76 Calories\n");
    }

    public void setNewExerciseNameTest() {
        Exercise testExercise = new Exercise("Floorball", 143);
        testExercise.setExerciseName("hockey");
        assert testExercise.getExerciseName().equals("hockey");
    }

    public void setNewCaloriesBurnedTest() {
        Exercise testExercise = new Exercise("Football", 309);
        testExercise.setCaloriesBurned(229);
        assert testExercise.getCaloriesBurned() == 229;
    }
}
