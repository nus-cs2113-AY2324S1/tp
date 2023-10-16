package seedu.financialplanner.commands;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.visualisations.Categorizer;
import seedu.financialplanner.visualisations.Visualizer;

import java.util.ArrayList;

public class VisCommand extends AbstractCommand {

    private String type;
    private String chart;

    public VisCommand(RawCommand rawCommand) throws IllegalArgumentException {
        if (!rawCommand.extraArgs.containsKey("t")) {
            throw new IllegalArgumentException("Entry type must be defined");
        }
        if (!rawCommand.extraArgs.containsKey("c")) {
            throw new IllegalArgumentException("Chart type must be defined");
        }
        this.type = rawCommand.extraArgs.get("t");
        rawCommand.extraArgs.remove("t");
        this.chart = rawCommand.extraArgs.get("c");
        rawCommand.extraArgs.remove("c");
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }
    @Override
    public void execute() throws FinancialPlannerException {
        Visualizer.displayChart(chart, Categorizer.sortType(CashflowList.INSTANCE, type));
    }
}
