package essenmakanan.recipe;

import essenmakanan.exception.EssenInvalidEnumException;

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
        System.out.println("Finished adding steps!");
    }

    public RecipeStepList(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public RecipeStepList(String[] inputSteps) {
        String tagValue;
        Step step = null;
        for (String stepString : inputSteps) {
            // check if step has tag
            if (stepString.indexOf("t/") != -1) {
                String[] stepStringSplit = stepString.split("t/");

                // step description
                stepString = stepStringSplit[0].trim();

                // get tag
                tagValue = stepStringSplit[1];
                try {
                    Tag tag = Tag.mapStringToTag(tagValue);
                    step = new Step(stepString, tag);
                } catch (EssenInvalidEnumException e) {
                    System.out.println("No such Tag");
                }

            } else {
                step = new Step(stepString);
            }

            // step should not be null here, either with or without tag, error handled before
            assert (step != null) : "Step is not initialised";
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
