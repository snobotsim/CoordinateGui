package org.snobot.nt.ramsete_plotter.plots;

import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.nt.ramsete_plotter.RamseteInstantaneousPoint;
import org.snobot.nt.ramsete_plotter.RamsetePointInfo;

import java.util.List;

public class RamseteXYPositionGraphController
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
    public void setIdeal(List<RamsetePointInfo> aCoordinates, Distance.Unit aDistanceUnit)
    {
        mIdeal.getData().clear();
        clearActuals();

        for (RamsetePointInfo coord : aCoordinates)
        {
            mIdeal.getData().add(new XYChart.Data<>(coord.mPosition.mX.as(aDistanceUnit), coord.mPosition.mY.as(aDistanceUnit)));
        }
    }

    public void clearActuals()
    {
        mActual.getData().clear();
    }

    public void setActual(RamseteInstantaneousPoint aActual, Distance.Unit aDistanceUnit)
    {
        mActual.getData().add(new XYChart.Data<>(aActual.mMeasuredPosition.mX.as(aDistanceUnit), aActual.mMeasuredPosition.mY.as(aDistanceUnit)));
    }
}
