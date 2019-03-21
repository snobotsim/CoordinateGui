package org.snobot.nt.pure_pursuit_plotter.plots;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class PurePursuitWheelGraphController
{
    @FXML
    private LineChart<Integer, Double> mChart;

    private final XYChart.Series<Integer, Double> mIdeal = new XYChart.Series<>();
    private final XYChart.Series<Integer, Double> mActual = new XYChart.Series<>();

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

    public void addPoint(int aIndex, double aActual, double aIdeal)
    {
        mActual.getData().add(new XYChart.Data<Integer, Double>(aIndex, aActual));
        mIdeal.getData().add(new XYChart.Data<Integer, Double>(aIndex, aIdeal));
    }

    public void clear()
    {
        mIdeal.getData().clear();
        mActual.getData().clear();
    }
}
