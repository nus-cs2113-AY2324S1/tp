package essenmakanan;

import essenmakanan.command.Command;
import essenmakanan.command.ExitCommand;
import essenmakanan.exception.EssenCommandException;
import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.parser.Parser;
import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;

import java.util.Scanner;
//import java.util.logging.Level;
import java.util.logging.Logger;


public class EssenMakanan {

    private RecipeList recipes;
    private IngredientList ingredients;
    private Parser parser;

    private Logger logger = Logger.getLogger("app log");

    public void run() {
        Ui.start();

        Scanner in = new Scanner(System.in);
        String input = "";

        Command command = null;
        Ui.showCommands();
        do {
            input = in.nextLine();
            try {
                command = parser.parseCommand(input, recipes, ingredients);
                command.executeCommand();
            } catch (EssenCommandException exception) {
                exception.handleException();
            } catch (EssenFormatException exception) {
                exception.handleException();
            } catch (EssenOutOfRangeException exception) {
                exception.handleException();
            }
        } while (!ExitCommand.isExitCommand(command));
    }

    public void setup() {
        recipes = new RecipeList();
        ingredients = new IngredientList();
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
