package essenmakanan.ui;

public class Ui {

    private final String DIVIDER = "--------------------------------------------";
    public void start() {
        System.out.println("Welcome to EssenMakanan!!! A one-stop place" +
                "to track the\n ingredients in your kitchen and store" +
                "your favourite recipes");

        System.out.println(DIVIDER);
    }

    public void bye() {
        System.out.println("Hope you had fun! See you again!");
    }

    public void functionSelect() {
        System.out.println("Enter the number for the function you want \n" +
                "1. Recipe \n" +
                "2. Ingredients");

        System.out.println(DIVIDER);
    }

    public void drawDivider() {
        System.out.println(DIVIDER);
    }

    public void showRecipeFunctions() {
        System.out.println("1. Add recipe. [add r/RECIPE_NAME]");
        System.out.println("2. Delete recipe. [delete r/RECIPE_ID]");
        System.out.println("3. View all recipes. [view r]");
    }

    public void showIngredientFunctions() {
        System.out.println("1. Add ingredient. [add i/INGREDIENT_NAME]");
        System.out.println("2. Delete recipe. [delete i/INGREDIENT_ID]");
        System.out.println("3. View all recipes. [view i]");
    }


}
