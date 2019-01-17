package org.snobot.coordinate_gui.ui.render_props;

import java.awt.Color;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.snobot.coordinate_gui.model.Coordinate;

public class CreatePointsLayerRenderProps
{
    private static final Logger sLOGGER = LogManager.getLogger(CreatePointsLayerRenderProps.class);

    public enum AngleCalculationType
    {
        Zero, Calculate, CalculateBackwards, Custom
    }

    protected AngleCalculationType mAngleCalculationType;
    private final boolean mSnapToGrid;
    private final double mGridFactorX;
    private final double mGridFactorY;
    private final double mMinDragDistance;

    private final CoordinateLayerRenderProps mConfigRenderProperties;
    private final CoordinateLayerRenderProps mPreviewRenderProperties;

    /**
     * Constructor.
     */
    public CreatePointsLayerRenderProps()
    {
        mConfigRenderProperties = new CoordinateLayerRenderProps();
        mConfigRenderProperties.setFadeOverTime(false);
        mConfigRenderProperties.setPointSize(5);
        mConfigRenderProperties.setPointMemory(-1);
        mConfigRenderProperties.setPointColor(Color.green);

        mPreviewRenderProperties = new CoordinateLayerRenderProps();
        mPreviewRenderProperties.setFadeOverTime(false);
        mPreviewRenderProperties.setPointSize(2);
        mPreviewRenderProperties.setPointMemory(-1);
        mPreviewRenderProperties.setPointColor(Color.red);

        mSnapToGrid = true;
        mGridFactorX = .25;
        mGridFactorY = .25;
        mMinDragDistance = 2.5;
        mAngleCalculationType = AngleCalculationType.Calculate;
    }

    public int getActivePointSize()
    {
        return 5;
    }

    public Color getActivePointColor()
    {
        return Color.red;
    }

    /**
     * Gets the angle between the most recent point and the provided point.
     * 
     * @param aLastPoint
     *            The last point
     * @param aXFeet
     *            The current coordinates X position, in feet
     * @param aYFeet
     *            The current coordinates Y position, in feet
     * @return The angle between the points
     */
    public double getAngle(Coordinate aLastPoint, double aXFeet, double aYFeet)
    {
        double output = 0;

        switch (mAngleCalculationType)
        {
        case Zero:
            output = 0;
            break;
        case Calculate:
        {
            if (aLastPoint != null)
            {
                double dx = aXFeet - aLastPoint.mX;
                double dy = aYFeet - aLastPoint.mY;
                output = Math.toDegrees(Math.atan2(dx, dy));
            }
            break;
        }
        case CalculateBackwards:
        {
            if (aLastPoint != null)
            {
                double dx = aXFeet - aLastPoint.mX;
                double dy = aYFeet - aLastPoint.mY;
                output = Math.toDegrees(Math.atan2(dx, dy)) - 180;
            }
            break;
        }
        case Custom:
            output = 90;
            break;
        default:
            sLOGGER.log(Level.ERROR, "Unknown angle calculation type " + mAngleCalculationType);
            break;
        }

        return output;
    }

    public boolean isSnapToGrid()
    {
        return mSnapToGrid;
    }

    public double getGridFactorX()
    {
        return mGridFactorX;
    }

    public double getGridFactorY()
    {
        return mGridFactorY;
    }

    public void setAngleCalculationType(AngleCalculationType aCalculationType)
    {
        mAngleCalculationType = aCalculationType;
    }

    public double getMinDragDistance()
    {
        return mMinDragDistance;
    }

    public CoordinateLayerRenderProps getConfigRenderProperties()
    {
        return mConfigRenderProperties;
    }

    public CoordinateLayerRenderProps getPreviewRenderProperties()
    {
        return mPreviewRenderProperties;
    }
}
