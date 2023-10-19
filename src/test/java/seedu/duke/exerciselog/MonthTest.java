package seedu.duke.exerciselog;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonthTest {
    public static void main(String[] args) {
        MonthTest test = new MonthTest();
        test.createMonthTest();
        test.addExerciseTest();
        test.removeExerciseTest();
    }

    public void createMonthTest() {
        Month month = new Month(31, "JAN");
        assert month.getName().equals("JAN");
        assert month.getNumberOfDays() == 31;
    }

    public void addExerciseTest() {
        Month month = new Month(28, "FEB");
        month.addExercise(3, "Volleyball", 79);
        assertEquals(month.getNumberOfExercisesForDay(3), 1);
        month.addExercise(14, "Running", 121);
        assert month.getNumberOfExercisesForDay(14) == 1;
        assert month.getTotalNumberOfExercises() == 2;
    }

    public void removeExerciseTest() {
        Month month = new Month(28, "FEB");
        month.addExercise(3, "Volleyball", 79);
        month.addExercise(14, "Running", 121);
        month.removeExercise(3, "Volleyball", 79);
        assert month.getNumberOfExercisesForDay(3) == 0;
        assert month.getTotalNumberOfExercises() == 1;
    }
}
