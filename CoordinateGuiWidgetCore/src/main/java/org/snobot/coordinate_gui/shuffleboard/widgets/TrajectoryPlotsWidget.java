package org.snobot.coordinate_gui.shuffleboard.widgets;

import java.util.Map;
import java.util.StringTokenizer;

import org.snobot.coordinate_gui.shuffleboard.data.SmartDashboardNames;
import org.snobot.coordinate_gui.shuffleboard.data.TrajectoryData;
import org.snobot.nt.spline_plotter.SplineSegment;
import org.snobot.nt.spline_plotter.TrajectoryGraphOverviewController;

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
    private TrajectoryGraphOverviewController mOverviewContainerController;

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
                handleNewIdealPoints(newData);
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

    private void handleNewIdealPoints(TrajectoryData aData)
    {
        String newIdeal = aData.getIdealSpline();
        mOverviewContainerController.setPath(IdealSplineSerializer.deserializePath(newIdeal));
    }

    private void handleNewRealPoint(TrajectoryData aData)
    {
        StringTokenizer tokenizer = new StringTokenizer(aData.getMeasuredSpline(), ",");

        int index = Integer.parseInt(tokenizer.nextElement().toString());

        if (index == 0 || index < mLastIndex)
        {
            mOverviewContainerController.clearActuals();
        }

        if (index > mLastIndex)
        {
            SplineSegment segment = IdealSplineSerializer.deserializePathPoint(tokenizer);
            mOverviewContainerController.setPoint(index, segment);
        }
        mLastIndex = index;
    }
}
