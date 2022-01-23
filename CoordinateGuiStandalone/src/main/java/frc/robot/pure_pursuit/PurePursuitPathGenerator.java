package frc.robot.pure_pursuit;

import java.util.ArrayList;
import java.util.List;

public class PurePursuitPathGenerator
{
    private final List<PurePursuitWaypoint> mUpsampled;
    private final List<PurePursuitWaypoint> mSmoothed;

    /**
     * Constructor.
     *
     * @param aWaypoints
     *            The original waypoints to use
     * @param aSpacing
     *            The spacing for up-sampling
     * @param aWeightSmooth
     *            The smoothing factor around turns
     * @param aTolerance
     *            The tolerance used for the gradient descent smoothing step
     */
    public PurePursuitPathGenerator(List<PurePursuitWaypoint> aWaypoints, double aSpacing, double aWeightSmooth, double aTolerance)
    {
        if (aWaypoints.size() < 2) // NOPMD
        {
            throw new IllegalArgumentException("Need to supply at least two points");
        }

        mUpsampled = upSamplePath(aWaypoints, aSpacing);
        mSmoothed = smoothPoints(mUpsampled, aWeightSmooth, aTolerance);
    }

    private List<PurePursuitWaypoint> upSamplePath(List<PurePursuitWaypoint> aInput, double aSpacing)
    {
        List<PurePursuitWaypoint> output = new ArrayList<>();

        for (int i = 0; i < aInput.size() - 1; ++i)
        {
            Vector2D start = aInput.get(i).asVector();
            Vector2D end = aInput.get(i + 1).asVector();
            Vector2D vector = Vector2D.subtract(end, start);

            double numPoints = vector.magnitude() / aSpacing;
            Vector2D unitVector = Vector2D.normalize(vector);

            for (int j = 0; j < numPoints; ++j)
            {
                Vector2D newPoint = Vector2D.multiply(unitVector, j * aSpacing);
                newPoint.add(start);
                output.add(new PurePursuitWaypoint(newPoint));
            }
        }

        output.add(aInput.get(aInput.size() - 1));

        return output;
    }

    private List<PurePursuitWaypoint> smoothPoints(List<PurePursuitWaypoint> aInput, double aWeightSmooth, double aTolerance)
    {
        double a = 1 - aWeightSmooth;
        double b = aWeightSmooth;

        List<PurePursuitWaypoint> output = new ArrayList<>(aInput.size());
        for (PurePursuitWaypoint waypoint : aInput)
        {
            output.add(new PurePursuitWaypoint(waypoint));
        }

        int loops = 0;
        double change = aTolerance;
        while (change >= aTolerance)
        {
            ++loops;
            change = 0.0;
            for (int i = 1; i < aInput.size() - 1; ++i)
            {
                PurePursuitWaypoint originalPoint = aInput.get(i);
                PurePursuitWaypoint currentPoint = output.get(i);
                PurePursuitWaypoint previousPoint = output.get(i - 1);
                PurePursuitWaypoint nextPoint = output.get(i + 1);

                double newX = currentPoint.mX
                        + (a * (originalPoint.mX - currentPoint.mX) + b * (previousPoint.mX + nextPoint.mX - 2 * currentPoint.mX));
                double newY = currentPoint.mY
                        + (a * (originalPoint.mY - currentPoint.mY) + b * (previousPoint.mY + nextPoint.mY - 2 * currentPoint.mY));

                change += Math.abs(currentPoint.mX - newX);
                change += Math.abs(currentPoint.mY - newY);

                currentPoint.mX = newX;
                currentPoint.mY = newY;
            }
            // break;
        }

        return output;
    }

    public List<PurePursuitWaypoint> getUpsampled()
    {
        return mUpsampled;
    }

    public List<PurePursuitWaypoint> getSmoothed()
    {
        return mSmoothed;
    }

}
