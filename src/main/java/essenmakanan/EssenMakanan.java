package essenmakanan;

import essenmakanan.recipe.RecipeList;

import java.util.Scanner;

public class EssenMakanan {
    private static RecipeList recipes;

    public static String[] parseCommand(String input) {
        return input.split(" ", 2);
    }

    public static void main(String[] args) {
        recipes = new RecipeList();
        Scanner in = new Scanner(System.in);
        String input;
        boolean isUsing = true;

        while (isUsing) {
            input = in.nextLine();

            String[] parsedInput = parseCommand(input);
            String commandType = parsedInput[0];
            String inputDetail = parsedInput.length == 1 ? "" : parsedInput[1].trim();

            if (commandType.equals("add") && inputDetail.contains("r/")) {
                recipes.addRecipe(inputDetail);
            } else {
                isUsing = false;
            }
        }
        System.exit(0);
    }
}
