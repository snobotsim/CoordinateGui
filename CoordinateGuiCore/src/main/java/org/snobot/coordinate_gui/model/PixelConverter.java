package org.snobot.coordinate_gui.model;

public class PixelConverter
{
    protected double mXCenterFeet;
    protected double mYCenterFeet;

    protected double mXCenterPixels;
    protected double mImageScaleFactor;

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

    public double convertFieldXPixelsToFeet(int aX)
    {
        return mXCenterFeet - (mXCenterPixels - aX) / mImageScaleFactor;
    }

    public double convertFieldYPixelsToFeet(int aY)
    {
        return mYCenterFeet - aY / mImageScaleFactor;
    }

    /**
     * Updates the scale factor, based on the size of the window.
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
    public void setImageScale(double aWidthPixels, double aHeightPixels, double aWidthFeet, double aHeightFeet)
    {
        double horizontalScaleFactor = aWidthPixels / aWidthFeet;
        double verticalScaleFactor = aHeightPixels / aHeightFeet;

        mImageScaleFactor = Math.min(horizontalScaleFactor, verticalScaleFactor);
        mXCenterPixels = aWidthFeet * mImageScaleFactor;
    }
}
