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

    /**
     * To add step by as a string of array into the recipe steps
     *
     * @param inputSteps total steps for a recipe
     */
    public RecipeStepList(String[] inputSteps) {
        Step step = null;
        for (String stepString : inputSteps) {
            if (stepString.contains("t/") && stepString.contains("d/")) {
                step = this.createStepWithTagAndDuration(stepString);
                assert step != null : "Step is not initialised";
            } else if (stepString.contains("t/")) {
                // step with tag only
                step = this.createStepWithTag(stepString);
                assert step != null : "Step is not initialised";
            } else if (stepString.contains("d/")) {
                // step with duration only
                step = this.createStepWithDuration(stepString);
                assert step != null : "Step is not initialised";
            }else {
                // only step description
                step = new Step(stepString);
            }

            this.addStep(step);

        }
    }

    /**
     * To create step that only has a tag
     *
     * @param stepString is the step with tag
     * @return step with tag
     */
    public Step createStepWithTag(String stepString) {
        String tagValue;

        String[] stepStringSplit = stepString.split("t/");

        // step description
        String stepDescription = stepStringSplit[0].trim();

        // get tag
        tagValue = stepStringSplit[1];
        Tag tag = this.obtainTag(tagValue);
        return new Step(stepDescription, tag);
    }

    /**
     * To create step that only has a duration
     *
     * @param stepString is the step with duration
     * @return step with duration
     */
    public Step createStepWithDuration(String stepString) {

        String[] stepStringSplit = stepString.split("d/");

        // step description
        String stepDescription = stepStringSplit[0].trim();

        // get duration (in minutes)
        String durationString = stepStringSplit[1];
        int duration = Integer.parseInt(durationString);
        return new Step(stepDescription, duration);
    }

    /**
     * To create step that has a tag and duration
     *
     * @param stepString is the step with tag and duration
     * @return step with tag and duration
     */
    public Step createStepWithTagAndDuration(String stepString) {

        // by implementation, if tag exists, it will be before duration
        int tagFlag = stepString.indexOf("t/");
        int durationFlag = stepString.indexOf("d/");

        // step description
        String stepDescription = stepString.substring(0, tagFlag).trim();

        // get tag
        String tagValue = stepString.substring(tagFlag + 2, durationFlag).trim();
        Tag tag = this.obtainTag(tagValue);

        // get duration (in minutes)
        String durationString = stepString.substring(durationFlag + 2).trim();
        int duration = Integer.parseInt(durationString);

        return new Step(stepDescription, tag, duration);
    }

    /**
     * To obtain tag from string
     *
     * @param tagValue is the tag value
     * @return tag
     */
    public Tag obtainTag(String tagValue) {
        try {
            return Tag.mapStringToTag(tagValue);
        } catch (EssenInvalidEnumException e) {
            System.out.println("No such tag");
        }
        return null;
    }

    /**
     * To add step by string into the recipe steps
     *
     * @param stepString is the step
     */
    public void addStep(String stepString) {
        Step step = new Step(stepString);
        this.steps.add(step);
    }

    /**
     * To add step object into the recipe steps
     *
     * @param step is the step object
     */
    public void addStep(Step step) {
        this.steps.add(step);
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    /**
     * To get step by index
     *
     * @param index of the step to be retrieved
     */
    public Step getStepByIndex(int index) {
        return steps.get(index);
    }

    /**
     * To get step by name
     *
     * @param name of the step to be retrieved
     */
    public int getStepIndexByName(String name) {
        for (int i = 0; i < steps.size(); i++) {
            if (steps.get(i).getDescription().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public void deleteStep(Step step) {
        steps.remove(step);
    }

}
