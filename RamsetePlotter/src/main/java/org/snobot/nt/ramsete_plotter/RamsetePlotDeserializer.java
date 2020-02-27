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
    public static RamseteInstantaneousPoint deserializeInstantaneousPoint(String aSerialized, Distance.Unit aDistanceUnit, Velocity.Unit aVelocityUnit)
    {
        StringTokenizer tokenizer = new StringTokenizer(aSerialized, ",");

        try
        {
            double time = Double.parseDouble(tokenizer.nextToken());
            double x = Double.parseDouble(tokenizer.nextToken());
            double y = Double.parseDouble(tokenizer.nextToken());
            double heading = Double.parseDouble(tokenizer.nextToken());
            double idealLeft = Double.parseDouble(tokenizer.nextToken());
            double idealRight = Double.parseDouble(tokenizer.nextToken());
            double measuredLeft = Double.parseDouble(tokenizer.nextToken());
            double measuredRight = Double.parseDouble(tokenizer.nextToken());

            return new RamseteInstantaneousPoint(time, heading, new Position2dDistance(x, y, aDistanceUnit),
                Velocity.from(idealLeft, aVelocityUnit), Velocity.from(idealRight, aVelocityUnit),
                Velocity.from(measuredLeft, aVelocityUnit), Velocity.from(measuredRight, aVelocityUnit));
        }
        catch (NumberFormatException ex)
        {
            sLOGGER.log(Level.ERROR, "", ex);
        }

        return null;
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
