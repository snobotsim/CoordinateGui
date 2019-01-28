package org.snobot.coordinate_gui.game.powerup;

import org.snobot.coordinate_gui.ui.layers.BaseGuiController;
import org.snobot.coordinate_gui.ui.layers.RobotPositionLayerController;

import javafx.fxml.FXML;

public class CoordinateGui2018 extends BaseGuiController
{
    private static final String FIELD_IMAGE_PATH = "/org/snobot/coordinate_gui/game/powerup/2018Field.png";

    private static final double FIELD_WIDTH = 27;
    private static final double FIELD_HEIGHT = 54;

    private static final double ROBOT_WIDTH = 36 / 12.0;
    private static final double ROBOT_HEIGHT = 44 / 12.0;

    @FXML
    private RobotPositionLayerController mRobotPositionController;

    /**
     * Constructor.
     */
    public CoordinateGui2018()
    {
        super(FIELD_IMAGE_PATH, FIELD_WIDTH, FIELD_HEIGHT);
    }

    @Override
    public void initialize()
    {
        super.initialize();
        mRobotPositionController.setRobotDimensions(mPixelConverter, ROBOT_WIDTH, ROBOT_HEIGHT);
    }
}
