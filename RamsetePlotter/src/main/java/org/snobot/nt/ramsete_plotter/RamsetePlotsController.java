package org.snobot.nt.ramsete_plotter;

import javafx.fxml.FXML;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.model.Velocity;
import org.snobot.nt.ramsete_plotter.plots.RamseteHeadingGraphController;
import org.snobot.nt.ramsete_plotter.plots.RamseteVelocityGraphController;
import org.snobot.nt.ramsete_plotter.plots.RamseteXYPositionGraphController;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RamsetePlotsController
{
    private static final Logger sLOGGER = LogManager.getLogger(RamsetePlotsController.class);

    @FXML
    private RamseteXYPositionGraphController mXYController;
    @FXML
    private RamseteVelocityGraphController mLeftVelocityController;
    @FXML
    private RamseteVelocityGraphController mRightVelocityController;
    @FXML
    private RamseteHeadingGraphController mHeadingController;

    /**
     * Clears the actuals from the plots.
     */
    public void clearActuals()
    {
        mXYController.clearActuals();
        mLeftVelocityController.clearActuals();
        mRightVelocityController.clearActuals();
        mHeadingController.clearActuals();
    }

    public void setIdeal(List<RamsetePointInfo> pathPoints, Distance.Unit aDistanceUnit, Velocity.Unit aVelocityUnit)
    {
        mXYController.setIdeal(pathPoints, aDistanceUnit);
        mLeftVelocityController.setIdeal(pathPoints, aVelocityUnit);
        mRightVelocityController.setIdeal(pathPoints, aVelocityUnit);
        mHeadingController.setIdeal(pathPoints);
    }

    public void addPoint(RamseteInstantaneousPoint aActual, Distance.Unit aDistanceUnit, Velocity.Unit aVelocityUnit)
    {
        mXYController.setActual(aActual, aDistanceUnit);
        mLeftVelocityController.setActual(aActual.mTime, aActual.mIdealLeftVelocity, aActual.mMeasuredLeftVelocity, aVelocityUnit);
        mRightVelocityController.setActual(aActual.mTime, aActual.mIdealRightVelocity, aActual.mMeasuredRightVelocity, aVelocityUnit);
        mHeadingController.setActual(aActual);
    }

    public void clear()
    {
        mXYController.clearActuals();
        mLeftVelocityController.clearActuals();
        mRightVelocityController.clearActuals();
        mHeadingController.clearActuals();
    }

    public void setPath(String idealPoints, Distance.Unit decodeUnit, Velocity.Unit aVelocityUnit)
    {
        List<RamsetePointInfo> points = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(idealPoints, ",");

        while (tokenizer.hasMoreElements())
        {
            RamsetePointInfo segment = deserializePoint(tokenizer, decodeUnit, aVelocityUnit);
            if(segment != null)
            {
                points.add(segment);
            }
        }

        setIdeal(points, decodeUnit, aVelocityUnit);
    }

    public void addMeasuredPoint(String realPoints, Distance.Unit aDistanceUnit, Velocity.Unit aVelocityUnit)
    {
        StringTokenizer tokenizer = new StringTokenizer(realPoints, ",");
        RamseteInstantaneousPoint point = deserializeInsteantaneousPoint(tokenizer, aDistanceUnit, aVelocityUnit);
        System.out.println("Adding measured " + point);
        if(point.mTime < .03)
        {
            clear();
        }
        else
        {
            addPoint(point, aDistanceUnit, aVelocityUnit);
        }
    }

    private RamseteInstantaneousPoint deserializeInsteantaneousPoint(StringTokenizer aTokenizer, Distance.Unit aDistanceUnit, Velocity.Unit aVelocityUnit)
    {
        try
        {
            double time = Double.parseDouble(aTokenizer.nextToken());
            double x = Double.parseDouble(aTokenizer.nextToken());
            double y = Double.parseDouble(aTokenizer.nextToken());
            double heading = Double.parseDouble(aTokenizer.nextToken());
            double idealLeft = Double.parseDouble(aTokenizer.nextToken());
            double idealRight = Double.parseDouble(aTokenizer.nextToken());
            double measuredLeft = Double.parseDouble(aTokenizer.nextToken());
            double measuredRight = Double.parseDouble(aTokenizer.nextToken());

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

    private RamsetePointInfo deserializePoint(StringTokenizer aTokenizer, Distance.Unit aDistanceUnit, Velocity.Unit aVelocityUnit)
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