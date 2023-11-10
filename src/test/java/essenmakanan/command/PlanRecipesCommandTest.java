package essenmakanan.command;

import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;
import essenmakanan.ingredient.IngredientList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlanRecipesCommandTest {
    public RecipeList myRecipeList;
    public IngredientList myIngredientInventory;
    public Recipe myRecipe1;
    public Recipe myRecipe2;

    @BeforeEach
    public void setup() {
        // Add recipes to myRecipeList
        String[] inputIngredients1 = {"flour,200,g", "egg,2,pc"};
        String[] inputSteps1 = {"beat the egg", "add flour into bowl of egg", "mix them together"};
        this.myRecipe1 = new Recipe("bread", inputSteps1, inputIngredients1);

        String[] inputIngredients2 = {"noodles,100,g", "egg,1,pc", "vegetable,4,pc"};
        String[] inputSteps2 = {"beat the egg", "add flour into bowl of egg", "mix them together"};
        this.myRecipe2 = new Recipe("noodles", inputSteps2, inputIngredients2);

        this.myRecipeList = new RecipeList();
        this.myRecipeList.addRecipe(myRecipe1);
        this.myRecipeList.addRecipe(myRecipe2);

        // Add ingredients to myInventory
        String[] ingredientsString = {"flour,100,g", "egg,1,pc", "Banana,10,pc", "noodles,100,g"};
        this.myIngredientInventory = new IngredientList(ingredientsString);
    }

    @Test
    public void planRecipeCommand_planRecipe1_correctOutput() {
        PlanRecipesCommand command = new PlanRecipesCommand(myIngredientInventory, myRecipeList, "1 r/1");
        command.executeCommand();

        String[] allIngredientsNeededString = {"flour,200,g", "egg,2,pc"};
        IngredientList allIngredientsNeeded = new IngredientList(allIngredientsNeededString);
        assert command.getAllIngredientsNeeded().equals(allIngredientsNeeded) : "Failed to plan recipe 1";

        String[] missingIngredientsString = {"flour,100,g", "egg,1,pc"};
        IngredientList missingIngredients = new IngredientList(missingIngredientsString);
        assert command.getMissingIngredients().equals(missingIngredients) : "Failed to plan recipe 1";
    }

    @Test
    public void planRecipeCommand_planRecipe1and2_correctOutput() {
        PlanRecipesCommand command = new PlanRecipesCommand(myIngredientInventory, myRecipeList, "2 r/1 r/2");
        command.executeCommand();

        String[] allIngredientsNeededString = {"flour,200,g",
            "egg,2,pc", "noodles,100,g", "egg,1,pc", "vegetable,4,pc"};
        IngredientList allIngredientsNeeded = new IngredientList(allIngredientsNeededString);
        assert command.getAllIngredientsNeeded().equals(allIngredientsNeeded) : "Failed to plan recipe 1";

        String[] missingIngredientsString = {"flour,100,g", "egg,1,pc", "vegetable,4,pc"};
        IngredientList missingIngredients = new IngredientList(missingIngredientsString);
        assert command.getMissingIngredients().equals(missingIngredients) : "Failed to plan recipe 1";
    }

    @Test
    public void planRecipeCommand_extraWhiteSpaces_correctOutput() {
        PlanRecipesCommand command = new PlanRecipesCommand(myIngredientInventory,
                myRecipeList, "2    r/1      r/2");
        command.executeCommand();

        String[] allIngredientsNeededString = {"flour,200,g", "egg,2,pc",
            "noodles,100,g", "egg,1,pc", "vegetable,4,pc"};
        IngredientList allIngredientsNeeded = new IngredientList(allIngredientsNeededString);
        assert command.getAllIngredientsNeeded().equals(allIngredientsNeeded) : "Failed to plan recipe 1";

        String[] missingIngredientsString = {"flour,100,g", "egg,1,pc", "vegetable,4,pc"};
        IngredientList missingIngredients = new IngredientList(missingIngredientsString);
        assert command.getMissingIngredients().equals(missingIngredients) : "Failed to plan recipe 1";
    }

    @Test void planRecipeCommand_planInvalidRecipeId_noActionTaken() {
        PlanRecipesCommand command = new PlanRecipesCommand(myIngredientInventory, myRecipeList, "2 r/1 r/3");
        command.executeCommand();
        IngredientList emptyIngredients = new IngredientList();

        assert command.getAllIngredientsNeeded().equals(emptyIngredients) : "No action should be taken";
        assert  command.getMissingIngredients().equals(emptyIngredients) : "No action should be taken";
    }

    @Test void planRecipeCommand_planInvalidRecipeCount_noActionTaken() {
        PlanRecipesCommand command = new PlanRecipesCommand(myIngredientInventory, myRecipeList, "5 r/1 r/3");
        command.executeCommand();
        IngredientList emptyIngredients = new IngredientList();

        assert command.getAllIngredientsNeeded().equals(emptyIngredients) : "No action should be taken";
        assert  command.getMissingIngredients().equals(emptyIngredients) : "No action should be taken";
    }

    @Test void planRecipeCommand_stringFormattedRecipeCount_noActionTaken() {
        PlanRecipesCommand command = new PlanRecipesCommand(myIngredientInventory, myRecipeList, "two r/1 r/3");
        command.executeCommand();
        IngredientList emptyIngredients = new IngredientList();

        assert command.getAllIngredientsNeeded().equals(emptyIngredients) : "No action should be taken";
        assert  command.getMissingIngredients().equals(emptyIngredients) : "No action should be taken";
    }

    @Test void planRecipeCommand_stringFormattedRecipeId_noActionTaken() {
        PlanRecipesCommand command = new PlanRecipesCommand(myIngredientInventory, myRecipeList, "1 r/noodles");
        command.executeCommand();
        IngredientList emptyIngredients = new IngredientList();

        assert command.getAllIngredientsNeeded().equals(emptyIngredients) : "No action should be taken";
        assert  command.getMissingIngredients().equals(emptyIngredients) : "No action should be taken";
    }
}
