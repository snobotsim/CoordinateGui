package org.snobot.coordinate_gui.shuffleboard.data;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.shuffleboard.api.data.ComplexData;

public class VisionData extends ComplexData<VisionData>
{
    private final String mValue;

    public VisionData()
    {
        this("");
    }

    /**
     * Constructor.
     * 
     * @param aMap
     *            The map potentially containing the coordinate data
     */
    public VisionData(Map<String, Object> aMap)
    {
        this("", aMap);
    }

    /**
     * Constructor.
     * 
     * @param aPrefix
     *            Optional prefix to prepend the names with in the map
     * @param aMap
     *            The map potentially containing the coordinate data
     */
    public VisionData(String aPrefix, Map<String, Object> aMap)
    {
        this((String) aMap.getOrDefault(aPrefix + SmartDashboardNames.sCAMERA_POSITIONS, ""));
    }

    /**
     * Constructor.
     * 
     * @param aValue
     *            The vision data
     */
    public VisionData(String aValue)
    {
        mValue = aValue;
    }

    @Override
    public Map<String, Object> asMap()
    {
        return asMap("");
    }

    /**
     * Gets a representation of this data as a map.
     * 
     * @param aPrefix
     *            The prefix to prepend to the field names
     * @return The map representation
     */
    public Map<String, Object> asMap(String aPrefix)
    {
        Map<String, Object> map = new HashMap<>();
        map.put(aPrefix + SmartDashboardNames.sCAMERA_POSITIONS, mValue);
        return map;
    }

    public String getValue()
    {
        return mValue;
    }

    @Override
    public String toString()
    {
        return "VisionData [mValue=" + mValue + "]";
    }

}
