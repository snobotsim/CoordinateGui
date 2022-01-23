package org.snobot.nt.spline_plotter.plots;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class TrajectoryErrorGraphController
{
    private List<Double> mIdeal;

    @FXML
    private LineChart<Integer, Double> mChart;

    private final XYChart.Series<Integer, Double> mErrorAxis = new XYChart.Series<>();

    /**
     * Called after the JavaFX things have been initialized.
     */
    @SuppressWarnings("unchecked")
    @FXML
    public void initialize()
    {
        mChart.setAnimated(false);

        mErrorAxis.setName("Error");

        mChart.getData().addAll(mErrorAxis);
    }

    public void setIdeal(List<Double> aIdeal)
    {
        mIdeal = aIdeal;
    }

    public void setActual(int aIndex, double aActual)
    {
        double error = mIdeal.get(aIndex) - aActual;
        mErrorAxis.getData().add(new XYChart.Data<Integer, Double>(aIndex, error));
    }

    public void clearActuals()
    {
        mErrorAxis.getData().clear();
    }

}
