package cashleh;

import java.util.ArrayList;

public class Ui {
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

    public static void printFinancialStatement(String[] texts) {
        final int ID_SPACE = 10;
        final int TYPE_SPACE = 14;
        final int DATE_SPACE = 20;
        final int DESCRIPTION_SPACE = 30;
        final int AMOUNT_SPACE = 20;
        final int CATEGORY_SPACE = 20;
        final int TOTAL_SPACE = ID_SPACE + TYPE_SPACE + DATE_SPACE + DESCRIPTION_SPACE + AMOUNT_SPACE + CATEGORY_SPACE + 7;

        // Header
        printHeader(TOTAL_SPACE, ID_SPACE, TYPE_SPACE, DATE_SPACE, DESCRIPTION_SPACE, CATEGORY_SPACE, AMOUNT_SPACE);

        double totalIncome = 0.0;
        double totalExpense = 0.0;

        for (int i = 0; i < texts.length; i++) {
            String[] statementDetails = texts[i].split(" ");
            String type = statementDetails[0];
            String date = statementDetails[1];
            String description = statementDetails[2];
            double amount = Double.parseDouble(statementDetails[3]);
            String cat = statementDetails[4];
            String sign = (type.equals("Income")) ? "+" : "-";
            totalIncome += (type.equals("Income")) ? amount : 0;
            totalExpense += (type.equals("Expense")) ? amount : 0;

            // Format and print each row
            printTableRow(i + 1, type, date, description, cat, amount, sign, ID_SPACE, TYPE_SPACE, DATE_SPACE, DESCRIPTION_SPACE, CATEGORY_SPACE, AMOUNT_SPACE);
        }

        // Footer
        double netIncome = totalIncome - totalExpense;
        printFooter(String.valueOf(totalIncome), String.valueOf(totalExpense), String.valueOf(netIncome), TOTAL_SPACE);
    }

    private static void printHeader(int totalSpace, int idSpace, int typeSpace, int dateSpace, int descriptionSpace, int categorySpace, int amountSpace) {
        System.out.println("+" + repeatChars('-', totalSpace - 2) + "+");
        System.out.println("|" + centerText("Overall Financial Statement", totalSpace - 2) + "|");

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

    private static void printTableRow(int id, String type, String date, String description, String category, double amount,
        String sign, int idSpace, int typeSpace, int dateSpace, int descriptionSpace, int categorySpace, int amountSpace) {
        System.out.println("|" + centerText(String.valueOf(id), idSpace) + "|" + centerText(type, typeSpace)
            + "|" + centerText(date, dateSpace) + "|" + centerText(description, descriptionSpace)
            + "|" + centerText(category, categorySpace) + "|" + centerText(sign + " $" + amount, amountSpace) + "|");
    }

    private static void printFooter(String totalIncome, String totalExpense, String netIncome, int totalSpace) {
        String totalIncomeLabel = "Total Income: $";
        String totalExpenseLabel = "Total Expenses: $";
        String netIncomeLabel = "Net Income: $";

        String totalIncomeString = "| " + totalIncomeLabel + totalIncome +
            " ".repeat(totalSpace - totalIncomeLabel.length() - totalIncome.length() - 3) + "|";
        String totalExpenseString = "| " + totalExpenseLabel + totalExpense +
            " ".repeat(totalSpace - totalExpenseLabel.length() - totalExpense.length() - 3) + "|";
        String netIncomeString = "| " + netIncomeLabel +  netIncome +
            " ".repeat(totalSpace - netIncomeLabel.length() - netIncome.length() - 3) + "|";

        System.out.println("+" + repeatChars('-', totalSpace - 2) + "+");
        System.out.println(totalIncomeString);
        System.out.println(totalExpenseString);
        System.out.println(netIncomeString);
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
