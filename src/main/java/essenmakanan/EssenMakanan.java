package essenmakanan;

import essenmakanan.command.Command;
import essenmakanan.command.ExitCommand;
import essenmakanan.exception.EssenCommandException;
import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.parser.Parser;
import essenmakanan.recipe.RecipeList;
import essenmakanan.storage.IngredientStorage;
import essenmakanan.storage.RecipeStorage;
import essenmakanan.ui.Ui;

import java.io.IOException;
import java.util.Scanner;
//import java.util.logging.Level;
import java.util.logging.Logger;


public class EssenMakanan {

    private RecipeList recipes;
    private IngredientList ingredients;
    private Parser parser;
    private IngredientStorage ingredientStorage;
    private RecipeStorage recipeStorage;

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

        try {
            ingredientStorage.saveData(ingredients.getIngredients());
            recipeStorage.saveData(recipes.getRecipes());
        } catch (IOException exception) {
            Ui.handleIOException(exception);
        }
    }

    public void setup() {
        recipes = new RecipeList();
        parser = new Parser();
        ingredientStorage = new IngredientStorage();
        recipeStorage = new RecipeStorage();
        ingredients = new IngredientList(ingredientStorage.restoreSavedData());
        recipes = new RecipeList(recipeStorage.restoreSavedData());
    }

    public void start() {
        setup();
        run();
    }

    public static void main(String[] args) {
        new EssenMakanan().start();
    }
}
