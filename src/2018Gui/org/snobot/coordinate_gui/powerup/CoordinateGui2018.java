package org.snobot.coordinate_gui.powerup;

import java.awt.Color;
import java.util.List;

import org.snobot.coordinate_gui.BaseCoordinateGui;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.DataProvider;
import org.snobot.coordinate_gui.ui.layers.CoordinateLayer;
import org.snobot.coordinate_gui.ui.layers.FieldImageLayer;
import org.snobot.coordinate_gui.ui.layers.RobotLayer;
import org.snobot.coordinate_gui.ui.renderProps.CoordinateLayerRenderProps;
import org.snobot.coordinate_gui.ui.renderProps.RobotLayerRenderProps;

public class CoordinateGui2018 extends BaseCoordinateGui
{
    private static final String FIELD_IMAGE_PATH = "/org/snobot/coordinate_gui/powerup/2018Field.png";

    private static final double FIELD_WIDTH = 27;
    private static final double FIELD_HEIGHT = 54;

    private static final double CENTER_POINT_X = 13.5;
    private static final double CENTER_POINT_Y = 27;

    private static final double ROBOT_WIDTH = 36 / 12.0;
    private static final double ROBOT_HEIGHT = 44 / 12.0;

    protected FieldImageLayer mFieldLayer;
    protected CoordinateLayer mCoordinateLayer;

    protected DataProvider<Coordinate> mTrajectoryDataProvider;
    protected CoordinateLayer mTrajectoryLayer;

    protected RobotLayer mRobotLayer;

    public CoordinateGui2018()
    {
        super(CENTER_POINT_X, CENTER_POINT_Y);

        CoordinateLayerRenderProps trajectoryLayerRenderProps = new CoordinateLayerRenderProps();
        CoordinateLayerRenderProps coordinateLayerRenderProps = new CoordinateLayerRenderProps();
        RobotLayerRenderProps robotLayerRenderProps = new RobotLayerRenderProps();

        trajectoryLayerRenderProps.setFadeOverTime(false);
        trajectoryLayerRenderProps.setPointSize(5);
        trajectoryLayerRenderProps.setPointMemory(-1);
        trajectoryLayerRenderProps.setPointColor(Color.red);

        mTrajectoryDataProvider = new DataProvider<>();

        mFieldLayer = new FieldImageLayer(FIELD_IMAGE_PATH, mConverter, FIELD_WIDTH, FIELD_HEIGHT);
        mRobotLayer = new RobotLayer(mCoordinateDataProvider, robotLayerRenderProps, mConverter, ROBOT_WIDTH,
                ROBOT_HEIGHT);
        mCoordinateLayer = new CoordinateLayer(mCoordinateDataProvider, coordinateLayerRenderProps, mConverter);
        mTrajectoryLayer = new CoordinateLayer(mTrajectoryDataProvider, trajectoryLayerRenderProps, mConverter);

        mLayerManager.addLayer(mFieldLayer);
        mLayerManager.addLayer(mRobotLayer);
        mLayerManager.addLayer(mCoordinateLayer);
        mLayerManager.addLayer(mTrajectoryLayer);
    }

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
}
