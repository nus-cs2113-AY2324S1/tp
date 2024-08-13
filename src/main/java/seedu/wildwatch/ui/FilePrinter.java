package seedu.wildwatch.ui;

public class FilePrinter {
    /**
     * Prints out appropriate message when file is corrupt
     */
    public static void corruptFileMessagePrinter() {
        System.out.println("File is corrupted.\nUnable to read file");
    }

    /**
     * Prints out message after a new file has been created.
     */
    public static void createNewFileMessagePrinter(String filename) {
        System.out.println("Creating new file...\nNew empty file: \"" +
                filename + "\" created successfully.");
    }

    /**
     * Prints out appropriate message when creation of new file failed
     */
    public static void fileCreationFailMessagePrinter() {
        System.out.println("File creation failed.");
    }

    /**
     * Prints out appropriate message when file already exists.
     */
    public static void fileExistMessagePrinter() {
        System.out.println("File already exists.");
    }

    /**
     * Prints out appropriate message when opening a file.
     */
    public static void openingFileMessagePrinter() {
        System.out.println("Opening existing file...\n");
    }

    /**
     * Prints out appropriate message when no file found.
     * Home directory meaning where WildWatch.jar file is residing.
     */
    public static void noFileMessagePrinter() {
        System.out.println("File does not exist.");
    }

    /**
     * Prints out appropriate message when checking if file exists
     */
    public static void checkingIfFileExistsMessagePrinter(String filename) {
        System.out.println("Checking if the file: \"" + filename + "\" already exists...");
    }

    /**
     * Prints out appropriate message when entries are loaded
     */
    public static void entriesLoadedMessagePrinter() {
        System.out.println("These are entries loaded from before: ");
    }

    /**
     * Prints out appropriate message to ask for the new filename.
     */
    public static void newFilenamePromptPrinterOne() {
        System.out.println("What would you like to name your new file?");
    }

    /**
     * Prints out appropriate message to ask for the new filename.
     */
    public static void newFilenamePromptPrinterTwo() {
        System.out.println("Input a new filename with no spaces, that ends with \".csv\"." +
                "\nInput q/ if you would like to quit: ");
    }

    /**
     * Prints out appropriate message when input is invalid.
     */
    public static void unrecognizedInputMessagePrinter() {
        System.out.println("Unrecognized input! Please ensure that you only respond with Y or N.");
    }

    /**
     * Prints out appropriate message to ask for the column selection.
     */
    public static void selectColumnMessagePrinter() {
        System.out.println("Please select the columns you would like to include in your csv:");
    }

    /**
     * Prints out appropriate message when the filename is invalid.
     */
    public static void invalidFilenameMessagePrinter() {
        System.out.print("Filename is invalid! ");
    }

    public static void csvCreationSuccess(String filename) {
        System.out.printf("Your data has been successfully saved to %s.\n", filename);
    }

    public static void updateFileMessagePrinter() {
        System.out.println("File has been updated successfully!");
    }

    public static void fileAlreadyExistsMessagePrinter() {
        System.out.println("File of the name already exists.\nPlease input a different name.");
    }
}
