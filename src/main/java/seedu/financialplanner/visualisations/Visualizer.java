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

public class Visualizer {
    public static void displayChart(String chartType, Map<String, Double> expensesByCat)
            throws FinancialPlannerException {
        switch (chartType) {
        case "pie":
            displayPieChart(expensesByCat);
            break;
        case "bar":
            displayBarChart(expensesByCat);
            break;
        default:
            throw new FinancialPlannerException("Chart Type Not Found");
        }


    }
    public static void displayPieChart (Map<String, Double> expensesByCat) {
        PieChart chart = new PieChartBuilder().width(800).height(600).title("Test").build();

        // Customize Chart
        Color[] sliceColors = new Color[] {
            new Color(224, 68, 14),
            new Color(230, 105, 62),
            new Color(236, 143, 110),
            new Color(243, 180, 159),
            new Color(246, 199, 182)
        };
        chart.getStyler().setSeriesColors(sliceColors);

        for (Map.Entry<String, Double> set: expensesByCat.entrySet()) {
            chart.addSeries(set.getKey(), set.getValue());
        }
        // Show it
        JFrame swHR = new SwingWrapper<>(chart).displayChart();
        javax.swing.SwingUtilities.invokeLater(
                ()->swHR.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
        );
    }

    public static void displayBarChart (Map<String, Double> expensesByCat) {
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600)
                .title("Expenses").xAxisTitle("Type").yAxisTitle("Value").build();
        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);

        // Series
        List<Double> values = new ArrayList<Double>(expensesByCat.values());
        List<String> keys = new ArrayList<String>(expensesByCat.keySet());
        chart.addSeries("Expense", keys, values);
        JFrame swHR = new SwingWrapper<>(chart).displayChart();
        javax.swing.SwingUtilities.invokeLater(
                ()->swHR.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
        );
    }
}
