package org.snobot.coordinate_gui.shuffleboard.data;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.shuffleboard.api.data.ComplexData;
import org.snobot.coordinate_gui.model.Coordinate;

public class CoordinateData extends ComplexData<CoordinateData>
{
    private final double mX;
    private final double mY;
    private final double mAngle;
    private final double mCtr;

    public CoordinateData()
    {
        this(0, 0, 0, 0);
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
        this(
                (Double) aMap.getOrDefault(aPrefix + SmartDashboardNames.sX_POSITION, 0.0), 
                (Double) aMap.getOrDefault(aPrefix + SmartDashboardNames.sY_POSITION, 0.0),
                (Double) aMap.getOrDefault(aPrefix + SmartDashboardNames.sORIENTATION, 0.0),
                (Double) aMap.getOrDefault(aPrefix + SmartDashboardNames.sROBOT_POSITION_CTR, 0.0));
    }

    /**
     * Constructor.
     * 
     * @param aX
     *            The X position
     * @param aY
     *            The Y position
     * @param aAngle
     *            The angle of the robot, relative to north
     */
    public CoordinateData(double aX, double aY, double aAngle, double aCtr)
    {
        mX = aX;
        mY = aY;
        mAngle = aAngle;
        mCtr = aCtr;
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
        map.put(aPrefix + SmartDashboardNames.sX_POSITION, mX);
        map.put(aPrefix + SmartDashboardNames.sY_POSITION, mY);
        map.put(aPrefix + SmartDashboardNames.sORIENTATION, mAngle);
        map.put(aPrefix + SmartDashboardNames.sROBOT_POSITION_CTR, mCtr);
        return map;
    }

    public double getX()
    {
        return mX;
    }

    public double getY()
    {
        return mY;
    }

    public double getAngle()
    {
        return mAngle;
    }

    /**
     * Converts this to the data model the gui core understands.
     * @return The new value
     */
    public Coordinate toCoord()
    {
        double x = getX() / 12;
        double y = getY() / 12;
        double angle = getAngle();

        return new Coordinate(x, y, angle);
    }
}
