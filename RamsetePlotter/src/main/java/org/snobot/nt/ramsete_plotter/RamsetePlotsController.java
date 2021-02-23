package org.snobot.nt.ramsete_plotter;

import javafx.fxml.FXML;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Velocity;
import org.snobot.nt.ramsete_plotter.plots.RamseteHeadingGraphController;
import org.snobot.nt.ramsete_plotter.plots.RamseteVelocityGraphController;
import org.snobot.nt.ramsete_plotter.plots.RamseteXYPositionGraphController;

import java.util.List;

public class RamsetePlotsController
{

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
    public void setPath(List<RamsetePointInfo> aIdealPoints, Distance.Unit aDistanceUnit, Velocity.Unit aVelocityUnit)
    {
        setIdeal(aIdealPoints, aDistanceUnit, aVelocityUnit);
    }

    /**
     * Adds a point measured by the robot.
     * @param aPoint The serialized data
     * @param aVelocityUnit The velocity unit represented in the data
     * @param aDistanceUnit The distance unit represented in the data
     */
    public void addMeasuredPoint(RamseteInstantaneousPoint aPoint, Distance.Unit aDistanceUnit, Velocity.Unit aVelocityUnit)
    {
        if (aPoint.mTime == 0 || aPoint.mTime < mLastMeasuredTime)
        {
            clear();
        }
        mLastMeasuredTime = aPoint.mTime;

        addPoint(aPoint, aDistanceUnit, aVelocityUnit);
    }

}
