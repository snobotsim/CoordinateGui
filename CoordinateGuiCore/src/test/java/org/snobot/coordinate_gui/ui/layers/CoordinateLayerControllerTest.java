package org.snobot.coordinate_gui.ui.layers;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snobot.coordinate_gui.TestablePixelConverterFactory;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.DataProvider;
import org.snobot.coordinate_gui.model.PixelConverter;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.ui.render_props.CoordinateLayerRenderProps;

public class CoordinateLayerControllerTest
{
    private static final double EPSILON = 1e-4;

    private static class TestableCoordinateLayerController extends CoordinateLayerController
    {
        TestableCoordinateLayerController()
        {
            mCoordinates = new Group();
        }
    }

    @Test
    public void testSinglePoint()
    {
        int pointMemory = 1;
        PixelConverter converter = TestablePixelConverterFactory.setupPortraitMode();
        CoordinateLayerController controller = new TestableCoordinateLayerController();
        CoordinateLayerRenderProps renderProps = new CoordinateLayerRenderProps(pointMemory, 5, Color.WHEAT, true);
        DataProvider<Coordinate> dataProvider = new DataProvider<>(pointMemory);
        controller.setup(renderProps, dataProvider, converter);

        dataProvider.addData(new Coordinate(new Position2dDistance(12.41, 24.41), 0));
        dataProvider.addData(new Coordinate(new Position2dDistance(0, 0), 0));
        dataProvider.addData(new Coordinate(new Position2dDistance(-13.5, 27), 0));
        dataProvider.addData(new Coordinate(new Position2dDistance(13.5, 27), 0));
        dataProvider.addData(new Coordinate(new Position2dDistance(-13.5, -27), 0));
        dataProvider.addData(new Coordinate(new Position2dDistance(13.5, -27), 0));

        controller.render();

        Assertions.assertEquals(1, controller.mCoordinates.getChildren().size());

        Circle circle;

        circle = (Circle) controller.mCoordinates.getChildren().get(0);
        Assertions.assertEquals(270, circle.getCenterX(), EPSILON);
        Assertions.assertEquals(540, circle.getCenterY(), EPSILON);
    }

    @Test
    public void testMultiplePoints()
    {
        int pointMemory = 20;
        PixelConverter converter = TestablePixelConverterFactory.setupPortraitMode();
        CoordinateLayerController controller = new TestableCoordinateLayerController();
        CoordinateLayerRenderProps renderProps = new CoordinateLayerRenderProps(pointMemory, 5, Color.WHEAT, true);
        DataProvider<Coordinate> dataProvider = new DataProvider<>(pointMemory);
        controller.setup(renderProps, dataProvider, converter);

        dataProvider.addData(new Coordinate(new Position2dDistance(12.41, 24.41), 0));
        dataProvider.addData(new Coordinate(new Position2dDistance(0, 0), 0));
        dataProvider.addData(new Coordinate(new Position2dDistance(-13.5, 27), 0));
        dataProvider.addData(new Coordinate(new Position2dDistance(13.5, 27), 0));
        dataProvider.addData(new Coordinate(new Position2dDistance(-13.5, -27), 0));
        dataProvider.addData(new Coordinate(new Position2dDistance(13.5, -27), 0));

        controller.render();

        Assertions.assertEquals(6, controller.mCoordinates.getChildren().size());

        Circle circle;

        circle = (Circle) controller.mCoordinates.getChildren().get(0);
        Assertions.assertEquals(270, circle.getCenterX(), EPSILON);
        Assertions.assertEquals(540, circle.getCenterY(), EPSILON);

        circle = (Circle) controller.mCoordinates.getChildren().get(1);
        Assertions.assertEquals(0, circle.getCenterX(), EPSILON);
        Assertions.assertEquals(540, circle.getCenterY(), EPSILON);

        circle = (Circle) controller.mCoordinates.getChildren().get(2);
        Assertions.assertEquals(270, circle.getCenterX(), EPSILON);
        Assertions.assertEquals(0, circle.getCenterY(), EPSILON);

        circle = (Circle) controller.mCoordinates.getChildren().get(3);
        Assertions.assertEquals(0, circle.getCenterX(), EPSILON);
        Assertions.assertEquals(0, circle.getCenterY(), EPSILON);

        circle = (Circle) controller.mCoordinates.getChildren().get(4);
        Assertions.assertEquals(135, circle.getCenterX(), EPSILON);
        Assertions.assertEquals(270, circle.getCenterY(), EPSILON);

        circle = (Circle) controller.mCoordinates.getChildren().get(5);
        Assertions.assertEquals(259.1, circle.getCenterX(), EPSILON);
        Assertions.assertEquals(25.9, circle.getCenterY(), EPSILON);
    }
}
