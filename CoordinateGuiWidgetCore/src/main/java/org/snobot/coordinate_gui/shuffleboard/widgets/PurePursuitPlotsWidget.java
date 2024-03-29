package org.snobot.coordinate_gui.shuffleboard.widgets;

import java.util.Map;

import org.snobot.coordinate_gui.shuffleboard.data.PurePursuitData;
import org.snobot.coordinate_gui.shuffleboard.data.SmartDashboardNames;
import org.snobot.nt.pure_pursuit_plotter.PurePursuitPlotsController;
import org.snobot.nt.pure_pursuit_plotter.PurePursuitPointInfo;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

@Description(name = "Pure Pursuit Plots", dataTypes = PurePursuitData.class)
@ParametrizedController("PurePursuitPlotsWidget.fxml")
public class PurePursuitPlotsWidget extends SimpleAnnotatedWidget<PurePursuitData>
{
    @FXML
    private Pane mRoot;

    @FXML
    private PurePursuitPlotsController mOverviewContainerController;

    private int mLastIndex;

    /**
     * Called after the JavaFX things have been initialized.
     */
    @FXML
    public void initialize()
    {
        dataOrDefault.addListener((observable, oldData, newData) ->
        {
            final Map<String, Object> changes = newData.changesFrom(oldData);
            if (changes.containsKey(SmartDashboardNames.sPURE_PURSUIT_SMOOTHED))
            {
                mOverviewContainerController.setIdealPath(newData.getSmoothedPoints());
            }

            if (changes.containsKey(SmartDashboardNames.sPURE_PURSUIT_CURRENT_POINT))
            {
                handleNewPoint(newData.getCurrentPoint());
            }

        });
    }

    @Override
    public Pane getView()
    {
        return mRoot;
    }

    private void handleNewPoint(PurePursuitPointInfo aData)
    {
        if (aData.mIndex == 0 || aData.mIndex < mLastIndex)
        {
            mOverviewContainerController.clearActuals();
        }

        mOverviewContainerController.addPoint(aData);

        mLastIndex = aData.mIndex;
    }

}
