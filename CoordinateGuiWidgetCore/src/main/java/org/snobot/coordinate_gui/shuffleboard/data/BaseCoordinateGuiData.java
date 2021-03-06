package org.snobot.coordinate_gui.shuffleboard.data;

import edu.wpi.first.shuffleboard.api.data.ComplexData;

import java.util.HashMap;
import java.util.Map;

public class BaseCoordinateGuiData extends ComplexData<BaseCoordinateGuiData>
{
    private final CoordinateData mCoordinateData;
    private final GoToPositionData mGoToPositionData;
    private final VisionData mVisionData;
    private final TrajectoryData mTrajectoryData;
    private final PurePursuitData mPurePursuitData;
    private final RamseteControllerData mRamseteData;

    /**
     * Constructor.
     */
    public BaseCoordinateGuiData()
    {
        mCoordinateData = new CoordinateData();
        mVisionData = new VisionData();
        mTrajectoryData = new TrajectoryData();
        mGoToPositionData = new GoToPositionData();
        mPurePursuitData = new PurePursuitData();
        mRamseteData = new RamseteControllerData();
    }

    /**
     * Constructor. Initializes the data from the map.
     *
     * @param aMap
     *            The map from NetworkTables
     */
    public BaseCoordinateGuiData(Map<String, Object> aMap)
    {
        mCoordinateData = new CoordinateData(CoordinateDataType.NAME + "/", aMap);
        mVisionData = new VisionData(VisionDataType.NAME + "/", aMap);
        mTrajectoryData = new TrajectoryData(TrajectoryDataType.NAME + "/", aMap);
        mGoToPositionData = new GoToPositionData(GoToPositionDataType.NAME + "/", aMap);
        mPurePursuitData = new PurePursuitData(PurePursuitDataType.NAME + "/", aMap);
        mRamseteData = new RamseteControllerData(RamseteControllerDataType.NAME + "/", aMap);
    }

    @Override
    public Map<String, Object> asMap()
    {
        Map<String, Object> map = new HashMap<>();
        map.putAll(mCoordinateData.asMap(CoordinateDataType.NAME + "/"));
        map.putAll(mVisionData.asMap(VisionDataType.NAME + "/"));
        map.putAll(mTrajectoryData.asMap(TrajectoryDataType.NAME + "/"));
        map.putAll(mGoToPositionData.asMap(GoToPositionDataType.NAME + "/"));
        map.putAll(mPurePursuitData.asMap(PurePursuitDataType.NAME + "/"));
        map.putAll(mRamseteData.asMap(RamseteControllerDataType.NAME + "/"));
        return map;
    }

    public CoordinateData getRobotPosition()
    {
        return mCoordinateData;
    }

    public GoToPositionData getGoToPositionData()
    {
        return mGoToPositionData;
    }

    public VisionData getVisionData()
    {
        return mVisionData;
    }

    public TrajectoryData getTrajectoryData()
    {
        return mTrajectoryData;
    }

    public PurePursuitData getPurePursuitData()
    {
        return mPurePursuitData;
    }

    public RamseteControllerData getRamseteData()
    {
        return mRamseteData;
    }

    @Override
    public String toString()
    {
        return "BaseCoordinateGuiData{"
            + "mCoordinateData=" + mCoordinateData
            + ", mGoToPositionData=" + mGoToPositionData
            + ", mVisionData=" + mVisionData
            + ", mTrajectoryData=" + mTrajectoryData
            + ", mPurePursuitData=" + mPurePursuitData
            + ", mRamseteData=" + mRamseteData
            + '}';
    }
}
