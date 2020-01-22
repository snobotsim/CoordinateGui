package org.snobot.coordinate_gui.game.infinite_recharge;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.DataProvider;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.PixelConverter;
import org.snobot.coordinate_gui.ui.layers.BaseGuiController;
import org.snobot.coordinate_gui.ui.layers.CameraRayLayerController;
import org.snobot.coordinate_gui.ui.layers.CameraRayLayerController.Ray;
import org.snobot.coordinate_gui.ui.layers.CoordinateLayerController;
import org.snobot.coordinate_gui.ui.layers.RobotPositionLayerController;
import org.snobot.coordinate_gui.ui.render_props.CoordinateLayerRenderProps;

import java.util.List;

public class InfiniteRechargeController extends BaseGuiController
{
    private static final String FIELD_IMAGE_PATH = "/org/snobot/coordinate_gui/game/infinite_recharge/2020Field.png";

//    private static final Distance LONG_DIM = Distance.fromFeet(52 + 5.25 / 12);
//    private static final Distance SHORT_DIM = Distance.fromFeet(26 + 11.25 / 12);
    private static final Distance LONG_DIM = Distance.fromFeet(54);
    private static final Distance SHORT_DIM = Distance.fromFeet(27);

    private static final Distance ROBOT_WIDTH = Distance.fromInches(36);
    private static final Distance ROBOT_HEIGHT = Distance.fromInches(44);

    @FXML
    private RobotPositionLayerController mRobotPositionController;

    @FXML
    private CameraRayLayerController mCameraLayerController;

    @FXML
    private CoordinateLayerController mFadingCoordinatesController;

    private final DataProvider<Coordinate> mCoordinatesDataProvider;
    private final CoordinateLayerRenderProps mCoordinatesRenderProperties;

    /**
     * Constructor.
     */
    public InfiniteRechargeController()
    {
        super(FIELD_IMAGE_PATH, LONG_DIM, SHORT_DIM, PixelConverter.Orientation.Landscape, PixelConverter.OriginPosition.AlwaysIncreasing);

        mCoordinatesRenderProperties = new CoordinateLayerRenderProps(100, 5, Color.ORANGERED, true);
        mCoordinatesDataProvider = new DataProvider<>(1000);
    }

    @Override
    public void initialize()
    {
        super.initialize();
        mRobotPositionController.setRobotDimensions(mPixelConverter, ROBOT_WIDTH, ROBOT_HEIGHT);

        mFadingCoordinatesController.setup(mCoordinatesRenderProperties, mCoordinatesDataProvider, mPixelConverter);
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

}
