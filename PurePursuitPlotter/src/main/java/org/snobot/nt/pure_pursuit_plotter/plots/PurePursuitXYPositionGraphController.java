package org.snobot.nt.pure_pursuit_plotter.plots;

import java.util.List;

import org.snobot.coordinate_gui.model.Coordinate;

import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import org.snobot.coordinate_gui.model.Distance;

public class PurePursuitXYPositionGraphController
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
     * Sets the ideal path.
     *
     * @param aCoordinates
     *            the coordinates
     */
    public void setPath(List<Coordinate> aCoordinates)
    {
        mIdeal.getData().clear();
        clearActuals();

        for (Coordinate coord : aCoordinates)
        {
            mIdeal.getData().add(new XYChart.Data<Double, Double>(coord.mPosition.mX.as(Distance.Unit.Feet), coord.mPosition.mY.as(Distance.Unit.Feet)));
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
    public void addPoint(double aX, double aY)
    {
        mActual.getData().add(new XYChart.Data<Double, Double>(aX, aY));
    }

    public void clearActuals()
    {
        mActual.getData().clear();
    }
}
