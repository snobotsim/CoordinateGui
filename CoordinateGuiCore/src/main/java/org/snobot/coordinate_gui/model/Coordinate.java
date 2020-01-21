package org.snobot.coordinate_gui.model;

/**
 * Represents a positioning coordinate in feet.
 * @author Rich
 */
public class Coordinate
{
    /** The (x, y) position. */
    public Position2dDistance mPosition;

    /** The angle of the coordinate, in degrees. */
    public double mAngle;

    /**
     * Constructor.  Sets the (x,y,angle) position to all zeros
     */
    public Coordinate()
    {
        this(new Position2dDistance(0, 0, Distance.Unit.FEET), 0.0);
    }

    /**
     * Copy constructor.  Copies the (x,y,angle) position from the given coordinate
     * @param aCopy The coordinate to copy
     */
    public Coordinate(Coordinate aCopy)
    {
        this(aCopy.mPosition, aCopy.mAngle);
    }

    /**
     * Constructor.  Sets the (x,y,angle) to the given values
     * @param aPosition The (X, Y) position
     * @param aAngle The angle
     */
    public Coordinate(Position2dDistance aPosition, double aAngle)
    {
        this.mPosition = aPosition;
        this.mAngle = aAngle;
    }

    @Override
    public String toString()
    {
        return "Coordinate{" + "position=" + mPosition + ", angle=" + mAngle + '}';
    }
    
    

}
