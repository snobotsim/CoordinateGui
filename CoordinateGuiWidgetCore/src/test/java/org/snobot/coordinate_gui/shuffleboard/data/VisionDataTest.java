package org.snobot.coordinate_gui.shuffleboard.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snobot.coordinate_gui.ui.layers.CameraRayLayerController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisionDataTest
{
    private static final double EPSILON = 1e-4;

    @Test
    public void testConversion()
    {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(SmartDashboardNames.sCAMERA_POSITIONS, "0,1,2,3,1.23,125.6,64.35,90.52,18.42,85.52,71.24,92.5,1.23,125.6,64.35,90.52");

        List<CameraRayLayerController.Ray> rays = new VisionData(dataMap).toRays();

        Assertions.assertEquals(4, rays.size());

        CameraRayLayerController.Ray ray;

        ray = rays.get(0);
        Assertions.assertEquals(0.0, ray.mStart.mX * 12, EPSILON);
        Assertions.assertEquals(1.0, ray.mStart.mY * 12, EPSILON);
        Assertions.assertEquals(2.0, ray.mEnd.mX * 12, EPSILON);
        Assertions.assertEquals(3.0, ray.mEnd.mY * 12, EPSILON);

        ray = rays.get(1);
        Assertions.assertEquals(1.23, ray.mStart.mX * 12, EPSILON);
        Assertions.assertEquals(125.6, ray.mStart.mY * 12, EPSILON);
        Assertions.assertEquals(64.35, ray.mEnd.mX * 12, EPSILON);
        Assertions.assertEquals(90.52, ray.mEnd.mY * 12, EPSILON);

        ray = rays.get(2);
        Assertions.assertEquals(18.42, ray.mStart.mX * 12, EPSILON);
        Assertions.assertEquals(85.52, ray.mStart.mY * 12, EPSILON);
        Assertions.assertEquals(71.24, ray.mEnd.mX * 12, EPSILON);
        Assertions.assertEquals(92.5, ray.mEnd.mY * 12, EPSILON);

        ray = rays.get(3);
        Assertions.assertEquals(1.23, ray.mStart.mX * 12, EPSILON);
        Assertions.assertEquals(125.6, ray.mStart.mY * 12, EPSILON);
        Assertions.assertEquals(64.35, ray.mEnd.mX * 12, EPSILON);
        Assertions.assertEquals(90.52, ray.mEnd.mY * 12, EPSILON);

//        Assertions.assertEquals(2.78, coordinate.mX, EPSILON);
//        Assertions.assertEquals(-12.95, coordinate.mY, EPSILON);
//        Assertions.assertEquals(98.73, coordinate.mAngle, EPSILON);
    }
}
