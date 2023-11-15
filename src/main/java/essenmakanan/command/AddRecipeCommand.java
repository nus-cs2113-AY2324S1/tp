package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.parser.IngredientParser;
import essenmakanan.parser.RecipeParser;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;
import essenmakanan.recipe.Step;
import essenmakanan.ui.Ui;
import essenmakanan.recipe.Tag;

public class AddRecipeCommand extends Command {
    private String toAdd;
    private RecipeList recipes;

    public AddRecipeCommand(String toAdd, RecipeList recipes) {
        super();
        this.toAdd = toAdd;
        this.recipes = recipes;
    }

    /**
     * Check if structure of command is valid
     */
    @Override
    public void executeCommand() {
        // if toAdd does not contain the necessary flag, it should have been flagged in Parser class
        assert toAdd.contains("r/") && toAdd.contains("s/") && toAdd.contains("i/")
                : "Parser did not catch incomplete input";

        if ((toAdd.contains("r/") && toAdd.contains("s/") && toAdd.contains("i/"))) {
            // title, steps and ingredients are available
            try {
                this.addValidRecipe();
            } catch (EssenFormatException e) {
                e.handleException();
            }
        }
    }

    /**
     * Add a valid recipe to the list
     *
     * @throws EssenFormatException if input is invalid
     */
    public void addValidRecipe() throws EssenFormatException {

        // initialisation
        int numberOfSteps = countOccurrences(toAdd, "s/");
        int numberOfIngredients = countOccurrences(toAdd, "i/");

        String[] stepsInString = new String[numberOfSteps];
        int stepsCounter = 0;

        String[] ingredientsInString = new String[numberOfIngredients];
        int ingredientsCounter = 0;

        String recipeTitle = "";
        String tag = null;
        int recipeIndexToOverwrite = -1;

        int flagIndex;
        String typeFlag;
        String content;
        int nextSlashIndex;
        int slashIndex = toAdd.indexOf("/");

        // getting contents from input
        while (slashIndex != -1) {
            flagIndex = slashIndex - 1;
            typeFlag = toAdd.substring(flagIndex, flagIndex+1);
            nextSlashIndex = toAdd.indexOf("/",slashIndex+1);

            if ((flagIndex + 2 > nextSlashIndex - 2) && nextSlashIndex!=-1){
                System.out.println("Please enter valid input! Make sure flags are spaced out. Examples on user guide.");
                throw new EssenFormatException();
            }

            if (nextSlashIndex != -1) {
                // obtain content after each flag until the next flag
                content = toAdd.substring(flagIndex + 2, nextSlashIndex-2);
            } else {
                // from flag to end of string
                content = toAdd.substring(flagIndex + 2);
            }

            switch (typeFlag) {
            case "r":
                int recipeIndex = this.obtainRecipeIndex(content, recipeTitle);

                // check if recipe already exist
                if (recipes.recipeExist(recipeIndex)) {
                    if (overwriteExistingRecipe()) {
                        recipeIndexToOverwrite = recipeIndex;
                    } else {
                        System.out.println("Operation cancelled!");
                        return;
                    }
                }

                recipeTitle = RecipeParser.parseRecipeTitle(content);

                break;
            case "s":
                content = this.getStepsContent(content, tag, stepsCounter);
                stepsInString[stepsCounter] = content;
                stepsCounter++;
                break;
            case "i":
                content = this.getIngredientContent(content);
                ingredientsInString[ingredientsCounter] = content;
                ingredientsCounter++;
                break;
            case "t":
                if (content.isEmpty()) {
                    System.out.println("Tag is empty! Please enter valid tag number \"t/\"");
                    throw new EssenFormatException();
                }

                if (!Tag.tagExist(content)) {
                    System.out.println("Tag does not exist! Please enter valid tag number \"t/\"");
                    throw new EssenFormatException();
                }

                // steps after this tag (and before the next tag) will belong to this tag
                tag = content;
                break;
            case "d":
                // add duration to the latest step
                int duration = RecipeParser.parseStepsDuration(content);

                // if step has 2 specified duration, throw error
                if (stepsInString[stepsCounter-1].contains("d/")) {
                    System.out.println("Please only enter one duration per step!");
                    throw new EssenFormatException();
                }

                stepsInString[stepsCounter-1] = stepsInString[stepsCounter-1] + " d/" + duration;
                break;
            default:
                System.out.println("Please enter a valid recipe!");
            }
            slashIndex = nextSlashIndex;
        }
        if (recipeTitle.isEmpty()) {
            System.out.println("The title of the recipe shouldn't be empty! Please give a valid title!");
            return;
        }
        Recipe newRecipe = new Recipe(recipeTitle, stepsInString, ingredientsInString);
        if (recipeIndexToOverwrite != -1) {
            recipes.deleteRecipe(recipeIndexToOverwrite);
        }
        recipes.addRecipe(newRecipe);
        Ui.printAddRecipeSuccess(recipeTitle);
    }

    /**
     * Obtain recipe index from user input
     *
     * @param content user input
     * @param recipeTitle recipe title
     * @return recipe index
     * @throws EssenFormatException if input is invalid
     */
    public int obtainRecipeIndex(String content, String recipeTitle) throws EssenFormatException {
        if (content.isBlank()) {
            System.out.println("Recipe title is empty! Please enter valid title after \"r/\"");
            throw new EssenFormatException();
        }

        if (!recipeTitle.isEmpty()) {
            // user input more than one recipe title
            System.out.println("Please only enter one recipe title!");
            throw new EssenFormatException();
        }

        // return recipe index
        return recipes.getIndexOfRecipe(content.trim());
    }

    /**
     * Obtain steps content from user input
     *
     * @param content user input
     * @param tag tag of the step
     * @param stepsCounter number of steps
     * @return steps content
     * @throws EssenFormatException if input is invalid
     */
    public String getStepsContent(String content, String tag, int stepsCounter) throws EssenFormatException  {
        if (content.isBlank()) {
            System.out.println("Step is empty! Please enter valid step after \"s/\"");
            throw new EssenFormatException();
        }

        content = content.trim();
        content = Step.convertToStepIdTemplate(content, stepsCounter+1);

        if (tag != null) {
            // this step belongs to a tag
            content = content + " t/" + tag;
        }

        return content;
    }

    /**
     * Obtain ingredient content from user input
     *
     * @param content user input
     * @return ingredient content
     * @throws EssenFormatException if input is invalid
     */
    public String getIngredientContent(String content) throws EssenFormatException {
        if (content.isBlank()) {
            System.out.println("Ingredient is empty! Please enter valid ingredient after \"i/\"");
            throw new EssenFormatException();
        }

        if (!IngredientParser.isValidIngredient(content)) {
            System.out.println("Ingredient is not valid! Please enter valid ingredient after \"i/\"");
            throw new EssenFormatException();
        }

        return content;
    }

    /**
     * Count the number of occurrences of a substring in a string
     *
     * @param mainStr main string to count from
     * @param subStr substring to count
     * @return number of occurrences
     */
    public static int countOccurrences(String mainStr, String subStr) {
        int count = 0;
        int index = 0;
        while ((index = mainStr.indexOf(subStr, index)) != -1) {
            count++;
            index += subStr.length();  // Move to the end of the found substring and continue
        }
        return count;
    }

    /**
     * Ask user if they want to overwrite existing recipe. If yes, delete existing recipe
     *
     * @return true if user wants to overwrite existing recipe, false otherwise
     */
    public boolean overwriteExistingRecipe() {
        System.out.println("Recipe already exist! Do you want to overwrite it? (Y/N)");
        String input = Ui.readUserInput();
        if (input.equalsIgnoreCase("Y")) {
            return true;
        } else if (input.equalsIgnoreCase("N")) {
            return false;
        } else {
            System.out.println("Invalid input! Please enter Y or N");
            return overwriteExistingRecipe();
        }
    }


}
