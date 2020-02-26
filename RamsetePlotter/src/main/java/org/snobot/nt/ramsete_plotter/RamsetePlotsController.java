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

    private double mLastMeasuredTime;

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

    /**
     * Sets the ideal points.
     *
     * @param aPathPoints The point data
     * @param aVelocityUnit The velocity unit represented in the data
     * @param aDistanceUnit The distance unit represented in the data
     */
    public void setIdeal(List<RamsetePointInfo> aPathPoints, Distance.Unit aDistanceUnit, Velocity.Unit aVelocityUnit)
    {
        mXYController.setIdeal(aPathPoints, aDistanceUnit);
        mLeftVelocityController.setIdeal(aPathPoints, aVelocityUnit);
        mRightVelocityController.setIdeal(aPathPoints, aVelocityUnit);
        mHeadingController.setIdeal(aPathPoints);
    }

    /**
     * Addes a measured point.
     *
     * @param aActual The point data
     * @param aVelocityUnit The velocity unit represented in the data
     * @param aDistanceUnit The distance unit represented in the data
     */
    public void addPoint(RamseteInstantaneousPoint aActual, Distance.Unit aDistanceUnit, Velocity.Unit aVelocityUnit)
    {
        mXYController.setActual(aActual, aDistanceUnit);
        mLeftVelocityController.setActual(aActual.mTime, aActual.mIdealLeftVelocity, aActual.mMeasuredLeftVelocity, aVelocityUnit);
        mRightVelocityController.setActual(aActual.mTime, aActual.mIdealRightVelocity, aActual.mMeasuredRightVelocity, aVelocityUnit);
        mHeadingController.setActual(aActual);
    }

    private void clear()
    {
        mXYController.clearActuals();
        mLeftVelocityController.clearActuals();
        mRightVelocityController.clearActuals();
        mHeadingController.clearActuals();
    }

    /**
     * Sets the ideal path for hte controller.
     * @param aIdealPoints The serialized data
     * @param aVelocityUnit The velocity unit represented in the data
     * @param aDistanceUnit The distance unit represented in the data
     */
    public void setPath(String aIdealPoints, Distance.Unit aDistanceUnit, Velocity.Unit aVelocityUnit)
    {
        List<RamsetePointInfo> points = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(aIdealPoints, ",");

        while (tokenizer.hasMoreElements())
        {
            RamsetePointInfo segment = deserializePoint(tokenizer, aDistanceUnit, aVelocityUnit);
            if (segment != null)
            {
                points.add(segment);
            }
        }

        setIdeal(points, aDistanceUnit, aVelocityUnit);
    }

    /**
     * Adds a point measured by the robot.
     * @param aRealPoints The serialized data
     * @param aVelocityUnit The velocity unit represented in the data
     * @param aDistanceUnit The distance unit represented in the data
     */
    public void addMeasuredPoint(String aRealPoints, Distance.Unit aDistanceUnit, Velocity.Unit aVelocityUnit)
    {
        StringTokenizer tokenizer = new StringTokenizer(aRealPoints, ",");
        RamseteInstantaneousPoint point = deserializeInsteantaneousPoint(tokenizer, aDistanceUnit, aVelocityUnit);

        if (point.mTime == 0 || point.mTime < mLastMeasuredTime)
        {
            clear();
        }
        mLastMeasuredTime = point.mTime;

        addPoint(point, aDistanceUnit, aVelocityUnit);
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
