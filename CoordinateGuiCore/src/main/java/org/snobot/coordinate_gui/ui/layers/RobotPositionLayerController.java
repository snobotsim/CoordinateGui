package org.snobot.coordinate_gui.ui.layers;

import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.PixelConverter;

import javafx.fxml.FXML;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.model.Position2dPixels;

public class RobotPositionLayerController
{
    private static final double ARROW_SIZE = 10;

    @FXML
    protected Rectangle mRobot;

    @FXML
    protected Polygon mOrientationArrow;

    private double mRobotWidth; // Feet
    private double mRobotHeight; // Feet

    /**
     * Sets the size of the robot.
     * 
     * @param aPixelConverter
     *            The pixel converter
     * @param aWidth
     *            Width of the robot, in feet
     * @param aHeight
     *            Height of the robot, in feet
     */
    public void setRobotDimensions(PixelConverter aPixelConverter, double aWidth, double aHeight)
    {
        mRobotWidth = aWidth;
        mRobotHeight = aHeight;

        mRobot.setWidth(aPixelConverter.convertFeetToPixels(mRobotWidth));
        mRobot.setHeight(aPixelConverter.convertFeetToPixels(mRobotHeight));
        
        mOrientationArrow.getPoints().addAll(
                -ARROW_SIZE, ARROW_SIZE,
                0.0, -ARROW_SIZE, 
                ARROW_SIZE, ARROW_SIZE);
    }

    @FXML
    public void initialize()
    {
        mRobot.setFill(javafx.scene.paint.Color.BLUE);
        mOrientationArrow.setFill(javafx.scene.paint.Color.ORANGE);
    }

    /**
     * Sets the position of the robot to draw.
     * 
     * @param aPixelConverter
     *            The pixel converter
     * @param aPosition
     *            The robot position
     */
    public void setPosition(PixelConverter aPixelConverter, Coordinate aPosition)
    {
        mRobot.setRotate(aPosition.mAngle);
        Position2dDistance robotDimensions = new Position2dDistance(aPosition.mPosition.mX - mRobotWidth / 2, aPosition.mPosition.mY + mRobotHeight / 2);
        Position2dPixels robotAsPixels = aPixelConverter.convertDistanceToPixels(robotDimensions);
        mRobot.setX(robotAsPixels.mX);
        mRobot.setY(robotAsPixels.mY);

        Position2dPixels asPixels = aPixelConverter.convertDistanceToPixels(aPosition.mPosition);
        mOrientationArrow.setRotate(aPosition.mAngle);
        mOrientationArrow.setTranslateX(asPixels.mX);
        mOrientationArrow.setTranslateY(asPixels.mY);
    }

}
