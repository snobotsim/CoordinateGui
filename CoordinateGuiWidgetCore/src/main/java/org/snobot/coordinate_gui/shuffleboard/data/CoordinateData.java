package org.snobot.coordinate_gui.shuffleboard.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.shuffleboard.api.data.ComplexData;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Position2dDistance;

public class CoordinateData extends ComplexData<CoordinateData>
{
    private static final double[] DEFAULT_VALUE = new double[]{0, 0, 0};

    private final double[] mData;

    public CoordinateData()
    {
        this(DEFAULT_VALUE);
    }

    /**
     * Constructor.
     * 
     * @param aMap
     *            The map potentially containing the coordinate data
     */
    public CoordinateData(Map<String, Object> aMap)
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
    public CoordinateData(String aPrefix, Map<String, Object> aMap)
    {
        this((double[]) aMap.getOrDefault(aPrefix + SmartDashboardNames.sROBOT_POSITION, DEFAULT_VALUE));
    }

    /**
     * Constructor.
     * 
     * @param aData
     *            The coordinate data
     */
    public CoordinateData(double[] aData)
    {
        mData = Arrays.copyOf(aData, aData.length);
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
        map.put(aPrefix + SmartDashboardNames.sROBOT_POSITION, mData);
        return map;
    }

    /**
     * Converts this to the data model the gui core understands.
     * @param aDistanceUnit the distance units to use
     * @return The new value
     */
    public Coordinate toCoord(Distance.Unit aDistanceUnit)
    {
        return new Coordinate(new Position2dDistance(mData[0], mData[1], aDistanceUnit), mData[2]);
    }
}
