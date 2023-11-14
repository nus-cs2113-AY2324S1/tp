package seedu.duke.models.schema;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.utils.exceptions.InvalidTimetableUserCommandException;
import seedu.duke.utils.exceptions.TimetableUnavailableException;
import seedu.duke.views.TimetableView;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.duke.views.TimetableUserGuideView.printTTModifySimpleLessonGuide;

class TimetableUserCommandTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Student student = new Student();
    private UserCommand currentUserCommand = new UserCommand();

    @BeforeEach
    public void setUpStreams() {
        this.student = new Student();
        student.setName("Janelle");
        student.setYear("Y2/S1");
        student.setFirstMajor("CEG");
        System.setOut(new PrintStream(outputStream));

    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void partialTestTimetableModify_perfectInputs_expectTimetable() throws InvalidTimetableUserCommandException,
            TimetableUnavailableException {
        System.setOut(originalOut);
        String addUserInputs = "add cs1010 3";
        currentUserCommand = new UserCommand(addUserInputs);
        if (currentUserCommand.isValid() && !currentUserCommand.isBye()) {
            currentUserCommand.processCommand(student);
        }

        System.setOut(new PrintStream(outputStream));
        student.updateTimetable();

        TimetableUserCommand currentTimetableCommand = new TimetableUserCommand(student,
                student.getTimetable().getCurrentSemesterModulesWeekly(), "cs1010 lecture 9 2 Monday");
        currentTimetableCommand.processTimetableCommand(student.getTimetable().getCurrentSemesterModulesWeekly());
        if (student.getTimetable().timetableViewIsAvailable()) {
            TimetableView.printTimetable(student.getTimetable().getCurrentSemesterModulesWeekly());
        } else {
            printTTModifySimpleLessonGuide("Timetable view is unavailable as modules in your " +
                    "current semester have no lessons yet.");
        }

        // Capture the printed output
        String printedOutput = outputStream.toString().trim();
        printedOutput = printedOutput
                .replaceAll("\r\n", "\n")
                .replaceAll("\r", "\n");


        String expectedOutput = "------------------------------------------------------------\n" +
                "| DAY       | TIMETABLE                                    |\n" +
                "------------------------------------------------------------\n" +
                "| Monday    | CS1010 Lecture (9am-11am)                    |\n" +
                "------------------------------------------------------------";
        expectedOutput = expectedOutput
                .replaceAll("\r\n", "\n")
                .replaceAll("\r", "\n");

        assertEquals(expectedOutput, printedOutput);
    }

    @Test
    void partialTestTimetableModify_perfectInput_expectTimetableErrorMessage() throws TimetableUnavailableException {
        System.setOut(originalOut);
        String addUserInputs = "add cs1010 3";
        currentUserCommand = new UserCommand(addUserInputs);
        if (currentUserCommand.isValid() && !currentUserCommand.isBye()) {
            currentUserCommand.processCommand(student);
        }

        System.setOut(new PrintStream(outputStream));
        student.updateTimetable();

        assertThrows(InvalidTimetableUserCommandException.class,
                () -> badInput("cs1010 lecture 9 2 Mon"));
    }


    @Test
    void partialTestTimetableModify_badLessonInput_expectTimetableErrorMessage() throws TimetableUnavailableException {
        System.setOut(originalOut);
        String addUserInputs = "add cs1010 3";
        currentUserCommand = new UserCommand(addUserInputs);
        if (currentUserCommand.isValid() && !currentUserCommand.isBye()) {
            currentUserCommand.processCommand(student);
        }

        System.setOut(new PrintStream(outputStream));
        student.updateTimetable();

        assertThrows(InvalidTimetableUserCommandException.class,
                () -> badInput("cs1010 lect 9 2 Monday"));
    }

    public void badInput(String timetableUserInput) throws InvalidTimetableUserCommandException {
        TimetableUserCommand currentTimetableCommand = new TimetableUserCommand(student,
                student.getTimetable().getCurrentSemesterModulesWeekly(), timetableUserInput);
        currentTimetableCommand.processTimetableCommand(student.getTimetable().getCurrentSemesterModulesWeekly());
    }

}
