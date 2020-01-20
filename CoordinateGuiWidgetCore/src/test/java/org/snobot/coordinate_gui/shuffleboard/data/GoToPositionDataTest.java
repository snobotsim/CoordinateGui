package org.snobot.coordinate_gui.shuffleboard.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snobot.coordinate_gui.model.Position2dDistance;

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

        Position2dDistance coordinate = new GoToPositionData(dataMap).toCoordinate();
        Assertions.assertNull(coordinate);
    }

    @Test
    public void testRealConversion()
    {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(SmartDashboardNames.sCAMERA_POSITIONS, "1.23,54.41");

        Position2dDistance coordinate = new GoToPositionData(dataMap).toCoordinate();
        Assertions.assertEquals(1.23 / 12, coordinate.mX, EPSILON);
        Assertions.assertEquals(54.41 / 12, coordinate.mY, EPSILON);
    }
}
