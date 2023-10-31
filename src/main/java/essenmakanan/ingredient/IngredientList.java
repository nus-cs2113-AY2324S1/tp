package essenmakanan.ingredient;

import java.util.ArrayList;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.parser.IngredientParser;
import essenmakanan.ui.Ui;

public class IngredientList {
    private ArrayList<Ingredient> ingredients;

    public IngredientList() {
        ingredients = new ArrayList<>();
    }

    public IngredientList(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public Ingredient getIngredient(int index) {
        return ingredients.get(index);
    }

    public Ingredient getIngredient(String name) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(name)) {
                return ingredient;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return ingredients.isEmpty();
    }

    public boolean exist(String ingredientName) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(ingredientName)) {
                return true;
            }
        }
        return false;
    }

    public boolean exist(int id) {
        if (id >= 0 && id < ingredients.size()) {
            return true;
        }
        return false;
    }

    public int getIndex(Ingredient ingredient) {
        return ingredients.indexOf(ingredient);
    }

    public int getIndex(String ingredientName) {
        int i = 0;
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(ingredientName)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void editIngredient(Ingredient existingIngredient, String[] editDetails) throws EssenFormatException {
        for (int i = 1; i < editDetails.length; i++) {
            // get flag of input to know which field to edit
            String flag = editDetails[i].substring(0, 2);

            switch (flag) {
            case "n/":
                String newName = editDetails[i].substring(2);
                Ui.printEditIngredientNameSuccess(existingIngredient.getName(), newName);
                existingIngredient.setName(newName);
                break;
            case "q/":
                String newQuantity = editDetails[i].substring(2);
                Ui.printEditIngredientQuantitySuccess(existingIngredient.getQuantity(), newQuantity);
                existingIngredient.setQuantity(newQuantity);
                break;
            case "u/":
                try {
                    String newUnitString = editDetails[i].substring(2);
                    IngredientUnit newUnit = IngredientParser.mapIngredientUnit(newUnitString);
                    Ui.printEditIngredientUnitSuccess(existingIngredient.getUnit(), newUnit);
                    existingIngredient.setUnit(newUnit);
                } catch (EssenFormatException e) {
                    e.handleException();
                }
                break;
            default:
                throw new EssenFormatException();
            }
        }

    }

    public void deleteIngredient(int index) {
        Ui.printDeleteIngredientsSuccess(ingredients.get(index).getName());
        ingredients.remove(index);
    }

    public void listIngredients() {
        Ui.drawDivider();
        int count = 1;

        for (Ingredient ingredient : ingredients) {
            assert ingredients.get(count - 1).getName().equals(ingredient.getName())
                    : "Name is not matching with the current index";

            System.out.println(count + ". " + ingredient);
            count++;
        }
    }
}
