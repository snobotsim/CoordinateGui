package org.snobot.coordinate_gui.ui.layers;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import org.junit.Assert;
import org.junit.Test;
import org.snobot.coordinate_gui.TestablePixelConverterFactory;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.PixelConverter;

public class RobotPositionLayerControllerTest
{
    private static final double EPSILON = 1e-4;

    private static class TestableRobotPositionLayerController extends RobotPositionLayerController
    {
        public TestableRobotPositionLayerController()
        {
            mRobot = new Rectangle();
            mOrientationArrow = new Polygon();
        }
    }

    @Test
    public void testController()
    {
        PixelConverter converter = TestablePixelConverterFactory.setupPortraitMode();

        RobotPositionLayerController controller = new TestableRobotPositionLayerController();
        controller.setRobotDimensions(converter, 10, 15);
        controller.initialize();

        controller.setPosition(converter, new Coordinate(1.451, 6.512, -65.53));

        controller.mRobot.getX();
        Assert.assertEquals(99.51, controller.mRobot.getX(), EPSILON);
        Assert.assertEquals(129.88, controller.mRobot.getY(), EPSILON);
        Assert.assertEquals(100, controller.mRobot.getWidth(), EPSILON);
        Assert.assertEquals(150, controller.mRobot.getHeight(), EPSILON);
        Assert.assertEquals(-65.53, controller.mRobot.getRotate(), EPSILON);

        Assert.assertEquals(-65.53, controller.mOrientationArrow.getRotate(), EPSILON);
        Assert.assertEquals(149.51, controller.mOrientationArrow.getTranslateX(), EPSILON);
        Assert.assertEquals(204.88, controller.mOrientationArrow.getTranslateY(), EPSILON);
    }
}
