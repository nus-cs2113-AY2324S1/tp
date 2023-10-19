package essenmakanan;

import essenmakanan.command.Command;
import essenmakanan.exception.EssenMakananCommandException;
import essenmakanan.exception.EssenMakananFormatException;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.parser.Parser;
import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;

import java.util.Scanner;

public class EssenMakanan {

    private RecipeList recipes;
    private IngredientList ingredients;
    private Ui ui;
    private Parser parser;

    public void run() {
        ui.start();

        Scanner in = new Scanner(System.in);
        String input = "";
        boolean isExit = false;

        do {
            input = in.nextLine();
            try {
                Command command = parser.parseCommand(input);
                command.executeCommand(recipes, ingredients);
                isExit = command.isExit(); //someone can assert here
            } catch (EssenMakananCommandException exception) {
                exception.handleException();
            } catch (EssenMakananFormatException exception) {
                exception.handleException();
            }
        } while (!isExit);
    }

    public void setup() {
        recipes = new RecipeList();
        ingredients = new IngredientList();
        ui = new Ui();
        parser = new Parser();
    }

    public void start() {
        setup();
        run();
    }

    public static void main(String[] args) {
        new EssenMakanan().start();
    }
}
