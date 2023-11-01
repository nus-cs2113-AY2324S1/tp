package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.parser.IngredientParser;
import essenmakanan.parser.RecipeParser;
import essenmakanan.recipe.*;
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
            try {
                this.addWithTitleAndStepsAndIngredients();
            } catch (EssenFormatException e) {
                e.handleException();
            }
        } else if (toAdd.contains("r/") && toAdd.contains("s/") && toAdd.contains("t/")) {
            // only title and steps are available
            this.addWithTitle_Steps_Tags();
        } else if (toAdd.contains("r/") && toAdd.contains("s/")) {
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



    public void addWithTitle_Steps_Tags() {
        // add r/bread t/b s/buy ingredients s/store ingredients t/a s/cook
        String[] allToAdd = toAdd.split("t/");
        String recipeTitle = RecipeParser.parseRecipeTitle(allToAdd[0].trim());
        String[] steps = new String[allToAdd.length - 1];
        for (int i = 1; i < allToAdd.length; i++) {
            steps[i - 1] = allToAdd[i].trim();
        }
        RecipeStepList recipeStepList = new RecipeStepList(new String[]{});
        for (String step : steps) {
            if (step.startsWith("1")) {
                step = step.replace("1", "").trim();
                addSteps(step, recipeStepList, Tag.NIGHT_BEFORE);
            } else if (step.startsWith("2")) {
                step = step.replace("2", "");
                addSteps(step, recipeStepList, Tag.MORNING_OF_COOKING);
            } else if (step.startsWith("3")) {
                step = step.replace("3", "");
                addSteps(step, recipeStepList, Tag.MORE_THAN_ONE_DAY);
            } else if (step.startsWith("4")) {
                step = step.replace("4", "");
                addSteps(step, recipeStepList, Tag.ACTUAL_COOKING);
            } else {
                System.out.println("No such Tag");
            }
        }
        Recipe newRecipe = new Recipe(recipeTitle, recipeStepList);
        recipes.addRecipe(newRecipe);
        Ui.printAddRecipeSuccess(recipeTitle);
    }

    private static void addSteps(String step, RecipeStepList recipeStepList, Tag tag) {
        String[] allSteps = step.trim().split("s/");
        for (String eachStep : allSteps) {
            if (eachStep.length()>0) {
                recipeStepList.addStep(new Step(eachStep.trim(), tag));
            }
        }
    }

    public static void main(String[] args) {

    }

    public void addWithTitleAndStepsAndIngredients() throws EssenFormatException {
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
                    throw new EssenFormatException();
                }

                break;
            case "s/":
                stepsInString[stepsCounter] = detail;
                stepsCounter++;
                break;
            case "i/":
                if (!IngredientParser.isValidIngredient(detail)) {
                    System.out.println("Ingredient is not valid! Please enter valid ingredient after \"i/\"");
                    throw new EssenFormatException();
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
