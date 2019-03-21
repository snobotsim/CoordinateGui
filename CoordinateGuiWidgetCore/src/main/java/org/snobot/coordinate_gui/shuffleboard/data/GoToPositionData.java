package org.snobot.coordinate_gui.shuffleboard.data;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.shuffleboard.api.data.ComplexData;

public class GoToPositionData extends ComplexData<GoToPositionData>
{
    private final String mPositionCsv;
    private final Double mX;
    private final Double mY;

    public GoToPositionData()
    {
        this("0,0");
    }

    public GoToPositionData(Map<String, Object> aMap)
    {
        this("", aMap);
    }

    public GoToPositionData(String aPrefix, Map<String, Object> aMap)
    {
        this((String) aMap.getOrDefault(aPrefix + SmartDashboardNames.sGO_TO_POSITION_POSITIONS, ""));
    }

    /**
     * Constructor.
     * 
     * @param aPositionString
     *            The CSV representation of the coordinate data
     */
    public GoToPositionData(String aPositionString)
    {
        mPositionCsv = aPositionString;

        if (!mPositionCsv.isEmpty())
        {
            String[] pieces = aPositionString.split(",");
            mX = Double.parseDouble(pieces[0]);
            mY = Double.parseDouble(pieces[1]);
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
        map.put(aPrefix + SmartDashboardNames.sGO_TO_POSITION_POSITIONS, mPositionCsv);
        return map;
    }

    public Double getX()
    {
        return mX;
    }

    public Double getY()
    {
        return mY;
    }
}