package essenmakanan.recipe;

import java.util.ArrayList;
import java.util.Collection;

public class RecipeStepList extends ArrayList<Step> {
    public void addStep(Step step) {
        add(step);
    }

    public void deleteStep(Step step) {
        remove(step);
    }

    public RecipeStepList() {
    }

    public RecipeStepList(Collection<? extends Step> c) {
        super(c);
    }
}
