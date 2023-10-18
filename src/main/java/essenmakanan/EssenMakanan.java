package essenmakanan;

import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;
import java.util.Scanner;

public class EssenMakanan {

    private static final String EXIT = "bye";
    private static final String RECIPE_FUNCTION = "1";
    private static final String INGREDIENT_FUNCTION = "2";

    public static boolean handleRecipeFunctions(RecipeList recipes, String command, String inputDetail) {
        switch(command) {
        case "add":
            String recipeName = inputDetail.substring(2);
            Recipe newRecipe = new Recipe(recipeName);
            recipes.addRecipe(newRecipe);
            System.out.println("Recipe: " + recipeName + " has been successfully created!");
            return true;
        case "view":
            recipes.viewAllRecipes();
            return true;
        default:
            return false;
        }
    }

    public static boolean handleIngredientFunctions(IngredientList ingredients, String command, String inputDetail) {
        switch(command) {
        case "add":
            String ingredientName = inputDetail.substring(2);
            Ingredient newIngredient = new Ingredient(ingredientName);
            ingredients.addIngredient(newIngredient);
            System.out.println("Ingredient: " + ingredientName + " has been successfully created!");
            return true;
        case "view":
            ingredients.listIngredients();
            return true;
        default:
            return false;
        }
    }

    public static void main(String[] args) {
        RecipeList recipes = new RecipeList();
        IngredientList ingredients = new IngredientList();
        Ui ui = new Ui();

        // Prompt users that program is ready
        ui.start();

        Scanner in = new Scanner(System.in);
        String functionInput;
        String input;
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
                break;
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
}
