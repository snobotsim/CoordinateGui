package org.snobot.coordinate_gui.shuffleboard.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.Distance;

import java.util.HashMap;
import java.util.Map;

public class CoordinateDataTest
{
    private static final double EPSILON = 1e-4;

    @Test
    public void testConversion()
    {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(SmartDashboardNames.sROBOT_POSITION_CTR, 1.0);
        dataMap.put(SmartDashboardNames.sORIENTATION, 98.73);
        dataMap.put(SmartDashboardNames.sX_POSITION, 2.78);
        dataMap.put(SmartDashboardNames.sY_POSITION, -12.95);

        Coordinate coordinate = new CoordinateData(dataMap).toCoord(Distance.Unit.FEET);
        Assertions.assertEquals(Distance.fromFeet(2.78), coordinate.mPosition.mX);
        Assertions.assertEquals(Distance.fromFeet(-12.95), coordinate.mPosition.mY);
        Assertions.assertEquals(98.73, coordinate.mAngle, EPSILON);
    }
}
