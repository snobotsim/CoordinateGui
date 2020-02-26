package org.snobot.nt.ramsete_plotter;

import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.model.Velocity;

@SuppressWarnings("PMD.DataClass")
public class RamsetePointInfo
{
    public final double mTime;
    public final Velocity mVelocity;
    public final Position2dDistance mPosition;
    public final double mHeading;

    public RamsetePointInfo(double aTime, Velocity aVelocity, Position2dDistance aPosition, double aHeading)
    {
        mTime = aTime;
        mVelocity = aVelocity;
        mPosition = aPosition;
        mHeading = aHeading;
    }

    @Override
    public String toString()
    {
        return "RamsetePointInfo{" +
            "mTime=" + mTime +
            ", mVelocity=" + mVelocity +
            ", mPosition=" + mPosition +
            ", mHeading=" + mHeading +
            '}';
    }
}
