package seedu.cafectrl.command;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewTotalStockCommandTest {
    @Test
    void execute_printPantryStock() {
        ArrayList<Ingredient> pantryStock = new ArrayList<>();
        pantryStock.add(new Ingredient("chicken", 500, "g"));
        pantryStock.add(new Ingredient("rice", 1000, "g"));

        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui, pantryStock);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream consoleStream = new PrintStream(baos);

        PrintStream originalOut = System.out;
        System.setOut(consoleStream);

        ViewTotalStockCommand viewTotalStockCommand = new ViewTotalStockCommand(pantry, ui);
        viewTotalStockCommand.execute();

        String actualOutput = baos.toString().trim();
        System.setOut(originalOut);

        String expectedOutput = "+-------------------------------------------------------+\n"
                + "| You have the following ingredients in pantry:         |\n"
                + "+----------------------------------------+--------------+\n"
                + "| Ingredients                            |  Qty         |\n"
                + "+----------------------------------------+--------------+\n"
                + "| chicken                                | 500g         |\n"
                + "| rice                                   | 1000g        |\n"
                + "+-------------------------------------------------------+\n";

        assertEquals(expectedOutput.trim().replaceAll("\\s+", " "),
                actualOutput.trim().replaceAll("\\s+", " "));
    }
}
