package org.snobot.coordinate_gui.powerup;

import org.snobot.coordinate_gui.BaseYearSpecificGui;

public class CoordinateGui2019 extends BaseYearSpecificGui
{
    private static final String FIELD_IMAGE_PATH = "/org/snobot/coordinate_gui/powerup/2018Field.png";

    private static final double FIELD_WIDTH = 27;
    private static final double FIELD_HEIGHT = 54;

    private static final double CENTER_POINT_Y = 27;
    private static final double CENTER_POINT_X = 13.5;
    private static final double ROBOT_WIDTH = 36 / 12.0;
    private static final double ROBOT_HEIGHT = 44 / 12.0;

    /**
     * Constructor.
     */
    public CoordinateGui2019()
    {
        super(FIELD_IMAGE_PATH, FIELD_WIDTH, FIELD_HEIGHT, CENTER_POINT_X, CENTER_POINT_Y, ROBOT_WIDTH, ROBOT_HEIGHT);
    }
}
