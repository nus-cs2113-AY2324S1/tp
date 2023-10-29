package essenmakanan.recipe;

import java.util.ArrayList;
import java.util.Scanner;

public class RecipeStepList {
    private ArrayList<Step> steps = new ArrayList<>();

    public RecipeStepList() {
        Scanner in = new Scanner(System.in);
        String input;
        boolean isAddingSteps = true;

        do {
            System.out.println("Add steps of your recipe, type \"end\" to finish");
            input = in.nextLine();

            if (input.equals("end")) {
                isAddingSteps = false;
            } else {
                assert (input != null) : "Input is null";
                this.addStep(input);
            }
        } while (isAddingSteps);
        System.out.println("done steps");
    }

    public RecipeStepList(String[] inputSteps) {

        for (String stepString : inputSteps) {
            Step step = new Step(stepString);
            this.addStep(step);
        }
    }

    public void addStep(String stepString) {
        Step step = new Step(stepString);
        this.steps.add(step);
    }

    public void addStep(Step step) {
        this.steps.add(step);
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public Step getStepByIndex(int index) {
        return steps.get(index);
    }

    public void deleteStep(Step step) {
        steps.remove(step);
    }

}
