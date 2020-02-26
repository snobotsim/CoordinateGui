package org.snobot.nt.ramsete_plotter.plots;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import org.snobot.nt.ramsete_plotter.RamseteInstantaneousPoint;
import org.snobot.nt.ramsete_plotter.RamsetePointInfo;

import java.util.List;

public class RamseteHeadingGraphController
{
    @FXML
    private LineChart<Double, Double> mChart;

    private final XYChart.Series<Double, Double> mIdealHeading = new XYChart.Series<>();
    private final XYChart.Series<Double, Double> mActualHeading = new XYChart.Series<>();

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
     * @param aPoints
     *            The points
     */
    public void setIdeal(List<RamsetePointInfo> aPoints)
    {
        mIdealHeading.getData().clear();
        clearActuals();

        for (RamsetePointInfo point : aPoints)
        {
            System.out.println(point.mHeading);
            mIdealHeading.getData().add(new XYChart.Data<>(point.mTime, point.mHeading));
        }
    }

    public void clearActuals()
    {
        mActualHeading.getData().clear();
    }

    public void setActual(RamseteInstantaneousPoint aActual)
    {
        mActualHeading.getData().add(new XYChart.Data<>(aActual.mTime, aActual.mMeasuredHeading));
    }
}
