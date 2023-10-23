package cashleh.budget;

/**
 * This class creates an object called Progress which controls the state of the budget 
 * relative to recent incomes and expenses.
 * It provides a method for displaying the progress as a bar chart.
 */
public class Progress {
    private double progress;

    public Progress(double cashOnHand, double budgetAmount) {
        assert budgetAmount != 0;
        this.progress = (cashOnHand / budgetAmount);
        if (this.progress <= 0) {
            /*
            Progress below zero implies a negative cash on hand. Accordingly, we set the progress
            equal to one (or 100%), so to imply that the budget has been fully used
            and the user has a budget deficit.
             */
            this.progress = 0;
        } else if (this.progress > 1) {
            /*
            Progress larger than one implies that the net cash on hand in larger than the
            budget. Accordingly, we set the progress equal to 0 (0%), so to imply that the
            full amount of the budget is still available for spending (and more...).
             */
            this.progress = 1;
        }
    }
    public double getProgress() {
        return this.progress;
    }

    /**
     * Displays the percentage as a bar chart of style [****------] 40.00%.
     * @return a String containing the bar chart and some data.
     */
    public String displayProgressBar() {
        String progressBar = buildProgressBar();
        return progressBar;
    }

    /**
     * Builds the Progress Bar.
     * @return a String containing the progress bar to be displayed.
     */
    private String buildProgressBar() {
        double maxBarLength = 30; // length of the progress bar chart to be displayed
        double reachedPercent =  this.progress * maxBarLength; // percent of bar to be filled
        assert reachedPercent <= maxBarLength;

        String emptyBar = "-";
        String fullBar = "*";
        StringBuilder barBuilder = new StringBuilder();
        barBuilder.append("[");
        for (int i = 0; i < reachedPercent; i++) {
            barBuilder.append(fullBar);
        }
        for (int i = (int) reachedPercent; i < maxBarLength; i++) {
            barBuilder.append(emptyBar);
        }
        barBuilder.append("] ").append(String.format("%.2f", (this.progress) * 100)).append("%");
        return String.valueOf(barBuilder);
    }
}
