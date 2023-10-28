package essenmakanan.parser;

import essenmakanan.command.*;

import essenmakanan.exception.EssenMakananCommandException;
import essenmakanan.exception.EssenMakananFormatException;
import essenmakanan.exception.EssenMakananOutOfRangeException;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.RecipeList;

public class Parser {
    public Command parseCommand(String input, RecipeList recipes, IngredientList ingredients)
            throws EssenMakananCommandException, EssenMakananFormatException, EssenMakananOutOfRangeException {
        Command command;

        String[] parsedInput = input.split(" ", 2);
        String commandType = parsedInput[0];
        String inputDetail = parsedInput.length == 1 ? "" : parsedInput[1].trim();

        switch (commandType) {
        case "add":

            if (inputDetail.startsWith("r/")) {
                command = new AddRecipeCommand(inputDetail, recipes);
            } else if (inputDetail.startsWith("i/")) {
                command = new AddIngredientCommand(inputDetail, ingredients);
            } else {
                throw new EssenMakananFormatException();
            }
            break;
        case "delete":
            if (inputDetail.startsWith("r/")) {
                command = new DeleteRecipeCommand(recipes, inputDetail);
            } else if (inputDetail.startsWith("i/")) {
                command = new DeleteIngredientCommand(ingredients, inputDetail);
            } else {
                throw new EssenMakananFormatException();
            }
            break;
        case "view":
            if (inputDetail.equals("r")) {
                command = new ViewRecipesCommand(recipes);
            } else if (inputDetail.equals("i")) {
                command = new ViewIngredientsCommand(ingredients);
            } else if (inputDetail.startsWith("r/")) {
                assert inputDetail.length() > 2 : "To view a recipe, make sure title is not empty";
                command = new ViewSpecificRecipeCommand(recipes, inputDetail);
            }else {
                throw new EssenMakananFormatException();
            }
            break;
        case "edit":
            if (inputDetail.startsWith("i/")) {
                command = new EditIngredientCommand(inputDetail, ingredients);
            } else if (inputDetail.startsWith("r/")) {
                command = new EditRecipeCommand(inputDetail, recipes);
            }else {
                throw new EssenMakananFormatException();
            }
            break;
        case "help":
            command = new HelpCommand();
            break;
        case "exit":
            command = new ExitCommand();
            break;
        default:
            throw new EssenMakananCommandException();
        }

        return command;
    }
}
