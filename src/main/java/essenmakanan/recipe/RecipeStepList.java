package essenmakanan.recipe;

import essenmakanan.exception.EssenMakananNullInputException;
import essenmakanan.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RecipeStepList extends ArrayList<Step> {
    private ArrayList<String> steps;

    public RecipeStepList() {
        steps = new ArrayList<>();

        boolean isAddingSteps;

        do {
            Ui.drawDivider();
            System.out.println("Add steps of your recipe, type \"|\" to separate each step, press \"Enter\" to finish");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            try {
                createStepList(input);
                isAddingSteps = false;
            } catch (EssenMakananNullInputException e) {
                isAddingSteps = true;
            }
        } while (isAddingSteps);

    }

    private void createStepList(String input) throws EssenMakananNullInputException {
        if (input.length() == 0) {
            throw new EssenMakananNullInputException();
        }

        String[] totalSteps = input.split("\\|");

        for (String element : totalSteps) {
            steps.add(element.trim());
        }

    }

    public RecipeStepList(String[] steps) {
        this.steps = new ArrayList<>(Arrays.asList(steps));
    }

    public void addStep(String input) {
        steps.add(input);
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public void deleteStep(Step step) {
        remove(step);
    }
}
