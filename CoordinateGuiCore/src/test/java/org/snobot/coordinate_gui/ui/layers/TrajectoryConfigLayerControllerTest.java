package org.snobot.coordinate_gui.ui.layers;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snobot.coordinate_gui.TestablePixelConverterFactory;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.PixelConverter;

public class TrajectoryConfigLayerControllerTest
{
    private static final double EPSILON = 1e-4;

    private static class TestableTrajectoryConfigLayerController extends TrajectoryConfigLayerController
    {
        public TestableTrajectoryConfigLayerController()
        {
            mPathPoints = new Group();
        }
    }

    @Test
    public void testController()
    {
        PixelConverter converter = TestablePixelConverterFactory.setupPortraitMode();

        TestableTrajectoryConfigLayerController controller = new TestableTrajectoryConfigLayerController();

        controller.addPoint(converter, new Coordinate(0, 0, 0));
        controller.addPoint(converter, new Coordinate(-9.52, 18.54, 13));
        controller.addPoint(converter, new Coordinate(-10.12, 21.74, 13));

        Assertions.assertEquals(3, controller.getWaypoints().size());
        Assertions.assertEquals(6, controller.mPathPoints.getChildren().size());

        Line line;
        Polygon polygon;

        line = (Line) controller.mPathPoints.getChildren().get(0);
        polygon = (Polygon) controller.mPathPoints.getChildren().get(1);
        Assertions.assertEquals(135, line.getStartX(), EPSILON);
        Assertions.assertEquals(270, line.getStartY(), EPSILON);
        Assertions.assertEquals(135, line.getEndX(), EPSILON);
        Assertions.assertEquals(170, line.getEndY(), EPSILON);
        Assertions.assertEquals(135, polygon.getTranslateX(), EPSILON);
        Assertions.assertEquals(270, polygon.getTranslateY(), EPSILON);
        Assertions.assertEquals(-90, polygon.getRotate(), EPSILON);

        line = (Line) controller.mPathPoints.getChildren().get(2);
        polygon = (Polygon) controller.mPathPoints.getChildren().get(3);
        Assertions.assertEquals(39.8, line.getStartX(), EPSILON);
        Assertions.assertEquals(84.6, line.getStartY(), EPSILON);
        Assertions.assertEquals(62.2951, line.getEndX(), EPSILON);
        Assertions.assertEquals(-12.837, line.getEndY(), EPSILON);
        Assertions.assertEquals(39.8, polygon.getTranslateX(), EPSILON);
        Assertions.assertEquals(84.6, polygon.getTranslateY(), EPSILON);
        Assertions.assertEquals(-77, polygon.getRotate(), EPSILON);

        line = (Line) controller.mPathPoints.getChildren().get(4);
        polygon = (Polygon) controller.mPathPoints.getChildren().get(5);
        Assertions.assertEquals(33.8, line.getStartX(), EPSILON);
        Assertions.assertEquals(52.6, line.getStartY(), EPSILON);
        Assertions.assertEquals(56.2951, line.getEndX(), EPSILON);
        Assertions.assertEquals(-44.837, line.getEndY(), EPSILON);
        Assertions.assertEquals(33.8, polygon.getTranslateX(), EPSILON);
        Assertions.assertEquals(52.6, polygon.getTranslateY(), EPSILON);
        Assertions.assertEquals(-77.0, polygon.getRotate(), EPSILON);
    }
}
