package essenmakanan;

import essenmakanan.command.Command;
import essenmakanan.command.ExitCommand;
import essenmakanan.exception.EssenCommandException;
import essenmakanan.exception.EssenFileNotFoundException;
import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.shortcut.ShortcutList;
import essenmakanan.logger.EssenLogger;
import essenmakanan.parser.Parser;
import essenmakanan.recipe.RecipeList;
import essenmakanan.storage.IngredientStorage;
import essenmakanan.storage.RecipeStorage;
import essenmakanan.storage.ShortcutStorage;
import essenmakanan.ui.Ui;

import java.util.Scanner;

public class EssenMakanan {

    private final String DATA_INGREDIENT_PATH = "data/ingredients.txt";
    private final String DATA_RECIPE_PATH = "data/recipes.txt";
    private final String DATA_SHORTCUT_PATH = "data/shortcuts.txt";
    private final String DATA_DIRECTORY = "data";

    private RecipeList recipes;
    private IngredientList ingredients;
    private ShortcutList shortcuts;
    private Parser parser;
    private IngredientStorage ingredientStorage;
    private RecipeStorage recipeStorage;
    private ShortcutStorage shortcutStorage;

    public void run() {
        Ui.start();

        Scanner in = new Scanner(System.in);
        String input;

        Command command = null;
        do {
            input = in.nextLine();
            try {
                command = parser.parseCommand(input, recipes, ingredients, shortcuts);
                command.executeCommand();
                ingredientStorage.saveData(ingredients.getIngredients());
                recipeStorage.saveData(recipes.getRecipes());
                shortcutStorage.saveData(shortcuts.getShortcuts());
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
        EssenLogger.setup();
        recipes = new RecipeList();
        parser = new Parser();
        ingredientStorage = new IngredientStorage(DATA_INGREDIENT_PATH);
        recipeStorage = new RecipeStorage(DATA_RECIPE_PATH);

        try {
            ingredients = new IngredientList(ingredientStorage.restoreSavedData());
        } catch (EssenFileNotFoundException exception) {
            exception.handleFileNotFoundException(DATA_DIRECTORY, DATA_INGREDIENT_PATH);
            ingredients = new IngredientList();
        }

        try {
            recipes = new RecipeList(recipeStorage.restoreSavedData());
        } catch (EssenFileNotFoundException exception) {
            exception.handleFileNotFoundException(DATA_DIRECTORY, DATA_RECIPE_PATH);
            recipes = new RecipeList();
        }

        shortcutStorage = new ShortcutStorage(DATA_SHORTCUT_PATH, ingredients);
        try {
            shortcuts = new ShortcutList(shortcutStorage.restoreSavedData());
        } catch (EssenFileNotFoundException exception) {
            exception.handleFileNotFoundException(DATA_DIRECTORY, DATA_SHORTCUT_PATH);
            shortcuts = new ShortcutList();
        }
    }

    public void start() {
        setup();
        run();
    }

    public static void main(String[] args) {
        new EssenMakanan().start();
    }
}
