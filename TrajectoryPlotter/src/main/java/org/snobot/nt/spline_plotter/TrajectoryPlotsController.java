package org.snobot.nt.spline_plotter;

import java.util.ArrayList;
import java.util.List;

import org.snobot.nt.spline_plotter.plot_groups.TrajectoryPositionOverviewController;
import org.snobot.nt.spline_plotter.plots.TrajectoryErrorGraphController;
import org.snobot.nt.spline_plotter.plots.TrajectoryHeadingGraphController;
import org.snobot.nt.spline_plotter.plots.TrajectoryWheelGraphController;
import org.snobot.nt.spline_plotter.plots.TrajectoryXYPositionGraphController;

import javafx.fxml.FXML;

public class TrajectoryPlotsController
{

    private final List<TrajectoryWheelGraphController> mLeftWheelPositionGraphControllers;
    private final List<TrajectoryWheelGraphController> mRightWheelPositionGraphControllers;
    private final List<TrajectoryHeadingGraphController> mHeadingGraphControllers;
    private final List<TrajectoryXYPositionGraphController> mXyGraphControllers;

    @FXML
    private TrajectoryPositionOverviewController mPositionOverviewController;

    @FXML
    private TrajectoryWheelGraphController mLeftWheelController;

    @FXML
    private TrajectoryWheelGraphController mRightWheelController;

    @FXML
    private TrajectoryHeadingGraphController mHeadingController;

    @FXML
    private TrajectoryXYPositionGraphController mXYController;

    @FXML
    private TrajectoryErrorGraphController mLeftWheelErrorController;

    @FXML
    private TrajectoryErrorGraphController mRightWheelErrorController;

    @FXML
    private TrajectoryErrorGraphController mHeadingErrorController;

    /**
     * Constructor.
     */
    public TrajectoryPlotsController()
    {
        mLeftWheelPositionGraphControllers = new ArrayList<>();
        mRightWheelPositionGraphControllers = new ArrayList<>();
        mHeadingGraphControllers = new ArrayList<>();
        mXyGraphControllers = new ArrayList<>();
    }

    /**
     * Called when the javafx view is initialized.
     */
    @FXML
    public void initialize()
    {
        mLeftWheelPositionGraphControllers.add(mPositionOverviewController.mLeftWheelGraphController);
        mRightWheelPositionGraphControllers.add(mPositionOverviewController.mRightWheelGraphController);
        mHeadingGraphControllers.add(mPositionOverviewController.mHeadingGraphController);
        mXyGraphControllers.add(mPositionOverviewController.mXYPositionGraphController);

        mLeftWheelPositionGraphControllers.add(mLeftWheelController);
        mRightWheelPositionGraphControllers.add(mRightWheelController);
        mHeadingGraphControllers.add(mHeadingController);
        mXyGraphControllers.add(mXYController);
    }

    /**
     * Sets the spline segments.
     *
     * @param aPathPoints
     *            The segments
     */
    public void setPath(List<SplineSegment> aPathPoints)
    {
        List<Double> leftPos = new ArrayList<>();
        List<Double> leftVel = new ArrayList<>();
        List<Double> rightPos = new ArrayList<>();
        List<Double> rightVel = new ArrayList<>();
        List<Double> heading = new ArrayList<>();
        List<Double> xPosition = new ArrayList<>();
        List<Double> yPosition = new ArrayList<>();

        for (SplineSegment point : aPathPoints)
        {
            leftPos.add(point.mLeftSidePosition);
            leftVel.add(point.mLeftSideVelocity);
            rightPos.add(point.mRightSidePosition);
            rightVel.add(point.mRightSideVelocity);
            heading.add(point.mRobotHeading);
            xPosition.add(point.mAverageX);
            yPosition.add(point.mAverageY);
        }

        mLeftWheelErrorController.setIdeal(leftVel);
        mRightWheelErrorController.setIdeal(rightVel);
        mHeadingErrorController.setIdeal(heading);

        // Pass it on down
        for (TrajectoryWheelGraphController controller : mLeftWheelPositionGraphControllers)
        {
            controller.setPath(leftPos, leftVel);
        }

        for (TrajectoryWheelGraphController controller : mRightWheelPositionGraphControllers)
        {
            controller.setPath(rightPos, rightVel);
        }

        for (TrajectoryHeadingGraphController controller : mHeadingGraphControllers)
        {
            controller.setPath(heading);
        }

        for (TrajectoryXYPositionGraphController controller : mXyGraphControllers)
        {
            controller.setPath(xPosition, yPosition);
        }
    }

    /**
     * Sets a measured point.
     *
     * @param aIndex
     *            The index of this measurement
     * @param aSplineSegment
     *            The measurement
     */
    public void setPoint(int aIndex, SplineSegment aSplineSegment)
    {

        // Pass it on down
        for (TrajectoryWheelGraphController controller : mLeftWheelPositionGraphControllers)
        {
            controller.setPoint(aIndex, aSplineSegment.mLeftSidePosition, aSplineSegment.mLeftSideVelocity);
        }

        for (TrajectoryWheelGraphController controller : mRightWheelPositionGraphControllers)
        {
            controller.setPoint(aIndex, aSplineSegment.mRightSidePosition, aSplineSegment.mRightSideVelocity);
        }

        for (TrajectoryHeadingGraphController controller : mHeadingGraphControllers)
        {
            controller.setPoint(aIndex, aSplineSegment.mRobotHeading);
        }

        for (TrajectoryXYPositionGraphController controller : mXyGraphControllers)
        {
            controller.setPoint(aSplineSegment.mAverageX, aSplineSegment.mAverageY);
        }

        mLeftWheelErrorController.setActual(aIndex, aSplineSegment.mLeftSideVelocity);
        mRightWheelErrorController.setActual(aIndex, aSplineSegment.mRightSideVelocity);
        mHeadingErrorController.setActual(aIndex, aSplineSegment.mRobotHeading);
    }

    /**
     * Clears the actual values, given by the robot.
     */
    public void clearActuals()
    {
        for (TrajectoryWheelGraphController controller : mLeftWheelPositionGraphControllers)
        {
            controller.clearActuals();
        }

        for (TrajectoryWheelGraphController controller : mRightWheelPositionGraphControllers)
        {
            controller.clearActuals();
        }

        for (TrajectoryHeadingGraphController controller : mHeadingGraphControllers)
        {
            controller.clearActuals();
        }

        for (TrajectoryXYPositionGraphController controller : mXyGraphControllers)
        {
            controller.clearActuals();
        }

        mLeftWheelErrorController.clearActuals();
        mRightWheelErrorController.clearActuals();
        mHeadingErrorController.clearActuals();
    }

}
