package org.snobot.coordinate_gui.game.deep_space;

import java.util.List;

import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.DataProvider;
import org.snobot.coordinate_gui.ui.layers.BaseGuiController;
import org.snobot.coordinate_gui.ui.layers.CameraRayLayerController;
import org.snobot.coordinate_gui.ui.layers.CameraRayLayerController.Ray;
import org.snobot.coordinate_gui.ui.layers.CoordinateLayerController;
import org.snobot.coordinate_gui.ui.layers.GoToPositionController;
import org.snobot.coordinate_gui.ui.layers.RobotPositionLayerController;
import org.snobot.coordinate_gui.ui.layers.TrajectoryConfigLayerController;
import org.snobot.coordinate_gui.ui.layers.TrajectoryConfigLayerController.CoodrinateWrapper;
import org.snobot.coordinate_gui.ui.render_props.CoordinateLayerRenderProps;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;

public class DeepSpaceController extends BaseGuiController
{
    private static final String FIELD_IMAGE_PATH = "/org/snobot/coordinate_gui/game/deep_space/2019Field.png";

    private static final double FIELD_WIDTH = 27;
    private static final double FIELD_HEIGHT = 54;

    private static final double ROBOT_WIDTH = 36 / 12.0;
    private static final double ROBOT_HEIGHT = 44 / 12.0;

    @FXML
    private TrajectoryConfigLayerController mTrajectoryConfigController;

    @FXML
    private RobotPositionLayerController mRobotPositionController;

    @FXML
    private GoToPositionController mGoToPositionController;

    @FXML
    private CameraRayLayerController mCameraLayerController;

    @FXML
    private CoordinateLayerController mFadingCoordinatesController;

    @FXML
    private CoordinateLayerController mIdealTrajectoryCoordinatesController;

    private final DataProvider<Coordinate> mCoordinatesDataProvider;
    private final CoordinateLayerRenderProps mCoordinatesRenderProperties;

    private final DataProvider<Coordinate> mIdealTrajectoryDataProvider;
    private final CoordinateLayerRenderProps mIdealTrajectoryRenderProperties;

    /**
     * Constructor.
     */
    public DeepSpaceController()
    {
        super(FIELD_IMAGE_PATH, FIELD_WIDTH, FIELD_HEIGHT);

        mCoordinatesRenderProperties = new CoordinateLayerRenderProps(100, 5, Color.GREEN, true);
        mCoordinatesDataProvider = new DataProvider<>();

        mIdealTrajectoryRenderProperties = new CoordinateLayerRenderProps(100, 1, Color.YELLOWGREEN, false);
        mIdealTrajectoryDataProvider = new DataProvider<>();
    }

    @Override
    public void initialize()
    {
        super.initialize();
        mRobotPositionController.setRobotDimensions(mPixelConverter, ROBOT_WIDTH, ROBOT_HEIGHT);

        mFadingCoordinatesController.setup(mCoordinatesRenderProperties, mCoordinatesDataProvider, mPixelConverter);
        mIdealTrajectoryCoordinatesController.setup(mIdealTrajectoryRenderProperties, mIdealTrajectoryDataProvider, mPixelConverter);
    }

    /**
     * Adds a robot position to the layers.
     * 
     * @param aRobotPosition
     *            The position of the robot
     */
    public void addRobotPosition(Coordinate aRobotPosition)
    {
        mRobotPositionController.setPosition(mPixelConverter, aRobotPosition);
        mCoordinatesDataProvider.addData(aRobotPosition);
        mFadingCoordinatesController.render();
    }

    public void setCameraRays(List<Ray> aRays)
    {
        mCameraLayerController.setRays(mPixelConverter, aRays);
    }

    public void setWaypoints(List<Coordinate> aWaypoints)
    {
        mTrajectoryConfigController.setTrajectoryPoints(mPixelConverter, aWaypoints);
    }

    public List<Coordinate> getWaypoints()
    {
        return mTrajectoryConfigController.getWaypoints();
    }

    /**
     * Sets the ideal trajectory to draw on the display.
     * 
     * @param aCoordinates
     *            The ideal trajectory
     */
    public void setIdealTrajectory(List<Coordinate> aCoordinates)
    {
        mIdealTrajectoryDataProvider.clear();
        for (Coordinate coord : aCoordinates)
        {
            mIdealTrajectoryDataProvider.addData(coord);
        }
        mIdealTrajectoryCoordinatesController.render();
    }

    public CoodrinateWrapper getSelectedWaypoint()
    {
        return mTrajectoryConfigController.getSelectedWaypoint();
    }

    public void addIdealTrajectory(Coordinate aCoordinate)
    {
        mTrajectoryConfigController.addPoint(mPixelConverter, aCoordinate);
    }

    public void setGoToXYPosition(Double aX, Double aY)
    {
        mGoToPositionController.setGoToXYPosition(mPixelConverter, aX, aY);
    }
}
