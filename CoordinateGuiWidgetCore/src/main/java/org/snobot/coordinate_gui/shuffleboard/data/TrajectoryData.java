package org.snobot.coordinate_gui.shuffleboard.data;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.shuffleboard.api.data.ComplexData;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.shuffleboard.widgets.IdealSplineSerializer;
import org.snobot.nt.spline_plotter.SplineSegment;

public class TrajectoryData extends ComplexData<TrajectoryData>
{
    private static final double[] DEFAULT_VALUE = {};

    private final double[] mIdealSpline;
    private final double[] mMeasuredSpline;
    private final double[] mSplineWaypoints;

    public TrajectoryData()
    {
        this(DEFAULT_VALUE, DEFAULT_VALUE, DEFAULT_VALUE);
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
                (double[]) aMap.getOrDefault(aPrefix + SmartDashboardNames.sSPLINE_IDEAL_POINTS, DEFAULT_VALUE),
                (double[]) aMap.getOrDefault(aPrefix + SmartDashboardNames.sSPLINE_REAL_POINT, DEFAULT_VALUE),
                (double[]) aMap.getOrDefault(aPrefix + SmartDashboardNames.sSPLINE_WAYPOINTS, DEFAULT_VALUE));
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
            double[] aIdealSpline,
            double[] aMeasuredSpline,
            double[] aSplineWaypoints)
    {
        mIdealSpline = Arrays.copyOf(aIdealSpline, aIdealSpline.length);
        mMeasuredSpline = Arrays.copyOf(aMeasuredSpline, aMeasuredSpline.length);
        mSplineWaypoints = Arrays.copyOf(aSplineWaypoints, aSplineWaypoints.length);
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

    /**
     * Converts this to the data model the gui core understands.
     * @return The new value
     */
    public List<Coordinate> toWaypoints(Distance.Unit aDistanceUnit)
    {
        List<Coordinate> coordinates = new ArrayList<>();

        int dataPtr = 0;

        while (dataPtr < mSplineWaypoints.length)
        {
            double x = mSplineWaypoints[dataPtr++];
            double y = mSplineWaypoints[dataPtr++];
            double angle = Math.toDegrees(mSplineWaypoints[dataPtr++]);
            Coordinate coordinate = new Coordinate(new Position2dDistance(x, y, aDistanceUnit), angle);
            coordinates.add(coordinate);
        }

        return coordinates;
    }

    /**
     * Converts this to the data model the gui core understands.
     * @param aDistanceUnit the distance units to use
     * @return The new value
     */
    public List<Coordinate> toIdealCoordinates(Distance.Unit aDistanceUnit)
    {
        List<Coordinate> coordinates = new ArrayList<>();
        List<SplineSegment> segments = IdealSplineSerializer.deserializePath(mIdealSpline);

        for (SplineSegment splineSegment : segments)
        {
            coordinates.add(new Coordinate(new Position2dDistance(splineSegment.mAverageX, splineSegment.mAverageY, aDistanceUnit), splineSegment.mRobotHeading));
        }

        return coordinates;
    }

    public List<SplineSegment> getIdealSpline()
    {
        return IdealSplineSerializer.deserializePath(mIdealSpline);
    }

    public int getMeasurementIndex()
    {
        return (int) mMeasuredSpline[0];
    }

    public SplineSegment getMeasurementSegment()
    {
        return IdealSplineSerializer.deserializePathPoint(mMeasuredSpline, 1);
    }
}
