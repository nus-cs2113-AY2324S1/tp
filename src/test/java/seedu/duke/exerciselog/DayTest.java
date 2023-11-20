package seedu.duke.exerciselog;

public class DayTest {
    public static void main(String[] args) {
        DayTest test = new DayTest();
        test.containsExercisesTest();
        test.getExerciseTest();
        test.toStringTest();
    }

    public void containsExercisesTest() {
        Day day = new Day();
        assert !day.containsExercises();
        day.addExercise("Swimming", 0);
        assert day.containsExercises();
        day.removeExercise("Swimming", 0);
        assert !day.containsExercises();
    }

    public void getExerciseTest() {
        Day day = new Day();
        day.addExercise("Basketball", 197);
        assert day.getExercise(1).equals(new Exercise("Basketball", 197));
        assert day.getNumberOfExercises() == 1;
    }

    public void toStringTest() {
        Day day = new Day();
        assert day.toString().equals("\tNO EXCERCISES FOR THIS DAY!\n");
        day.addExercise("chicken", 28);
        assert day.toString().equals("\tExercise: chicken, Calories Burned: 28 Calories\n");
    }
}
