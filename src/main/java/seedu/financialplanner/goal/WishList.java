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
        Goal toRemove = list.get(listIndex);
        list.remove(listIndex);
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
