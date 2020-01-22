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

    private static final Distance LONG_DIM = Distance.fromFeet(52 + 5.25 / 12);
    private static final Distance SHORT_DIM = Distance.fromFeet(26 + 11.25 / 12);

    private static final Distance ROBOT_WIDTH = Distance.fromInches(36);
    private static final Distance ROBOT_HEIGHT = Distance.fromInches(44);

    /**
     * Constructor.
     */
    public InfiniteRechargeController()
    {
        super(FIELD_IMAGE_PATH, LONG_DIM, SHORT_DIM, ROBOT_WIDTH, ROBOT_HEIGHT, PixelConverter.Orientation.Landscape, PixelConverter.OriginPosition.AlwaysIncreasing);
    }
}
