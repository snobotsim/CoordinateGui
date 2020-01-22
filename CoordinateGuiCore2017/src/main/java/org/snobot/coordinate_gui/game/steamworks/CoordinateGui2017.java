package org.snobot.coordinate_gui.game.steamworks;

import java.util.List;

import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.PixelConverter;
import org.snobot.coordinate_gui.ui.layers.BaseGuiController;
import org.snobot.coordinate_gui.ui.layers.CameraRayLayerController;
import org.snobot.coordinate_gui.ui.layers.CameraRayLayerController.Ray;
import org.snobot.coordinate_gui.ui.layers.RobotPositionLayerController;

import javafx.fxml.FXML;

public class CoordinateGui2017 extends BaseGuiController
{
    private static final String FIELD_IMAGE_PATH = "/org/snobot/coordinate_gui/game/steamworks/2017Field.png";

    private static final Distance FIELD_WIDTH = Distance.fromFeet(27);
    private static final Distance FIELD_HEIGHT = Distance.fromFeet(54);

    private static final Distance ROBOT_WIDTH = Distance.fromInches(36);
    private static final Distance ROBOT_HEIGHT = Distance.fromInches(44);

    /**
     * Constructor.
     */
    public CoordinateGui2017()
    {
        super(FIELD_IMAGE_PATH, FIELD_WIDTH, FIELD_HEIGHT, ROBOT_WIDTH, ROBOT_HEIGHT, PixelConverter.Orientation.Portrait, PixelConverter.OriginPosition.CenterField);
    }

}
