package fittrack.command;

import fittrack.MealList;
import fittrack.UserProfile;
import fittrack.WorkList;

public abstract class Command {
    protected UserProfile userProfile;
    protected MealList mealList;
    protected WorkList workList;

    public void setData(UserProfile userProfile, MealList mealList, WorkList workList) {
        this.userProfile = userProfile;
        this.mealList = mealList;
        this.workList = workList;
    }

    public abstract CommandResult execute();
}
