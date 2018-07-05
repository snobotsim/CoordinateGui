package org.snobot.coordinate_gui.powerup;

import java.util.Locale;

import org.snobot.coordinate_gui.BaseYearSpecificGui;

public class CoordinateGui2018 extends BaseYearSpecificGui
{
    private static final String FIELD_IMAGE_PATH = "/org/snobot/coordinate_gui/powerup/2018Field.png";

    private static final double FIELD_WIDTH = 27;
    private static final double FIELD_HEIGHT = 54;

    private static final double CENTER_POINT_Y = 27;
    private static final double CENTER_POINT_X = 13.5;
    private static final double ROBOT_WIDTH = 36 / 12.0;
    private static final double ROBOT_HEIGHT = 44 / 12.0;

    protected final ScoringPostitionLayer mScoringPositionLayer;

    /**
     * Constructor.
     */
    public CoordinateGui2018()
    {
        super(FIELD_IMAGE_PATH, FIELD_WIDTH, FIELD_HEIGHT, CENTER_POINT_X, CENTER_POINT_Y, ROBOT_WIDTH, ROBOT_HEIGHT);

        mScoringPositionLayer = new ScoringPostitionLayer(mConverter);
        mLayerManager.addLayer(mScoringPositionLayer);
    }

    /**
     * Sets the game specific data for.
     * 
     * @param aGameSpecificData
     *            The game data from the DS
     */
    public void setPanelLocations(String aGameSpecificData)
    {
        if (aGameSpecificData == null || aGameSpecificData.length() != 3)
        {
            return;
        }

        String gameSpecificData = aGameSpecificData.toLowerCase(Locale.getDefault());

        boolean closeSwitchRight = gameSpecificData.charAt(0) == 'r';
        boolean scaleRight = gameSpecificData.charAt(1) == 'r';
        boolean farSwitchRight = gameSpecificData.charAt(2) == 'r';

        mScoringPositionLayer.setData(closeSwitchRight, scaleRight, farSwitchRight);
    }
}
