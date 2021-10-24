package org.snobot.coordinate_gui.model;

import javafx.scene.transform.Scale;

public class PixelConverter
{
    private static final Distance.Unit CONVERSION_UNIT = Distance.Unit.Feet;

    public enum Orientation
    {
        Landscape,
        Portrait,
    }

    public enum OriginPosition
    {
        CenterField,
        AlwaysIncreasing,
        BottomLeft,
    }

    protected final Distance mXCenterFeet;
    protected final Distance mYCenterFeet;
    protected final double mHeightMultiplier;

    protected double mWidthPixels;
    protected double mHeightPixels;
    protected double mImageScaleFactor;

    protected Scale mScale;

    protected final Orientation mOrientation;
    protected final OriginPosition mOriginPosition;

    /**
     * Constructor.
     * @param aDimension1 One of the two dimensions (width, height)
     * @param aDimension2 One of the two dimensions (width, height)
     * @param aOrientation The orientation
     * @param aOriginPosition Where the origin should be
     */
    @SuppressWarnings("PMD.CyclomaticComplexity")
    public PixelConverter(Distance aDimension1, Distance aDimension2, Orientation aOrientation, OriginPosition aOriginPosition)
    {
        Distance aFieldShortDimension;
        Distance aFieldLongDimension;
        aFieldShortDimension = aDimension1.as(CONVERSION_UNIT) > aDimension2.as(CONVERSION_UNIT) ? aDimension2 : aDimension1;
        aFieldLongDimension = aDimension1.as(CONVERSION_UNIT) > aDimension2.as(CONVERSION_UNIT) ? aDimension1 : aDimension2;
        mOrientation = aOrientation;
        mOriginPosition = aOriginPosition;

        if (aOrientation == Orientation.Landscape)
        {
            switch (aOriginPosition)
            {
            case CenterField:
                mHeightMultiplier = -1;
                mXCenterFeet = Distance.divide(aFieldLongDimension, 2);
                mYCenterFeet = Distance.divide(aFieldShortDimension, 2);
                break;
            case AlwaysIncreasing:
                mHeightMultiplier = -1;
                mXCenterFeet = aFieldLongDimension;
                mYCenterFeet = aFieldShortDimension;
                break;
            case BottomLeft:
                mHeightMultiplier = 1;
                mXCenterFeet = aFieldLongDimension;
                mYCenterFeet = Distance.mult(aFieldShortDimension, 1);
                break;
            default:
                throw new IllegalArgumentException("Unknown origin " + aOriginPosition);
            }
        }
        else
        {
            mHeightMultiplier = 1;
            switch (aOriginPosition)
            {
            case CenterField:
                mXCenterFeet = Distance.divide(aFieldShortDimension, 2);
                mYCenterFeet = Distance.divide(aFieldLongDimension, 2);
                break;
            case AlwaysIncreasing:
            case BottomLeft:
                mXCenterFeet = aFieldShortDimension;
                mYCenterFeet = aFieldLongDimension;
                break;
            default:
                throw new IllegalArgumentException("Unknown origin " + aOriginPosition);
            }
        }

    }

    public double convertFeetToPixels(Distance aDistance)
    {
        return aDistance.as(CONVERSION_UNIT) * mImageScaleFactor;
    }

    /**
     * Converts a distance to pixels.
     * @param aDistance The distance
     * @return The distance, converted to field pixels
     */
    public Position2dPixels convertDistanceToPixels(Position2dDistance aDistance)
    {
        Distance relativeX = Distance.subtract(mXCenterFeet, aDistance.mX);
        Distance relativeY = Distance.subtract(mYCenterFeet, aDistance.mY);
        double x = mWidthPixels - convertFeetToPixels(relativeX);
        double y;
        if (mOrientation == Orientation.Portrait || mOriginPosition == OriginPosition.BottomLeft)
        {
            y = convertFeetToPixels(relativeY);
        }
        else
        {
            y = mHeightPixels - convertFeetToPixels(relativeY);
        }

        return new Position2dPixels(x, mHeightMultiplier *  y);
    }

    /**
     * Converts field pixels into distance units.
     * @param aPixels The pixels
     * @return The distance
     */
    public Position2dDistance convertPixelsToDistance(Position2dPixels aPixels)
    {
        double x = mXCenterFeet.as(CONVERSION_UNIT) - (mWidthPixels - aPixels.mX / mScale.getX()) / mImageScaleFactor;
        double y;

        if (mOrientation == Orientation.Portrait)
        {
            y = mYCenterFeet.as(CONVERSION_UNIT) - aPixels.mY / mScale.getY() / mImageScaleFactor;
        }
        else
        {
            y = mYCenterFeet.as(CONVERSION_UNIT) - (mHeightPixels - aPixels.mY / mScale.getY()) / mImageScaleFactor;
        }

        return new Position2dDistance(x, mHeightMultiplier * y, CONVERSION_UNIT);
    }

    /**
     * Updates the scale factor, based on the size of the window.
     * 
     * @param aScale
     * 
     * @param aWidthPixels
     *            The width of the panel, in pixels
     * @param aHeightPixels
     *            The height of the panel, in pixels
     * @param aWidthFeet
     *            The width of the field, in feet
     * @param aHeightFeet
     *            The height of the field, in feet
     */
    public void setImageScale(Scale aScale, double aWidthPixels, double aHeightPixels, Distance aWidthFeet, Distance aHeightFeet)
    {
        double horizontalScaleFactor = aWidthPixels / aWidthFeet.as(CONVERSION_UNIT);
        double verticalScaleFactor = aHeightPixels / aHeightFeet.as(CONVERSION_UNIT);

        mScale = aScale;
        mImageScaleFactor = Math.min(horizontalScaleFactor, verticalScaleFactor);
        mWidthPixels = aWidthFeet.as(CONVERSION_UNIT) * mImageScaleFactor;
        mHeightPixels = aHeightFeet.as(CONVERSION_UNIT) * mImageScaleFactor;
    }

    /**
     * Gets the angle.
     * @param aAngle The input angle
     * @return The converted angle
     */
    public double convertHeading(double aAngle)
    {
        if (mOrientation == Orientation.Portrait)
        {
            return aAngle;
        }

        return 90 - aAngle;
    }
}
