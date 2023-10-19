package essenmakanan;

import essenmakanan.command.Command;
import essenmakanan.exception.EssenMakananCommandException;
import essenmakanan.exception.EssenMakananFormatException;
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
    private Ui ui;
    private Parser parser;

    private Logger logger = Logger.getLogger("app log");

    public void run() {
        //logger.log(Level.INFO, "App starting");
        ui.start();

        Scanner in = new Scanner(System.in);
        String input = "";
        boolean isExit = false;

        do {
            //logger.log(Level.INFO, "Getting input");
            input = in.nextLine();
            try {
                Command command = parser.parseCommand(input);
                command.executeCommand(recipes, ingredients);
                isExit = command.isExit(); //someone can assert here
            } catch (EssenMakananCommandException exception) {
                exception.handleException();
                //logger.log(Level.WARNING, "Invalid command given");
            } catch (EssenMakananFormatException exception) {
                exception.handleException();
                //logger.log(Level.WARNING, "Invalid format given");
            }
        } while (!isExit);

        //logger.log(Level.INFO, "Exiting app");
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
