package seedu.financialplanner.commands;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.list.FinancialList;
import seedu.financialplanner.utils.Ui;
import seedu.financialplanner.visualisations.Categorizer;
import seedu.financialplanner.visualisations.Visualizer;

public class VisCommand extends Command {

    private String type;
    private String chart;

    public VisCommand(String type, String chart) {
        this.type = type;
        this.chart = chart;
    }
    @Override
    public void execute(Ui ui, FinancialList financialList, WatchList watchList) throws FinancialPlannerException {
        Visualizer.displayChart(chart, Categorizer.SortExpenses(financialList));
    }
}
