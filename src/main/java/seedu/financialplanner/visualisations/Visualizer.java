package seedu.financialplanner.visualisations;


import org.apache.commons.lang3.StringUtils;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.RadarChart;
import org.knowm.xchart.RadarChartBuilder;
import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;
import seedu.financialplanner.exceptions.FinancialPlannerException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Visualizer {
    private static final Logger logger = Logger.getLogger("Financial Planner Logger");

    public static void displayChart(String chart, HashMap<String, Double> cashFlowByCat, String type)
            throws FinancialPlannerException {
        switch (chart) {
        case "pie":
            displayPieChart(cashFlowByCat, type);
            break;
        case "bar":
            displayBarChart(cashFlowByCat, type);
            break;
        case "radar":
            displayRadarChart(cashFlowByCat, type);
            break;
        default:
            throw new FinancialPlannerException(chart + " Chart Type Not Found");
        }
    }

    public static void displayPieChart (HashMap<String, Double> cashflowByCat, String type) {
        PieChart chart = new PieChartBuilder().width(800).height(600)
                .title(StringUtils.capitalize(type) + " Chart")
                .build();

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

        for (Map.Entry<String, Double> set: cashflowByCat.entrySet()) {
            chart.addSeries(set.getKey(), set.getValue());
        }
        logger.log(Level.INFO, "Displaying Pie Chart");
        // Show it
        JFrame swHR = new SwingWrapper<>(chart).displayChart();
        javax.swing.SwingUtilities.invokeLater(
                ()->swHR.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
        );
    }

    public static void displayBarChart (HashMap<String, Double> cashflowByCat, String type) {
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600)
                .title(StringUtils.capitalize(type) + " Chart")
                .xAxisTitle(StringUtils.capitalize(type) + " Type")
                .yAxisTitle("Value")
                .build();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        assert !cashflowByCat.isEmpty();
        // Series
        List<Double> values = new ArrayList<Double>();
        List<String> keys = new ArrayList<String>();
        for (Map.Entry<String, Double> set : cashflowByCat.entrySet()) {
            keys.add(set.getKey());
            values.add(set.getValue());
        }
        chart.addSeries(StringUtils.capitalize(type), keys, values);

        logger.log(Level.INFO, "Displaying Bar Chart");
        JFrame swHR = new SwingWrapper<>(chart).displayChart();
        SwingUtilities.invokeLater(
                ()->swHR.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
        );
    }


    public static void displayRadarChart (HashMap<String, Double> cashflowByCat, String type)
            throws FinancialPlannerException {
        RadarChart radarChart = new RadarChartBuilder().width(800).height(600)
                .title(StringUtils.capitalize(type) + " Chart")
                .build();

        // Customize Chart
        Color[] sliceColors = new Color[] {
            new Color(62, 154, 230),
        };

        radarChart.getStyler().setSeriesColors(sliceColors);

        String[] keys;
        switch (type) {
        case "income":
            keys = IncomeType.getNames(IncomeType.class);
            break;
        case "expense":
            keys = ExpenseType.getNames(ExpenseType.class);
            break;
        default:
            throw new FinancialPlannerException("Error displaying RadarChart");
        }
        if (keys.length == 0) {
            throw new FinancialPlannerException("Error displaying RadarChart");
        }
        double[] values = new double[keys.length];
        double max = 1;
        for (int i = 0; i < keys.length; i += 1) {
            if (cashflowByCat.containsKey(keys[i].toLowerCase())) {
                values[i] = cashflowByCat.get(keys[i].toLowerCase());
                max = Math.max(values[i], max);
            } else {
                values[i] = 0;
            }
        }

        for (int i = 0; i < keys.length; i += 1) {
            values[i] /= max;
        }

        radarChart.setRadiiLabels(keys);
        radarChart.addSeries(type, values);

        logger.log(Level.INFO, "Displaying Radar Chart");
        JFrame swHR = new SwingWrapper<>(radarChart).displayChart();
        SwingUtilities.invokeLater(
                ()->swHR.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
        );
    }


}
