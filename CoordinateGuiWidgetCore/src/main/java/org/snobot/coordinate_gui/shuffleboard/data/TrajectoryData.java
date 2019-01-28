package org.snobot.coordinate_gui.shuffleboard.data;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.shuffleboard.api.data.ComplexData;

public class TrajectoryData extends ComplexData<TrajectoryData>
{
    private final String mIdealSpline;
    private final String mMeasuredSpline;
    private final String mSplineWaypoints;

    public TrajectoryData()
    {
        this("", "", "");
    }

    /**
     * Constructor.
     * 
     * @param aMap
     *            A data map, created from the widget
     */
    public TrajectoryData(Map<String, Object> aMap)
    {
        this("", aMap);
    }

    /**
     * Constructor.
     * 
     * @param aPrefix
     *            The prefix to prepend data names with
     * @param aMap
     *            The map used to populate the data
     */
    public TrajectoryData(String aPrefix, Map<String, Object> aMap)
    {
        this(
                (String) aMap.getOrDefault(aPrefix + SmartDashboardNames.sSPLINE_IDEAL_POINTS, ""),
                (String) aMap.getOrDefault(aPrefix + SmartDashboardNames.sSPLINE_REAL_POINT, ""),
                (String) aMap.getOrDefault(aPrefix + SmartDashboardNames.sSPLINE_WAYPOINTS, ""));
    }

    /**
     * Constructor.
     * 
     * @param aIdealSpline
     *            A string representing the serialized ideal spline
     * @param aMeasuredSpline
     *            A string representing the serialized measured spline
     * @param aSplineWaypoints
     *            A string representing the serialized waypoints
     */
    public TrajectoryData(
            String aIdealSpline,
            String aMeasuredSpline,
            String aSplineWaypoints)
    {
        mIdealSpline = aIdealSpline;
        mMeasuredSpline = aMeasuredSpline;
        mSplineWaypoints = aSplineWaypoints;
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
        map.put(aPrefix + SmartDashboardNames.sSPLINE_IDEAL_POINTS, mIdealSpline);
        map.put(aPrefix + SmartDashboardNames.sSPLINE_REAL_POINT, mMeasuredSpline);
        map.put(aPrefix + SmartDashboardNames.sSPLINE_WAYPOINTS, mSplineWaypoints);
        return map;
    }

    public String getIdealSpline()
    {
        return mIdealSpline;
    }

    public String getMeasuredSpline()
    {
        return mMeasuredSpline;
    }

    public String getSplineWaypoints()
    {
        return mSplineWaypoints;
    }

}
