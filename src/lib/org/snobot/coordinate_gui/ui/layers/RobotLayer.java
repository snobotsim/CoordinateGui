package org.snobot.coordinate_gui.ui.layers;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.DataProvider;
import org.snobot.coordinate_gui.model.PixelConverter;
import org.snobot.coordinate_gui.ui.render_props.RobotLayerRenderProps;

public class RobotLayer implements ILayer
{
    protected final DataProvider<Coordinate> mDataProvider;
    protected final PixelConverter mPixelConverter;
    protected final double mRobotWidth;
    protected final double mRobotHeight;
    protected final RobotLayerRenderProps mRenderProperties;

    /**
     * Constructor.
     * 
     * @param aDataProvider
     *            Data provider for robot coordinates
     * @param aRenderProps
     *            The render properties for the layer
     * @param aPixelConverter
     *            The pixel converter
     * @param aRobotWidth
     *            The widght of the robot, in inches
     * @param aRobotHeight
     *            The height of the robot, in inches
     */
    public RobotLayer(DataProvider<Coordinate> aDataProvider, RobotLayerRenderProps aRenderProps, PixelConverter aPixelConverter, double aRobotWidth,
            double aRobotHeight)
    {
        mDataProvider = aDataProvider;
        mPixelConverter = aPixelConverter;
        mRobotWidth = aRobotWidth;
        mRobotHeight = aRobotHeight;
        mRenderProperties = aRenderProps;
    }

    @Override
    public void render(Graphics2D aGraphics)
    {
        Coordinate coord = mDataProvider.getMostRecentData();
        if (coord != null)
        {
            drawRobot(aGraphics, coord);
            drawReferencePoint(aGraphics, coord);
        }
    }

    protected void drawRobot(Graphics2D aGraphics, Coordinate aCoordinates)
    {
        double centerX = mPixelConverter.convertXFeetToPixels(aCoordinates.mX);
        double centerY = mPixelConverter.convertYFeetToPixels(aCoordinates.mY);

        double widthInPixels = mPixelConverter.convertFeetToPixels(mRobotWidth);
        double heightInPixels = mPixelConverter.convertFeetToPixels(mRobotHeight);

        double robotCenterX = centerX - widthInPixels / 2;
        double robotCenterY = centerY - heightInPixels / 2;

        Rectangle2D robot = new Rectangle2D.Double(0, 0, widthInPixels, heightInPixels);

        int pivotX = (int) (robotCenterX + widthInPixels / 2);
        int pivotY = (int) (robotCenterY + heightInPixels / 2);

        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(aCoordinates.mAngle), pivotX, pivotY);
        transform.translate(robotCenterX, robotCenterY);

        Shape shape = transform.createTransformedShape(robot);
        aGraphics.setColor(mRenderProperties.getRobotColor());
        aGraphics.fill(shape);
    }

    protected void drawReferencePoint(Graphics2D aGraphics, Coordinate aCoordinate)
    {
        double centerX = mPixelConverter.convertXFeetToPixels(aCoordinate.mX);
        double centerY = mPixelConverter.convertYFeetToPixels(aCoordinate.mY);
        double heightInPixels = mPixelConverter.convertFeetToPixels(mRobotHeight);

        double halfRobotHeight = heightInPixels / 2;

        double dx = halfRobotHeight * Math.sin(Math.toRadians(aCoordinate.mAngle));
        double dy = halfRobotHeight * Math.cos(Math.toRadians(aCoordinate.mAngle));

        int dotSize = mRenderProperties.getReferencePointSize();

        int pointX = (int) (centerX + dx - dotSize / 2.0);
        int pointY = (int) (centerY - dy - dotSize / 2.0);

        aGraphics.setColor(mRenderProperties.getReferencePointColor());
        aGraphics.fillOval(pointX, pointY, dotSize, dotSize);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return null;
    }

}
