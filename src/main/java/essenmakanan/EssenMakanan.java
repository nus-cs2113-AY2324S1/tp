package essenmakanan;

import essenmakanan.command.AddIngredientCommand;
import essenmakanan.command.AddRecipeCommand;
import essenmakanan.command.IngredientCommand;
import essenmakanan.command.RecipeCommand;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;
import java.util.Scanner;

public class EssenMakanan {

    private RecipeList recipes;
    private IngredientList ingredients;
    private Ui ui;

    private final String EXIT = "bye";
    private final String RECIPE_FUNCTION = "1";
    private final String INGREDIENT_FUNCTION = "2";

    public boolean handleRecipeFunctions(RecipeList recipes, String command, String inputDetail) {
        switch(command) {
        case "add":
            RecipeCommand addCommand = new AddRecipeCommand();
            addCommand.executeCommand(recipes, inputDetail);
            return true;
        case "view":
            recipes.viewAllRecipes();
            return true;
        default:
            return false;
        }
    }

    public boolean handleIngredientFunctions(IngredientList ingredients, String command, String inputDetail) {
        switch(command) {
        case "add":
            IngredientCommand addCommand = new AddIngredientCommand();
            addCommand.executeCommand(ingredients, inputDetail);
            return true;
        case "view":
            ingredients.listIngredients();
            return true;
        default:
            return false;
        }
    }

    public void run() {
        ui.start();

        Scanner in = new Scanner(System.in);
        String functionInput;
        String input = "";
        boolean validInput;

        do {
            validInput = true;

            ui.functionSelect();
            functionInput = in.nextLine();


            switch (functionInput) {
            case RECIPE_FUNCTION:
                ui.showRecipeFunctions();
                break;
            case INGREDIENT_FUNCTION:
                ui.showIngredientFunctions();
                break;
            default:
                System.out.println("Please enter valid input.");
                validInput = false;
            }

            // exit while loop for invalid inputs
            if (!validInput) {
                ui.bye();
                System.exit(0);
            }

            input = in.nextLine();
            String[] parsedInput = input.split(" ", 2);
            String commandType = parsedInput[0];
            String inputDetail = parsedInput.length == 1 ? "" : parsedInput[1].trim();

            if (functionInput.equals(RECIPE_FUNCTION)) {
                validInput = handleRecipeFunctions(recipes, commandType, inputDetail);
            } else if (functionInput.equals(INGREDIENT_FUNCTION)) {
                validInput = handleIngredientFunctions(ingredients, commandType, inputDetail);
            }
            ui.drawDivider();
        } while (!input.equals(EXIT) || validInput);

        ui.bye();
    }

    public void setup() {
        recipes = new RecipeList();
        ingredients = new IngredientList();
        ui = new Ui();
    }

    public void start() {
        setup();
        run();
    }

    public static void main(String[] args) {
        new EssenMakanan().start();
    }
}
