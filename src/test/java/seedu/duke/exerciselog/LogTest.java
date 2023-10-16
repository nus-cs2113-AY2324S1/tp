package seedu.duke.exerciselog;

public class LogTest {

    public static void main(String[] args) {
        Log exerciseLog = new Log();
        exerciseLog.addExercise(1, 6, "Working out", 101);
        System.out.println(exerciseLog.getMonth(1));
    }
}
