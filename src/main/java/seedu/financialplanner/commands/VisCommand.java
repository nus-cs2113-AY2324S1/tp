package seedu.financialplanner.commands;

import seedu.financialplanner.commands.utils.Command;
import seedu.financialplanner.commands.utils.RawCommand;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.utils.Ui;
import seedu.financialplanner.visualisations.Categorizer;
import seedu.financialplanner.visualisations.Visualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Command class that inherit from Command abstract class
 * Represents the command to visualize your cash flow
 */
@SuppressWarnings("unused")
public class VisCommand extends Command {

    public static final String NAME = "vis";

    public static final String USAGE =
            "vis </t TYPE> </c pie/bar/radar>";

    public static final String EXAMPLE =
            "vis /t expense /c pie" + "\n" +
            "vis /t income /c bar";

    private static final Logger logger = Logger.getLogger("Financial Planner Logger");
    private String type;
    private String chart;

    /**
     * Constructor for the command to visualize cash flow
     *
     * @param rawCommand
     * @throws IllegalArgumentException
     */
    public VisCommand(RawCommand rawCommand) throws IllegalArgumentException {
        if (!rawCommand.extraArgs.containsKey("t")) {
            throw new IllegalArgumentException("Entry type must be defined");
        }
        if (!rawCommand.extraArgs.containsKey("c")) {
            throw new IllegalArgumentException("Chart type must be defined");
        }
        logger.log(Level.INFO, "Parsing entry type and chart type");
        this.type = rawCommand.extraArgs.get("t").toLowerCase().trim();
        rawCommand.extraArgs.remove("t");
        this.chart = rawCommand.extraArgs.get("c").toLowerCase().trim();
        rawCommand.extraArgs.remove("c");
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            logger.log(Level.WARNING, "Invalid extra arguments found");
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    /**
     * Executes the command to visualize cash flow
     *
     * @throws FinancialPlannerException
     */
    @Override
    public void execute() throws FinancialPlannerException {
        Ui ui = Ui.getInstance();

        assert !chart.isEmpty();
        assert !type.isEmpty();

        HashMap<String, Double> cashflowByType = Categorizer.sortType(CashflowList.getInstance(), type);
        if (cashflowByType.isEmpty()) {
            ui.printEmptyCashFlow(type);
            return;
        }
        ui.printDisplayChartMessage(type, chart);
        Visualizer.displayChart(chart, cashflowByType, type);
    }
}
