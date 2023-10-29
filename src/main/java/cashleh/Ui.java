package cashleh;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Ui {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final int MIN_SPACE_PER_SIDE = 2;
    private static final int MAX_DESCRIPTION = 60;

    public static String getDateString(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

    private static void printHorizontalLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints the given text sandwiched by two horizontal lines
     * @param text Text to be printed
     */
    public static void printText(String text) {
        printHorizontalLine();
        System.out.println("\t" + text);
        printHorizontalLine();
    }

    /**
     * Prints the given texts sandwiched by two horizontal lines
     * @param texts Texts to be printed in a list
     */
    public static void printMultipleText(String[] texts) {
        printHorizontalLine();
        for (String text : texts) {
            System.out.println("\t" + text);
        }
        printHorizontalLine();
    }

    /**
     * Prints the given texts sandwiched by two horizontal lines
     * @param texts Texts to be printed in a list
     */
    public static void printMultipleText(ArrayList<String> texts) {
        printHorizontalLine();
        for (String text : texts) {
            System.out.println("\t" + text);
        }
        printHorizontalLine();
    }

    /**
     * Responsible for formatting and printing a financial statement based on the provided text data.
     * It computes the total income, total expenditure, and net income and formats the data
     * into a tabular format with headers and footers.
     * 
     * @param texts An array of text data containing the financial statement details in a structured format.
     */
    public static void printStatement(String statementType, String[] texts) {
        int idSpace = 10;
        int typeSpace = 14;
        int dateSpace = 20;
        int descriptionSpace = 30;
        int amountSpace = 20;
        int categorySpace = 20;

        // Dynamically change the width of each column based on longest string
        for (String text :  texts) {
            String[] statementDetails = text.split(", ");
            idSpace = Math.max(idSpace, texts.length + 2 * MIN_SPACE_PER_SIDE);
            typeSpace = Math.max(typeSpace, statementDetails[0].length() + 2 * MIN_SPACE_PER_SIDE);
            dateSpace = Math.max(dateSpace, statementDetails[1].length() + 2 * MIN_SPACE_PER_SIDE);
            // Set a limit to the desccription text space
            descriptionSpace = Math.min(MAX_DESCRIPTION,
                Math.max(descriptionSpace, statementDetails[2].length() + 2 * MIN_SPACE_PER_SIDE));
            amountSpace = Math.max(amountSpace, statementDetails[3].length() + 2 * MIN_SPACE_PER_SIDE);
            categorySpace = Math.max(categorySpace, statementDetails[4].length() + 2 * MIN_SPACE_PER_SIDE);
        }

        int totalSpace = idSpace + typeSpace + dateSpace +
            descriptionSpace + amountSpace + categorySpace + 7;

        // Header
        printHeader(statementType, totalSpace, idSpace, typeSpace, dateSpace,
            descriptionSpace, categorySpace, amountSpace);

        double totalIncome = 0.0;
        double totalExpense = 0.0;

        for (int i = 0; i < texts.length; i++) {
            String[] statementDetails = texts[i].split(", ");
            String type = statementDetails[0];
            String date = statementDetails[1];
            String description = statementDetails[2];
            double amount = Double.parseDouble(statementDetails[3]);
            String cat = statementDetails[4];
            String sign = (type.equals("Income")) ? "+" : "-";
            totalIncome += (type.equals("Income")) ? amount : 0;
            totalExpense += (type.equals("Expense")) ? amount : 0;

            // Format description text so that anything more than MAX_DESCRIPTION
            // is replaced with "..."
            String textReplacement = " ...";
            if (description.length() > MAX_DESCRIPTION) {
                description = description.substring(0, MAX_DESCRIPTION - textReplacement.length()
                    - 2 * MIN_SPACE_PER_SIDE) + textReplacement;
            }

            // Format and print each row
            printTableRow(i + 1, type, date, description, cat, amount, sign, idSpace,
                typeSpace, dateSpace, descriptionSpace, categorySpace, amountSpace);
        }

        // Footer
        switch (statementType) {
        case "Financial Statement":
            double netIncome = totalIncome - totalExpense;
            printRowText(totalSpace);
            printFooter("Total Income: $", String.valueOf(totalIncome), totalSpace);
            printFooter("Total Expense: $", String.valueOf(totalExpense), totalSpace);
            printFooter("Net Income: $", String.valueOf(netIncome), totalSpace);
            printRowText(totalSpace);
            break;
        case "Income Statement":
            printRowText(totalSpace);
            printFooter("Total Income: $", String.valueOf(totalIncome), totalSpace);
            printRowText(totalSpace);
            break;
        case "Expense Statement":
            printRowText(totalSpace);
            printFooter("Total Expense: $", String.valueOf(totalExpense), totalSpace);
            printRowText(totalSpace);
            break;
        default:
            break;
        }

    }

    private static void printHeader(String statementType, int totalSpace, int idSpace, int typeSpace, int dateSpace
        , int descriptionSpace, int categorySpace, int amountSpace) {
        System.out.println("+" + repeatChars('-', totalSpace - 2) + "+");
        System.out.println("|" + centerText(statementType, totalSpace - 2) + "|");

        System.out.println("+" + repeatChars('-', idSpace) + "+" + repeatChars('-', typeSpace) + "+"
            + repeatChars('-', dateSpace) + "+" + repeatChars('-', descriptionSpace) + "+"
            + repeatChars('-', categorySpace) + "+" + repeatChars('-', amountSpace) + "+");
        System.out.println("|" + centerText("ID", idSpace) + "|" + centerText("Type", typeSpace) + "|"
            + centerText("Date", dateSpace) + "|" + centerText("Description", descriptionSpace) + "|"
            + centerText("Category", categorySpace) + "|" + centerText("Amount", amountSpace) + "|");
        System.out.println("+" + repeatChars('-', idSpace) + "+" + repeatChars('-', typeSpace) + "+"
            + repeatChars('-', dateSpace) + "+" + repeatChars('-', descriptionSpace) + "+"
            + repeatChars('-', categorySpace) + "+" + repeatChars('-', amountSpace) + "+");
    }

    private static void printTableRow(int id, String type, String date, String description
        , String category, double amount, String sign, int idSpace, int typeSpace, int dateSpace
        , int descriptionSpace, int categorySpace, int amountSpace) {
        System.out.println("|" + centerText(String.valueOf(id), idSpace) + "|" + centerText(type, typeSpace)
            + "|" + centerText(date, dateSpace) + "|" + centerText(description, descriptionSpace)
            + "|" + centerText(category, categorySpace) + "|" + centerText(sign + " $" + amount, amountSpace) + "|");
    }

    private static void printFooter(String label, String total, int totalSpace) {
        String totalString = "| " + label + total +
            " ".repeat(totalSpace - label.length() - total.length() - 3) + "|";

        System.out.println(totalString);
    }

    private static void printRowText(int totalSpace) {
        System.out.println("+" + repeatChars('-', totalSpace - 2) + "+");
    }

    private static String centerText(String text, int width) {
        int padding = width - text.length();
        int leftPadding = padding / 2;
        int rightPadding = padding - leftPadding;
        return " ".repeat(leftPadding) + text + " ".repeat(rightPadding);
    }

    private static String repeatChars(char character, int count) {
        return new String(new char[count]).replace('\0', character);
    }

}
