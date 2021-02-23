package org.snobot.coordinate_gui.shuffleboard.widgets;

import java.util.ArrayList;
import java.util.List;

import org.snobot.nt.spline_plotter.SplineSegment;

/**
 * Serializes a 2D cubic spline into a string, to be used by the SmartDashboard.
 * 
 * @author PJ
 *
 */
public final class IdealSplineSerializer
{
    /**
     * Constructor, private because the static functions should be used.
     */
    private IdealSplineSerializer()
    {

    }

    /**
     * De-Serializes a list of spline segments from the given string.
     * 
     * @param aData
     *            The string to de-serialize
     * 
     * @return The path to drive
     */
    public static List<SplineSegment> deserializePath(double[] aData)
    {
        List<SplineSegment> points = new ArrayList<>();

        int dataPtr = 0;
        while (dataPtr < aData.length)
        {
            SplineSegment segment = deserializePathPoint(aData, dataPtr);
            points.add(segment);
            dataPtr += 7;
        }

        return points;
    }

    /**
     * Serializes a path of spline points into a string.
     * 
     * @param aPoints
     *            The list of points
     * 
     * @return The serialized string
     */
    public static String serializePath(List<SplineSegment> aPoints)
    {
        StringBuilder output = new StringBuilder();

        for (SplineSegment point : aPoints)
        {
            output.append(serializePathPoint(point));
        }

        return output.toString();
    }

    /**
     * Serializes a single spline point.
     * 
     * @param aPoint
     *            The piont to serialize
     * 
     * @return The serialized point
     */
    public static String serializePathPoint(SplineSegment aPoint)
    {
        return aPoint.mLeftSidePosition + ", "
                + aPoint.mLeftSideVelocity + ", "
                + aPoint.mRightSidePosition + ", "
                + aPoint.mRightSideVelocity + ", "
                + aPoint.mRobotHeading + ","
                + aPoint.mAverageX + ","
                + aPoint.mAverageY + ",";
    }

    /**
     * De-serializes a spline point from the given string.
     * 
     * @param aData
     *            The data
     * 
     * @return The de-serialized point
     */
    @SuppressWarnings("PMD.AvoidReassigningParameters")
    public static SplineSegment deserializePathPoint(double[] aData, int aDataPtr)
    {
        SplineSegment point = new SplineSegment();

        point.mLeftSidePosition = aData[aDataPtr++];
        point.mLeftSideVelocity = aData[aDataPtr++];
        point.mRightSidePosition = aData[aDataPtr++];
        point.mRightSideVelocity = aData[aDataPtr++];
        point.mRobotHeading = aData[aDataPtr++];
        point.mAverageX = aData[aDataPtr++];
        point.mAverageY = aData[aDataPtr++];


        return point;
    }
}
