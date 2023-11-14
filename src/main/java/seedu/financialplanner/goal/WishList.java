package seedu.financialplanner.goal;

import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.utils.Ui;

import java.util.ArrayList;
public class WishList {
    private static WishList wishList = null;
    public final ArrayList<Goal> list = new ArrayList<>();
    private WishList() {
    }

    public static WishList getInstance() {
        if (wishList == null) {
            wishList = new WishList();
        }
        return wishList;
    }


    /**
     * Loads a goal to the wish list from file.
     *
     * @param goal The goal to be added.
     */
    public void load(Goal goal) {
        list.add(goal);
    }


    /**
     * Deletes a goal from the wish list.
     *
     * @param index
     */
    public void deleteGoal(int index) {
        int existingListSize = list.size();
        int listIndex = index;
        assert listIndex >= 0  && listIndex < existingListSize;
        list.remove(listIndex);
    }

    public void addGoal(String label, int amount){
        Goal goal = new Goal(label, amount);
        list.add(goal);
        Ui.getInstance().showMessage("You have added " + goal);
    }

    public void markGoal(int index) {
        Goal goal = list.get(index - 1);
        goal.markAsDone();
        Ui.getInstance().showMessage("You have achieved " + goal + System.lineSeparator() + "Congratulations!");
        CashflowList.getInstance().addExpense(goal.getAmount(), ExpenseType.OTHERS, 0, goal.getLabel());
    }
    /**
     * Formats the reminder list into an easy-to-read format to be output to the user.
     *
     * @return The formatted reminder list.
     */
    public String toString() {
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                result += String.format("%d. %s", i + 1, list.get(i));
            } else {
                result += String.format("%d. %s\n", i + 1, list.get(i));
            }
        }
        return result;
    }
}
