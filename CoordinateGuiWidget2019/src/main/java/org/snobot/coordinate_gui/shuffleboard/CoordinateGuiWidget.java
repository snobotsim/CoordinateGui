
package org.snobot.coordinate_gui.shuffleboard;

import java.util.Map;

import org.snobot.coordinate_gui.game.deep_space.DeepSpaceController;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.shuffleboard.data.CoordinateDataType;
import org.snobot.coordinate_gui.shuffleboard.data.DeepSpaceCoordinateGuiData;
import org.snobot.coordinate_gui.shuffleboard.data.GoToPositionDataType;
import org.snobot.coordinate_gui.shuffleboard.data.PurePursuitData;
import org.snobot.coordinate_gui.shuffleboard.data.PurePursuitDataType;
import org.snobot.coordinate_gui.shuffleboard.data.SmartDashboardNames;
import org.snobot.coordinate_gui.shuffleboard.data.TrajectoryDataType;
import org.snobot.coordinate_gui.shuffleboard.data.VisionDataType;
import org.snobot.coordinate_gui.ui.layers.PurePursuitLayerController;

import edu.wpi.first.shuffleboard.api.widget.ComplexAnnotatedWidget;
import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

@Description(name = "Coordinate GUI", dataTypes = {DeepSpaceCoordinateGuiData.class})
@ParametrizedController("CoordinateGuiWidget.fxml")
public class CoordinateGuiWidget extends ComplexAnnotatedWidget<DeepSpaceCoordinateGuiData>
{
    private static final Distance.Unit DISTANCE_UNIT = Distance.Unit.INCH;

    @FXML
    private Pane mRoot;

    @FXML
    protected DeepSpaceController mFieldController;

    /**
     * Called after JavaFX initialization.
     */
    @FXML
    public void initialize()
    {
        dataOrDefault.addListener((__, oldData, newData) ->
        {
            final Map<String, Object> changes = newData.changesFrom(oldData);

            if (changes.containsKey(CoordinateDataType.NAME + "/" + SmartDashboardNames.sROBOT_POSITION_CTR))
            {
                mFieldController.addRobotPosition(newData.getRobotPosition().toCoord(DISTANCE_UNIT));
            }

            if (changes.containsKey(VisionDataType.NAME + "/" + SmartDashboardNames.sCAMERA_POSITIONS))
            {
                mFieldController.setCameraRays(newData.getVisionData().toRays(DISTANCE_UNIT));
            }

            if (changes.containsKey(GoToPositionDataType.NAME + "/" + SmartDashboardNames.sCAMERA_POSITIONS))
            {
                mFieldController.setGoToXYPosition(newData.getGoToPositionData().toCoordinate(DISTANCE_UNIT));
            }

            if (changes.containsKey(TrajectoryDataType.NAME + "/" + SmartDashboardNames.sSPLINE_WAYPOINTS))
            {
                mFieldController.setWaypoints(newData.getTrajectoryData().toWaypoints(DISTANCE_UNIT));
            }

            if (changes.containsKey(TrajectoryDataType.NAME + "/" + SmartDashboardNames.sSPLINE_IDEAL_POINTS))
            {
                mFieldController.setIdealTrajectory(newData.getTrajectoryData().toIdealCoordinates(DISTANCE_UNIT));
            }

            if (changes.containsKey(PurePursuitDataType.NAME + "/" + SmartDashboardNames.sPURE_PURSUIT_SMOOTHED)
                    || changes.containsKey(PurePursuitDataType.NAME + "/" + SmartDashboardNames.sPURE_PURSUIT_LOOKAHEAD))
            {
                updatePurePursuit(newData.getPurePursuitData());
            }

        });
    }

    private void updatePurePursuit(PurePursuitData aPurePursuitData)
    {
        mFieldController.setPurePursuitWaypoints(aPurePursuitData.getWaypoints(), aPurePursuitData.getUpSampledPoints(),
                aPurePursuitData.getSmoothedPoints());

        PurePursuitLayerController.PurePursuitLookaheadData lookaheadData = aPurePursuitData.toLookaheadData(DISTANCE_UNIT);
        if (lookaheadData != null)
        {
            mFieldController.setPurePursuitLookahead(lookaheadData);
        }
    }

    @Override
    public Pane getView()
    {
        return mRoot;
    }

}
