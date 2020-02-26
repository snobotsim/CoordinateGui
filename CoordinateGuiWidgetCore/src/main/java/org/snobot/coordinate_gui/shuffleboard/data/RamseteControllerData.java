package org.snobot.coordinate_gui.shuffleboard.data;

import edu.wpi.first.shuffleboard.api.data.ComplexData;

import java.util.HashMap;
import java.util.Map;

public class RamseteControllerData extends ComplexData<RamseteControllerData>
{
    private final String mIdealSpline;
    private final String mMeasuredSpline;

    public RamseteControllerData()
    {
        this("", "");
    }

    /**
     * Constructor.
     *
     * @param aMap
     *            A data map, created from the widget
     */
    public RamseteControllerData(Map<String, Object> aMap)
    {
        this("", aMap);
    }

    /**
     * Constructor.
     *
     * @param aIdealSpline
     *            A string representing the serialized ideal spline
     * @param aMeasuredSpline
     *            A string representing the serialized measured spline
     */
    public RamseteControllerData(
        String aIdealSpline,
        String aMeasuredSpline)
    {
        mIdealSpline = aIdealSpline;
        mMeasuredSpline = aMeasuredSpline;
    }


    /**
     * Constructor.
     *
     * @param aPrefix
     *            The prefix to prepend data names with
     * @param aMap
     *            The map used to populate the data
     */
    public RamseteControllerData(String aPrefix, Map<String, Object> aMap)
    {
        this(
            (String) aMap.getOrDefault(aPrefix + SmartDashboardNames.sRAMSETE_IDEAL_POINTS, ""),
            (String) aMap.getOrDefault(aPrefix + SmartDashboardNames.sRAMSETE_REAL_POINT, ""));
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
        map.put(aPrefix + SmartDashboardNames.sRAMSETE_IDEAL_POINTS, mIdealSpline);
        map.put(aPrefix + SmartDashboardNames.sRAMSETE_REAL_POINT, mMeasuredSpline);
        return map;
    }

    public String getIdealPoints()
    {
        return mIdealSpline;
    }

    public String getRealPoints()
    {
        return mMeasuredSpline;
    }
}
