package org.snobot.coordinate_gui.shuffleboard.data;

import edu.wpi.first.shuffleboard.api.data.ComplexData;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Velocity;
import org.snobot.nt.ramsete_plotter.RamsetePlotDeserializer;
import org.snobot.nt.ramsete_plotter.RamsetePointInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    /**
     * Converts the serialized string into coordinate points.
     * @param aDistanceUnit The distance unit
     * @return The converted coordinates
     */
    public List<Coordinate> toIdealCoordinates(Distance.Unit aDistanceUnit)
    {
        Velocity.Unit velocityUnit = Velocity.Unit.INCH_PER_SEC; // Note: velocity doesn't matter for this, so hardcode it
        List<RamsetePointInfo> ramsetePoints = RamsetePlotDeserializer.deserializeIdealPoints(mIdealSpline, aDistanceUnit, velocityUnit);

        List<Coordinate> coordinates = new ArrayList<>();
        for (RamsetePointInfo point : ramsetePoints)
        {
            coordinates.add(new Coordinate(point.mPosition, point.mHeading));
        }
        return coordinates;
    }
}
