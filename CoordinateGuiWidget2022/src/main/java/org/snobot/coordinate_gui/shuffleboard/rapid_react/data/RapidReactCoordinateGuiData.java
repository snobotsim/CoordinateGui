package org.snobot.coordinate_gui.shuffleboard.rapid_react.data;

import java.util.Map;

import org.snobot.coordinate_gui.shuffleboard.data.BaseCoordinateGuiData;

public class RapidReactCoordinateGuiData extends BaseCoordinateGuiData
{
    /**
     * Constructor.
     */
    public RapidReactCoordinateGuiData()
    {
    }

    /**
     * Constructor. Initializes the data from the map.
     *
     * @param aMap
     *            The map from NetworkTables
     */
    public RapidReactCoordinateGuiData(Map<String, Object> aMap)
    {
        super(aMap);
    }
}
