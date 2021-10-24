package org.snobot.coordinate_gui.model;

import java.util.Objects;

@SuppressWarnings("PMD.TooManyMethods")
public final class Velocity
{
    public enum Unit
    {
        InchPerSec, FeetPerSec, CentimetersPerSec, MetersPerSec
    }

    private static final Unit DEFAULT_UNIT = Unit.FeetPerSec;

    private static final double INCHES_PER_METER = 0.0254;
    private static final double FEET_PER_METER = 0.3048;
    private static final double CM_PER_METER = 1e-2;

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
        case CentimetersPerSec:
            return fromCentimeters(aVelocity);
        case FeetPerSec:
            return fromFeet(aVelocity);
        case InchPerSec:
            return fromInches(aVelocity);
        case MetersPerSec:
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
    public double as(Unit aUnit) // NOPMD(ShortMethodName)
    {
        switch (aUnit)
        {
        case CentimetersPerSec:
            return asCentimeters();
        case FeetPerSec:
            return asFeet();
        case InchPerSec:
            return asInches();
        case MetersPerSec:
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
