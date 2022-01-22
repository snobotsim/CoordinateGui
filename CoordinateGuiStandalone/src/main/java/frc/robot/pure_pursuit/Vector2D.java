package frc.robot.pure_pursuit;

@SuppressWarnings("PMD.TooManyMethods")
public class Vector2D
{
    private double mX;
    private double mY;

    public Vector2D(double aX, double aY)
    {
        mX = aX;
        mY = aY;
    }

    public Vector2D(Vector2D aCopy)
    {
        mX = aCopy.mX;
        mY = aCopy.mY;
    }

    @Override
    public String toString()
    {
        return "Vector2D [mX=" + mX + ", mY=" + mY + "]";
    }

    /**
     * Calculates the normal vector for the input.
     *
     * @param aInput
     *            The vector to compute
     * @return The normal vector
     */
    public static Vector2D normalize(Vector2D aInput)
    {
        Vector2D output = new Vector2D(aInput);
        output.divide(output.magnitude());
        return output;
    }

    /**
     * Adds the two vectors together, returning a new vector as a result.
     *
     * @param aVector1
     *            The left vector
     * @param aVector2
     *            The right vector
     * @return The new result
     */
    public static Vector2D add(Vector2D aVector1, Vector2D aVector2)
    {
        Vector2D output = new Vector2D(aVector1);
        output.add(aVector2);
        return output;
    }

    public void add(Vector2D aOther)
    {
        mX += aOther.mX;
        mY += aOther.mY;
    }

    /**
     * Subtracts two vectors, returning a new vector as a result.
     *
     * @param aStart
     *            The left vector
     * @param aEnd
     *            The right vector
     * @return The result of subtraction
     */
    public static Vector2D subtract(Vector2D aStart, Vector2D aEnd)
    {
        Vector2D output = new Vector2D(aStart);
        output.subtract(aEnd);

        return output;
    }

    public void subtract(Vector2D aOther)
    {
        mX -= aOther.mX;
        mY -= aOther.mY;
    }

    /**
     * Multiplies the vector by the given scaler, returning a new vector.
     *
     * @param aInput
     *            The input vector
     * @param aMult
     *            The scaler
     * @return The new vector
     */
    public static Vector2D multiply(Vector2D aInput, double aMult)
    {
        Vector2D output = new Vector2D(aInput);
        output.multiply(aMult);
        return output;
    }

    public void multiply(double aMult)
    {
        mX *= aMult;
        mY *= aMult;
    }

    public static double distance(Vector2D aPathPoint, Vector2D aRobotPosition)
    {
        Vector2D difference = subtract(aPathPoint, aRobotPosition);
        return difference.magnitude();
    }

    public void divide(double aDivisor)
    {
        mX /= aDivisor;
        mY /= aDivisor;
    }


    public double magnitude()
    {
        return Math.sqrt(mX * mX + mY * mY);
    }

    public double dot(Vector2D aOther)
    {
        return mX * aOther.mX + mY * aOther.mY;
    }

    public double getX()
    {
        return mX;
    }

    public double getY()
    {
        return mY;
    }

}
