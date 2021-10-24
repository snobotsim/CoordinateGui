package org.snobot.nt.ramsete_plotter;

import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.model.Velocity;

import java.util.ArrayList;
import java.util.List;

public final class RamsetePlotDeserializer
{
    private RamsetePlotDeserializer()
    {

    }

    /**
     * Deserializes a measured point.
     *
     * @param aSerialized The serialized string
     * @param aDistanceUnit The distance unit
     * @param aVelocityUnit The velocity unit
     * @return The deserialized point
     */
    public static RamseteInstantaneousPoint deserializeInstantaneousPoint(double[] aSerialized, Distance.Unit aDistanceUnit, Velocity.Unit aVelocityUnit)
    {
        int dataPtr = 0;

        double time = aSerialized[dataPtr++];
        double x = aSerialized[dataPtr++];
        double y = aSerialized[dataPtr++];
        double heading = aSerialized[dataPtr++];
        double idealLeft = aSerialized[dataPtr++];
        double idealRight = aSerialized[dataPtr++];
        double measuredLeft = aSerialized[dataPtr++];
        double measuredRight = aSerialized[dataPtr++]; // NOPMD(UnusedAssignment)

        return new RamseteInstantaneousPoint(time, heading, new Position2dDistance(x, y, aDistanceUnit),
            Velocity.from(idealLeft, aVelocityUnit), Velocity.from(idealRight, aVelocityUnit),
            Velocity.from(measuredLeft, aVelocityUnit), Velocity.from(measuredRight, aVelocityUnit));
    }

    /**
     * Deserializes ideal points.
     *
     * @param aIdealPoints The serialized data
     * @param aDistanceUnit The distance unit
     * @param aVelocityUnit The velocity unit
     * @return The deserialized message
     */
    public static List<RamsetePointInfo> deserializeIdealPoints(double[] aIdealPoints, Distance.Unit aDistanceUnit, Velocity.Unit aVelocityUnit)
    {


        List<RamsetePointInfo> points = new ArrayList<>();

        int dataPtr = 0;
        while (dataPtr < aIdealPoints.length)
        {
            points.add(deserializePoint(aIdealPoints, dataPtr, aDistanceUnit, aVelocityUnit));
            dataPtr += 5;
        }

        return points;
    }

    @SuppressWarnings("PMD")
    private static RamsetePointInfo deserializePoint(double[] aData, int aDataPtr, Distance.Unit aDistanceUnit, Velocity.Unit aVelocityUnit)
    {
        double time = aData[aDataPtr++];
        double velocity = aData[aDataPtr++];
        double x = aData[aDataPtr++];
        double y = aData[aDataPtr++];
        double heading = aData[aDataPtr++];

        return new RamsetePointInfo(time, Velocity.from(velocity, aVelocityUnit), new Position2dDistance(x, y, aDistanceUnit), heading);
    }
}
