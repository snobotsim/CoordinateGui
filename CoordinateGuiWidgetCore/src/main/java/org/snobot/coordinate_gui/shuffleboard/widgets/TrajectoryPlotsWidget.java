package org.snobot.coordinate_gui.shuffleboard.widgets;

import java.util.Map;

import org.snobot.coordinate_gui.shuffleboard.data.SmartDashboardNames;
import org.snobot.coordinate_gui.shuffleboard.data.TrajectoryData;
import org.snobot.nt.spline_plotter.TrajectoryPlotsController;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

@Description(name = "Trajectory Plots", dataTypes = TrajectoryData.class)
@ParametrizedController("TrajectoryPlotsWidget.fxml")
public class TrajectoryPlotsWidget extends SimpleAnnotatedWidget<TrajectoryData>
{
    @FXML
    private Pane mRoot;

    @FXML
    private TrajectoryPlotsController mOverviewContainerController;

    private int mLastIndex;

    /**
     * Called after the JavaFX things have been initialized.
     */
    @FXML
    public void initialize()
    {
        dataOrDefault.addListener((__, oldData, newData) ->
        {
            final Map<String, Object> changes = newData.changesFrom(oldData);
            if (changes.containsKey(SmartDashboardNames.sSPLINE_IDEAL_POINTS))
            {
                mOverviewContainerController.setPath(newData.getIdealSpline());
            }
            if (changes.containsKey(SmartDashboardNames.sSPLINE_REAL_POINT))
            {
                handleNewRealPoint(newData);
            }
        });
    }

    @Override
    public Pane getView()
    {
        return mRoot;
    }

    private void handleNewRealPoint(TrajectoryData aData)
    {
        int index = aData.getMeasurementIndex();

        if (index == 0 || index < mLastIndex)
        {
            mOverviewContainerController.clearActuals();
        }

        if (index > mLastIndex)
        {
            mOverviewContainerController.setPoint(index, aData.getMeasurementSegment());
        }
        mLastIndex = index;
    }
}
