package org.snobot.coordinate_gui.model;

public class Position2dPixels
{

    /** The x coordinate, in pixels. */
    public double mX;

    /** The y coordinate, in pixels. */
    public double mY;

    public Position2dPixels(double aX, double aY)
    {
        mX = aX;
        mY = aY;
    }

    @Override
    public String toString()
    {
        return "Position2dPixels{mX=" + mX + ", mY=" + mY + '}';
    }
}
