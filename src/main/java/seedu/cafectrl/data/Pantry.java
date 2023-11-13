package seedu.cafectrl.data;

import seedu.cafectrl.CafeCtrl;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.ErrorMessages;
import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Pantry {
    public static final int DEFAULT_ORDER_QTY = 1;
    private static final Logger logger = Logger.getLogger(CafeCtrl.class.getName());
    
    private final Ui ui;
    private ArrayList<Ingredient> pantryStock;

    //@@author NaychiMin
    public Pantry(Ui ui, ArrayList<Ingredient> pantryStock) {
        this.ui = ui;
        this.pantryStock = pantryStock;
    }

    //@@author ShaniceTang
    public Pantry(Ui ui) {
        this.ui = ui;
        this.pantryStock = new ArrayList<>();
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
        logger.info("Adding ingredients to stock...");
        pantryStock = getPantryStock(); //get latest pantry stock from pantry.txt
        int ingredientIndex = getIndexOfIngredient(name);

        //if ingredient exists in pantry, add quantity of that ingredient
        if (ingredientIndex != -1) {
            logger.info(name + " exists in pantry, current quantity: "+ qty);
            return addIngredientQuantity(qty, ingredientIndex, unit);
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
    private Ingredient addIngredientQuantity(int qty, int ingredientIndex, String unit) {
        Ingredient ingredient = pantryStock.get(ingredientIndex);

        if (!unit.equalsIgnoreCase(ingredient.getUnit())) {
            logger.warning("Unit does not match previous unit");
            throw new RuntimeException(ingredient.getName()
                + ErrorMessages.UNIT_NOT_MATCHING
                + ingredient.getUnit()
                + ErrorMessages.IGNORE_REMAINING_INGREDIENTS);
        }
        qty += ingredient.getQty(); //adds new qty to current qty
        ingredient.setQty(qty);
        logger.info("New quantity: " + qty);

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
            String ingredientName = pantryStock.get(i).getName().trim();

            if (name.equalsIgnoreCase(ingredientName)) {
                return i;
            }
        }
        return -1;
    }

    //@@author NaychiMin
    /**
     * Decreases the stock of ingredients based on the given dish order.
     *
     * @param dishIngredients Array of ingredients used to make the dish order.
     */
    public boolean isDishCooked(ArrayList<Ingredient> dishIngredients) {
        logger.info("Checking if dish can be cooked");

        //for each ingredient that is used in the dish, update the stock of ingredient left.
        for (Ingredient dishIngredient : dishIngredients) {
            Ingredient usedIngredientFromStock = getIngredient(dishIngredient);

            if (usedIngredientFromStock == null) {
                return false;
            }

            int stockQuantity = usedIngredientFromStock.getQty();
            int usedQuantity = dishIngredient.getQty();
            int finalQuantity = stockQuantity - usedQuantity;

            if (finalQuantity < 0) {
                return false;
            }
            usedIngredientFromStock.setQty(finalQuantity);
        }
        return true;
    }

    /**
     * Retrieves the ingredient used in the ordered dish from pantryStock.
     *
     * @param dishIngredient The ingredient used in the ordered dish.
     * @return The corresponding ingredient in pantryStock.
     */
    private Ingredient getIngredient(Ingredient dishIngredient) {
        return pantryStock.stream()
                .filter(ingredient -> ingredient.getName().trim().equalsIgnoreCase(dishIngredient.getName().trim()))
                .findFirst()
                .orElse(null);
    }

    //@@author NaychiMin
    /**
     * Checks the availability of dishes based on ingredient stock.
     */

    public void calculateDishAvailability(Menu menu, Order order) {
        logger.info("Calculating dish availability...");
        int menuSize = menu.getSize();

        for (int i = 0; i < menuSize; i++) {
            Dish dish = menu.getDishFromId(i);
            ui.showToUser("Dish: " + dish.getName());
            int numberOfDishes = calculateMaxDishes(dish, menu, order);
            ui.showDishAvailability(numberOfDishes);

            if (i != menuSize - 1) {
                ui.printLine();
            }
        }
    }
    //@@author
    /**
     * Calculates the number of dishes that can be prepared with the available ingredients.
     *
     * @param dish The dish being ordered.
     */
    public int calculateMaxDishes(Dish dish, Menu menu, Order order) {
        logger.info("Calculating max number of dishes possible...");
        int maxNumofDish = Integer.MAX_VALUE;
        boolean isRestockHeaderDisplayed = false;
        ArrayList<Ingredient> dishIngredients = retrieveIngredientsForDish(dish.getName(), menu);

        for (Ingredient dishIngredient : dishIngredients) {
            int numOfDish = calculateMaxDishForEachIngredient(dishIngredient);
            maxNumofDish = Math.min(numOfDish, maxNumofDish);

            if (!order.getIsComplete()) {
                isRestockHeaderDisplayed = showRestockHeaderIfNeeded(isRestockHeaderDisplayed);
                handleIncompleteDishCase(dishIngredient, order, numOfDish);
            } else {
                isRestockHeaderDisplayed = (numOfDish == 0) ? showRestockHeaderIfNeeded(isRestockHeaderDisplayed)
                        : isRestockHeaderDisplayed;
                handleZeroDishCase(dishIngredient, numOfDish);
            }
        }

        return maxNumofDish;
    }

    private boolean showRestockHeaderIfNeeded(boolean isRestockHeaderDisplayed) {
        if (!isRestockHeaderDisplayed) {
            ui.showToUser(Messages.RESTOCK_CORNER, Messages.RESTOCK_TITLE, Messages.RESTOCK_CORNER);
            isRestockHeaderDisplayed = true;
        }
        return isRestockHeaderDisplayed;
    }

    private void handleIncompleteDishCase(Ingredient dishIngredient, Order order, int numOfDish) {
        int orderQuantity = order.getQuantity();

        if (numOfDish < orderQuantity) {
            handleRestock(dishIngredient, orderQuantity);
        }
    }
    private void handleZeroDishCase(Ingredient dishIngredient, int numOfDish) {
        if (numOfDish == 0) {
            handleRestock(dishIngredient, DEFAULT_ORDER_QTY);
        }
    }

    /**
     * Calculates the number of dishes that can be prepared with the provided ingredients.
     *
     * @param dishIngredient The ingredient used in the ordered dish.
     * @return The number of dishes that can be prepared.
     */
    private int calculateMaxDishForEachIngredient(Ingredient dishIngredient) {
        logger.info("Calculating max dish for each ingredient...");
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
    private void handleRestock(Ingredient dishIngredient, int dishQty) {
        String dishIngredientName = dishIngredient.getName();
        Ingredient stockIngredient = getIngredient(dishIngredient);

        int currentQuantity = (stockIngredient == null) ? 0 : stockIngredient.getQty();
        String unit = dishIngredient.getUnit();
        int neededQuantity = dishIngredient.getQty() * dishQty;
        ui.showNeededRestock(dishIngredientName, currentQuantity, unit, neededQuantity);
    }

    /**
     * Retrieves the ingredients for a specific ordered dish.
     *
     * @param orderedDish The name of the ordered dish.
     * @return The list of ingredients for the ordered dish.
     */
    public ArrayList<Ingredient> retrieveIngredientsForDish(String orderedDish, Menu menu) {
        ArrayList<Ingredient> dishIngredients = new ArrayList<>();

        //retrieving the ingredients for orderedDish
        for (Dish dish : menu.getMenuItemsList()) {
            if (dish.getName().equals(orderedDish)) {
                dishIngredients.addAll(dish.getIngredients());
                break;
            }
        }
        return dishIngredients;
    }
}
