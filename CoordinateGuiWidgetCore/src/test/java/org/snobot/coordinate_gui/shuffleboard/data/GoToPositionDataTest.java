package org.snobot.coordinate_gui.shuffleboard.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Position2dDistance;

import java.util.HashMap;
import java.util.Map;

public class GoToPositionDataTest
{
    @Test
    public void testEmptyConversion()
    {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(SmartDashboardNames.sCAMERA_POSITIONS, new double[]{});

        Position2dDistance coordinate = new GoToPositionData(dataMap).toCoordinate(Distance.Unit.FEET);
        Assertions.assertNull(coordinate);
    }

    @Test
    public void testRealConversion()
    {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(SmartDashboardNames.sCAMERA_POSITIONS, new double[]{1.23, 54.41});

        Position2dDistance coordinate = new GoToPositionData(dataMap).toCoordinate(Distance.Unit.FEET);
        Assertions.assertEquals(Distance.fromFeet(1.23), coordinate.mX);
        Assertions.assertEquals(Distance.fromFeet(54.41), coordinate.mY);
    }
}
