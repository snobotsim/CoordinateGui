package org.snobot.nt.spline_plotter;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class TrajectoryHeadingGraphController
{
    @FXML
    private LineChart<Integer, Double> mChart;

    private final XYChart.Series<Integer, Double> mIdealHeading = new XYChart.Series<>();
    private final XYChart.Series<Integer, Double> mActualHeading = new XYChart.Series<>();

    /**
     * Called after the JavaFX things have been initialized.
     */
    @SuppressWarnings("unchecked")
    @FXML
    public void initialize()
    {
        mChart.setAnimated(false);

        mIdealHeading.setName("Ideal Heading");
        mActualHeading.setName("Actual Heading");

        mChart.getData().addAll(mIdealHeading, mActualHeading);
    }

    /**
     * Sets the ideal heading.
     * 
     * @param aHeadings
     *            The heading
     */
    public void setPath(List<Double> aHeadings)
    {
        mIdealHeading.getData().clear();
        clearActuals();

        for (int i = 0; i < aHeadings.size(); ++i)
        {
            mIdealHeading.getData().add(new XYChart.Data<Integer, Double>(i, aHeadings.get(i)));
        }
    }

    /**
     * Sets a measured point.
     * 
     * @param aIndex
     *            The index of the point
     * @param aHeading
     *            The measured heading
     */
    public void setPoint(int aIndex, double aHeading)
    {
        mActualHeading.getData().add(new XYChart.Data<Integer, Double>(aIndex, aHeading));
    }

    public void clearActuals()
    {
        mActualHeading.getData().clear();
    }
}
