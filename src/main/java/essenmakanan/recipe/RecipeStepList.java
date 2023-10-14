package essenmakanan.recipe;

import java.util.ArrayList;

public class RecipeStepList extends ArrayList<Step> {
    public void addStep(Step step) {
        add(step);
    }

    public void deleteStep(Step step) {
        remove(step);
    }

}
