package org.snobot.coordinate_gui.ui.layers;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import org.junit.Assert;
import org.junit.Test;
import org.snobot.coordinate_gui.TestablePixelConverterFactory;
import org.snobot.coordinate_gui.model.PixelConverter;

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

        PixelConverter converter = TestablePixelConverterFactory.setupPortraitMode();
        List<CameraRayLayerController.Ray> rays = new ArrayList<>();

        rays.add(new CameraRayLayerController.Ray(0, -2, 5, 5));
        rays.add(new CameraRayLayerController.Ray(13, -26, 0, 0));

        rayController.setRays(converter, rays);
        Assert.assertEquals(2, rayController.mRays.getChildren().size());

        Line line;

        line = (Line) rayController.mRays.getChildren().get(0);
        Assert.assertEquals(135, line.getStartX(), EPSILON);
        Assert.assertEquals(290, line.getStartY(), EPSILON);
        Assert.assertEquals(185, line.getEndX(), EPSILON);
        Assert.assertEquals(220, line.getEndY(), EPSILON);

        line = (Line) rayController.mRays.getChildren().get(1);
        Assert.assertEquals(265, line.getStartX(), EPSILON);
        Assert.assertEquals(530, line.getStartY(), EPSILON);
        Assert.assertEquals(135, line.getEndX(), EPSILON);
        Assert.assertEquals(270, line.getEndY(), EPSILON);
    }
}
