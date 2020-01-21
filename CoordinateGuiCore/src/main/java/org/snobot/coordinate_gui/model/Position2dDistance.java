package org.snobot.coordinate_gui.model;

public class Position2dDistance
{

    /** The x coordinate, in feet. */
    public Distance mX;

    /** The y coordinate, in feet. */
    public Distance mY;

    public Position2dDistance(double aX, double aY, Distance.Unit aUnit)
    {
        mX = Distance.from(aX, aUnit);
        mY = Distance.from(aY, aUnit);
    }

    public Position2dDistance(Distance aX, Distance aY)
    {
        mX = aX;
        mY = aY;
    }
}
