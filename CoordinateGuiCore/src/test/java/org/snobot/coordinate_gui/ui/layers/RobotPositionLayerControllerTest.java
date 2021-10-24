package org.snobot.coordinate_gui.ui.layers;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snobot.coordinate_gui.TestablePixelConverterFactory;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.PixelConverter;
import org.snobot.coordinate_gui.model.Position2dDistance;

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
        PixelConverter converter = TestablePixelConverterFactory.setupPortraitModeOriginAtCenter();

        RobotPositionLayerController controller = new TestableRobotPositionLayerController();
        controller.setRobotDimensions(converter, Distance.fromFeet(10), Distance.fromFeet(15));
        controller.initialize();

        controller.setPosition(converter, new Coordinate(new Position2dDistance(1.451, 6.512, Distance.Unit.Feet), -65.53));

        controller.mRobot.getX();
        Assertions.assertEquals(99.51, controller.mRobot.getX(), EPSILON);
        Assertions.assertEquals(129.88, controller.mRobot.getY(), EPSILON);
        Assertions.assertEquals(100, controller.mRobot.getWidth(), EPSILON);
        Assertions.assertEquals(150, controller.mRobot.getHeight(), EPSILON);
        Assertions.assertEquals(-65.53, controller.mRobot.getRotate(), EPSILON);

        Assertions.assertEquals(-65.53, controller.mOrientationArrow.getRotate(), EPSILON);
        Assertions.assertEquals(149.51, controller.mOrientationArrow.getTranslateX(), EPSILON);
        Assertions.assertEquals(204.88, controller.mOrientationArrow.getTranslateY(), EPSILON);
    }
}
