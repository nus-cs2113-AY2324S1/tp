package seedu.cafectrl.command;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyIngredientCommandTest {

    @Test
    void execute_validInput_returnCorrectOutput() {
        ArrayList<Ingredient> ingredientsList = new ArrayList<>();
        ingredientsList.add(new Ingredient("chicken", 500, "g"));
        ingredientsList.add(new Ingredient("rice", 1000, "g"));
        ingredientsList.add(new Ingredient("milk", 100, "ml"));

        Ui ui = new Ui();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream consoleStream = new PrintStream(baos);
        PrintStream originalOut = System.out;
        System.setOut(consoleStream);

        Pantry pantry = new Pantry(ui);
        BuyIngredientCommand buyIngredientCommand = new BuyIngredientCommand(ingredientsList, ui, pantry);

        buyIngredientCommand.execute();

        String actualOutput = baos.toString().trim();
        System.setOut(originalOut);

        String expectedOutput = "Added to stock: \n"
                + "Ingredient: milk\nTotal Qty: 100ml\n\n"
                + "Ingredient: rice\nTotal Qty: 1000g\n\n"
                + "Ingredient: chicken\nTotal Qty: 500g\n\n";

        assertEquals(expectedOutput.trim().replaceAll("\\s+", " "),
                actualOutput.trim().replaceAll("\\s+", " "));
    }

    @Test
    void execute_validInputWithExistingIngredient_returnCorrectOutput() {
        ArrayList<Ingredient> ingredientsList = new ArrayList<>();
        ingredientsList.add(new Ingredient("chicken", 500, "g"));

        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui, ingredientsList);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream consoleStream = new PrintStream(baos);
        PrintStream originalOut = System.out;
        System.setOut(consoleStream);

        BuyIngredientCommand buyIngredientCommand = new BuyIngredientCommand(ingredientsList, ui, pantry);

        buyIngredientCommand.execute();

        String actualOutput = baos.toString().trim();
        System.setOut(originalOut);

        String expectedOutput = "Added to stock: \n"
                + "Ingredient: chicken\nTotal Qty: 1000g\n";

        assertEquals(expectedOutput.trim().replaceAll("\\s+", " "),
                actualOutput.trim().replaceAll("\\s+", " "));
    }
}
