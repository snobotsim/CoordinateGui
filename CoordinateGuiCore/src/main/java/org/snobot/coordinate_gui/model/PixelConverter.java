package org.snobot.coordinate_gui.model;

import javafx.scene.transform.Scale;

public class PixelConverter
{
    public enum Orientation
    {
        Landscape,
        Portrait,
    }

    public enum OriginPosition
    {
        CenterField,
        AlwaysIncreasing,
    }

    protected final double mXCenterFeet;
    protected final double mYCenterFeet;
    protected final double mHeightMultiplier;

    protected double mWidthPixels;
    protected double mHeightPixels;
    protected double mImageScaleFactor;

    protected Scale mScale;

    protected final Orientation mOrientation;

    /**
     * Constructor.
     * @param aFieldShortDimension The shorter dimension of the field
     * @param aFieldLongDimension The longer dimension of the field
     * @param aOrientation The orientation
     * @param aOriginPosition Where the origin should be
     */
    public PixelConverter(double aFieldShortDimension, double aFieldLongDimension, Orientation aOrientation, OriginPosition aOriginPosition)
    {
        mOrientation = aOrientation;
        if (aOrientation == Orientation.Landscape)
        {
            mHeightMultiplier = -1;
            if (aOriginPosition == OriginPosition.CenterField)
            {
                mXCenterFeet = aFieldLongDimension / 2;
                mYCenterFeet = aFieldShortDimension / 2;
            }
            else
            {
                mXCenterFeet = aFieldLongDimension;
                mYCenterFeet = aFieldShortDimension;
            }
        }
        else
        {
            mHeightMultiplier = 1;
            if (aOriginPosition == OriginPosition.CenterField)
            {
                mXCenterFeet = aFieldShortDimension / 2;
                mYCenterFeet = aFieldLongDimension / 2;
            }
            else
            {
                mXCenterFeet = aFieldShortDimension;
                mYCenterFeet = aFieldLongDimension;
            }
        }

    }

    public double convertFeetToPixels(double aFeet)
    {
        return aFeet * mImageScaleFactor;
    }

    /**
     * Converts a distance to pixels.
     * @param aDistance The distance
     * @return The distance, converted to field pixels
     */
    public Position2dPixels convertDistanceToPixels(Position2dDistance aDistance)
    {
        double x = mWidthPixels - convertFeetToPixels(mXCenterFeet - aDistance.mX);
        double y;
        if (mOrientation == Orientation.Portrait)
        {
            y = convertFeetToPixels(mYCenterFeet - aDistance.mY);
        }
        else
        {
            y = mHeightPixels - convertFeetToPixels(mYCenterFeet - aDistance.mY);
        }

        return new Position2dPixels(x, mHeightMultiplier *  y);
    }

    /**
     * Converts field pixels into distance units.
     * @param aPixels The pixels
     * @return The distance
     */
    public Position2dDistance convertPixelsToFeet(Position2dPixels aPixels)
    {
        double x = mXCenterFeet - (mWidthPixels - aPixels.mX / mScale.getX()) / mImageScaleFactor;
        double y;

        if (mOrientation == Orientation.Portrait)
        {
            y = mYCenterFeet - aPixels.mY / mScale.getY() / mImageScaleFactor;
        }
        else
        {
            y = mYCenterFeet - (mHeightPixels - aPixels.mY / mScale.getY()) / mImageScaleFactor;
        }

        return new Position2dDistance(x, mHeightMultiplier * y);
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
    public void setImageScale(Scale aScale, double aWidthPixels, double aHeightPixels, double aWidthFeet, double aHeightFeet)
    {
        double horizontalScaleFactor = aWidthPixels / aWidthFeet;
        double verticalScaleFactor = aHeightPixels / aHeightFeet;

        mScale = aScale;
        mImageScaleFactor = Math.min(horizontalScaleFactor, verticalScaleFactor);
        mWidthPixels = aWidthFeet * mImageScaleFactor;
        mHeightPixels = aHeightFeet * mImageScaleFactor;
    }
}
