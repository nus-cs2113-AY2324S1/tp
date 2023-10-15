package seedu.financialplanner.visualisations;

import org.knowm.xchart.*;

import java.awt.*;

public class PieChartVisual extends Visual {
    public void displayPieChart () {
        PieChart chart = new PieChartBuilder().width(800).height(600).title("Test").build();

        // Customize Chart
        Color[] sliceColors = new Color[] { new Color(224, 68, 14), new Color(230, 105, 62), new Color(236, 143, 110), new Color(243, 180, 159), new Color(246, 199, 182) };
        chart.getStyler().setSeriesColors(sliceColors);

        // Series
        chart.addSeries("Gold", 24);
        chart.addSeries("Silver", 21);
        chart.addSeries("Platinum", 39);
        chart.addSeries("Copper", 17);
        chart.addSeries("Zinc", 40);

        // Show it
        new SwingWrapper<>(chart).displayChart();
    }

}
