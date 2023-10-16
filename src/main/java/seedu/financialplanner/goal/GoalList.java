package seedu.financialplanner.goal;
import java.util.ArrayList;
public class GoalList {
    public static final GoalList INSTANCE = new GoalList();
    private ArrayList<Goal> list = new ArrayList<>();

    private GoalList() {
    }
    public void addGoal(String label, int amount) {
        list.add(new Goal(label, amount));
    }
    public int size() {
        return list.size();
    }
    public Goal get(int index) {
        return list.get(index);
    }
    public void load(Goal goal) {
        list.add(goal);
    }
    //TODO deleteGoal
}
