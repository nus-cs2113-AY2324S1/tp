package essenmakanan.ui;

public class Ui {

    private final String DIVIDER = "--------------------------------------------";
    public void start() {
        System.out.println("Welcome to EssenMakanan!!! A one-stop place " +
                "to track the\ningredients in your kitchen and store " +
                "your favourite recipes");

        System.out.println(DIVIDER);
    }

    public void bye() {
        System.out.println("Hope you had fun! See you again!");
    }

    public void functionSelect() {
        System.out.println("Enter the number for the function you want");
        System.out.println("1. Recipe");
        System.out.println("2. Ingredient");

        System.out.println(DIVIDER);
    }

    public void drawDivider() {
        System.out.println(DIVIDER);
    }

    public void showRecipeFunctions() {
        System.out.println("- Add recipe. [add r/RECIPE_NAME]");
        System.out.println("- Delete recipe. [delete r/RECIPE_ID]");
        System.out.println("- View all recipes. [view r]");
    }

    public void showIngredientFunctions() {
        System.out.println("- Add ingredient. [add i/INGREDIENT_NAME]");
        System.out.println("- Delete ingredient. [delete i/INGREDIENT_ID]");
        System.out.println("- View all ingredients. [view i]");
    }


}
