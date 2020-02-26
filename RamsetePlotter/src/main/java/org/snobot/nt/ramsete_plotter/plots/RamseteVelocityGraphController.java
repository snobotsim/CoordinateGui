package org.snobot.nt.ramsete_plotter.plots;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Velocity;
import org.snobot.nt.ramsete_plotter.RamseteInstantaneousPoint;
import org.snobot.nt.ramsete_plotter.RamsetePointInfo;

import java.util.List;

public class RamseteVelocityGraphController
{
    @FXML
    private LineChart<Double, Double> mChart;

    private final XYChart.Series<Double, Double> mIdealRobotVelocity = new XYChart.Series<>();
    private final XYChart.Series<Double, Double> mIdealVelocity = new XYChart.Series<>();
    private final XYChart.Series<Double, Double> mActualVelocity = new XYChart.Series<>();

    /**
     * Called after the JavaFX things have been initialized.
     */
    @SuppressWarnings("unchecked")
    @FXML
    public void initialize()
    {
        mChart.setAnimated(false);

        mIdealRobotVelocity.setName("Ideal Robot Velocity");
        mIdealVelocity.setName("Ideal Wheel Velocity");
        mActualVelocity.setName("Actual Wheel Velocity");

        mChart.getData().addAll(mIdealRobotVelocity, mIdealVelocity, mActualVelocity);
    }

    /**
     * Sets the ideal heading.
     *
     * @param aPoints
     *            The points
     */
    public void setIdeal(List<RamsetePointInfo> aPoints, Velocity.Unit aDecodeUnit)
    {
        mIdealRobotVelocity.getData().clear();
        clearActuals();

        for (RamsetePointInfo point : aPoints)
        {
            mIdealRobotVelocity.getData().add(new XYChart.Data<>(point.mTime, point.mVelocity.as(aDecodeUnit)));
        }
    }

    public void setActual(double aTime, Velocity aIdealVelocity, Velocity aMeasuredVelocity, Velocity.Unit decodeUnit) {
        mIdealVelocity.getData().add(new XYChart.Data<>(aTime, aIdealVelocity.as(decodeUnit)));
        mActualVelocity.getData().add(new XYChart.Data<>(aTime, aMeasuredVelocity.as(decodeUnit)));
    }

    public void clearActuals()
    {
        mIdealVelocity.getData().clear();
        mActualVelocity.getData().clear();
    }

}
