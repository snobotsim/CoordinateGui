package org.snobot.coordinate_gui.trajectory_gen.model;

public class PathConfig
{
    public double mMaxVelocity;
    public double mMaxAcceleration;
    public boolean mIsBackwards;

    public PathConfig()
    {

    }

    /**
     * Constructor.
     * 
     * @param aMaxVelocity
     *            The maximum velocity, in feet/sec
     * @param aMaxAcceleration
     *            The maximum acceleration, in feet/sec/sec
     * @param aIsBackwards
     *            If the path is going backwards
     */
    public PathConfig(double aMaxVelocity, double aMaxAcceleration, boolean aIsBackwards)
    {
        mMaxVelocity = aMaxVelocity;
        mMaxAcceleration = aMaxAcceleration;
        mIsBackwards = aIsBackwards;
    }

}
