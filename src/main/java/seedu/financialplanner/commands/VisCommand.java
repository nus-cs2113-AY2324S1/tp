package seedu.financialplanner.commands;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.visualisations.Categorizer;
import seedu.financialplanner.visualisations.Visualizer;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VisCommand extends Command {
    private static final Logger logger = Logger.getLogger("Financial Planner Logger");
    private String type;
    private String chart;

    public VisCommand(RawCommand rawCommand) throws IllegalArgumentException {
        if (!rawCommand.extraArgs.containsKey("t")) {
            throw new IllegalArgumentException("Entry type must be defined");
        }
        if (!rawCommand.extraArgs.containsKey("c")) {
            throw new IllegalArgumentException("Chart type must be defined");
        }
        logger.log(Level.INFO, "Parsing entry type and chart type");
        this.type = rawCommand.extraArgs.get("t");
        rawCommand.extraArgs.remove("t");
        this.chart = rawCommand.extraArgs.get("c");
        rawCommand.extraArgs.remove("c");
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            logger.log(Level.WARNING, "Invalid extra arguments found");
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    @Override
    public void execute() throws FinancialPlannerException {
        assert !chart.isEmpty();
        assert !type.isEmpty();
        Visualizer.displayChart(chart, Categorizer.sortType(CashflowList.getInstance(), type));
    }
}
