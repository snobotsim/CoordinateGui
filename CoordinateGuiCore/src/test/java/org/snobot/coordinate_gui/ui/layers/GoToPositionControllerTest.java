package org.snobot.coordinate_gui.ui.layers;

import javafx.scene.Group;
import javafx.scene.shape.Polygon;
import org.junit.Assert;
import org.junit.Test;
import org.snobot.coordinate_gui.TestablePixelConverterFactory;
import org.snobot.coordinate_gui.model.PixelConverter;

public class GoToPositionControllerTest
{
    private static final double EPSILON = 1e-4;

    private static class TestableGoToPositionController extends GoToPositionController
    {
        public TestableGoToPositionController()
        {
            mPosition = new Group();
        }
    }

    @Test
    public void testNullPoint()
    {
        PixelConverter converter = TestablePixelConverterFactory.setupPortraitMode();

        GoToPositionController controller = new TestableGoToPositionController();
        controller.initialize();

        controller.setGoToXYPosition(converter, null, null);
        Assert.assertEquals(0, controller.mPosition.getChildren().size());
    }

    @Test
    public void testValidPoint()
    {
        PixelConverter converter = TestablePixelConverterFactory.setupPortraitMode();

        GoToPositionController controller = new TestableGoToPositionController();
        controller.initialize();

        controller.setGoToXYPosition(converter, 5.42, -9.42);
        Assert.assertEquals(1, controller.mPosition.getChildren().size());

        Polygon icon = (Polygon) controller.mPosition.getChildren().get(0);

        Assert.assertEquals(189.2, icon.getTranslateX(), EPSILON);
        Assert.assertEquals(364.2, icon.getTranslateY(), EPSILON);
    }
}
