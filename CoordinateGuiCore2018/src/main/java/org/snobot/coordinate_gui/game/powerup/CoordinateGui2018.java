package org.snobot.coordinate_gui.game.powerup;

import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.PixelConverter;
import org.snobot.coordinate_gui.ui.layers.BaseGuiController;
import org.snobot.coordinate_gui.ui.layers.RobotPositionLayerController;

import javafx.fxml.FXML;

public class CoordinateGui2018 extends BaseGuiController
{
    private static final String FIELD_IMAGE_PATH = "/org/snobot/coordinate_gui/game/powerup/2018Field.png";

    private static final Distance FIELD_WIDTH = Distance.fromFeet(27);
    private static final Distance FIELD_HEIGHT = Distance.fromFeet(54);

    private static final Distance ROBOT_WIDTH = Distance.fromFeet(36);
    private static final Distance ROBOT_HEIGHT = Distance.fromFeet(44);

    @FXML
    private RobotPositionLayerController mRobotPositionController;

    /**
     * Constructor.
     */
    public CoordinateGui2018()
    {
        super(FIELD_IMAGE_PATH, FIELD_WIDTH, FIELD_HEIGHT, PixelConverter.Orientation.Portrait, PixelConverter.OriginPosition.CenterField);
    }

    @Override
    public void initialize()
    {
        super.initialize();
        mRobotPositionController.setRobotDimensions(mPixelConverter, ROBOT_WIDTH, ROBOT_HEIGHT);
    }
}
