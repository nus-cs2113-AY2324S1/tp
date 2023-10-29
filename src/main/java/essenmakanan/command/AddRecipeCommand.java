package essenmakanan.command;

import essenmakanan.parser.IngredientParser;
import essenmakanan.parser.RecipeParser;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;

public class AddRecipeCommand extends Command {
    private String toAdd;
    private RecipeList recipes;

    public AddRecipeCommand(String toAdd, RecipeList recipes) {
        super();
        this.toAdd = toAdd;
        this.recipes = recipes;
    }

    @Override
    public void executeCommand() {
        if ((toAdd.contains("r/") && toAdd.contains("s/") && toAdd.contains("i/"))) {
            // all attributes are available
            this.addWithTitleAndStepsAndIngredients();
        } else if (toAdd.contains("r/") && toAdd.contains("s/")) {
            // only title and steps are available
            this.addWithTitleAndSteps();
        } else {
            // only title is available
            this.addWithTitle();
        }
    }

    public void addWithTitle() {
        String recipeTitle = RecipeParser.parseRecipeTitle(toAdd);
        Recipe newRecipe = new Recipe(recipeTitle);
        recipes.addRecipe(newRecipe);
        Ui.printAddRecipeSuccess(recipeTitle);
    }

    public void addWithTitleAndSteps() {
        String[] allToAdd = toAdd.split("s/");
        String recipeTitle = RecipeParser.parseRecipeTitle(allToAdd[0].trim());
        String[] steps = new String[allToAdd.length - 1];
        for (int i = 1; i < allToAdd.length; i++) {
            steps[i - 1] = allToAdd[i].trim();
        }
        Recipe newRecipe = new Recipe(recipeTitle, steps);
        recipes.addRecipe(newRecipe);
        Ui.printAddRecipeSuccess(recipeTitle);
    }

    public void addWithTitleAndStepsAndIngredients() {
        String recipeTitle = "";

        String[] allToAdd = toAdd.split(" ");
        int numberOfSteps = countOccurrences(toAdd, "s/");
        int numberOfIngredients = countOccurrences(toAdd, "i/");

        String[] stepsInString = new String[numberOfSteps];
        int stepsCounter = 0;

        String[] ingredientsInString = new String[numberOfIngredients];
        int ingredientsCounter = 0;


        for (String recipeDetail : allToAdd) {
            // indicate the attributes of recipe
            String flag = recipeDetail.substring(0, 2);
            String detail = recipeDetail.substring(2);

            switch (flag) {
            case "r/":
                String inputRecipeTitle = allToAdd[0];

                // should have been caught by parser
                assert inputRecipeTitle.startsWith("r/") : "Recipe title must start with r/";

                recipeTitle = RecipeParser.parseRecipeTitle(inputRecipeTitle.trim());
                if (recipeTitle.isEmpty()) {
                    System.out.println("Recipe title is empty! Please enter valid title after \"r/\"");
                    return;
                }

                break;
            case "s/":
                stepsInString[stepsCounter] = detail;
                stepsCounter++;
                break;
            case "i/":
                if (!IngredientParser.isValidIngredient(detail)) {
                    System.out.println("Ingredient is not valid! Please enter valid ingredient after \"i/\"");
                    return;
                }
                ingredientsInString[ingredientsCounter] = detail;
                ingredientsCounter++;
                break;
            default:
                System.out.println("Please enter a valid recipe!");
            }
        }

        Recipe newRecipe = new Recipe(recipeTitle, stepsInString, ingredientsInString);
        recipes.addRecipe(newRecipe);
        Ui.printAddRecipeSuccess(recipeTitle);
    }

    public static int countOccurrences(String mainStr, String subStr) {
        int count = 0;
        int index = 0;
        while ((index = mainStr.indexOf(subStr, index)) != -1) {
            count++;
            index += subStr.length();  // Move to the end of the found substring and continue
        }
        return count;
    }

}
