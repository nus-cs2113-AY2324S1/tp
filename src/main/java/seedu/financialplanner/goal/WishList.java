package seedu.financialplanner.goal;

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

    public void load(Goal goal) {
        list.add(goal);
    }

    public void deleteGoal(int index) {
        int existingListSize = list.size();
        int listIndex = index;
        assert listIndex >= 0  && listIndex < existingListSize;
        Goal toRemove = list.get(listIndex);
        list.remove(listIndex);
    }
    public String toString() {
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            result += String.format("%d. %s\n", i + 1, list.get(i));
        }
        return result;
    }
}
