package org.snobot.coordinate_gui.model;


public class PixelConverter
{
    protected double mXCenterFeet;
    protected double mYCenterFeet;
    protected double mScaleFactor;

    protected int mXCenterPixels;

    public PixelConverter(double aFieldCenterX, double aFieldCenterY)
    {
        mXCenterFeet = aFieldCenterX;
        mYCenterFeet = aFieldCenterY;
    }

    public int convertFeetToPixels(double aFeet)
    {
        return (int) (aFeet * mScaleFactor);
    }

    public int convertXFeetToPixels(double aFeet)
    {
        return mXCenterPixels - convertFeetToPixels(mXCenterFeet - aFeet);
    }

    public int convertYFeetToPixels(double aFeet)
    {
        return convertFeetToPixels(mYCenterFeet - aFeet);
    }

    public double convertPixelsToFeet(int aPixels)
    {
        return aPixels / mScaleFactor;
    }

    public double convertXPixelsToFeet(int aX)
    {
        return mXCenterFeet - convertPixelsToFeet(mXCenterPixels - aX);
    }

    public double convertYPixelsToFeet(int aY)
    {
        return mYCenterFeet - convertPixelsToFeet(aY);
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
    public void updateScaleFactor(int aWidthPixels, int aHeightPixels, double aWidthFeet, double aHeightFeet)
    {
        double horizontalScaleFactor = aWidthPixels / aWidthFeet;
        double verticalScaleFactor = aHeightPixels / aHeightFeet;

        double minScaleFactor = Math.min(horizontalScaleFactor, verticalScaleFactor);

        mScaleFactor = minScaleFactor;
        mXCenterPixels = (int) (aWidthFeet * mScaleFactor);
    }
}
