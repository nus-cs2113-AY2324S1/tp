package essenmakanan.recipe;

import essenmakanan.ingredient.Ingredient;
import essenmakanan.parser.IngredientParser;

import essenmakanan.exception.EssenFormatException;

import java.util.ArrayList;

public class RecipeIngredientList {
    private ArrayList<Ingredient> ingredients = new ArrayList<>();


    public RecipeIngredientList(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public RecipeIngredientList(String[] inputIngredients) {
        for (String ingredientString : inputIngredients) {
            try {
                Ingredient ingredient = IngredientParser.parseIngredient(ingredientString);
                this.addIngredient(ingredient);
            } catch (EssenFormatException e) {
                e.handleException();
            }
        }
    }

    /**
     * To add ingredient by name into the recipe
     *
     * @param input is the input of the ingredient
     */
    public void addIngredient(String input) {
        try{
            Ingredient ingredient = IngredientParser.parseIngredient(input);
            this.ingredients.add(ingredient);
        } catch (EssenFormatException e) {
            e.handleException();
        }
    }

    /**
     * To add ingredient object into the recipe
     *
     * @param ingredient is the name of the ingredient
     */
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * To get ingredient by index
     *
     * @param index of the ingredient to be retrieved
     */
    public Ingredient getIngredientByIndex(int index) {
        return ingredients.get(index);
    }

    /**
     * To check if ingredient exist using its name
     * @param ingredientName is the name of the ingredient to check
     * @return true if ingredient exist
     */
    public boolean ingredientExist(String ingredientName) {
        String recipeIngredientName;
        for (Ingredient recipeIngredient : ingredients) {
            recipeIngredientName = recipeIngredient.getName();
            if (recipeIngredientName.equals(ingredientName)) {
                return true;
            }
        }
        return false;
    }
}
