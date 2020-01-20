
package org.snobot.coordinate_gui.shuffleboard;

import java.util.Map;

import org.snobot.coordinate_gui.game.deep_space.DeepSpaceController;
import org.snobot.coordinate_gui.model.Position2dDistance;
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
                mFieldController.addRobotPosition(newData.getRobotPosition().toCoord());
            }

            if (changes.containsKey(VisionDataType.NAME + "/" + SmartDashboardNames.sCAMERA_POSITIONS))
            {
                mFieldController.setCameraRays(newData.getVisionData().toRays());
            }

            if (changes.containsKey(GoToPositionDataType.NAME + "/" + SmartDashboardNames.sCAMERA_POSITIONS))
            {
                mFieldController.setGoToXYPosition(newData.getGoToPositionData().toCoordinate());
            }

            if (changes.containsKey(TrajectoryDataType.NAME + "/" + SmartDashboardNames.sSPLINE_WAYPOINTS))
            {
                mFieldController.setWaypoints(newData.getTrajectoryData().toWaypoints());
            }

            if (changes.containsKey(TrajectoryDataType.NAME + "/" + SmartDashboardNames.sSPLINE_IDEAL_POINTS))
            {
                mFieldController.setIdealTrajectory(newData.getTrajectoryData().toIdealCoordinates());
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

        PurePursuitLayerController.PurePursuitLookaheadData lookaheadData = aPurePursuitData.toLookaheadData();
        if (lookaheadData != null)
        {
            String[] lookahead = aPurePursuitData.getLookaheadString().split(",");
            mFieldController.setPurePursuitLookahead(new Position2dDistance(Double.parseDouble(lookahead[0]), Double.parseDouble(lookahead[1])),
                new Position2dDistance(Double.parseDouble(lookahead[2]), Double.parseDouble(lookahead[3])));
        }
    }

    @Override
    public Pane getView()
    {
        return mRoot;
    }

}
