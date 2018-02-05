package org.snobot.coordinate_gui;

import java.awt.Color;
import java.util.List;

import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.DataProvider;
import org.snobot.coordinate_gui.ui.layers.CoordinateLayer;
import org.snobot.coordinate_gui.ui.layers.FieldImageLayer;
import org.snobot.coordinate_gui.ui.layers.MovementArrowLayer;
import org.snobot.coordinate_gui.ui.layers.RobotLayer;
import org.snobot.coordinate_gui.ui.render_props.CoordinateLayerRenderProps;
import org.snobot.coordinate_gui.ui.render_props.MovementArrowLayerRenderProps;
import org.snobot.coordinate_gui.ui.render_props.RobotLayerRenderProps;

public class BaseYearSpecificGui extends BaseCoordinateGui
{

    protected final DataProvider<Coordinate> mTrajectoryDataProvider;
    protected final DataProvider<Coordinate> mTrajectoryWaypointDataProvider;
    protected final DataProvider<Coordinate> mStartAndStopPositionDataProvider;

    /**
     * Constructor.
     * 
     * @param aFieldImagePath
     *            A path (URL based) to the field image
     * @param aFieldWidth
     *            The width of the field
     * @param aFieldHeight
     *            The height of the field
     * @param aCenterPointX
     *            The choosen point for the center of the field (0, 0) point
     * @param aCenterPointY
     *            The choosen point for the center of the field (0, 0) point
     * @param aRobotWidth
     *            The width of the robot
     * @param aRobotHeight
     *            The height of the robot
     */
    public BaseYearSpecificGui(
            String aFieldImagePath, 
            double aFieldWidth, double aFieldHeight, 
            double aCenterPointX, double aCenterPointY, 
            double aRobotWidth, double aRobotHeight)
    {
        super(aCenterPointX, aCenterPointY);

        CoordinateLayerRenderProps trajectoryLayerRenderProps = new CoordinateLayerRenderProps();
        trajectoryLayerRenderProps.setFadeOverTime(false);
        trajectoryLayerRenderProps.setPointSize(5);
        trajectoryLayerRenderProps.setPointMemory(-1);
        trajectoryLayerRenderProps.setPointColor(Color.red);

        CoordinateLayerRenderProps startAndStopLayerRenderProps = new CoordinateLayerRenderProps();
        startAndStopLayerRenderProps.setFadeOverTime(false);
        startAndStopLayerRenderProps.setPointMemory(2);
        startAndStopLayerRenderProps.setPointColor(Color.orange);

        mTrajectoryDataProvider = new DataProvider<>();
        mStartAndStopPositionDataProvider = new DataProvider<>();
        mTrajectoryWaypointDataProvider = new DataProvider<>();

        CoordinateLayerRenderProps coordinateLayerRenderProps = new CoordinateLayerRenderProps();
        RobotLayerRenderProps robotLayerRenderProps = new RobotLayerRenderProps();

        FieldImageLayer fieldLayer = new FieldImageLayer(aFieldImagePath, mConverter, aFieldWidth, aFieldHeight);
        RobotLayer robotLayer = new RobotLayer(mCoordinateDataProvider, robotLayerRenderProps, mConverter, aRobotWidth, aRobotHeight);
        CoordinateLayer coordinateLayer = new CoordinateLayer(mCoordinateDataProvider, coordinateLayerRenderProps, mConverter);
        CoordinateLayer trajectoryLayer = new CoordinateLayer(mTrajectoryDataProvider, trajectoryLayerRenderProps, mConverter);
        CoordinateLayer startAndStopLayer = new CoordinateLayer(mStartAndStopPositionDataProvider, startAndStopLayerRenderProps, mConverter);
        MovementArrowLayer movementLayer = new MovementArrowLayer(mTrajectoryWaypointDataProvider, new MovementArrowLayerRenderProps(), mConverter);

        mLayerManager.addLayer(fieldLayer);
        mLayerManager.addLayer(robotLayer);
        mLayerManager.addLayer(coordinateLayer);
        mLayerManager.addLayer(trajectoryLayer);
        mLayerManager.addLayer(startAndStopLayer);
        mLayerManager.addLayer(movementLayer);
    }

    /**
     * Sets a trajectory path.
     * 
     * @param aSegments
     *            The path
     */
    public void setPath(List<Coordinate> aSegments)
    {
        synchronized (mDataLock)
        {
            mTrajectoryDataProvider.clear();
            for (Coordinate coord : aSegments)
            {
                mTrajectoryDataProvider.addData(coord);
            }
        }
        mLayerManager.repaint();
    }

    /**
     * Sets the waypoints.
     * 
     * @param aWaypoints
     *            The list of waypoints
     */
    public void setWaypoints(List<Coordinate> aWaypoints)
    {
        synchronized (mDataLock)
        {
            mTrajectoryWaypointDataProvider.clear();
            for (Coordinate coord : aWaypoints)
            {
                mTrajectoryWaypointDataProvider.addData(coord);
            }
        }
        mLayerManager.repaint();
    }

    /**
     * Sets the two points for a "go to position" mode.
     * 
     * @param aStart
     *            The starting position
     * @param aEnd
     *            The ending position
     */
    public void setStartAndStopPoints(Coordinate aStart, Coordinate aEnd)
    {
        synchronized (mDataLock)
        {
            mStartAndStopPositionDataProvider.clear();
            mStartAndStopPositionDataProvider.addData(aStart);
            mStartAndStopPositionDataProvider.addData(aEnd);
        }
        mLayerManager.repaint();
    }

    public DataProvider<Coordinate> getTrajectoryDataProvider()
    {
        return mTrajectoryDataProvider;
    }

    public DataProvider<Coordinate> getTrajectoryWaypointDataProvider()
    {
        return mTrajectoryWaypointDataProvider;
    }

}
