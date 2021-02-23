package org.snobot.coordinate_gui.game.infinite_recharge_at_home;

import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.PixelConverter;
import org.snobot.coordinate_gui.ui.layers.BaseGuiController;

public class GalacticSearchBController extends BaseGuiController
{
    private static final String FIELD_IMAGE_PATH = "/org/snobot/coordinate_gui/game/infinite_recharge_at_home/2021-galacticsearchb.png";

    private static final Distance LONG_DIM = Distance.fromFeet(30);
    private static final Distance SHORT_DIM = Distance.fromFeet(15);

    /**
     * Constructor.
     */
    public GalacticSearchBController()
    {
        super(FIELD_IMAGE_PATH, LONG_DIM, SHORT_DIM, RobotDims.ROBOT_WIDTH, RobotDims.ROBOT_HEIGHT, PixelConverter.Orientation.Landscape, PixelConverter.OriginPosition.BottomLeft);
    }
}
