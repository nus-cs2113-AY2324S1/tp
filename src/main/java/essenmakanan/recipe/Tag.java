package essenmakanan.recipe;

public enum Tag {
    NIGHT_BEFORE(1),
    MORNING_OF_COOKING(2),
    MORE_THAN_ONE_DAY(3),
    ACTUAL_COOKING(4);

    private int priority;

    Tag(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public static Tag getByPriority(int priority) {
        for (Tag tag : values()) {
            if (tag.getPriority() == priority) {
                return tag;
            }
        }
        throw new IllegalArgumentException("No enum constant with priority " + priority);
    }

    public int hasHigherPriorityThan(Tag otherTag) {
        if (this.priority > otherTag.priority) {
            return 1;
        } else if (this.priority < otherTag.priority) {
            return -1;
        } else {
            return 0;
        }
    }


}
