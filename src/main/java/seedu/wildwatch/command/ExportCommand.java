package seedu.wildwatch.command;

import seedu.wildwatch.entry.Entry;
import seedu.wildwatch.entry.EntryList;
import seedu.wildwatch.error.FilenameChecker;
import seedu.wildwatch.exception.InvalidInputException;
import seedu.wildwatch.storage.EntryToStringConverter;
import seedu.wildwatch.storage.ExistenceChecker;
import seedu.wildwatch.storage.FileCreator;
import seedu.wildwatch.ui.FilePrinter;
import seedu.wildwatch.ui.LinePrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Command class for exporting all current entries as a CSV file.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final Pattern EXPORT_COMMAND_FORMAT =
            Pattern.compile("export(?:\\s+(?<filename>\\S+))?\\s*");

    public static final String DEFAULT_FILENAME = "WildWatch.csv";

    private static final String[] ALL_COLUMNS = new String[] { "date", "species", "name", "remark" };

    private String filename;

    /**
     * Constructs a new {@code ExportCommand}.
     *
     * @param file Name of file to set on {@code filename}.
     */
    public ExportCommand(String file) {
        filename = file;
    }

    /**
     * Constructs a new {@code ExportCommand}, with filename set
     * to {@code DEFAULT_FILENAME}.
     */
    public ExportCommand() {
        this(DEFAULT_FILENAME);
    }

    @Override
    public void execute() throws InvalidInputException {
        if (EntryList.isArrayEmpty()) {
            throw new InvalidInputException("No entries to write to csv.");
        }

        boolean fileExists = ExistenceChecker.checkFileExistence(filename);

        if (fileExists) {
            FilePrinter.fileExistMessagePrinter();
            boolean canReplaceFile = canReplaceFile();
            createFileAccordingly(canReplaceFile);
        } else { //File does not exist
            FilePrinter.noFileMessagePrinter();
            System.out.print(System.lineSeparator());
            createFile();
        }

        ArrayList<Entry> entries = EntryList.getArray();

        ArrayList<String> columnsToInclude = getColumnsToInclude();
        if (columnsToInclude.isEmpty()) {
            throw new InvalidInputException("You need to select at least one column to include in the csv.");
        }

        String header = "id";
        for (String column : columnsToInclude) {
            header = String.join(",", header, column);
        }

        try {
            FileWriter writer = new FileWriter(filename);

            writer.write(header + "\n");
            for (int i = 0; i < EntryList.getArraySize(); i++) {
                writer.write(EntryToStringConverter.toCsvString(entries.get(i), i+1, columnsToInclude));
            }
            writer.close();
        } catch (IOException e) {
            throw new InvalidInputException("Error writing to file.");
        }

        FilePrinter.updateFileMessagePrinter();
        FilePrinter.csvCreationSuccess(filename);
    }

    private void createFile() throws InvalidInputException {
        try {
            FileCreator.createFile(filename);
        } catch (IOException e) {
            throw new InvalidInputException("Unable to create file.");
        }
    }

    private void createFileAccordingly(boolean canReplaceFile) throws InvalidInputException {
        if (!canReplaceFile) {
            getNewFilename();
            createFile();
        } else {
            new File(filename).delete();
            createFile();
        }
    }


    private void getNewFilename() throws InvalidInputException {
        File file;
        boolean fileExists = false;
        do {
            filename = setNewFilename();
            LinePrinter.printHorizontalLines();
            file = new File(filename);
            fileExists = file.exists();
            if (fileExists) {
                FilePrinter.fileAlreadyExistsMessagePrinter();
                System.out.print(System.lineSeparator());
            }
        } while (fileExists);
    }

    private String setNewFilename() throws InvalidInputException {
        String newFilename;
        Scanner scanner = new Scanner(System.in);

        boolean isValidFilename = false;
        do {
            FilePrinter.newFilenamePromptPrinterOne();
            FilePrinter.newFilenamePromptPrinterTwo();
            LinePrinter.printHorizontalLines();

            newFilename = scanner.nextLine().trim();

            if (newFilename.equals("q/")) {
                LinePrinter.printHorizontalLines();
                throw new InvalidInputException("Exiting export command...");
            }

            isValidFilename = FilenameChecker.isValidCsvFilenameChecker(newFilename);
            if (!isValidFilename) {
                FilePrinter.invalidFilenameMessagePrinter();
            }
        } while (!isValidFilename);

        return newFilename;
    }

    /**
     * Confirms with user if file can be replaced. Returns true if user
     * accepts, and false otherwise.
     *
     * @return True if user allows file to be replaced, else false.
     */
    private boolean canReplaceFile() {
        Scanner scanner = new Scanner(System.in);
        final String confirmationMessage =
                String.format("%s already exists. Would you like to replace it? (Y/N)", filename);

        return doesUserApprove(scanner, confirmationMessage);
    }

    /**
     * Reads user input in a loop until "Y" or "N" received.
     *
     * @param scanner Scanner used to read user input
     * @param confirmationMessage Message to prompt user for input
     * @return true if user inputs "Y", false if user inputs "N"
     */
    private boolean doesUserApprove(Scanner scanner, String confirmationMessage) {
        do {
            System.out.print(confirmationMessage);
            System.out.print(": ");
            String confirmation = scanner.nextLine().trim();

            switch (confirmation.toLowerCase()) {
            case ("y"):
                LinePrinter.printHorizontalLines();
                return true;
            case "n":
                LinePrinter.printHorizontalLines();
                return false;
            default:
                LinePrinter.printHorizontalLines();
                FilePrinter.unrecognizedInputMessagePrinter();
            }
        } while (true);
    }

    private ArrayList<String> getColumnsToInclude() {
        ArrayList<String> columnsToInclude = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        String includeAllColumnsMessage = "Would you like to include all columns? (Y/N)";
        if (doesUserApprove(scanner, includeAllColumnsMessage)) {
            Collections.addAll(columnsToInclude, ALL_COLUMNS);

            return columnsToInclude;
        }

        FilePrinter.selectColumnMessagePrinter();

        for (String column : ALL_COLUMNS) {
            String confirmationMessage = String.format("Would you like to include %s in your csv? (Y/N)", column);

            boolean shouldIncludeColumn = doesUserApprove(scanner, confirmationMessage);
            if (shouldIncludeColumn) {
                columnsToInclude.add(column);
            }
        }

        return columnsToInclude;
    }
}
