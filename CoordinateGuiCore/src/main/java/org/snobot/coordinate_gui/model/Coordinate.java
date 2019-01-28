package org.snobot.coordinate_gui.model;

/**
 * Represents a positioning coordinate in feet.
 * @author Rich
 */
public class Coordinate
{
    /** The x coordinate, in feet. */
    public double mX;

    /** The y coordinate, in feet. */
    public double mY;

    /** The angle of the coordinate, in degrees. */
    public double mAngle;

    /**
     * Constructor.  Sets the (x,y,angle) position to all zeros
     */
    public Coordinate()
    {
        this(0.0, 0.0, 0.0);
    }

    /**
     * Copy constructor.  Copies the (x,y,angle) position from the given coordinate
     * @param aCopy The coordinate to copy
     */
    public Coordinate(Coordinate aCopy)
    {
        this(aCopy.mX, aCopy.mY, aCopy.mAngle);
    }

    /**
     * Constructor.  Sets the (x,y,angle) to the given values
     * @param aX The x coordinate
     * @param aY The y coordinate
     * @param aAngle The angle
     */
    public Coordinate(double aX, double aY, double aAngle)
    {
        this.mX = aX;
        this.mY = aY;
        this.mAngle = aAngle;
    }

    @Override
    public String toString()
    {
        return "Coordinate{" + "x=" + mX + ", y=" + mY + ", angle=" + mAngle + '}';
    }
    
    

}
