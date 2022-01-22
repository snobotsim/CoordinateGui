package org.snobot.coordinate_gui.gen.pure_pursuit.model;

public class PurePursuitConfig
{
    public double mUpSampleSpacing;
    public double mTurnFactor;

    public PurePursuitConfig()
    {

    }

    /**
     * Constructor.
     *
     * @param aUpSampleSpacing
     *            The spacing between up-samples
     * @param aTurnSmoothing
     *            The smoothing to apply around curves
     */
    public PurePursuitConfig(double aUpSampleSpacing, double aTurnSmoothing)
    {
        mUpSampleSpacing = aUpSampleSpacing;
        mTurnFactor = aTurnSmoothing;
    }

}
