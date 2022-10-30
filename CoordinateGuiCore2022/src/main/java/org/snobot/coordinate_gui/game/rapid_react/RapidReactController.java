package org.snobot.coordinate_gui.game.rapid_react;

import edu.wpi.first.wpilibj.apriltag.AprilTagFields;
import org.snobot.coordinate_gui.model.FieldLoader;
import org.snobot.coordinate_gui.ui.layers.BaseGuiController;

public class RapidReactController extends BaseGuiController
{
    /**
     * Constructor.
     */
    public RapidReactController()
    {
        super(FieldLoader.FieldsConfig.Year2022, AprilTagFields.k2022RapidReact);
    }
}
