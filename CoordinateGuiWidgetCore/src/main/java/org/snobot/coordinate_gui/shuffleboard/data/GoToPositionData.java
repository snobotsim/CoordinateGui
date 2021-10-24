package org.snobot.coordinate_gui.shuffleboard.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.shuffleboard.api.data.ComplexData;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Position2dDistance;

public class GoToPositionData extends ComplexData<GoToPositionData>
{
    private static final double[] DEFAULT_VALUE = {0, 0};

    private final double[] mData;
    private final Double mX;
    private final Double mY;

    public GoToPositionData()
    {
        this(DEFAULT_VALUE);
    }

    public GoToPositionData(Map<String, Object> aMap)
    {
        this("", aMap);
    }

    public GoToPositionData(String aPrefix, Map<String, Object> aMap)
    {
        this((double[]) aMap.getOrDefault(aPrefix + SmartDashboardNames.sGO_TO_POSITION_POSITIONS, DEFAULT_VALUE));
    }

    /**
     * Constructor.
     * 
     * @param aData
     *            The CSV representation of the coordinate data
     */
    public GoToPositionData(double[] aData)
    {
        mData = Arrays.copyOf(aData, aData.length);

        if (aData.length == 2) // NOPMD
        {
            mX = mData[0];
            mY = mData[1];
        }
        else
        {
            mX = null;
            mY = null;
        }
    }

    @Override
    public Map<String, Object> asMap()
    {
        return asMap("");
    }

    /**
     * Gets a representation of the data as a map.
     * 
     * @param aPrefix
     *            The prefix to prepend to the names
     * @return The representative map
     */
    public Map<String, Object> asMap(String aPrefix)
    {
        Map<String, Object> map = new HashMap<>();
        map.put(aPrefix + SmartDashboardNames.sGO_TO_POSITION_POSITIONS, mData);
        return map;
    }

    /**
     * Gets the X coordinate.
     * @return The coordinate
     */
    public Double getX()
    {
        return mX;
    }

    /**
     * Gets the Y coordinate.
     * @return The coordinate
     */
    public Double getY()
    {
        return mY;
    }

    /**
     * Converts this to the data model the gui core understands.
     * @param aDistanceUnit the distance units to use
     * @return The new value
     */
    public Position2dDistance toCoordinate(Distance.Unit aDistanceUnit)
    {
        if (getX() == null || getY() == null)
        {
            return null;
        }
        return new Position2dDistance(getX(), getY(), aDistanceUnit);
    }
}
