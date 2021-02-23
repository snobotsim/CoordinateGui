package org.snobot.coordinate_gui.shuffleboard.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.Distance;

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
        dataMap.put(SmartDashboardNames.sSPLINE_IDEAL_POINTS, new double[]{12.34, 6.64, 12.86, 5.89, 79.98, 12.13, 11.89, 6.34, 23.64, 31.86, 77.89, 98.98, 7.13, 2.89});
        dataMap.put(SmartDashboardNames.sSPLINE_WAYPOINTS, new double[]{8.312, 913.12, 19.48, 175.4, 85.2, 11.01, 89.52, 87, 24});

        List<Coordinate> idealCoordinates = new TrajectoryData(dataMap).toIdealCoordinates(Distance.Unit.FEET);
        Assertions.assertEquals(2, idealCoordinates.size());

        {
            Coordinate ideal;

            ideal = idealCoordinates.get(0);
            Assertions.assertEquals(Distance.fromFeet(12.13), ideal.mPosition.mX);
            Assertions.assertEquals(Distance.fromFeet(11.89), ideal.mPosition.mY);
            Assertions.assertEquals(79.98, ideal.mAngle, EPSILON);

            ideal = idealCoordinates.get(1);
            Assertions.assertEquals(Distance.fromFeet(7.13), ideal.mPosition.mX);
            Assertions.assertEquals(Distance.fromFeet(2.89), ideal.mPosition.mY);
            Assertions.assertEquals(98.98, ideal.mAngle, EPSILON);
        }

        {
            List<Coordinate> waypoints = new TrajectoryData(dataMap).toWaypoints(Distance.Unit.FEET);

            Assertions.assertEquals(3, waypoints.size());

            Coordinate waypoint;

            waypoint = waypoints.get(0);
            Assertions.assertEquals(Distance.fromFeet(8.312), waypoint.mPosition.mX);
            Assertions.assertEquals(Distance.fromFeet(913.12), waypoint.mPosition.mY);
            Assertions.assertEquals(1116.1217849148436, waypoint.mAngle, EPSILON);

            waypoint = waypoints.get(1);
            Assertions.assertEquals(Distance.fromFeet(175.4), waypoint.mPosition.mX);
            Assertions.assertEquals(Distance.fromFeet(85.2), waypoint.mPosition.mY);
            Assertions.assertEquals(630.8265324390363, waypoint.mAngle, EPSILON);

            waypoint = waypoints.get(2);
            Assertions.assertEquals(Distance.fromFeet(89.52), waypoint.mPosition.mX);
            Assertions.assertEquals(Distance.fromFeet(87.0), waypoint.mPosition.mY);
            Assertions.assertEquals(1375.0987083139757, waypoint.mAngle, EPSILON);

//        Assertions.assertEquals(2.78, coordinate.mX, EPSILON);
//        Assertions.assertEquals(-12.95, coordinate.mY, EPSILON);
//        Assertions.assertEquals(98.73, coordinate.mAngle, EPSILON);
        }
    }
}
