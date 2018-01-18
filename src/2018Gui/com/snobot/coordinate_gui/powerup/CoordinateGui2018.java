package com.snobot.coordinate_gui.powerup;

import java.util.List;

import com.snobot.coordinate_gui.BaseCoordinateGui;
import com.snobot.coordinate_gui.model.Coordinate;
import com.snobot.coordinate_gui.model.DataProvider;
import com.snobot.coordinate_gui.ui.layers.CoordinateLayer;
import com.snobot.coordinate_gui.ui.layers.FieldImageLayer;
import com.snobot.coordinate_gui.ui.layers.RobotLayer;
import com.snobot.coordinate_gui.ui.renderProps.CoordinateLayerRenderProps;
import com.snobot.coordinate_gui.ui.renderProps.RobotLayerRenderProps;

public class CoordinateGui2018 extends BaseCoordinateGui
{
    private static final String FIELD_IMAGE_PATH = "/com/snobot/coordinate_gui/powerup/2018Field.png";

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

    public CoordinateGui2018(
            CoordinateLayerRenderProps aTrajectoryLayerRenderProps, 
            CoordinateLayerRenderProps aCoordinateLayerRenderProps, 
            RobotLayerRenderProps aRobotLayerRenderProps)
    {
        super(CENTER_POINT_X, CENTER_POINT_Y);

        mTrajectoryDataProvider = new DataProvider<>();

        mFieldLayer = new FieldImageLayer(FIELD_IMAGE_PATH, mConverter, FIELD_WIDTH, FIELD_HEIGHT);
        mRobotLayer = new RobotLayer(mCoordinateDataProvider, aRobotLayerRenderProps, mConverter, ROBOT_WIDTH,
                ROBOT_HEIGHT);
        mCoordinateLayer = new CoordinateLayer(mCoordinateDataProvider, aCoordinateLayerRenderProps, mConverter);
        mTrajectoryLayer = new CoordinateLayer(mTrajectoryDataProvider, aTrajectoryLayerRenderProps, mConverter);

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
