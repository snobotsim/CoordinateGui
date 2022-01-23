package org.snobot.coordinate_gui.shuffleboard.powerup.data;

import java.util.Map;

import org.snobot.coordinate_gui.shuffleboard.data.BaseCoordinateGuiData;

public class PowerupCoordinateGuiData extends BaseCoordinateGuiData
{
    /**
     * Constructor.
     */
    public PowerupCoordinateGuiData()
    {
    }

    /**
     * Constructor. Initializes the data from the map.
     *
     * @param aMap
     *            The map from NetworkTables
     */
    public PowerupCoordinateGuiData(Map<String, Object> aMap)
    {
        super(aMap);
    }
}
