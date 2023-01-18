package org.snobot.coordinate_gui.shuffleboard.charged_up.data;

import java.util.Map;

import org.snobot.coordinate_gui.shuffleboard.data.BaseCoordinateGuiData;

public class ChargedUpCoordinateGuiData extends BaseCoordinateGuiData
{
    /**
     * Constructor.
     */
    public ChargedUpCoordinateGuiData()
    {
    }

    /**
     * Constructor. Initializes the data from the map.
     *
     * @param aMap
     *            The map from NetworkTables
     */
    public ChargedUpCoordinateGuiData(Map<String, Object> aMap)
    {
        super(aMap);
    }
}
