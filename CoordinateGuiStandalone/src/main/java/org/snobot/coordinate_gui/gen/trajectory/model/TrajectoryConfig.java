package org.snobot.coordinate_gui.gen.trajectory.model;

public class TrajectoryConfig
{
    public double mMaxVelocity;
    public double mMaxAcceleration;
    public boolean mIsBackwards;

    public TrajectoryConfig()
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
    public TrajectoryConfig(double aMaxVelocity, double aMaxAcceleration, boolean aIsBackwards)
    {
        mMaxVelocity = aMaxVelocity;
        mMaxAcceleration = aMaxAcceleration;
        mIsBackwards = aIsBackwards;
    }

}
