package org.snobot.nt.ramsete_plotter;

import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.model.Velocity;

@SuppressWarnings("PMD.DataClass")
public class RamseteInstantaneousPoint
{
    public final double mTime;
    public final Position2dDistance mMeasuredPosition;
    public final double mMeasuredHeading;
    public final Velocity mIdealLeftVelocity;
    public final Velocity mIdealRightVelocity;
    public final Velocity mMeasuredLeftVelocity;
    public final Velocity mMeasuredRightVelocity;

    /**
     * Constructor.
     *
     * @param aTime The time of the measurement
     * @param aHeading The heading
     * @param aPosition The position
     * @param aIdealLeftVelocity The ideal velocity for the left wheel
     * @param aIdealRightVelocity The ideal velocity for the right wheel
     * @param aMeasuredLeftVelocity The measured velocity for the left wheel
     * @param aMeasuredRightVelocity The measured velocity for the right wheel
     */
    public RamseteInstantaneousPoint(double aTime, double aHeading, Position2dDistance aPosition,
                            Velocity aIdealLeftVelocity, Velocity aIdealRightVelocity,
                            Velocity aMeasuredLeftVelocity, Velocity aMeasuredRightVelocity)
    {
        mTime = aTime;
        mMeasuredHeading = aHeading;
        mMeasuredPosition = aPosition;
        mIdealLeftVelocity = aIdealLeftVelocity;
        mIdealRightVelocity = aIdealRightVelocity;
        mMeasuredLeftVelocity = aMeasuredLeftVelocity;
        mMeasuredRightVelocity = aMeasuredRightVelocity;
    }
}
