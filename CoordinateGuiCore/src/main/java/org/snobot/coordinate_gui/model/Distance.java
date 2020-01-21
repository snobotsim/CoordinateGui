package org.snobot.coordinate_gui.model;

import java.util.Objects;

@SuppressWarnings("PMD.TooManyMethods")
public final class Distance
{
    public enum Unit
    {
        INCH, FEET, CENTIMETERS, METERS
    }

    private static final Unit DEFAULT_UNIT = Unit.FEET;

    private static double INCHES_PER_METER = 0.0254;
    private static double FEET_PER_METER = 0.3048;
    private static double CM_PER_METER = 1e-2;

    private final double mMeters;

    private Distance(double aMeters)
    {
        mMeters = aMeters;
    }

    //////////////////////////
    // Setup
    //////////////////////////

    /**
     * Creates a distance, using the specified unit.
     * 
     * @param aDistance
     *            The raw distance, in the units provided with the other
     *            argument
     * @param aUnit
     *            The enumerated unit
     * @return The new distance
     */
    public static Distance from(double aDistance, Unit aUnit)
    {
        switch (aUnit)
        {
        case CENTIMETERS:
            return fromCentimeters(aDistance);
        case FEET:
            return fromFeet(aDistance);
        case INCH:
            return fromInches(aDistance);
        case METERS:
            return fromMeters(aDistance);
        default:
            throw new RuntimeException("Unknown enumeration"); // NOPMD

        }
    }

    public static Distance fromInches(double aInches)
    {
        return new Distance(aInches * INCHES_PER_METER);
    }

    public static Distance fromFeet(double aFeet)
    {
        return new Distance(aFeet * FEET_PER_METER);
    }

    public static Distance fromCentimeters(double aCm)
    {
        return new Distance(aCm * CM_PER_METER);
    }

    public static Distance fromMeters(double aMeters)
    {
        return new Distance(aMeters);
    }

    public static double atan2d(Distance aX1, Distance aX2, Distance aY1, Distance aY2)
    {
        Unit conversionUnit = Unit.METERS;
        return Math.toDegrees(Math.atan2(aX1.as(conversionUnit) - aX2.as(conversionUnit), aY1.as(conversionUnit) - aY2.as(conversionUnit)));
    }

    //////////////////////
    // Getters
    //////////////////////
    public double asCentimeters()
    {
        return this.mMeters / CM_PER_METER;
    }

    public double asFeet()
    {
        return this.mMeters / FEET_PER_METER;
    }

    public double asInches()
    {
        return this.mMeters / INCHES_PER_METER;
    }

    public double asMeters()
    {
        return this.mMeters;
    }

    /**
     * Converts the distance to the given unit.
     * 
     * @param aUnit
     *            The enumerated unit
     * @return The raw distance
     */
    public double as(Unit aUnit)
    {
        switch (aUnit)
        {
        case CENTIMETERS:
            return asCentimeters();
        case FEET:
            return asFeet();
        case INCH:
            return asInches();
        case METERS:
            return asMeters();
        default:
            throw new RuntimeException("Unknown enumeration"); // NOPMD

        }
    }

    //////////////////////
    // Math
    //////////////////////

    public static Distance add(Distance aD1, Distance aD2)
    {
        return new Distance(aD1.mMeters + aD2.mMeters);
    }

    public static Distance subtract(Distance aD1, Distance aD2)
    {
        return new Distance(aD1.mMeters - aD2.mMeters);
    }

    public static Distance divide(Distance aDistance, double aDivisor)
    {
        return new Distance(aDistance.mMeters / aDivisor);
    }

    public static Distance mult(Distance aDistance, double aMult)
    {
        return new Distance(aDistance.mMeters * aMult);
    }

    public static Distance half_sub(Distance aLeft, Distance aHalf)
    {
        return new Distance(aLeft.mMeters - aHalf.mMeters / 2);
    }

    public static Distance half_add(Distance aLeft, Distance aHalf)
    {
        return new Distance(aLeft.mMeters + aHalf.mMeters / 2);
    }


    @Override
    public String toString()
    {
        return "Distance: (" + DEFAULT_UNIT + "): " + as(DEFAULT_UNIT);
    }

    public static boolean approxEqual(Distance aLeft, Distance aRight)
    {

        return Math.abs(aLeft.mMeters - aRight.mMeters) < 1e-6;
    }

    @Override
    public boolean equals(Object aObject)
    {
        if (this == aObject)
        {
            return true;
        }
        if (aObject == null || getClass() != aObject.getClass())
        {
            return false;
        }
        Distance distance = (Distance) aObject;
        return Math.abs(distance.mMeters - mMeters) < 1e-6;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(mMeters);
    }
}
