package org.snobot.coordinate_gui.game.deep_space;

import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.PixelConverter;
import org.snobot.coordinate_gui.ui.layers.BaseGuiController;

public class DeepSpaceController extends BaseGuiController
{
    private static final String FIELD_IMAGE_PATH = "/org/snobot/coordinate_gui/game/deep_space/2019Field.png";

    private static final Distance FIELD_WIDTH = Distance.fromFeet(27);
    private static final Distance FIELD_HEIGHT = Distance.fromFeet(54);

    private static final Distance ROBOT_WIDTH = Distance.fromInches(36 / 12.0);
    private static final Distance ROBOT_HEIGHT = Distance.fromFeet(44 / 12.0);

    /**
     * Constructor.
     */
    public DeepSpaceController()
    {
        super(FIELD_IMAGE_PATH, FIELD_WIDTH, FIELD_HEIGHT, ROBOT_WIDTH, ROBOT_HEIGHT, PixelConverter.Orientation.Portrait, PixelConverter.OriginPosition.CenterField);

    }
}
