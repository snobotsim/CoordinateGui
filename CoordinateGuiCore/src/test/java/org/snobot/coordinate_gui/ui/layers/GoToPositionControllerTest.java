package org.snobot.coordinate_gui.ui.layers;

import javafx.scene.Group;
import javafx.scene.shape.Polygon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snobot.coordinate_gui.TestablePixelConverterFactory;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.PixelConverter;
import org.snobot.coordinate_gui.model.Position2dDistance;

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
        PixelConverter converter = TestablePixelConverterFactory.setupPortraitModeOriginAtCenter();

        GoToPositionController controller = new TestableGoToPositionController();
        controller.initialize();

        controller.setGoToXYPosition(converter, null);
        Assertions.assertEquals(0, controller.mPosition.getChildren().size());
    }

    @Test
    public void testValidPoint()
    {
        PixelConverter converter = TestablePixelConverterFactory.setupPortraitModeOriginAtCenter();

        GoToPositionController controller = new TestableGoToPositionController();
        controller.initialize();

        controller.setGoToXYPosition(converter, new Position2dDistance(5.42, -9.42, Distance.Unit.FEET));
        Assertions.assertEquals(1, controller.mPosition.getChildren().size());

        Polygon icon = (Polygon) controller.mPosition.getChildren().get(0);

        Assertions.assertEquals(189.2, icon.getTranslateX(), EPSILON);
        Assertions.assertEquals(364.2, icon.getTranslateY(), EPSILON);
    }
}
