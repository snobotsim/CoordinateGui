package org.snobot.nt.spline_plotter.plots;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class TrajectoryXYPositionGraphController
{
    @FXML
    private ScatterChart<Double, Double> mChart;

    private final XYChart.Series<Double, Double> mIdeal = new XYChart.Series<>();
    private final XYChart.Series<Double, Double> mActual = new XYChart.Series<>();

    /**
     * Called after the JavaFX things have been initialized.
     */
    @SuppressWarnings("unchecked")
    @FXML
    public void initialize()
    {
        mChart.setAnimated(false);

        mIdeal.setName("Ideal");
        mActual.setName("Actual");

        mChart.getData().addAll(mIdeal, mActual);
    }

    /**
     * Sets the spline center path, and the velocity.
     * 
     * @param aX
     *            The ideal X positions
     * @param aY
     *            The ideal Y positions
     */
    @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
    public void setPath(List<Double> aX, List<Double> aY)
    {
        mIdeal.getData().clear();
        clearActuals();

        for (int i = 0; i < aX.size(); ++i)
        {
            mIdeal.getData().add(new XYChart.Data<Double, Double>(aX.get(i), aY.get(i)));
        }
    }

    /**
     * Sets the measured point.
     * 
     * @param aX
     *            The X position, in inches
     * @param aY
     *            The Y position, in inches
     */
    public void setPoint(double aX, double aY)
    {
        mActual.getData().add(new XYChart.Data<Double, Double>(aX, aY));
    }

    public void clearActuals()
    {
        mActual.getData().clear();
    }
}
