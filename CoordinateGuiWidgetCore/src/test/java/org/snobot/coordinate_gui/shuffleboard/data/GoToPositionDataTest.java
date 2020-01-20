package org.snobot.coordinate_gui.shuffleboard.data;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class GoToPositionDataTest
{
    private static final double EPSILON = 1e-4;

    @Test
    public void testEmptyConversion()
    {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(SmartDashboardNames.sCAMERA_POSITIONS, "");

        Point2D coordinate = new GoToPositionData(dataMap).toCoordinate();
        Assertions.assertNull(coordinate);
    }

    @Test
    public void testRealConversion()
    {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(SmartDashboardNames.sCAMERA_POSITIONS, "1.23,54.41");

        Point2D coordinate = new GoToPositionData(dataMap).toCoordinate();
        Assertions.assertEquals(1.23 / 12, coordinate.getX(), EPSILON);
        Assertions.assertEquals(54.41 / 12, coordinate.getY(), EPSILON);
    }
}
