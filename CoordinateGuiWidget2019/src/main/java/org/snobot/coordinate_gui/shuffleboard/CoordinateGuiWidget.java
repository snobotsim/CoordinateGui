
package org.snobot.coordinate_gui.shuffleboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.snobot.coordinate_gui.game.deep_space.DeepSpaceController;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.shuffleboard.data.CoordinateData;
import org.snobot.coordinate_gui.shuffleboard.data.CoordinateDataType;
import org.snobot.coordinate_gui.shuffleboard.data.CoordinateGuiData;
import org.snobot.coordinate_gui.shuffleboard.data.GoToPositionData;
import org.snobot.coordinate_gui.shuffleboard.data.GoToPositionDataType;
import org.snobot.coordinate_gui.shuffleboard.data.PurePursuitData;
import org.snobot.coordinate_gui.shuffleboard.data.PurePursuitDataType;
import org.snobot.coordinate_gui.shuffleboard.data.SmartDashboardNames;
import org.snobot.coordinate_gui.shuffleboard.data.TrajectoryData;
import org.snobot.coordinate_gui.shuffleboard.data.TrajectoryDataType;
import org.snobot.coordinate_gui.shuffleboard.data.VisionData;
import org.snobot.coordinate_gui.shuffleboard.data.VisionDataType;
import org.snobot.coordinate_gui.shuffleboard.widgets.IdealSplineSerializer;
import org.snobot.coordinate_gui.ui.layers.CameraRayLayerController.Ray;
import org.snobot.nt.spline_plotter.SplineSegment;

import edu.wpi.first.shuffleboard.api.widget.ComplexAnnotatedWidget;
import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

@Description(name = "Coordinate GUI", dataTypes = {CoordinateGuiData.class})
@ParametrizedController("CoordinateGuiWidget.fxml")
public class CoordinateGuiWidget extends ComplexAnnotatedWidget<CoordinateGuiData>
{
    @FXML
    private Pane mRoot;

    @FXML
    private DeepSpaceController mFieldController;

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
                updateRobotPosition(newData.getRobotPosition());
            }

            if (changes.containsKey(VisionDataType.NAME + "/" + SmartDashboardNames.sCAMERA_POSITIONS))
            {
                updateCameraPositions(newData.getVisionData());
            }

            if (changes.containsKey(GoToPositionDataType.NAME + "/" + SmartDashboardNames.sCAMERA_POSITIONS))
            {
                updateGoToXY(newData.getGoToPositionData());
            }

            if (changes.containsKey(TrajectoryDataType.NAME + "/" + SmartDashboardNames.sSPLINE_WAYPOINTS))
            {
                updateTrajectoryWaypoints(newData.getTrajectoryData());
            }

            if (changes.containsKey(TrajectoryDataType.NAME + "/" + SmartDashboardNames.sSPLINE_IDEAL_POINTS))
            {
                updateIdealTrajectory(newData.getTrajectoryData());
            }

            if (changes.containsKey(PurePursuitDataType.NAME + "/" + SmartDashboardNames.sPURE_PURSUIT_SMOOTHED)
                    || changes.containsKey(PurePursuitDataType.NAME + "/" + SmartDashboardNames.sPURE_PURSUIT_LOOKAHEAD))
            {
                updatePurePursuit(newData.getPurePursuitData());
            }

        });
    }

    private void updateRobotPosition(CoordinateData aRobotPosition)
    {
        double x = aRobotPosition.getX() / 12;
        double y = aRobotPosition.getY() / 12;
        double angle = aRobotPosition.getAngle();

        Coordinate coord = new Coordinate(x, y, angle);
        mFieldController.addRobotPosition(coord);
    }

    private void updateCameraPositions(VisionData aVisionData)
    {
        List<Ray> rays = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(aVisionData.getValue(), ",");
        while (tokenizer.countTokens() >= 4)
        {
            Ray ray = new Ray();

            ray.mXStart = Double.parseDouble(tokenizer.nextToken()) / 12;
            ray.mYStart = Double.parseDouble(tokenizer.nextToken()) / 12;
            ray.mXEnd = Double.parseDouble(tokenizer.nextToken()) / 12;
            ray.mYEnd = Double.parseDouble(tokenizer.nextToken()) / 12;

            rays.add(ray);
        }

        mFieldController.setCameraRays(rays);
    }

    private void updateTrajectoryWaypoints(TrajectoryData aTrajectoryData)
    {
        List<Coordinate> coordinates = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(aTrajectoryData.getSplineWaypoints(), ",");
        while (tokenizer.countTokens() >= 3)
        {
            double x = Double.parseDouble(tokenizer.nextToken());
            double y = Double.parseDouble(tokenizer.nextToken());
            double angle = Math.toDegrees(Double.parseDouble(tokenizer.nextToken()));
            Coordinate coordinate = new Coordinate(x / 12.0, y / 12.0, angle);
            coordinates.add(coordinate);
        }
        mFieldController.setWaypoints(coordinates);
    }

    private void updateIdealTrajectory(TrajectoryData aTrajectoryData)
    {
        List<Coordinate> coordinates = new ArrayList<>();
        List<SplineSegment> segments = IdealSplineSerializer.deserializePath(aTrajectoryData.getIdealSpline());

        for (SplineSegment splineSegment : segments)
        {
            coordinates.add(new Coordinate(splineSegment.mAverageX / 12.0, splineSegment.mAverageY / 12.0, splineSegment.mRobotHeading));
        }
        mFieldController.setIdealTrajectory(coordinates);
    }

    private void updateGoToXY(GoToPositionData aGoToPositionData)
    {
        if (aGoToPositionData.getX() != null && aGoToPositionData.getY() != null)
        {
            mFieldController.setGoToXYPosition(aGoToPositionData.getX() / 12, aGoToPositionData.getY() / 12);
        }
        else
        {
            mFieldController.setGoToXYPosition(aGoToPositionData.getX(), aGoToPositionData.getY());
        }
    }

    private void updatePurePursuit(PurePursuitData aPurePursuitData)
    {
        mFieldController.setPurePursuitWaypoints(aPurePursuitData.getWaypoints(), aPurePursuitData.getUpSampledPoints(),
                aPurePursuitData.getSmoothedPoints());

        String lookaheadString = aPurePursuitData.getLookaheadString();
        if (!lookaheadString.isEmpty())
        {
            String[] lookahead = aPurePursuitData.getLookaheadString().split(",");
            mFieldController.setPurePursuitLookahead(Double.parseDouble(lookahead[0]), Double.parseDouble(lookahead[1]),
                    Double.parseDouble(lookahead[2]), Double.parseDouble(lookahead[3]));
        }
    }

    @Override
    public Pane getView()
    {
        return mRoot;
    }

}
