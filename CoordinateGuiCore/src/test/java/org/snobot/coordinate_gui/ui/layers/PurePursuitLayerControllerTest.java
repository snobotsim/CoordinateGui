package org.snobot.coordinate_gui.ui.layers;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import org.junit.Assert;
import org.junit.Test;
import org.snobot.coordinate_gui.TestablePixelConverterFactory;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.PixelConverter;

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
        PixelConverter converter = TestablePixelConverterFactory.setupPortraitMode();

        PurePursuitLayerController controller = new TestablePurePursuitLayerController();
        controller.setup(converter);

        List<Coordinate> waypoints = new ArrayList<>();
        waypoints.add(new Coordinate(8.41, 18.28, -48.93));
        waypoints.add(new Coordinate(1.41, 17.48, 38.93));

        List<Coordinate> upSampled = new ArrayList<>();
        upSampled.add(new Coordinate(.624, -9.42, 179));
        upSampled.add(new Coordinate(9.521, -9.48, -179));

        List<Coordinate> smoothed = new ArrayList<>();
        smoothed.add(new Coordinate(-12.41, -9.42, 95.67));
        smoothed.add(new Coordinate(12.56, -9.48, 0));

        controller.setLookaheadLine(-8.842, 1.34, -6.54, 2.9);
        controller.setWaypoints(waypoints, upSampled, smoothed);

        Assert.assertEquals(6, controller.mMarkers.getChildren().size());

        Circle circle;

        circle = (Circle) controller.mMarkers.getChildren().get(0);
        Assert.assertEquals(219.1, circle.getCenterX(), EPSILON);
        Assert.assertEquals(87.2, circle.getCenterY(), EPSILON);

        circle = (Circle) controller.mMarkers.getChildren().get(1);
        Assert.assertEquals(149.1, circle.getCenterX(), EPSILON);
        Assert.assertEquals(95.2, circle.getCenterY(), EPSILON);

        Rectangle rectangle;
        rectangle = (Rectangle) controller.mMarkers.getChildren().get(2);
        Assert.assertEquals(141.24, rectangle.getX(), EPSILON);
        Assert.assertEquals(364.2, rectangle.getY(), EPSILON);

        rectangle = (Rectangle) controller.mMarkers.getChildren().get(3);
        Assert.assertEquals(230.21, rectangle.getX(), EPSILON);
        Assert.assertEquals(364.8, rectangle.getY(), EPSILON);

        circle = (Circle) controller.mMarkers.getChildren().get(4);
        Assert.assertEquals(10.9, circle.getCenterX(), EPSILON);
        Assert.assertEquals(364.2, circle.getCenterY(), EPSILON);

        circle = (Circle) controller.mMarkers.getChildren().get(5);
        Assert.assertEquals(260.6, circle.getCenterX(), EPSILON);
        Assert.assertEquals(364.8, circle.getCenterY(), EPSILON);
    }
}
