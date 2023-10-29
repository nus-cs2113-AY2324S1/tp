package essenmakanan.recipe;

import essenmakanan.ingredient.Ingredient;
import essenmakanan.parser.IngredientParser;

import essenmakanan.exception.EssenFormatException;

import java.util.ArrayList;
import java.util.Scanner;

public class RecipeIngredientList {
    private ArrayList<Ingredient> ingredients = new ArrayList<>();

    public RecipeIngredientList() {
        Scanner in = new Scanner(System.in);
        String input;
        boolean isAddingIngredients = true;

        do {
            System.out.println("Add ingredient (name,quantity,unit) of your recipe, type \"end\" to finish");
            input = in.nextLine();

            if (input.equals("end")) {
                isAddingIngredients = false;
            } else {
                assert (input != null) : "Input is null";
                this.addIngredient(input);
            }
        } while (isAddingIngredients);
        System.out.println("Finished adding ingredients!");
    }

    public RecipeIngredientList(String[] ingredients) {
        for (String ingredientString : ingredients) {
            try {
                Ingredient ingredient = IngredientParser.parseIngredient(ingredientString);
                this.addIngredient(ingredient);
            } catch (EssenFormatException e) {
                e.handleException();
            }
        }
    }

    public void addIngredient(String input) {
        try{
            Ingredient ingredient = IngredientParser.parseIngredient(input);
            this.ingredients.add(ingredient);
        } catch (EssenFormatException e) {
            e.handleException();
        }
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public Ingredient getIngredientByIndex(int index) {
        return ingredients.get(index);
    }
}
