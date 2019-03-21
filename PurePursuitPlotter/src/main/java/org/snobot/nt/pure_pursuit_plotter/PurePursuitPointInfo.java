package org.snobot.nt.pure_pursuit_plotter;

@SuppressWarnings("PMD.DataClass")
public class PurePursuitPointInfo
{
    public final int mIndex;
    public double mX;
    public double mY;
    public double mLeftVelocity;
    public double mLeftGoalVelocity;
    public double mRightVelocity;
    public double mRightGoalVelocity;

    public PurePursuitPointInfo(int aIndex)
    {
        mIndex = aIndex;
    }

    @Override
    public String toString()
    {
        return "PurePursuitPointInfo [mIndex=" + mIndex + ", mX=" + mX + ", mY=" + mY + ", mLeftVelocity=" + mLeftVelocity + ", mLeftGoalVelocity="
                + mLeftGoalVelocity + ", mRightVelocity=" + mRightVelocity + ", mRightGoalVelocity=" + mRightGoalVelocity + "]";
    }

}
