package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.parser.IngredientParser;
import essenmakanan.parser.RecipeParser;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;
import essenmakanan.recipe.RecipeStepList;
import essenmakanan.ui.Ui;
import essenmakanan.recipe.Tag;
import essenmakanan.recipe.Step;

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
            this.addWithTitleStepsTags();
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

    public void addWithTitleStepsTags() {
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
                step = step.replaceFirst("1", "").trim();
                addSteps(step, recipeStepList, Tag.NIGHT_BEFORE);
            } else if (step.startsWith("2")) {
                step = step.replaceFirst("2", "");
                addSteps(step, recipeStepList, Tag.MORNING_OF_COOKING);
            } else if (step.startsWith("3")) {
                step = step.replaceFirst("3", "");
                addSteps(step, recipeStepList, Tag.MORE_THAN_ONE_DAY);
            } else if (step.startsWith("4")) {
                step = step.replaceFirst("4", "");
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
            if (eachStep.length()>0 && eachStep.contains("d/")) {
                try {
                    String description = eachStep.split("d/")[0].trim();
                    int duration = RecipeParser.parseStepsDuration(eachStep);
                    Step specificStep = new Step(description, tag, duration);
                    recipeStepList.addStep(specificStep);
                } catch (EssenFormatException e) {
                    throw new RuntimeException(e);
                }
            } else if (eachStep.length()>0) {
                recipeStepList.addStep(new Step(eachStep.trim(), tag));
            }
        }
    }

    public void addWithTitleAndStepsAndIngredients() throws EssenFormatException {

        int numberOfSteps = countOccurrences(toAdd, "s/");
        int numberOfIngredients = countOccurrences(toAdd, "i/");

        String[] stepsInString = new String[numberOfSteps];
        int stepsCounter = 0;

        String[] ingredientsInString = new String[numberOfIngredients];
        int ingredientsCounter = 0;

        String recipeTitle = "";

        int flagIndex;
        String typeFlag;
        String content;
        int nextSlashIndex;
        int slashIndex = toAdd.indexOf("/");
        while (slashIndex != -1) {
            flagIndex = slashIndex - 1;
            typeFlag = toAdd.substring(flagIndex, flagIndex+1);
            nextSlashIndex = toAdd.indexOf("/",slashIndex+1);

            if (nextSlashIndex != -1) {
                // obtain content after each flag until the next flag
                content = toAdd.substring(flagIndex + 2, nextSlashIndex-2);
            } else {
                // from flag to end of string
                content = toAdd.substring(flagIndex + 2);
            }

            switch (typeFlag) {
            case "r":
                recipeTitle = RecipeParser.parseRecipeTitle(content);

                if (recipeTitle.isEmpty()) {
                    System.out.println("Recipe title is empty! Please enter valid title after \"r/\"");
                    throw new EssenFormatException();
                }

                break;
            case "s":
                stepsInString[stepsCounter] = content;
                stepsCounter++;
                break;
            case "i":
                if (!IngredientParser.isValidIngredient(content)) {
                    System.out.println("Ingredient is not valid! Please enter valid ingredient after \"i/\"");
                    throw new EssenFormatException();
                }
                ingredientsInString[ingredientsCounter] = content;
                ingredientsCounter++;
                break;
            default:
                System.out.println("Please enter a valid recipe!");
            }
            slashIndex = nextSlashIndex;
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
