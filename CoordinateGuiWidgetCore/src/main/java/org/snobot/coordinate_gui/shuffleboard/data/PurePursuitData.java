package org.snobot.coordinate_gui.shuffleboard.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.ui.layers.PurePursuitLayerController;
import org.snobot.nt.pure_pursuit_plotter.PurePursuitPointInfo;

import edu.wpi.first.shuffleboard.api.data.ComplexData;

public class PurePursuitData extends ComplexData<PurePursuitData>
{
    private final String mWaypoints;
    private final String mUpSampledPoints;
    private final String mSmoothedPoints;
    private final String mLookaheadPoints;
    private final String mCurrentPoint;

    public PurePursuitData()
    {
        this("", "", "", "", "");
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
        this((String) aMap.getOrDefault(aPrefix + SmartDashboardNames.sPURE_PURSUIT_WAYPOINTS, ""),
                (String) aMap.getOrDefault(aPrefix + SmartDashboardNames.sPURE_PURSUIT_UP_SAMPLED, ""),
                (String) aMap.getOrDefault(aPrefix + SmartDashboardNames.sPURE_PURSUIT_SMOOTHED, ""),
                (String) aMap.getOrDefault(aPrefix + SmartDashboardNames.sPURE_PURSUIT_LOOKAHEAD, ""),
                (String) aMap.getOrDefault(aPrefix + SmartDashboardNames.sPURE_PURSUIT_CURRENT_POINT, ""));
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
    public PurePursuitData(String aWaypointString, String aUpSampledString, String aSmoothedString, String aLookahead, String aCurrentPoint)
    {
        mWaypoints = aWaypointString;
        mUpSampledPoints = aUpSampledString;
        mSmoothedPoints = aSmoothedString;
        mLookaheadPoints = aLookahead;
        mCurrentPoint = aCurrentPoint;
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

    private List<Coordinate> convert(String aInput)
    {
        List<Coordinate> output = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(aInput, ",");
        while (tokenizer.countTokens() >= 2)
        {
            double x = Double.parseDouble(tokenizer.nextToken());
            double y = Double.parseDouble(tokenizer.nextToken());
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

    public String getLookaheadString()
    {
        return mLookaheadPoints;
    }

    /**
     * Gets the last point sent by the pure pursuit command.
     * 
     * @return The last point, or a point with index of -1 if the data is
     *         invalid
     */
    public PurePursuitPointInfo getCurrentPoint()
    {
        StringTokenizer tokenizer = new StringTokenizer(mCurrentPoint, ",");

        if (tokenizer.countTokens() < 7) // NOPMD
        {
            return new PurePursuitPointInfo(-1);
        }

        PurePursuitPointInfo output = new PurePursuitPointInfo(Integer.parseInt(tokenizer.nextToken()));

        output.mX = Double.parseDouble(tokenizer.nextToken());
        output.mY = Double.parseDouble(tokenizer.nextToken());
        output.mLeftVelocity = Double.parseDouble(tokenizer.nextToken());
        output.mLeftGoalVelocity = Double.parseDouble(tokenizer.nextToken());
        output.mRightVelocity = Double.parseDouble(tokenizer.nextToken());
        output.mRightGoalVelocity = Double.parseDouble(tokenizer.nextToken());

        return output;
    }

    /**
     * Converts this to the data model the gui core understands.
     * @param aDistanceUnit the distance units to use
     * @return The new value
     */
    public PurePursuitLayerController.PurePursuitLookaheadData toLookaheadData(Distance.Unit aDistanceUnit)
    {
        String lookaheadString = getLookaheadString();
        if (!lookaheadString.isEmpty())
        {
            String[] lookahead = getLookaheadString().split(",");
            Position2dDistance robotPosition = new Position2dDistance(Double.parseDouble(lookahead[0]), Double.parseDouble(lookahead[1]), aDistanceUnit);
            Position2dDistance lookaheadPoint = new Position2dDistance(Double.parseDouble(lookahead[2]), Double.parseDouble(lookahead[3]), aDistanceUnit);
            PurePursuitLayerController.PurePursuitLookaheadData data = new PurePursuitLayerController.PurePursuitLookaheadData(robotPosition, lookaheadPoint);
            return data;
        }
        return null;
    }
}

