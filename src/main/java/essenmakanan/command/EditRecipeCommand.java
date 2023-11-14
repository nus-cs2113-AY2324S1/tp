package essenmakanan.command;

import essenmakanan.exception.EssenDoesNotExistException;
import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenInvalidEditException;
import essenmakanan.exception.EssenNullInputException;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;

public class EditRecipeCommand extends Command {

    private String editDetails;
    private RecipeList recipes;

    public EditRecipeCommand(String editDetails, RecipeList recipes) {
        super();
        this.editDetails = editDetails;
        this.recipes = recipes;
    }

    @Override
    public void executeCommand() {

        assert editDetails.startsWith("r/") : "Parser did not catch incomplete input";
        this.editDetails = this.editDetails.replace("r/", "");

        // getting recipe title
        String recipeTitle = null;
        try {
            recipeTitle = getRecipeTitle();
        } catch (EssenNullInputException e) {
            return;
        }

        // getting recipe from recipe list based on title
        Recipe existingRecipe = null;
        try {
            existingRecipe = recipes.getRecipe(recipeTitle);
            if (existingRecipe == null) {
                System.out.println("Recipe not found!");
                throw new EssenDoesNotExistException();
            }
        } catch (EssenDoesNotExistException e){
            e.handleException();
        }

        // getting the attributes to edit
        String[] attributesToEdit = null;
        try {
            attributesToEdit = getAttributesToEdit();
        } catch (EssenInvalidEditException e) {
            e.handleException();
        }

        assert !(attributesToEdit==null) : "Attributes is null";

        try {
            recipes.editRecipe(existingRecipe, attributesToEdit);
        } catch (EssenFormatException e) {
            e.handleException();
        }

    }

    /**
     * Returns the recipe title from the editDetails string.
     * @return recipe title
     */
    public String getRecipeTitle() throws EssenNullInputException {
        int firstFlag = editDetails.indexOf("/");

        if ( firstFlag < 4 ){
            // missing other flags or missing title
            throw new EssenNullInputException();
        }

        String recipeTitle = editDetails.substring(0, firstFlag-2).trim();

        return recipeTitle;

    }

    /**
     * Returns the attributes to edit from the editDetails string.
     * @return attributes to edit
     */
    public String[] getAttributesToEdit() throws EssenInvalidEditException {
        int firstFlag = editDetails.indexOf("/");
        int totalFlagsMinusTitle = editDetails.split("/").length - 1;

        String[] attributesToEdit = new String[totalFlagsMinusTitle];
        int attributeCounter = 0;

        while (firstFlag != -1) {
            if ((firstFlag + 1) >= editDetails.length()) {
                System.out.println("Please provide details to edit");
                throw new EssenInvalidEditException();
            }

            int nextFlag = editDetails.indexOf("/", firstFlag+1);

            if (nextFlag != -1) {
                attributesToEdit[attributeCounter] = editDetails.substring(firstFlag-1, nextFlag-2).trim();
            } else {
                attributesToEdit[attributeCounter] = editDetails.substring(firstFlag-1).trim();
            }
            attributeCounter++;
            firstFlag = nextFlag;
        }

        if (attributesToEdit.length == 0) {
            System.out.println("Please provide details to edit");
            throw new EssenInvalidEditException();
        }

        for (String attribute : attributesToEdit) {
            if (!attribute.contains("n/") && !attribute.contains("s/") && !attribute.contains("i/")) {
                // must have either one of these flags
                System.out.println("Flag is invalid or format is incorrect.");
                throw new EssenInvalidEditException();
            } else if (this.detailsIsNull(attribute)){
                throw new EssenInvalidEditException();
            }
        }

        return attributesToEdit;
    }

    /**
     * Given user input for editing, return true if details not provided.
     * @param details string in the format: FLAG/CONTENT
     * @return true if details is null
     */
    public boolean detailsIsNull(String details) {
        int flag = details.indexOf("/");
        String content = details.substring(flag+1);
        if (content.isEmpty()) {
            return true;
        }

        return false;
    }
}
