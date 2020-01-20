package org.snobot.coordinate_gui.ui.layers;

import java.util.List;

import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.PixelConverter;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.model.Position2dPixels;

public class PurePursuitLayerController
{
    @SuppressWarnings("PMD.DataClass")
    public static class PurePursuitLookaheadData
    {
        public final double mRobotX;
        public final double mRobotY;
        public final double mLookaheadX;
        public final double mLookaheadY;

        /**
         * Constructor.
         * @param aRobotX Robots X
         * @param aRobotY Robots Y
         * @param aLookaheadX Lookahead X
         * @param aLookaheadY Lookahead Y
         */
        public PurePursuitLookaheadData(double aRobotX, double aRobotY, double aLookaheadX, double aLookaheadY)
        {
            mRobotX = aRobotX;
            mRobotY = aRobotY;
            mLookaheadX = aLookaheadX;
            mLookaheadY = aLookaheadY;
        }
    }

    @FXML
    protected Group mMarkers;

    @FXML
    protected Line mLookaheadLine;

    protected PixelConverter mPixelConverter;

    @FXML
    public void initialize()
    {
        mLookaheadLine.setFill(Color.CYAN);
    }

    /**
     * Sets the line representing the lookahead distance.
     * 
     * @param aRobotPosition
     *            The robots position
     * @param aLookaheadPosition
     *            The lookahead position
     */
    public void setLookaheadLine(Position2dDistance aRobotPosition, Position2dDistance aLookaheadPosition)
    {
        Position2dPixels robot = mPixelConverter.convertDistanceToPixels(aRobotPosition);
        Position2dPixels lookahead = mPixelConverter.convertDistanceToPixels(aLookaheadPosition);
        mLookaheadLine.setStartX(robot.mX);
        mLookaheadLine.setStartY(robot.mY);
        mLookaheadLine.setEndX(lookahead.mX);
        mLookaheadLine.setEndY(lookahead.mY);
    }

    /**
     * Sets the waypoint information.
     * 
     * @param aWaypoints
     *            The waypoints
     * @param aUpSampled
     *            The waypoints, upsampled into a path
     * @param aSmoothed
     *            The upsampled points after smoothing
     */
    public void setWaypoints(List<Coordinate> aWaypoints, List<Coordinate> aUpSampled, List<Coordinate> aSmoothed)
    {
        mMarkers.getChildren().clear();

        for (Coordinate coordinate : aWaypoints)
        {
            Position2dPixels asPixels = mPixelConverter.convertDistanceToPixels(coordinate.mPosition);

            Circle circle = new Circle();
            circle.setRadius(10);
            circle.setCenterX(asPixels.mX);
            circle.setCenterY(asPixels.mY);
            circle.setStroke(Color.RED);
            circle.setFill(Color.TRANSPARENT);
            mMarkers.getChildren().add(circle);
        }

        for (Coordinate coordinate : aUpSampled)
        {
            Position2dPixels asPixels = mPixelConverter.convertDistanceToPixels(coordinate.mPosition);

            Rectangle rectangle = new Rectangle();
            rectangle.setHeight(5);
            rectangle.setWidth(5);
            rectangle.setX(asPixels.mX);
            rectangle.setY(asPixels.mY);
            rectangle.setFill(Color.ORANGE);
            mMarkers.getChildren().add(rectangle);
        }
        for (Coordinate coordinate : aSmoothed)
        {
            Position2dPixels asPixels = mPixelConverter.convertDistanceToPixels(coordinate.mPosition);

            Circle circle = new Circle();
            circle.setRadius(5);
            circle.setCenterX(asPixels.mX);
            circle.setCenterY(asPixels.mY);
            circle.setFill(Color.GREEN);
            mMarkers.getChildren().add(circle);
        }
    }

    public void setup(PixelConverter aPixelConverter)
    {
        mPixelConverter = aPixelConverter;
    }

}
