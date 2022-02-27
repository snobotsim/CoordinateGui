package org.snobot.coordinate_gui.ui.layers;

import javafx.scene.paint.Color;
import org.snobot.coordinate_gui.model.FieldLoader;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.DataProvider;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.PixelConverter;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.ui.render_props.CoordinateLayerRenderProps;

import java.util.List;

@SuppressWarnings("PMD.TooManyFields")
public class BaseGuiController
{
    private final Image mFieldImage;
    private final Distance mFieldShortDimension;
    private final Distance mFieldLongDimension;

    private final Distance mRobotWidth;
    private final Distance mRobotHeight;

    protected final PixelConverter mPixelConverter;

    private final DataProvider<Coordinate> mCoordinatesDataProvider;
    private final CoordinateLayerRenderProps mCoordinatesRenderProperties;

    private final DataProvider<Coordinate> mIdealTrajectoryDataProvider;
    private final CoordinateLayerRenderProps mIdealTrajectoryRenderProperties;

    private final DataProvider<Coordinate> mIdealRamseteDataProvider;
    private final CoordinateLayerRenderProps mIdealRamseteRenderProperties;

    @FXML
    protected Pane mTopPane;

    @FXML
    protected FieldLayerController mFieldController;

    @FXML
    protected Group mLayers;

    @FXML
    private CameraRayLayerController mCameraLayerController;

    @FXML
    private RobotPositionLayerController mRobotPositionController;

    @FXML
    private TrajectoryConfigLayerController mTrajectoryConfigController;

    @FXML
    private GoToPositionController mGoToPositionController;

    @FXML
    private CoordinateLayerController mFadingCoordinatesController;

    @FXML
    private CoordinateLayerController mIdealTrajectoryCoordinatesController;

    @FXML
    private CoordinateLayerController mIdealRamseteCoordinatesController;

    @FXML
    private PurePursuitLayerController mPurePursuitController;

    protected BaseGuiController(FieldLoader.FieldsConfig aFieldConfig)
    {
        this(new FieldLoader(aFieldConfig), Distance.fromInches(36), Distance.fromInches(44));
    }

    protected BaseGuiController(FieldLoader aFieldLoader, Distance aRobotWidth, Distance aRobotHeight)
    {
        this(aFieldLoader.getImage(), aFieldLoader.getShortDim(), aFieldLoader.getLongDim(), aRobotWidth, aRobotHeight, PixelConverter.Orientation.Landscape, PixelConverter.OriginPosition.BottomLeft);
    }

    protected BaseGuiController(String aFieldImageUrl,
                                Distance aShortDirection, Distance aLongDirection,
                                Distance aRobotWidth, Distance aRobotHeight,
                                PixelConverter.Orientation aOrientation, PixelConverter.OriginPosition aOriginPosition)
    {
        this(new Image(BaseGuiController.class.getResource(aFieldImageUrl).toExternalForm()), aShortDirection, aLongDirection, aRobotWidth, aRobotHeight, aOrientation, aOriginPosition);
    }

    protected BaseGuiController(Image aFieldImage,
                                Distance aShortDirection, Distance aLongDirection,
                                Distance aRobotWidth, Distance aRobotHeight,
                                PixelConverter.Orientation aOrientation, PixelConverter.OriginPosition aOriginPosition)
    {
        mFieldImage = aFieldImage;
        mFieldShortDimension = aShortDirection;
        mFieldLongDimension = aLongDirection;
        mRobotWidth = aRobotWidth;
        mRobotHeight = aRobotHeight;
        mPixelConverter = new PixelConverter(mFieldShortDimension, mFieldLongDimension, aOrientation, aOriginPosition);

        mCoordinatesRenderProperties = new CoordinateLayerRenderProps(100, 5, Color.ORANGERED, true);
        mCoordinatesDataProvider = new DataProvider<>(750);

        mIdealTrajectoryRenderProperties = new CoordinateLayerRenderProps(100, 1, Color.YELLOWGREEN, false);
        mIdealTrajectoryDataProvider = new DataProvider<>();

        mIdealRamseteRenderProperties = new CoordinateLayerRenderProps(100, 5, Color.DARKRED, false);
        mIdealRamseteDataProvider = new DataProvider<>();
    }

    /**
     * Called after FXML fields have been initialized.
     */
    public void initialize()
    {
        mFieldController.setFieldParameters(mFieldImage);

        Scale scale = new Scale();
        scale.xProperty()
                .bind(Bindings.createDoubleBinding(
                    () -> Math.min(mTopPane.getWidth() / mFieldImage.getWidth(), mTopPane.getHeight() / mFieldImage.getHeight()),
                        mTopPane.widthProperty(), mTopPane.heightProperty()));

        scale.yProperty()
                .bind(Bindings.createDoubleBinding(
                    () -> Math.min(mTopPane.getWidth() / mFieldImage.getWidth(), mTopPane.getHeight() / mFieldImage.getHeight()),
                        mTopPane.widthProperty(), mTopPane.heightProperty()));

        mLayers.getTransforms().add(scale);
        mPixelConverter.setImageScale(scale, mFieldImage.getWidth(), mFieldImage.getHeight(), mFieldShortDimension, mFieldLongDimension);

        mRobotPositionController.setRobotDimensions(mPixelConverter, mRobotWidth, mRobotHeight);

        mFadingCoordinatesController.setup(mCoordinatesRenderProperties, mCoordinatesDataProvider, mPixelConverter);
        mIdealTrajectoryCoordinatesController.setup(mIdealTrajectoryRenderProperties, mIdealTrajectoryDataProvider, mPixelConverter);
        mIdealRamseteCoordinatesController.setup(mIdealRamseteRenderProperties, mIdealRamseteDataProvider, mPixelConverter);
        mPurePursuitController.setup(mPixelConverter);
    }

    public PixelConverter getPixelConverter()
    {
        return mPixelConverter;
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

    public void setCameraRays(List<CameraRayLayerController.Ray> aRays)
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

    /**
     * Sets the ideal trajectory to draw on the display.
     *
     * @param aCoordinates
     *            The ideal trajectory
     */
    public void setIdealRamsete(List<Coordinate> aCoordinates)
    {
        mIdealRamseteDataProvider.clear();
        for (Coordinate coord : aCoordinates)
        {
            mIdealRamseteDataProvider.addData(coord);
        }
        mIdealRamseteCoordinatesController.render();
    }

    public TrajectoryConfigLayerController.CoodrinateWrapper getSelectedWaypoint()
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
