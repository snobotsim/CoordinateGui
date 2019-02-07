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

    public double convertFieldXFeetToPixels(double aFeet)
    {
        return mXCenterPixels - convertFeetToPixels(mXCenterFeet - aFeet);
    }

    public double convertFieldYFeetToPixels(double aFeet)
    {
        return convertFeetToPixels(mYCenterFeet - aFeet);
    }

    public double convertFieldXPixelsToFeet(double aX)
    {
        return mXCenterFeet - (mXCenterPixels - aX / mScale.getX()) / mImageScaleFactor;
    }

    public double convertFieldYPixelsToFeet(double aY)
    {
        return mYCenterFeet - aY / mScale.getY() / mImageScaleFactor;
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
