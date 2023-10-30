package seedu.cafectrl.data;

import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;

public class Pantry {
    private ArrayList<Ingredient> pantryStock;
    private ArrayList<Dish> menuItems;
    private Ui ui;

    public Pantry(Ui ui, ArrayList<Ingredient> pantryStock) {
        this.ui = ui;
        this.pantryStock = pantryStock;
    }

    public Pantry(Ui ui) {
        this.ui = ui;
        this.pantryStock = new ArrayList<Ingredient>();
    }

    /**
     * Retrieves the current pantry stock from storage, which may include reading from a file (pantry.txt).
     *
     * @return An ArrayList of Ingredient objects representing the current pantry stock.
     */
    public ArrayList<Ingredient> getPantryStock() {
        return pantryStock;
    }

    /**
     * Adds or updates an ingredient in the pantry stock based on its name and quantity.
     *
     * @param name The name of the ingredient to add or update.
     * @param qty The quantity of the ingredient (e.g., "100g").
     * @param unit The unit of measurement for the quantity.
     * @return The Ingredient object that was added or updated in the pantry stock.
     */
    public Ingredient addIngredientToStock (String name, int qty, String unit) {
        pantryStock = getPantryStock(); //get latest pantry stock from pantry.txt
        int ingredientIndex = getIndexOfIngredient(name);
        //TODO: Add error handling for invalid index

        //if ingredient exists in pantry, add quantity of that ingredient
        if (ingredientIndex != -1) {
            return addIngredientQuantity(qty, ingredientIndex);
        }

        //else, add new ingredient to pantry
        Ingredient ingredient = new Ingredient(name, qty, unit);
        pantryStock.add(ingredient);
        return ingredient;
    }

    /**
     * Updates an ingredient's quantity in the pantry stock or adds a new ingredient if it doesn't exist.
     *
     * @param qty            The quantity of the ingredient (e.g., "100g").
     * @param ingredientIndex The index of the ingredient in the pantry stock (-1 if not found).
     * @return The Ingredient object that was added or updated in the pantry stock.
     */
    private Ingredient addIngredientQuantity(int qty, int ingredientIndex) {
        Ingredient ingredient = pantryStock.get(ingredientIndex);
        qty += ingredient.getQty(); //adds new qty to current qty
        ingredient.setQty(qty);
        return ingredient;
    }

    /**
     * Gets the index of an ingredient in the pantry stock based on its name (case-insensitive comparison).
     *
     * @param name The name of the ingredient to search for.
     * @return The index of the ingredient in the pantry stock or -1 if not found.
     */
    private int getIndexOfIngredient(String name) {
        for (int i = 0; i < pantryStock.size(); i++) {
            if (name.equalsIgnoreCase(pantryStock.get(i).getName().trim())) {
                return i;
            }
        }
        return -1;
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
        //pantryStock = retrieveStockFromStorage();

        //for each ingredient that is used in the dish, update the stock of ingredient left.
        for (Ingredient dishIngredient : dishIngredients) {
            Ingredient usedIngredientFromStock = getIngredient(dishIngredient);
            int stockQuantity = usedIngredientFromStock.getQty();
            int usedQuantity = dishIngredient.getQty();
            int finalQuantity = stockQuantity-usedQuantity;

            usedIngredientFromStock.setQty(finalQuantity);
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
     * Provides dummy data for menu items. This function will be removed when storage is implemented.
     *
     * @return The list of dummy menu items.
     */
    private ArrayList<Dish> dummyData() {
        //dummy data for pantry stock which will be replaced with storage data
        menuItems = new ArrayList<>();
        menuItems.add(new Dish("Chicken Rice",
                new ArrayList<>(Arrays.asList(new Ingredient("Rice", 300, "g"),
                        new Ingredient("Chicken", 100, "g"))), 8.0F));
        menuItems.add(new Dish("Chicken Sandwich",
                new ArrayList<>(Arrays.asList(new Ingredient("Lettuce", 50, "g"),
                        new Ingredient("Chicken", 50, "g"))), 5.0F));
        return menuItems;
    }
}


