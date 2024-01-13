package seedu.wildwatch.ui;

public class SummaryCommandPrinter {
    public static void printSummarySpecieMessage() {
        System.out.println("Here are the species recorded: ");
    }

    public static void printSummaryNameMessage(String speciesName) {
        System.out.println("Here is the data for the " + speciesName + ", grouped by their names");
    }
}
