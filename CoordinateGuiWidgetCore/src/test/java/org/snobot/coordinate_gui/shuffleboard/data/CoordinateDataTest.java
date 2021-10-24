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
        dataMap.put(SmartDashboardNames.sROBOT_POSITION, new double[]{2.78, -12.95, 98.73, 1});

        Coordinate coordinate = new CoordinateData(dataMap).toCoord(Distance.Unit.Feet);
        Assertions.assertEquals(Distance.fromFeet(2.78), coordinate.mPosition.mX);
        Assertions.assertEquals(Distance.fromFeet(-12.95), coordinate.mPosition.mY);
        Assertions.assertEquals(98.73, coordinate.mAngle, EPSILON);
    }
}
