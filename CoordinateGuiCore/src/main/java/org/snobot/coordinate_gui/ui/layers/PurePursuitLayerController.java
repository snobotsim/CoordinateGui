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

public class PurePursuitLayerController
{
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
     * @param aRobotX
     *            The robots X position
     * @param aRobotY
     *            The robots Y position
     * @param aLookaheadX
     *            The lookahead position
     * @param aLookaheadY
     *            The lookahead position
     */
    public void setLookaheadLine(double aRobotX, double aRobotY, double aLookaheadX, double aLookaheadY)
    {
        mLookaheadLine.setStartX(mPixelConverter.convertFieldXFeetToPixels(aRobotX));
        mLookaheadLine.setStartY(mPixelConverter.convertFieldYFeetToPixels(aRobotY));
        mLookaheadLine.setEndX(mPixelConverter.convertFieldXFeetToPixels(aLookaheadX));
        mLookaheadLine.setEndY(mPixelConverter.convertFieldYFeetToPixels(aLookaheadY));
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
            Circle circle = new Circle();
            circle.setRadius(10);
            circle.setCenterX(mPixelConverter.convertFieldXFeetToPixels(coordinate.mX));
            circle.setCenterY(mPixelConverter.convertFieldYFeetToPixels(coordinate.mY));
            circle.setStroke(Color.RED);
            circle.setFill(Color.TRANSPARENT);
            mMarkers.getChildren().add(circle);
        }

        for (Coordinate coordinate : aUpSampled)
        {
            Rectangle rectangle = new Rectangle();
            rectangle.setHeight(5);
            rectangle.setWidth(5);
            rectangle.setX(mPixelConverter.convertFieldXFeetToPixels(coordinate.mX));
            rectangle.setY(mPixelConverter.convertFieldYFeetToPixels(coordinate.mY));
            rectangle.setFill(Color.ORANGE);
            mMarkers.getChildren().add(rectangle);
        }
        for (Coordinate coordinate : aSmoothed)
        {
            Circle circle = new Circle();
            circle.setRadius(5);
            circle.setCenterX(mPixelConverter.convertFieldXFeetToPixels(coordinate.mX));
            circle.setCenterY(mPixelConverter.convertFieldYFeetToPixels(coordinate.mY));
            circle.setFill(Color.GREEN);
            mMarkers.getChildren().add(circle);
        }
    }

    public void setup(PixelConverter aPixelConverter)
    {
        mPixelConverter = aPixelConverter;
    }

}
