package org.snobot.coordinate_gui.shuffleboard.data;


import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.ui.layers.PurePursuitLayerController;
import org.snobot.nt.pure_pursuit_plotter.PurePursuitPointInfo;

import edu.wpi.first.shuffleboard.api.data.ComplexData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurePursuitData extends ComplexData<PurePursuitData>
{
    private static final double[] DEFAULT_VALUE = new double[]{};

    private final double[] mWaypoints;
    private final double[] mUpSampledPoints;
    private final double[] mSmoothedPoints;
    private final double[] mLookaheadPoints;
    private final double[] mCurrentPoint;

    public PurePursuitData()
    {
        this(DEFAULT_VALUE, DEFAULT_VALUE, DEFAULT_VALUE, DEFAULT_VALUE, DEFAULT_VALUE);
    }

    /**
     * Constructor.
     * 
     * @param aMap
     *            A data map, created from the widget
     */
    public PurePursuitData(Map<String, Object> aMap)
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
    public PurePursuitData(String aPrefix, Map<String, Object> aMap)
    {
        this((double[]) aMap.getOrDefault(aPrefix + SmartDashboardNames.sPURE_PURSUIT_WAYPOINTS, DEFAULT_VALUE),
                (double[]) aMap.getOrDefault(aPrefix + SmartDashboardNames.sPURE_PURSUIT_UP_SAMPLED, DEFAULT_VALUE),
                (double[]) aMap.getOrDefault(aPrefix + SmartDashboardNames.sPURE_PURSUIT_SMOOTHED, DEFAULT_VALUE),
                (double[]) aMap.getOrDefault(aPrefix + SmartDashboardNames.sPURE_PURSUIT_LOOKAHEAD, DEFAULT_VALUE),
                (double[]) aMap.getOrDefault(aPrefix + SmartDashboardNames.sPURE_PURSUIT_CURRENT_POINT, DEFAULT_VALUE));
    }

    /**
     * Constructor.
     * 
     * @param aWaypointString
     *            The original waypoints
     * @param aUpSampledString
     *            The waypoints upsampled into a path
     * @param aSmoothedString
     *            The up sampled points post smoothing
     * @param aLookahead
     *            The lookahead data
     */
    public PurePursuitData(double[] aWaypointString, double[] aUpSampledString, double[] aSmoothedString, double[] aLookahead, double[] aCurrentPoint)
    {
        mWaypoints = Arrays.copyOf(aWaypointString, aWaypointString.length);
        mUpSampledPoints = Arrays.copyOf(aUpSampledString, aUpSampledString.length);
        mSmoothedPoints = Arrays.copyOf(aSmoothedString, aSmoothedString.length);
        mLookaheadPoints = Arrays.copyOf(aLookahead, aLookahead.length);
        mCurrentPoint = Arrays.copyOf(aCurrentPoint, aCurrentPoint.length);
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
        map.put(aPrefix + SmartDashboardNames.sPURE_PURSUIT_WAYPOINTS, mWaypoints);
        map.put(aPrefix + SmartDashboardNames.sPURE_PURSUIT_UP_SAMPLED, mUpSampledPoints);
        map.put(aPrefix + SmartDashboardNames.sPURE_PURSUIT_SMOOTHED, mSmoothedPoints);
        map.put(aPrefix + SmartDashboardNames.sPURE_PURSUIT_LOOKAHEAD, mLookaheadPoints);
        map.put(aPrefix + SmartDashboardNames.sPURE_PURSUIT_CURRENT_POINT, mCurrentPoint);
        return map;
    }

    private List<Coordinate> convert(double[] aInput)
    {
        List<Coordinate> output = new ArrayList<>();

        for (int i = 0; i < aInput.length;)
        {
            double x = aInput[i++];
            double y = aInput[i++];
            Coordinate coordinate = new Coordinate(new Position2dDistance(x, y, Distance.Unit.FEET), 0);
            output.add(coordinate);
        }

        return output;
    }

    public List<Coordinate> getWaypoints()
    {
        return convert(mWaypoints);
    }

    public List<Coordinate> getUpSampledPoints()
    {
        return convert(mUpSampledPoints);
    }

    public List<Coordinate> getSmoothedPoints()
    {
        return convert(mSmoothedPoints);
    }

    /**
     * Gets the last point sent by the pure pursuit command.
     * 
     * @return The last point, or a point with index of -1 if the data is
     *         invalid
     */
    public PurePursuitPointInfo getCurrentPoint()
    {
        if (mCurrentPoint.length < 7) // NOPMD
        {
            return new PurePursuitPointInfo(-1);
        }

        PurePursuitPointInfo output = new PurePursuitPointInfo((int) mCurrentPoint[0]);

        output.mX = mCurrentPoint[1];
        output.mY = mCurrentPoint[2];
        output.mLeftVelocity = mCurrentPoint[3];
        output.mLeftGoalVelocity = mCurrentPoint[4];
        output.mRightVelocity = mCurrentPoint[5];
        output.mRightGoalVelocity = mCurrentPoint[6];

        return output;
    }

    /**
     * Converts this to the data model the gui core understands.
     * @param aDistanceUnit the distance units to use
     * @return The new value
     */
    public PurePursuitLayerController.PurePursuitLookaheadData toLookaheadData(Distance.Unit aDistanceUnit)
    {
        if (mLookaheadPoints.length != 0)
        {
            Position2dDistance robotPosition = new Position2dDistance(mLookaheadPoints[0], mLookaheadPoints[1], aDistanceUnit);
            Position2dDistance lookaheadPoint = new Position2dDistance(mLookaheadPoints[2], mLookaheadPoints[3], aDistanceUnit);
            PurePursuitLayerController.PurePursuitLookaheadData data = new PurePursuitLayerController.PurePursuitLookaheadData(robotPosition, lookaheadPoint);
            return data;
        }
        return null;
    }
}

