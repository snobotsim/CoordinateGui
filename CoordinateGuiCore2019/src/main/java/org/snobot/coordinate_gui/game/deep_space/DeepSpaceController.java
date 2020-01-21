package org.snobot.coordinate_gui.game.deep_space;

import java.util.List;

import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.DataProvider;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.PixelConverter;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.ui.layers.BaseGuiController;
import org.snobot.coordinate_gui.ui.layers.CameraRayLayerController;
import org.snobot.coordinate_gui.ui.layers.CameraRayLayerController.Ray;
import org.snobot.coordinate_gui.ui.layers.CoordinateLayerController;
import org.snobot.coordinate_gui.ui.layers.GoToPositionController;
import org.snobot.coordinate_gui.ui.layers.PurePursuitLayerController;
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

    @FXML
    private PurePursuitLayerController mPurePursuitController;

    private final DataProvider<Coordinate> mCoordinatesDataProvider;
    private final CoordinateLayerRenderProps mCoordinatesRenderProperties;

    private final DataProvider<Coordinate> mIdealTrajectoryDataProvider;
    private final CoordinateLayerRenderProps mIdealTrajectoryRenderProperties;

    /**
     * Constructor.
     */
    public DeepSpaceController()
    {
        super(FIELD_IMAGE_PATH, Distance.fromFeet(FIELD_WIDTH), Distance.fromFeet(FIELD_HEIGHT), PixelConverter.Orientation.Portrait, PixelConverter.OriginPosition.CenterField);

        mCoordinatesRenderProperties = new CoordinateLayerRenderProps(100, 5, Color.ORANGERED, true);
        mCoordinatesDataProvider = new DataProvider<>(1000);

        mIdealTrajectoryRenderProperties = new CoordinateLayerRenderProps(100, 1, Color.YELLOWGREEN, false);
        mIdealTrajectoryDataProvider = new DataProvider<>();
    }

    @Override
    public void initialize()
    {
        super.initialize();
        mRobotPositionController.setRobotDimensions(mPixelConverter, Distance.fromFeet(ROBOT_WIDTH), Distance.fromFeet(ROBOT_HEIGHT));

        mFadingCoordinatesController.setup(mCoordinatesRenderProperties, mCoordinatesDataProvider, mPixelConverter);
        mIdealTrajectoryCoordinatesController.setup(mIdealTrajectoryRenderProperties, mIdealTrajectoryDataProvider, mPixelConverter);
        mPurePursuitController.setup(mPixelConverter);
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

    public void setGoToXYPosition(Position2dDistance aPosition)
    {
        mGoToPositionController.setGoToXYPosition(mPixelConverter, aPosition);
    }

    public void setPurePursuitWaypoints(List<Coordinate> aCoordinates, List<Coordinate> aUpSampled, List<Coordinate> aSmoothed)
    {
        mPurePursuitController.setWaypoints(aCoordinates, aUpSampled, aSmoothed);
    }

    public void setPurePursuitLookahead(PurePursuitLayerController.PurePursuitLookaheadData aData)
    {
        mPurePursuitController.setLookaheadLine(aData);
    }
}
