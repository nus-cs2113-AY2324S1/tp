package seedu.duke.models.schema;

import seedu.duke.utils.exceptions.InvalidTimetableUserCommandException;

import java.util.ArrayList;

import static seedu.duke.models.schema.Timetable.getIndexOfModuleWeekly;
import static seedu.duke.utils.Parser.removeNulls;
import static seedu.duke.utils.TimetableParser.isModifyValid;
import static seedu.duke.utils.TimetableParser.parseModuleCode;
import static seedu.duke.utils.TimetableParser.isModifyClear;
import static seedu.duke.utils.TimetableParser.parseLessonType;
import static seedu.duke.utils.TimetableParser.parseTime;
import static seedu.duke.utils.TimetableParser.parseDuration;
import static seedu.duke.utils.TimetableParser.parseDay;

//@@author janelleenqi
/**
 * The TimetableUserCommand class represents a user command related to the timetable and provides methods to process
 * and validate the user input.
 */
public class TimetableUserCommand {

    private static final String ERROR_INVALID_NUMBER_OF_ARGUMENTS = "Invalid Number of Arguments";

    private static final int NUMBER_OF_ARGUMENTS_EXIT = 1;
    private static final int NUMBER_OF_ARGUMENTS_CLEAR = 2;
    private static final int NUMBER_OF_ARGUMENTS_LESSON = 5;
    private static final String DELIMITER = " ";

    private Student student;
    private ArrayList<ModuleWeekly> currentSemesterModulesWeekly;
    private String[] arguments;


    /**
     * Constructs a TimetableUserCommand object with the specified student, current semester modules weekly,
     * and user timetable input.
     *
     * @param student                       The student associated with the command.
     * @param currentSemesterModulesWeekly The list of current semester modules weekly.
     * @param userTimetableInput            The user input related to the timetable command.
     * @throws InvalidTimetableUserCommandException If there is an issue with the user command.
     */
    public TimetableUserCommand(Student student, ArrayList<ModuleWeekly> currentSemesterModulesWeekly,
                                String userTimetableInput)
            throws InvalidTimetableUserCommandException {
        this.student = student;
        this.currentSemesterModulesWeekly = currentSemesterModulesWeekly;
        arguments = userTimetableInput.split(DELIMITER);
        cutArguments();
        clearNullArguments();
        //cleanArguments();
    }

    private void cutArguments() throws InvalidTimetableUserCommandException {
        String[] cutArguments = new String[NUMBER_OF_ARGUMENTS_LESSON];

        int currentIndex = 0;
        for (int i = 0; i < arguments.length; i++) {
            arguments[i] = arguments[i].trim();
            if (arguments[i].isEmpty()) {
                continue;
            }
            try {
                cutArguments[currentIndex] = arguments[i];
                currentIndex++;
            } catch (IndexOutOfBoundsException e) {
                // too many arguments
                throw new InvalidTimetableUserCommandException(ERROR_INVALID_NUMBER_OF_ARGUMENTS);
            }
        }

        if (currentIndex == NUMBER_OF_ARGUMENTS_EXIT || currentIndex == NUMBER_OF_ARGUMENTS_CLEAR ||
                currentIndex == NUMBER_OF_ARGUMENTS_LESSON) {
            // shrink arguments

            String[] newCutArguments = new String[currentIndex];
            int newCurrentIndex = 0;
            for (int i = 0; i < cutArguments.length; i++) {
                if (cutArguments[i] == null || cutArguments[i].isEmpty()) {
                    continue;
                }
                newCutArguments[newCurrentIndex] = cutArguments[i];
                newCurrentIndex++;
            }
            arguments = newCutArguments;
            return;
        }
        // invalid number of arguments
        //System.out.println(currentIndex);
        throw new InvalidTimetableUserCommandException(ERROR_INVALID_NUMBER_OF_ARGUMENTS);
    }

    private void clearNullArguments() throws InvalidTimetableUserCommandException {
        String[] argumentsNotNull = removeNulls(arguments);
        if (!isModifyValid(arguments, currentSemesterModulesWeekly)) {
            return;
        }
        arguments = argumentsNotNull;
    }


    /**
     * Processes the timetable command, lessons clear and lessons modification.
     *
     * @param currentSemesterModulesWeekly The list of current semester modules weekly.
     * @throws InvalidTimetableUserCommandException If there is an issue with the user command.
     */
    public void processTimetableCommand(ArrayList<ModuleWeekly> currentSemesterModulesWeekly)
            throws InvalidTimetableUserCommandException {

        String moduleCode = parseModuleCode(arguments[0]);
        int indexOfModuleWeeklyToModify = getIndexOfModuleWeekly(moduleCode, currentSemesterModulesWeekly);
        if (indexOfModuleWeeklyToModify == -1) {
            throw new InvalidTimetableUserCommandException(moduleCode + " does not exist in your schedule.");
        }

        if (isModifyClear(arguments)) {
            currentSemesterModulesWeekly.get(indexOfModuleWeeklyToModify).clearLessons();
            System.out.println("All lessons for selected module are cleared.");
            return;
        }

        processTimetableCommandLesson(currentSemesterModulesWeekly);
    }

    /**
     * Processes the timetable command related to lessons modification.
     *
     * @param currentSemesterModulesWeekly The list of current semester modules weekly.
     * @throws InvalidTimetableUserCommandException If there is an issue with the user command.
     */
    public void processTimetableCommandLesson(ArrayList<ModuleWeekly> currentSemesterModulesWeekly)
            throws InvalidTimetableUserCommandException {

        String moduleCode = parseModuleCode(arguments[0]);
        int indexOfModuleWeeklyToModify = getIndexOfModuleWeekly(moduleCode, currentSemesterModulesWeekly);
        if (indexOfModuleWeeklyToModify == -1) {
            throw new InvalidTimetableUserCommandException(moduleCode + " does not exist in your schedule.");
        }

        String lessonType = parseLessonType(arguments[1]);
        int time = parseTime(arguments[2]);
        int duration = parseDuration(arguments[3]);
        String day = parseDay(arguments[4]);

        switch (lessonType) {
        case "LECTURE": {
            currentSemesterModulesWeekly.get(indexOfModuleWeeklyToModify).addLecture(day,
                    time, duration);
            return;
        }
        case "TUTORIAL": {
            currentSemesterModulesWeekly.get(indexOfModuleWeeklyToModify).addTutorial(day,
                    time, duration);
            return;
        }
        case "LAB": {
            currentSemesterModulesWeekly.get(indexOfModuleWeeklyToModify).addLab(day,
                    time, duration);
            return;
        }
        default: {
            System.out.println("Invalid Command. Please try again!");
        }
        }
    }

    /**
     * Retrieves the arguments of the timetable user command.
     *
     * @return The array of arguments.
     */
    public String[] getArguments() {
        return arguments;
    }

    public void printArguments() {
        for (String argument : arguments) {
            System.out.println(argument);
        }
    }
}
