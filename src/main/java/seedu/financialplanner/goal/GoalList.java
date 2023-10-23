package seedu.financialplanner.goal;
import java.util.ArrayList;
public class GoalList {
    public static final GoalList INSTANCE = new GoalList();
    public ArrayList<Goal> list = new ArrayList<>();

    private GoalList() {
    }

    public void load(Goal goal) {
        list.add(goal);
    }
    //TODO deleteGoal
}
