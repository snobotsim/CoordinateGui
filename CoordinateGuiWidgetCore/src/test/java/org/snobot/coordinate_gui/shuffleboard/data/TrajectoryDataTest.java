package org.snobot.coordinate_gui.shuffleboard.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snobot.coordinate_gui.model.Coordinate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrajectoryDataTest
{
    private static final double EPSILON = 1e-4;

    @Test
    public void testConversion()
    {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(SmartDashboardNames.sSPLINE_IDEAL_POINTS, "12.34,6.64,12.86,5.89,79.98,12.13,11.89,6.34,23.64,31.86,77.89,98.98,7.13,2.89");
        dataMap.put(SmartDashboardNames.sSPLINE_WAYPOINTS, "8.312,913.12,19.48,175.4,85.2,11.01,89.52,87,24");

        List<Coordinate> idealCoordinates = new TrajectoryData(dataMap).toIdealCoordinates();
        Assertions.assertEquals(2, idealCoordinates.size());

        {
            Coordinate ideal;

            ideal = idealCoordinates.get(0);
            Assertions.assertEquals(12.13, ideal.mPosition.mX * 12, EPSILON);
            Assertions.assertEquals(11.89, ideal.mPosition.mY * 12, EPSILON);
            Assertions.assertEquals(79.98, ideal.mAngle, EPSILON);

            ideal = idealCoordinates.get(1);
            Assertions.assertEquals(7.13, ideal.mPosition.mX * 12, EPSILON);
            Assertions.assertEquals(2.89, ideal.mPosition.mY * 12, EPSILON);
            Assertions.assertEquals(98.98, ideal.mAngle, EPSILON);
        }

        {
            List<Coordinate> waypoints = new TrajectoryData(dataMap).toWaypoints();

            Assertions.assertEquals(3, waypoints.size());

            Coordinate waypoint;

            waypoint = waypoints.get(0);
            Assertions.assertEquals(8.312, waypoint.mPosition.mX * 12, EPSILON);
            Assertions.assertEquals(913.12, waypoint.mPosition.mY * 12, EPSILON);
            Assertions.assertEquals(1116.1217849148436, waypoint.mAngle, EPSILON);

            waypoint = waypoints.get(1);
            Assertions.assertEquals(175.4, waypoint.mPosition.mX * 12, EPSILON);
            Assertions.assertEquals(85.2, waypoint.mPosition.mY * 12, EPSILON);
            Assertions.assertEquals(630.8265324390363, waypoint.mAngle, EPSILON);

            waypoint = waypoints.get(2);
            Assertions.assertEquals(89.52, waypoint.mPosition.mX * 12, EPSILON);
            Assertions.assertEquals(87.0, waypoint.mPosition.mY * 12, EPSILON);
            Assertions.assertEquals(1375.0987083139757, waypoint.mAngle, EPSILON);

//        Assertions.assertEquals(2.78, coordinate.mX, EPSILON);
//        Assertions.assertEquals(-12.95, coordinate.mY, EPSILON);
//        Assertions.assertEquals(98.73, coordinate.mAngle, EPSILON);
        }
    }
}
