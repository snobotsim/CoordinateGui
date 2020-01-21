package org.snobot.coordinate_gui.shuffleboard.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.ui.layers.PurePursuitLayerController;
import org.snobot.nt.pure_pursuit_plotter.PurePursuitPointInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurePursuitDataTest
{
    private static final double EPSILON = 1e-4;

    @Test
    public void testInvalidLookahead()
    {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(SmartDashboardNames.sPURE_PURSUIT_LOOKAHEAD, "");

        PurePursuitData data = new PurePursuitData(dataMap);
        PurePursuitLayerController.PurePursuitLookaheadData lookaheadData = data.toLookaheadData();

        Assertions.assertNull(lookaheadData);

    }

    @Test
    public void testGoodLookahead()
    {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(SmartDashboardNames.sPURE_PURSUIT_LOOKAHEAD, "12.47,-48.41,78.24,-9");

        PurePursuitData data = new PurePursuitData(dataMap);
        PurePursuitLayerController.PurePursuitLookaheadData lookaheadData = data.toLookaheadData();

        Assertions.assertEquals(12.47, lookaheadData.mRobot.mX, EPSILON);
        Assertions.assertEquals(-48.41, lookaheadData.mRobot.mY, EPSILON);
        Assertions.assertEquals(78.24, lookaheadData.mLookahead.mX, EPSILON);
        Assertions.assertEquals(-9, lookaheadData.mLookahead.mY, EPSILON);
    }

    @Test
    public void testInvalidCurrentPoint()
    {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(SmartDashboardNames.sPURE_PURSUIT_CURRENT_POINT, "12.47");

        PurePursuitData data = new PurePursuitData(dataMap);
        PurePursuitPointInfo info = data.getCurrentPoint();
        Assertions.assertEquals(-1, info.mIndex);
    }

    @Test
    public void testValidCurrentPoint()
    {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(SmartDashboardNames.sPURE_PURSUIT_CURRENT_POINT, "12,-48.41,78.24,-9,25,8.14,-84.1");

        PurePursuitData data = new PurePursuitData(dataMap);
        PurePursuitPointInfo info = data.getCurrentPoint();
        Assertions.assertEquals(12, info.mIndex);

        Assertions.assertEquals(-48.41, info.mX, EPSILON);
        Assertions.assertEquals(78.24, info.mY, EPSILON);
        Assertions.assertEquals(-9, info.mLeftVelocity, EPSILON);
        Assertions.assertEquals(25, info.mLeftGoalVelocity, EPSILON);
        Assertions.assertEquals(8.14, info.mRightVelocity, EPSILON);
        Assertions.assertEquals(-84.1, info.mRightGoalVelocity, EPSILON);
    }

    @Test
    public void testValidWaypoints()
    {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(SmartDashboardNames.sPURE_PURSUIT_WAYPOINTS, "0,0,0,74,92.45,-19.4,-84.24,-72.4");

        PurePursuitData data = new PurePursuitData(dataMap);
        List<Coordinate> waypoints = data.getWaypoints();
        Assertions.assertEquals(4, waypoints.size());

        Coordinate ideal;

        ideal = waypoints.get(0);
        Assertions.assertEquals(0.0, ideal.mPosition.mX, EPSILON);
        Assertions.assertEquals(0.0, ideal.mPosition.mY, EPSILON);
        Assertions.assertEquals(0.0, ideal.mAngle, EPSILON);

        ideal = waypoints.get(1);
        Assertions.assertEquals(0.0, ideal.mPosition.mX, EPSILON);
        Assertions.assertEquals(74, ideal.mPosition.mY, EPSILON);
        Assertions.assertEquals(0.0, ideal.mAngle, EPSILON);

        ideal = waypoints.get(2);
        Assertions.assertEquals(92.45, ideal.mPosition.mX, EPSILON);
        Assertions.assertEquals(-19.4, ideal.mPosition.mY, EPSILON);
        Assertions.assertEquals(0.0, ideal.mAngle, EPSILON);

        ideal = waypoints.get(3);
        Assertions.assertEquals(-84.24, ideal.mPosition.mX, EPSILON);
        Assertions.assertEquals(-72.4, ideal.mPosition.mY, EPSILON);
        Assertions.assertEquals(0.0, ideal.mAngle, EPSILON);
    }

    @Test
    public void testValidUpsampled()
    {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(SmartDashboardNames.sPURE_PURSUIT_UP_SAMPLED, "74,92.45");

        PurePursuitData data = new PurePursuitData(dataMap);
        List<Coordinate> waypoints = data.getUpSampledPoints();
        Assertions.assertEquals(1, waypoints.size());

        Coordinate ideal;

        ideal = waypoints.get(0);
        Assertions.assertEquals(74, ideal.mPosition.mX, EPSILON);
        Assertions.assertEquals(92.45, ideal.mPosition.mY, EPSILON);
        Assertions.assertEquals(0.0, ideal.mAngle, EPSILON);
    }

    @Test
    public void testValidSmoothed()
    {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(SmartDashboardNames.sPURE_PURSUIT_SMOOTHED, "-19.4,-84.24");

        PurePursuitData data = new PurePursuitData(dataMap);
        List<Coordinate> waypoints = data.getSmoothedPoints();
        Assertions.assertEquals(1, waypoints.size());

        Coordinate ideal;

        ideal = waypoints.get(0);
        Assertions.assertEquals(-19.4, ideal.mPosition.mX, EPSILON);
        Assertions.assertEquals(-84.24, ideal.mPosition.mY, EPSILON);
        Assertions.assertEquals(0.0, ideal.mAngle, EPSILON);
    }
}
