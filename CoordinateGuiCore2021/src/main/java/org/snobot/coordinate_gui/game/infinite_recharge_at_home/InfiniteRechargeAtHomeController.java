package org.snobot.coordinate_gui.game.infinite_recharge_at_home;

import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.PixelConverter;
import org.snobot.coordinate_gui.ui.layers.BaseGuiController;

public class InfiniteRechargeAtHomeController extends BaseGuiController
{
    private static final String FIELD_IMAGE_PATH = "/org/snobot/coordinate_gui/game/infinite_recharge_at_home/2021-infinite-recharge.png";

    private static final Distance LONG_DIM = Distance.fromFeet(52 + 5.25 / 12);
    private static final Distance SHORT_DIM = Distance.fromFeet(26 + 11.25 / 12);

    private static final Distance ROBOT_WIDTH = Distance.fromInches(36);
    private static final Distance ROBOT_HEIGHT = Distance.fromInches(44);

    /**
     * Constructor.
     */
    public InfiniteRechargeAtHomeController()
    {
        super(FIELD_IMAGE_PATH, LONG_DIM, SHORT_DIM, ROBOT_WIDTH, ROBOT_HEIGHT, PixelConverter.Orientation.Landscape, PixelConverter.OriginPosition.BottomLeft);
    }
}
