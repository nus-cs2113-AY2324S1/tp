package seedu.duke.exerciselog;

public class LogTest {
    public static void main(String[] args) {
        LogTest test = new LogTest();
        test.getNumberOfExercisesTest();
        test.addAndRemoveExerciseTest();
    }

    public void getNumberOfExercisesTest() {
        Log log = new Log();
        assert log.getNumberOfExercises() == 0;
        assert log.getNumberOfExercisesForMonth(1) == 0;
        assert log.getNumberOfExercisesForDay(1, 21) == 0;
    }

    public void addAndRemoveExerciseTest() {
        Log log = new Log();
        log.addExercise(6, 7, "Exercise", 8999999);
        log.addExercise(8, 24, "Exercise", 112);
        assert log.getNumberOfExercises() == 2;
        log.removeExercise(6, 7, "Exercise", 8999999);
        assert log.getNumberOfExercises() == 1;
        log.removeExercise(8, 24, "Exercise", 112);
        assert log.getNumberOfExercises() == 0;
    }
}
