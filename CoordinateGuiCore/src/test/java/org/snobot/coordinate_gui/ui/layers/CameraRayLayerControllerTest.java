package org.snobot.coordinate_gui.ui.layers;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snobot.coordinate_gui.TestablePixelConverterFactory;
import org.snobot.coordinate_gui.model.PixelConverter;
import org.snobot.coordinate_gui.model.Position2dDistance;

import java.util.ArrayList;
import java.util.List;

public class CameraRayLayerControllerTest
{
    private static final double EPSILON = 1e-4;

    public static class TestableCameraRayLayerController extends CameraRayLayerController
    {
        public TestableCameraRayLayerController()
        {
            mRays = new Group();
        }
    }

    @Test
    public void testRayConstruction()
    {
        TestableCameraRayLayerController rayController = new TestableCameraRayLayerController();

        PixelConverter converter = TestablePixelConverterFactory.setupPortraitModeOriginAtCenter();
        List<CameraRayLayerController.Ray> rays = new ArrayList<>();

        rays.add(new CameraRayLayerController.Ray(new Position2dDistance(0, -2), new Position2dDistance(5, 5)));
        rays.add(new CameraRayLayerController.Ray(new Position2dDistance(13, -26), new Position2dDistance(0, 0)));

        rayController.setRays(converter, rays);
        Assertions.assertEquals(2, rayController.mRays.getChildren().size());

        Line line;

        line = (Line) rayController.mRays.getChildren().get(0);
        Assertions.assertEquals(135, line.getStartX(), EPSILON);
        Assertions.assertEquals(290, line.getStartY(), EPSILON);
        Assertions.assertEquals(185, line.getEndX(), EPSILON);
        Assertions.assertEquals(220, line.getEndY(), EPSILON);

        line = (Line) rayController.mRays.getChildren().get(1);
        Assertions.assertEquals(265, line.getStartX(), EPSILON);
        Assertions.assertEquals(530, line.getStartY(), EPSILON);
        Assertions.assertEquals(135, line.getEndX(), EPSILON);
        Assertions.assertEquals(270, line.getEndY(), EPSILON);
    }
}
