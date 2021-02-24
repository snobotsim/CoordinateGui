package org.snobot.nt.ramsete_plotter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.model.Velocity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class RamsetePlotDeserializer
{
    private static final Logger sLOGGER = LogManager.getLogger(RamsetePlotDeserializer.class);

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
        double measuredRight = aSerialized[dataPtr++];

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
    public static List<RamsetePointInfo> deserializeIdealPoints(String aIdealPoints, Distance.Unit aDistanceUnit, Velocity.Unit aVelocityUnit)
    {

        List<RamsetePointInfo> points = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(aIdealPoints, ",");

        while (tokenizer.hasMoreElements())
        {
            RamsetePointInfo segment = RamsetePlotDeserializer.deserializePoint(tokenizer, aDistanceUnit, aVelocityUnit);
            if (segment != null)
            {
                points.add(segment);
            }
        }

        return points;
    }

    private static RamsetePointInfo deserializePoint(StringTokenizer aTokenizer, Distance.Unit aDistanceUnit, Velocity.Unit aVelocityUnit)
    {
        try
        {
            double time = Double.parseDouble(aTokenizer.nextToken());
            double velocity = Double.parseDouble(aTokenizer.nextToken());
            double x = Double.parseDouble(aTokenizer.nextToken());
            double y = Double.parseDouble(aTokenizer.nextToken());
            double heading = Double.parseDouble(aTokenizer.nextToken());

            return new RamsetePointInfo(time, Velocity.from(velocity, aVelocityUnit), new Position2dDistance(x, y, aDistanceUnit), heading);
        }
        catch (NumberFormatException ex)
        {
            sLOGGER.log(Level.ERROR, "", ex);
        }

        return null;
    }
}
