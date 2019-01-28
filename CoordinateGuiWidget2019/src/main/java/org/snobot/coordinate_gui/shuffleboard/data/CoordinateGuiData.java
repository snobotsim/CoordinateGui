package org.snobot.coordinate_gui.shuffleboard.data;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.shuffleboard.api.data.ComplexData;

public class CoordinateGuiData extends ComplexData<CoordinateGuiData>
{
    private final CoordinateData mCoordinateData;
    private final VisionData mVisionData;
    private final TrajectoryData mTrajectoryData;

    /**
     * Constructor.
     */
    public CoordinateGuiData()
    {
        mCoordinateData = new CoordinateData();
        mVisionData = new VisionData();
        mTrajectoryData = new TrajectoryData();
    }

    /**
     * Constructor. Initializes the data from the map.
     * 
     * @param aMap
     *            The map from NetworkTables
     */
    public CoordinateGuiData(Map<String, Object> aMap)
    {
        mCoordinateData = new CoordinateData(CoordinateDataType.NAME + "/", aMap);
        mVisionData = new VisionData(VisionDataType.NAME + "/", aMap);
        mTrajectoryData = new TrajectoryData(TrajectoryDataType.NAME + "/", aMap);

    }

    @Override
    public Map<String, Object> asMap()
    {
        Map<String, Object> map = new HashMap<>();
        map.putAll(mCoordinateData.asMap(CoordinateDataType.NAME + "/"));
        map.putAll(mVisionData.asMap(VisionDataType.NAME + "/"));
        map.putAll(mTrajectoryData.asMap(TrajectoryDataType.NAME + "/"));
        return map;
    }

    public CoordinateData getRobotPosition()
    {
        return mCoordinateData;
    }

    public VisionData getVisionData()
    {
        return mVisionData;
    }

    public TrajectoryData getTrajectoryData()
    {
        return mTrajectoryData;
    }

}
