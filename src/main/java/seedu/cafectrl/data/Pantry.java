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

    public Pantry() {
        this.ui = new Ui();
    }

    /**
     * Checks the stock of ingredients and dish availability based on a given order.
     */
    public void checkIngredientsStock(){
        //pass in dish when order function is completed
        String dish = "Chicken Rice";
        decreaseIngredientsStock(dish);
        checkDishAvailability();
    }

    /**
     * Decreases the stock of ingredients based on the given dish order.
     *
     * @param dish The name of the dish being ordered.
     */
    public void decreaseIngredientsStock(String dish){
        ArrayList<Ingredient> dishIngredients = retrieveIngredientsForDish(dish);
        pantryStock = retrieveStockFromStorage();

        //for each ingredient that is used in the dish, update the stock of ingredient left.
        for (Ingredient dishIngredient : dishIngredients) {
            Ingredient usedIngredientFromStock = getIngredient(dishIngredient);
            String unit = extractUnit(dishIngredient.getQuantity());
            int stockQuantity = extractQuantity(usedIngredientFromStock);
            int usedQuantity = extractQuantity(dishIngredient);

            usedIngredientFromStock.setQuantity(String.valueOf(stockQuantity-usedQuantity)+unit);
        }
        //TODO: store pantryStock to storage
    }

    /**
     * Extracts the quantity from the given ingredient quantity string.
     *
     * @param ingredient The ingredient from which quantity is extracted.
     * @return The extracted quantity as an integer.
     */
    private static int extractQuantity(Ingredient ingredient) {
        return Integer.parseInt(ingredient.getQuantity()
                .replaceAll("[^0-9]", ""));
    }

    /**
     * Extracts the unit from the given quantity string.
     *
     * @param qty The quantity string.
     * @return The extracted unit string.
     */
    public String extractUnit(String qty) {
        return qty.replaceAll("[0-9]", "");}

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
    public void checkDishAvailability(){
        for (Dish dish : menuItems) {
            ui.printLine();
            ui.showToUser("Dish: " + dish.getName());
            int numberOfDishes = checkIngredientAvailability(dish);
            ui.showToUser("Available Dishes: " + numberOfDishes);
            ui.printLine();
        }
    }

    /**
     * Checks the availability of ingredients in the pantry for a given dish order.
     *
     * @param dish The dish being ordered.
     */
    public int checkIngredientAvailability(Dish dish) {
        int maxNumofDish = Integer.MAX_VALUE;
        ArrayList<Ingredient> dishIngredients = retrieveIngredientsForDish(dish.getName());

        for (Ingredient dishIngredient : dishIngredients) {
            int numOfDish = calculateNumOfDish(dishIngredient);
            maxNumofDish = Math.min(numOfDish, maxNumofDish);

            if (numOfDish == 0) {
                handleRestock(dishIngredient);
            }
        }

        return maxNumofDish;
    }

    /**
     * Calculates the number of dishes that can be prepared with the available ingredients.
     *
     * @param dishIngredient The ingredient used in the ordered dish.
     * @return The number of dishes that can be prepared.
     */
    private int calculateNumOfDish(Ingredient dishIngredient) {
        Ingredient usedIngredientFromStock = getIngredient(dishIngredient);
        if (usedIngredientFromStock == null) {
            return 0;
        }

        int currentQuantity = extractQuantity(usedIngredientFromStock);
        int usedQuantity = extractQuantity(dishIngredient);
        return currentQuantity / usedQuantity;
    }

    /**
     * Handles the case when restocking is required for a specific ingredient.
     *
     * @param dishIngredient The ingredient for which restocking is needed.
     */
    private void handleRestock(Ingredient dishIngredient) {
        String unit = extractUnit(dishIngredient.getQuantity());
        String dishIngredientName = dishIngredient.getName();

        ui.showToUser("Please Restock: " + dishIngredientName);
        Ingredient stockIngredient = getIngredient(dishIngredient);
        int currentQuantity = (stockIngredient == null) ? 0 : extractQuantity(stockIngredient);

        ui.showToUser("Current " + dishIngredientName + ": " + currentQuantity + unit);
        ui.showToUser("Needed " + dishIngredientName + ": " + extractQuantity(dishIngredient) + unit);
    }


    /**
     * Retrieves the ingredients for a specific ordered dish.
     *
     * @param orderedDish The name of the ordered dish.
     * @return The list of ingredients for the ordered dish.
     */
    public ArrayList<Ingredient> retrieveIngredientsForDish(String orderedDish){
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



