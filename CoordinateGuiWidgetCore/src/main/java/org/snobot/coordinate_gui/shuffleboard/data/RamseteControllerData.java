package org.snobot.coordinate_gui.shuffleboard.data;

import edu.wpi.first.shuffleboard.api.data.ComplexData;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.model.Velocity;
import org.snobot.nt.ramsete_plotter.RamseteInstantaneousPoint;
import org.snobot.nt.ramsete_plotter.RamsetePlotDeserializer;
import org.snobot.nt.ramsete_plotter.RamsetePointInfo;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class RamseteControllerData extends ComplexData<RamseteControllerData>
{
    private static final double[] DEFAULT_VALUE = new double[]{};

    private final String mIdealSpline;
    private final double[] mMeasuredSpline;
    private final double[] mWaypoints;

    public RamseteControllerData()
    {
        this("", DEFAULT_VALUE, DEFAULT_VALUE);
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
        double[] aMeasuredSpline,
        double[] aWaypoints)
    {
        mIdealSpline = aIdealSpline;
        mMeasuredSpline = Arrays.copyOf(aMeasuredSpline, aMeasuredSpline.length);
        mWaypoints = Arrays.copyOf(aWaypoints, aWaypoints.length);
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
            (double[]) aMap.getOrDefault(aPrefix + SmartDashboardNames.sRAMSETE_REAL_POINT, DEFAULT_VALUE),
            (double[]) aMap.getOrDefault(aPrefix + SmartDashboardNames.sRAMSETE_WAYPOINTS, DEFAULT_VALUE));
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
        map.put(aPrefix + SmartDashboardNames.sRAMSETE_WAYPOINTS, mWaypoints);
        return map;
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

    public List<RamsetePointInfo> getIdealPoints(Distance.Unit aDistanceUnit, Velocity.Unit aVelocityUnit)
    {
        return RamsetePlotDeserializer.deserializeIdealPoints(mIdealSpline, aDistanceUnit, aVelocityUnit);
    }

    public RamseteInstantaneousPoint getRealPoints(Distance.Unit aDistanceUnit, Velocity.Unit aVelocityUnit)
    {
        return RamsetePlotDeserializer.deserializeInstantaneousPoint(mMeasuredSpline, aDistanceUnit, aVelocityUnit);
    }

    /**
     * Gets the waypoints.
     *
     * @param aDistanceUnit The distance unit
     * @return The waypoints
     */
    public List<Coordinate> getWaypoints(Distance.Unit aDistanceUnit)
    {
        List<Coordinate> waypoints = new ArrayList<>();

        int dataPtr = 0;
        while (dataPtr < mWaypoints.length)
        {
            waypoints.add(new Coordinate(new Position2dDistance(mWaypoints[dataPtr++], mWaypoints[dataPtr++], aDistanceUnit), mWaypoints[dataPtr++]));
        }

        return waypoints;
    }
}
