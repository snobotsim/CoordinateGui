package org.snobot.nt.spline_plotter;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;

public class TrajectoryGraphOverviewController
{
    @FXML
    private TrajectoryWheelGraphController mLeftWheelGraphController;

    @FXML
    private TrajectoryWheelGraphController mRightWheelGraphController;

    @FXML
    private TrajectoryHeadingGraphController mHeadingGraphController;

    @FXML
    private TrajectoryXYPositionGraphController mXYPositionGraphController;

    /**
     * Sets the spline segments.
     * 
     * @param aPathPoints
     *            The segments
     */
    public void setPath(List<SplineSegment> aPathPoints)
    {
        List<Double> leftPos = new ArrayList<Double>();
        List<Double> leftVel = new ArrayList<Double>();
        List<Double> rightPos = new ArrayList<Double>();
        List<Double> rightVel = new ArrayList<Double>();
        List<Double> heading = new ArrayList<Double>();
        List<Double> xPosition = new ArrayList<Double>();
        List<Double> yPosition = new ArrayList<Double>();

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

        mLeftWheelGraphController.setPath(leftPos, leftVel);
        mRightWheelGraphController.setPath(rightPos, rightVel);
        mHeadingGraphController.setPath(heading);
        mXYPositionGraphController.setPath(xPosition, yPosition);
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
        mLeftWheelGraphController.setPoint(aIndex, aSplineSegment.mLeftSidePosition, aSplineSegment.mLeftSideVelocity);
        mRightWheelGraphController.setPoint(aIndex, aSplineSegment.mRightSidePosition, aSplineSegment.mRightSideVelocity);
        mHeadingGraphController.setPoint(aIndex, aSplineSegment.mRobotHeading);
        mXYPositionGraphController.setPoint(aSplineSegment.mAverageX, aSplineSegment.mAverageY);
    }

    /**
     * Clears the actual/real measured values.
     */
    public void clearActuals()
    {
        mLeftWheelGraphController.clearActuals();
        mRightWheelGraphController.clearActuals();
        mHeadingGraphController.clearActuals();
        mXYPositionGraphController.clearActuals();
    }
}
