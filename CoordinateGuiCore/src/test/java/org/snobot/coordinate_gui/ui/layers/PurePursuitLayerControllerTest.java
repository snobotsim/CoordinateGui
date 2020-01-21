package org.snobot.coordinate_gui.ui.layers;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snobot.coordinate_gui.TestablePixelConverterFactory;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.PixelConverter;
import org.snobot.coordinate_gui.model.Position2dDistance;

import java.util.ArrayList;
import java.util.List;

public class PurePursuitLayerControllerTest
{
    private static final double EPSILON = 1e-4;

    private static class TestablePurePursuitLayerController extends PurePursuitLayerController
    {
        TestablePurePursuitLayerController()
        {
            mMarkers = new Group();
            mLookaheadLine = new Line();
        }
    }

    @Test
    public void testController()
    {
        PixelConverter converter = TestablePixelConverterFactory.setupPortraitModeOriginAtCenter();

        PurePursuitLayerController controller = new TestablePurePursuitLayerController();
        controller.setup(converter);

        List<Coordinate> waypoints = new ArrayList<>();
        waypoints.add(new Coordinate(new Position2dDistance(8.41, 18.28, Distance.Unit.FEET), -48.93));
        waypoints.add(new Coordinate(new Position2dDistance(1.41, 17.48, Distance.Unit.FEET), 38.93));

        List<Coordinate> upSampled = new ArrayList<>();
        upSampled.add(new Coordinate(new Position2dDistance(.624, -9.42, Distance.Unit.FEET), 179));
        upSampled.add(new Coordinate(new Position2dDistance(9.521, -9.48, Distance.Unit.FEET), -179));

        List<Coordinate> smoothed = new ArrayList<>();
        smoothed.add(new Coordinate(new Position2dDistance(-12.41, -9.42, Distance.Unit.FEET), 95.67));
        smoothed.add(new Coordinate(new Position2dDistance(12.56, -9.48, Distance.Unit.FEET), 0));

        PurePursuitLayerController.PurePursuitLookaheadData lookaheadData = new PurePursuitLayerController.PurePursuitLookaheadData(
            new Position2dDistance(-8.842, 1.34, Distance.Unit.FEET),
            new Position2dDistance(-6.54, 2.9, Distance.Unit.FEET));
        controller.setLookaheadLine(lookaheadData);
        controller.setWaypoints(waypoints, upSampled, smoothed);

        Assertions.assertEquals(6, controller.mMarkers.getChildren().size());

        Circle circle;

        circle = (Circle) controller.mMarkers.getChildren().get(0);
        Assertions.assertEquals(219.1, circle.getCenterX(), EPSILON);
        Assertions.assertEquals(87.2, circle.getCenterY(), EPSILON);

        circle = (Circle) controller.mMarkers.getChildren().get(1);
        Assertions.assertEquals(149.1, circle.getCenterX(), EPSILON);
        Assertions.assertEquals(95.2, circle.getCenterY(), EPSILON);

        Rectangle rectangle;
        rectangle = (Rectangle) controller.mMarkers.getChildren().get(2);
        Assertions.assertEquals(141.24, rectangle.getX(), EPSILON);
        Assertions.assertEquals(364.2, rectangle.getY(), EPSILON);

        rectangle = (Rectangle) controller.mMarkers.getChildren().get(3);
        Assertions.assertEquals(230.21, rectangle.getX(), EPSILON);
        Assertions.assertEquals(364.8, rectangle.getY(), EPSILON);

        circle = (Circle) controller.mMarkers.getChildren().get(4);
        Assertions.assertEquals(10.9, circle.getCenterX(), EPSILON);
        Assertions.assertEquals(364.2, circle.getCenterY(), EPSILON);

        circle = (Circle) controller.mMarkers.getChildren().get(5);
        Assertions.assertEquals(260.6, circle.getCenterX(), EPSILON);
        Assertions.assertEquals(364.8, circle.getCenterY(), EPSILON);
    }
}
