package org.snobot.coordinate_gui.shuffleboard.widgets;

import edu.wpi.first.shuffleboard.api.widget.ComplexAnnotatedWidget;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.shuffleboard.data.BaseCoordinateGuiData;
import org.snobot.coordinate_gui.shuffleboard.data.CoordinateDataType;
import org.snobot.coordinate_gui.shuffleboard.data.GoToPositionDataType;
import org.snobot.coordinate_gui.shuffleboard.data.PurePursuitData;
import org.snobot.coordinate_gui.shuffleboard.data.PurePursuitDataType;
import org.snobot.coordinate_gui.shuffleboard.data.SmartDashboardNames;
import org.snobot.coordinate_gui.shuffleboard.data.TrajectoryDataType;
import org.snobot.coordinate_gui.shuffleboard.data.VisionDataType;
import org.snobot.coordinate_gui.ui.layers.BaseGuiController;
import org.snobot.coordinate_gui.ui.layers.PurePursuitLayerController;

import java.util.Map;

public class BaseCoordinateGuiWidget<DataType extends BaseCoordinateGuiData, ControllerType extends BaseGuiController> extends ComplexAnnotatedWidget<DataType>
{
    private final Distance.Unit mDistanceUnit;

    @FXML
    private Pane mRoot;

    @FXML
    protected ControllerType mFieldController;

    @Override
    public Pane getView()
    {
        return mRoot;
    }

    public BaseCoordinateGuiWidget(Distance.Unit aDistanceUnit)
    {
        mDistanceUnit = aDistanceUnit;
    }

    /**
     * Called after JavaFX initialization.
     */
    @FXML
    public void initialize()
    {
        dataOrDefault.addListener((__, oldData, newData) ->
        {
            final Map<String, Object> changes = newData.changesFrom(oldData);

            handleChange(newData, changes);
        });
    }

    private void updatePurePursuit(PurePursuitData aPurePursuitData)
    {
        mFieldController.setPurePursuitWaypoints(aPurePursuitData.getWaypoints(), aPurePursuitData.getUpSampledPoints(),
            aPurePursuitData.getSmoothedPoints());

        PurePursuitLayerController.PurePursuitLookaheadData lookaheadData = aPurePursuitData.toLookaheadData(mDistanceUnit);
        if (lookaheadData != null)
        {
            mFieldController.setPurePursuitLookahead(lookaheadData);
        }
    }

    protected void handleChange(DataType aNewData, final Map<String, Object> aChanges)
    {

        //System.out.println("Getting coordinate data" + aChanges);
        //System.out.println(aNewData);
        if (aChanges.containsKey(CoordinateDataType.NAME + "/" + SmartDashboardNames.sROBOT_POSITION_CTR))
        {
            mFieldController.addRobotPosition(aNewData.getRobotPosition().toCoord(mDistanceUnit));
        }

        if (aChanges.containsKey(VisionDataType.NAME + "/" + SmartDashboardNames.sCAMERA_POSITIONS))
        {
            mFieldController.setCameraRays(aNewData.getVisionData().toRays(mDistanceUnit));
        }

        if (aChanges.containsKey(GoToPositionDataType.NAME + "/" + SmartDashboardNames.sCAMERA_POSITIONS))
        {
            mFieldController.setGoToXYPosition(aNewData.getGoToPositionData().toCoordinate(mDistanceUnit));
        }

        if (aChanges.containsKey(TrajectoryDataType.NAME + "/" + SmartDashboardNames.sSPLINE_WAYPOINTS))
        {
            mFieldController.setWaypoints(aNewData.getTrajectoryData().toWaypoints(mDistanceUnit));
        }

        if (aChanges.containsKey(TrajectoryDataType.NAME + "/" + SmartDashboardNames.sSPLINE_IDEAL_POINTS))
        {
            System.out.println("Getting coordinates: " + aNewData.getTrajectoryData().toIdealCoordinates(mDistanceUnit));
            mFieldController.setIdealTrajectory(aNewData.getTrajectoryData().toIdealCoordinates(mDistanceUnit));
        }

        if (aChanges.containsKey(PurePursuitDataType.NAME + "/" + SmartDashboardNames.sPURE_PURSUIT_SMOOTHED)
            || aChanges.containsKey(PurePursuitDataType.NAME + "/" + SmartDashboardNames.sPURE_PURSUIT_LOOKAHEAD))
        {
            updatePurePursuit(aNewData.getPurePursuitData());
        }
    }
}
