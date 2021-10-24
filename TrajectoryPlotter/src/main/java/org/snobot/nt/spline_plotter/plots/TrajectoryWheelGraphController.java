package org.snobot.nt.spline_plotter.plots;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class TrajectoryWheelGraphController
{
    @FXML
    private LineChart<Integer, Double> mChart;

    private final XYChart.Series<Integer, Double> mIdealPosition = new XYChart.Series<>();
    private final XYChart.Series<Integer, Double> mIdealVelocity = new XYChart.Series<>();

    private final XYChart.Series<Integer, Double> mActualPosition = new XYChart.Series<>();
    private final XYChart.Series<Integer, Double> mActualVelocity = new XYChart.Series<>();

    /**
     * Called after the JavaFX things have been initialized.
     */
    @SuppressWarnings("unchecked")
    @FXML
    public void initialize()
    {
        mChart.setAnimated(false);

        mIdealPosition.setName("Ideal Position");
        mIdealVelocity.setName("Ideal Velocity");

        mActualPosition.setName("Actual Position");
        mActualVelocity.setName("Actual Velocity");

        mChart.getData().addAll(mIdealPosition, mActualPosition, mIdealVelocity, mActualVelocity);
    }

    /**
     * Sets the spline center path, and the velocity.
     * 
     * @param aPosition
     *            The ideal position, in inches
     * @param aVelocity
     *            The ideal velocity, in in/sec
     */
    @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
    public void setPath(List<Double> aPosition, List<Double> aVelocity)
    {
        mIdealPosition.getData().clear();
        mIdealVelocity.getData().clear();
        clearActuals();

        for (int i = 0; i < aPosition.size(); ++i)
        {
            mIdealPosition.getData().add(new XYChart.Data<Integer, Double>(i, aPosition.get(i)));
            mIdealVelocity.getData().add(new XYChart.Data<Integer, Double>(i, aVelocity.get(i)));
        }
    }

    /**
     * Sets a measured point from the robot.
     * 
     * @param aIndex
     *            The index of this piont
     * @param aPosition
     *            The position, in inches
     * @param aVelocity
     *            The velocity, in in/sec
     */
    public void setPoint(int aIndex, double aPosition, double aVelocity)
    {
        mActualPosition.getData().add(new XYChart.Data<Integer, Double>(aIndex, aPosition));
        mActualVelocity.getData().add(new XYChart.Data<Integer, Double>(aIndex, aVelocity));
    }

    public void clearActuals()
    {
        mActualPosition.getData().clear();
        mActualVelocity.getData().clear();
    }
}
