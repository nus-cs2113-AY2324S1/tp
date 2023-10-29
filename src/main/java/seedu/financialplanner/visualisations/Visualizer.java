package seedu.financialplanner.visualisations;


import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;
import seedu.financialplanner.exceptions.FinancialPlannerException;

import javax.swing.JFrame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Visualizer {
    private static final Logger logger = Logger.getLogger("Financial Planner Logger");

    public static void displayChart(String chart, Map<String, Double> cashFlowByCat, String type)
            throws FinancialPlannerException {
        switch (chart) {
        case "pie":
            displayPieChart(cashFlowByCat, type);
            break;
        case "bar":
            displayBarChart(cashFlowByCat, type);
            break;
        default:
            throw new FinancialPlannerException(chart + " Chart Type Not Found");
        }
    }

    public static void displayPieChart (Map<String, Double> expensesByCat, String type) {
        PieChart chart = new PieChartBuilder().width(800).height(600).title(type).build();

        // Customize Chart
        Color[] sliceColors = new Color[] {
            new Color(21, 224, 14),
            new Color(62, 154, 230),
            new Color(236, 186, 110),
            new Color(243, 159, 242),
            new Color(246, 182, 197),
            new Color(210, 24, 24),
            new Color(211, 164, 8),
        };
        chart.getStyler().setSeriesColors(sliceColors);

        for (Map.Entry<String, Double> set: expensesByCat.entrySet()) {
            chart.addSeries(set.getKey(), set.getValue());
        }
        logger.log(Level.INFO, "Displaying Pie Chart");
        // Show it
        JFrame swHR = new SwingWrapper<>(chart).displayChart();
        javax.swing.SwingUtilities.invokeLater(
                ()->swHR.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
        );
    }

    public static void displayBarChart (Map<String, Double> expensesByCat, String type) {
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600)
                .title(type).xAxisTitle("Type").yAxisTitle("Value").build();
        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);
        assert !expensesByCat.isEmpty();
        // Series
        List<Double> values = new ArrayList<Double>(expensesByCat.values());
        List<String> keys = new ArrayList<String>(expensesByCat.keySet());
        chart.addSeries("Expense", keys, values);

        logger.log(Level.INFO, "Displaying Bar Chart");
        JFrame swHR = new SwingWrapper<>(chart).displayChart();
        javax.swing.SwingUtilities.invokeLater(
                ()->swHR.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
        );
    }
}
