package frc.robot.pure_pursuit;

public class PurePursuitWaypoint
{
    public double mX;
    public double mY;

    private boolean mPreCalculatedValuesValid;
    private double mPrecalculatedVelocity;
    private double mPrecalculatedCurvature;

    public PurePursuitWaypoint()
    {

    }

    public PurePursuitWaypoint(double aX, double aY)
    {
        mX = aX;
        mY = aY;
    }

    public PurePursuitWaypoint(PurePursuitWaypoint aCopy)
    {
        mX = aCopy.mX;
        mY = aCopy.mY;
    }

    public PurePursuitWaypoint(Vector2D aVector)
    {
        mX = aVector.getX();
        mY = aVector.getY();
    }

    public Vector2D asVector()
    {
        return new Vector2D(mX, mY);
    }

    @Override
    public String toString()
    {
        String output = "PurePursuitWaypoint [mX=" + mX + ", mY=" + mY;

        if (mPreCalculatedValuesValid)
        {
            output += ", mPrecalculatedVelocity=" + mPrecalculatedVelocity;
            output += ", mPrecalculatedCurvature=" + mPrecalculatedCurvature;
        }

        output += "]";

        return output;
    }

    /**
     * Sets the pre-calculated values used for later processing. Not all
     * waypoints have this, just ones in a path.
     * 
     * @param aVelocity
     *            The velocity
     * @param aCurvature
     *            The curvature
     */
    public void setCalculatedValues(double aVelocity, double aCurvature)
    {
        mPreCalculatedValuesValid = true;
        mPrecalculatedVelocity = aVelocity;
        mPrecalculatedCurvature = aCurvature;
    }

    public double getPrecalculatedVelocity()
    {
        return mPrecalculatedVelocity;
    }

}
