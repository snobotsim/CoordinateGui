package org.snobot.coordinate_gui.model;

import java.util.Objects;

@SuppressWarnings("PMD.TooManyMethods")
public final class Velocity
{
    public enum Unit
    {
        INCH_PER_SEC, FEET_PER_SEC, CENTIMETERS_PER_SEC, METERS_PER_SEC
    }

    private static final Unit DEFAULT_UNIT = Unit.FEET_PER_SEC;

    private static double INCHES_PER_METER = 0.0254;
    private static double FEET_PER_METER = 0.3048;
    private static double CM_PER_METER = 1e-2;

    private final double mMetersPerSec;

    private Velocity(double aMetersPerSec)
    {
        mMetersPerSec = aMetersPerSec;
    }

    //////////////////////////
    // Setup
    //////////////////////////

    /**
     * Creates a velocity, using the specified unit.
     * 
     * @param aVelocity
     *            The raw velocity, in the units provided with the other
     *            argument
     * @param aUnit
     *            The enumerated unit
     * @return The new distance
     */
    public static Velocity from(double aVelocity, Unit aUnit)
    {
        switch (aUnit)
        {
        case CENTIMETERS_PER_SEC:
            return fromCentimeters(aVelocity);
        case FEET_PER_SEC:
            return fromFeet(aVelocity);
        case INCH_PER_SEC:
            return fromInches(aVelocity);
        case METERS_PER_SEC:
            return fromMeters(aVelocity);
        default:
            throw new RuntimeException("Unknown enumeration"); // NOPMD

        }
    }

    public static Velocity fromInches(double aInches)
    {
        return new Velocity(aInches * INCHES_PER_METER);
    }

    public static Velocity fromFeet(double aFeet)
    {
        return new Velocity(aFeet * FEET_PER_METER);
    }

    public static Velocity fromCentimeters(double aCm)
    {
        return new Velocity(aCm * CM_PER_METER);
    }

    public static Velocity fromMeters(double aMeters)
    {
        return new Velocity(aMeters);
    }

    //////////////////////
    // Getters
    //////////////////////
    public double asCentimeters()
    {
        return this.mMetersPerSec / CM_PER_METER;
    }

    public double asFeet()
    {
        return this.mMetersPerSec / FEET_PER_METER;
    }

    public double asInches()
    {
        return this.mMetersPerSec / INCHES_PER_METER;
    }

    public double asMeters()
    {
        return this.mMetersPerSec;
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
        case CENTIMETERS_PER_SEC:
            return asCentimeters();
        case FEET_PER_SEC:
            return asFeet();
        case INCH_PER_SEC:
            return asInches();
        case METERS_PER_SEC:
            return asMeters();
        default:
            throw new RuntimeException("Unknown enumeration"); // NOPMD

        }
    }

    @Override
    public String toString()
    {
        return "Velocity: (" + DEFAULT_UNIT + "): " + as(DEFAULT_UNIT);
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
        Velocity velocity = (Velocity) aObject;
        return Math.abs(velocity.mMetersPerSec - mMetersPerSec) < 1e-6;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(mMetersPerSec);
    }
}
