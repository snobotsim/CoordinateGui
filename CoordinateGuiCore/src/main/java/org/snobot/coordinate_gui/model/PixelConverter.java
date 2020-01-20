package org.snobot.coordinate_gui.model;

import javafx.scene.transform.Scale;

public class PixelConverter
{
    protected double mXCenterFeet;
    protected double mYCenterFeet;

    protected double mXCenterPixels;
    protected double mImageScaleFactor;

    protected Scale mScale;

    public PixelConverter(double aFieldCenterX, double aFieldCenterY)
    {
        mXCenterFeet = aFieldCenterX;
        mYCenterFeet = aFieldCenterY;
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
        double x = mXCenterPixels - convertFeetToPixels(mXCenterFeet - aDistance.mX);
        double y = convertFeetToPixels(mYCenterFeet - aDistance.mY);

        return new Position2dPixels(x, y);
    }

    /**
     * Converts field pixels into distance units.
     * @param aPixels The pixels
     * @return The distance
     */
    public Position2dDistance convertPixelsToFeet(Position2dPixels aPixels)
    {
        double x = mXCenterFeet - (mXCenterPixels - aPixels.mX / mScale.getX()) / mImageScaleFactor;
        double y = mYCenterFeet - aPixels.mY / mScale.getY() / mImageScaleFactor;

        return new Position2dDistance(x, y);
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
        mXCenterPixels = aWidthFeet * mImageScaleFactor;
    }
}
