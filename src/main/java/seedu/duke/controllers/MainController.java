package seedu.duke.controllers;

import seedu.duke.storage.StorageManager;
import seedu.duke.models.schema.Student;
import seedu.duke.models.schema.CommandManager;
import seedu.duke.models.schema.UserCommand;
import seedu.duke.models.schema.Schedule;
import seedu.duke.utils.Parser;
import seedu.duke.utils.exceptions.CorruptedFileException;
import seedu.duke.utils.exceptions.MissingFileException;
import seedu.duke.utils.exceptions.TimetableUnavailableException;
import seedu.duke.views.Ui;

import java.io.IOException;
import java.util.ArrayList;

import static seedu.duke.controllers.ModuleServiceController.validateMajorInput;

import static seedu.duke.storage.StorageManager.saveTimetable;
import static seedu.duke.utils.Utility.detectInternet;
import static seedu.duke.utils.Utility.saveStudentData;
import static seedu.duke.views.Ui.displayWelcome;
import static seedu.duke.views.CommandLineView.displayReady;
import static seedu.duke.views.Ui.displayGoodbye;
import static seedu.duke.views.CommandLineView.displayGetMajor;
import static seedu.duke.views.CommandLineView.displayGetYear;
import static seedu.duke.views.Ui.showLoadingAnimation;
import static seedu.duke.views.Ui.stopLoadingAnimation;

import static seedu.duke.storage.StorageManager.saveSchedule;


public class MainController {
    private final Parser parser;
    private final Student student;
    private final CommandManager commandManager;
    private StorageManager storageManager;

    private final Ui ui;

    public MainController() {
        this.commandManager = new CommandManager();
        this.parser = new Parser();
        this.student = new Student();
        this.ui = new Ui();
    }

    //@@author ryanlohyr
    /**
     * Starts the application, guiding the user through its execution.
     * This method performs the following steps:
     * 1. Display a welcome message to the user.
     * 2. Initialize user-related data or settings.
     * 3. Display a message indicating that the application is ready for input.
     * 4. Handle user input until an exit command is given.
     * 5. Display a goodbye message when the application is finished.\
     *
     */
    public void start() throws IOException {
        displayWelcome();
        detectInternet();
        initialiseUser();
        displayReady();
        handleUserInputTillExitCommand();
        saveStudentData(storageManager,student);
        displayGoodbye();
    }

    //@@author SebasFok
    /**
     * Initializes the user by attempting to load data from save files. If successful, sets the user details,
     * schedule, and timetable. If loading fails or save files are missing, creates new save files, prompts for
     * user details, and resets the schedule and timetable.
     *
     * @throws IOException If an IO error occurs during file operations.
     */
    public void initialiseUser() throws IOException {

        storageManager = new StorageManager();
        try {
            System.out.println("Attempting to look for your data file...");

            showLoadingAnimation();

            // Load name, major and year from studentDetails.txt file
            ArrayList<String> studentDetails = storageManager.loadStudentDetails();

            // Set name, major and year from loaded data, throws exception if file is corrupted.
            setStudentDetails(studentDetails);

            // Load and set schedule from schedule.txt file
            student.setSchedule(storageManager.loadSchedule());

            // Load timetable from timetable.txt file
            try {
                student.updateTimetable();
                storageManager.addEventsToStudentTimetable(storageManager.loadTimetable(student), student);

            } catch (TimetableUnavailableException e) {
                // no modules in current sem, do nothing
            }


            stopLoadingAnimation();

            String dataRetrievalSuccess = "Data successfully retrieved";
            String welcomeBackMessage = "Welcome back " + student.getName() + ", you are currently in "
                    + student.getYear() +
                    " studying " + student.getMajor();
            ui.printMessage(dataRetrievalSuccess, welcomeBackMessage);

            return;

        } catch (MissingFileException e) {
            stopLoadingAnimation();
            System.out.println("Looks like you're new, new save files will be created.");

        } catch (CorruptedFileException e) {
            stopLoadingAnimation();
            ui.printStorageError();
        }

        resetStorageData();

        System.out.println("New save files successfully created!");
    }

    public void resetStorageData() throws IOException {
        storageManager.createUserStorageFile();

        String userInput;

        do {
            stopLoadingAnimation();
            userInput = ui.getUserCommand("Please enter your name: ").trim();
        } while (!parser.checkNameInput(userInput, commandManager.getListOfCommandNames()));
        student.setName(userInput);

        // Get and set student's major
        displayGetMajor(student.getName());
        do {
            userInput = ui.getUserCommand("Please enter major: ").trim();
        } while (!validateMajorInput(userInput));
        student.setFirstMajor(userInput);

        // Get and set student's year
        displayGetYear();
        do {
            userInput = ui.getUserCommand("Please enter your current academic year: ").trim();
        } while (!Parser.isValidAcademicYear(userInput.toUpperCase()));
        student.setYear(userInput.toUpperCase());
        storageManager.saveStudentDetails(student);

        //get blank schedule.txt
        student.setSchedule(new Schedule());
        saveSchedule(student);

        //get blank timetable.txt
        //requires student details
        try {
            student.updateTimetable();
        } catch (TimetableUnavailableException ignoredError) {
            //should be unavailable
        }
        saveTimetable(student);
    }

    //@@author SebasFok
    /**
     * Sets the student details based on the provided list of information, such as name, major, and year.
     *
     * @param studentDetails The list of student information containing name, major, and year in this order.
     * @throws CorruptedFileException If the provided student information is null, has an incorrect number of elements,
     *                                or if any of the information is invalid.
     */
    private void setStudentDetails(ArrayList<String> studentDetails) throws CorruptedFileException {
        int correctNumOfStudentInfo = 3;

        if (studentDetails == null || studentDetails.size() != correctNumOfStudentInfo) {
            throw new CorruptedFileException();
        }
        // Check if name is valid and set if yes
        if (!parser.checkNameInput(studentDetails.get(0), commandManager.getListOfCommandNames())) {
            throw new CorruptedFileException();
        }
        student.setName(studentDetails.get(0));

        // Check if major is valid and set if yes
        if (!validateMajorInput(studentDetails.get(1))) {
            throw new CorruptedFileException();
        }
        student.setFirstMajor(studentDetails.get(1));

        //Check if year is valid and set if yes
        if (!Parser.isValidAcademicYear(studentDetails.get(2).toUpperCase())) {
            throw new CorruptedFileException();
        }
        student.setYear(studentDetails.get(2).toUpperCase());
    }

    public void handleUserInputTillExitCommand() {

        UserCommand currentUserCommand = new UserCommand();
        while (!currentUserCommand.isBye()) {
            String userInput = ui.getUserCommand("Input command here: ");
            currentUserCommand = new UserCommand(userInput.replace("\r", ""));
            if (currentUserCommand.isValid() && !currentUserCommand.isBye()) {
                currentUserCommand.processCommand(student);
            }
        }
    }



}
