package essenmakanan.recipe;

public class Step {

    private String description;

    private Tag tag;

    private int estimatedDuration;

    public Step(String description, Tag tag) {
        this.description = description;
        this.tag = tag;
        this.estimatedDuration = 0;
    }

    public Step(String description) {
        this.description = description;
        this.estimatedDuration = 0;
        this.tag = Tag.ACTUAL_COOKING;
    }

    public Step(String description, Tag tag, int estimatedDuration) {
        this.description = description;
        this.tag = tag;
        this.estimatedDuration = estimatedDuration;
    }

    public Step(String description, int estimatedDuration) {
        this.description = description;
        this.estimatedDuration = estimatedDuration;
        this.tag = Tag.ACTUAL_COOKING;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public int getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    /**
     * Converts step description to step id template. Eg: "Wash vegetables" -> "Wash vegetables (step id = 1)"
     *
     * @param stepDescription step description
     * @param id              step id
     * @return step id template
     */
    public static String convertToStepIdTemplate(String stepDescription, int id) {
        return stepDescription + (" (step id = " + id + ")");
    }

    @Override
    public String toString() {
        String detailedStep = "You need to " + getDescription();
        if (estimatedDuration == 0) {
            return detailedStep;
        } else {
            return detailedStep + " for " + estimatedDuration + " minutes.";
        }
    }
}
