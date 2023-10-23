package seedu.cafectrl.data;

import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;

public class Pantry {

    private ArrayList<Ingredient> pantryStock;
    private ArrayList<Dish> menuItems;
    private  Ui ui;

    public Pantry(Ui ui) {
        this.ui = ui;
    }

    /**
     * Checks the stock of ingredients and dish availability based on a given order.
     */
    public void checkIngredientsStock(){
        //the dish variable and dishIngredients array will be removed
        // when order class is implemented as it will pass in dishIngredients
        String dish = "Chicken Rice";
        ArrayList<Ingredient> dishIngredients = retrieveIngredientsForDish(dish);
        decreaseIngredientsStock(dishIngredients);
        calculateDishAvailability();
    }

    /**
     * Decreases the stock of ingredients based on the given dish order.
     *
     * @param dishIngredients Array of ingredients used to make the dish order.
     */
    public void decreaseIngredientsStock(ArrayList<Ingredient> dishIngredients){
        pantryStock = retrieveStockFromStorage();

        //for each ingredient that is used in the dish, update the stock of ingredient left.
        for (Ingredient dishIngredient : dishIngredients) {
            Ingredient usedIngredientFromStock = getIngredient(dishIngredient);
            int stockQuantity = usedIngredientFromStock.getQty();
            int usedQuantity = dishIngredient.getQty();
            int finalQuantity = stockQuantity-usedQuantity;

            usedIngredientFromStock.setQuantity(String.valueOf(finalQuantity)+dishIngredient.getUnit());
        }
        //TODO: store pantryStock to storage
    }

    /**
     * Retrieves the ingredient used in the ordered dish from pantryStock.
     *
     * @param dishIngredient The ingredient used in the ordered dish.
     * @return The corresponding ingredient in pantryStock.
     */
    private Ingredient getIngredient(Ingredient dishIngredient) {
        return pantryStock.stream()
                .filter(ingredient -> ingredient.getName().equals(dishIngredient.getName()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Checks the availability of dishes based on ingredient stock.
     */
    public void calculateDishAvailability(){
        for (Dish dish : menuItems) {
            ui.showToUser("Dish: " + dish.getName());
            int numberOfDishes = calculateMaxDishes(dish);
            ui.showDishAvailability(numberOfDishes);
        }
    }

    /**
     * Calculates the number of dishes that can be prepared with the available ingredients.
     *
     * @param dish The dish being ordered.
     */
    public int calculateMaxDishes(Dish dish) {
        int maxNumofDish = Integer.MAX_VALUE;
        //This function will be replaced by the function used
        //to retrieve dishIngredients when order class is implemented
        ArrayList<Ingredient> dishIngredients = retrieveIngredientsForDish(dish.getName());

        for (Ingredient dishIngredient : dishIngredients) {
            int numOfDish = calculateMaxDishForEachIngredient(dishIngredient);
            maxNumofDish = Math.min(numOfDish, maxNumofDish);

            if (numOfDish == 0) {
                handleRestock(dishIngredient);
            }
        }

        return maxNumofDish;
    }

    /**
     * Calculates the number of dishes that can be prepared with the provided ingredients.
     *
     * @param dishIngredient The ingredient used in the ordered dish.
     * @return The number of dishes that can be prepared.
     */
    private int calculateMaxDishForEachIngredient(Ingredient dishIngredient) {
        Ingredient usedIngredientFromStock = getIngredient(dishIngredient);
        if (usedIngredientFromStock == null) {
            return 0;
        }

        int currentQuantity = usedIngredientFromStock.getQty();
        int usedQuantity = dishIngredient.getQty();
        return currentQuantity / usedQuantity;
    }

    /**
     * Handles the case when restocking is required for a specific ingredient.
     *
     * @param dishIngredient The ingredient for which restocking is needed.
     */
    private void handleRestock(Ingredient dishIngredient) {
        String dishIngredientName = dishIngredient.getName();
        Ingredient stockIngredient = getIngredient(dishIngredient);

        int currentQuantity = (stockIngredient == null) ? 0 : stockIngredient.getQty();
        String unit = dishIngredient.getUnit();
        String neededIngredient = dishIngredient.toString();
        ui.showNeededRestock(dishIngredientName, currentQuantity, unit, neededIngredient);
    }


    /**
     * Retrieves the ingredients for a specific ordered dish.
     *
     * @param orderedDish The name of the ordered dish.
     * @return The list of ingredients for the ordered dish.
     */
    public ArrayList<Ingredient> retrieveIngredientsForDish(String orderedDish){
        //function will be removed once order class is implemented
        ArrayList<Dish> menuItems = dummyData();
        ArrayList<Ingredient> dishIngredients = new ArrayList<>();

        //retrieving the ingredients for orderedDish
        for (Dish dish : menuItems) {
            if (dish.getName().equals(orderedDish)) {
                dishIngredients.addAll(dish.getIngredients());
                break;
            }
        }
        return dishIngredients;
    }

    /**
     * Retrieves the current stock of ingredients from storage.
     *
     * @return The list of ingredients representing the current stock.
     */
    public ArrayList<Ingredient> retrieveStockFromStorage () {
        //dummy data for pantry stock which will be replaced with storage data
        ArrayList<Ingredient> pantryStock = new ArrayList<>(Arrays.asList(new Ingredient("Rice", "600g"),
                new Ingredient("Chicken", "150g"), new Ingredient("Bread", "2 pieces"),
                new Ingredient("Lettuce", "50g")));
        return pantryStock;
    }

    /**
     * Provides dummy data for menu items. This function will be removed when storage is implemented.
     *
     * @return The list of dummy menu items.
     */
    private ArrayList<Dish> dummyData() {
        //dummy data for pantry stock which will be replaced with storage data
        menuItems = new ArrayList<>();
        menuItems.add(new Dish("Chicken Rice",
                new ArrayList<>(Arrays.asList(new Ingredient("Rice", "300g"),
                        new Ingredient("Chicken", "100g"))), 8.0F));
        menuItems.add(new Dish("Chicken Sandwich",
                new ArrayList<>(Arrays.asList(new Ingredient("Lettuce", "50g"),
                        new Ingredient("Chicken", "50g"))), 5.0F));
        return menuItems;
    }
}



