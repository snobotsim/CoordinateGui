package org.snobot.coordinate_gui.steamworks;

import java.util.List;

import org.snobot.coordinate_gui.BaseCoordinateGui;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.DataProvider;
import org.snobot.coordinate_gui.steamworks.CameraRayLayer.Ray;
import org.snobot.coordinate_gui.ui.layers.CoordinateLayer;
import org.snobot.coordinate_gui.ui.layers.FieldImageLayer;
import org.snobot.coordinate_gui.ui.layers.RobotLayer;
import org.snobot.coordinate_gui.ui.renderProps.CoordinateLayerRenderProps;
import org.snobot.coordinate_gui.ui.renderProps.RobotLayerRenderProps;

public class CoordinateGui2017 extends BaseCoordinateGui
{
    private static final String FIELD_IMAGE_PATH = "/com/snobot/coordinate_gui/steamworks/2017Field.png";

    private static final double FIELD_WIDTH = 27;
    private static final double FIELD_HEIGHT = 54;

    private static final double CENTER_POINT_X = 13.5;
    private static final double CENTER_POINT_Y = 27;

    private static final double ROBOT_WIDTH = 36 / 12.0;
    private static final double ROBOT_HEIGHT = 44 / 12.0;

    protected FieldImageLayer mFieldLayer;
    protected CoordinateLayer mCoordinateLayer;
    protected CameraRayLayer mRayLayer;

    protected DataProvider<Coordinate> mTrajectoryDataProvider;
    protected CoordinateLayer mTrajectoryLayer;

    protected RobotLayer mRobotLayer;

    public CoordinateGui2017(
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
        mRayLayer = new CameraRayLayer(mConverter);

        mLayerManager.addLayer(mFieldLayer);
        mLayerManager.addLayer(mRobotLayer);
        mLayerManager.addLayer(mCoordinateLayer);
        mLayerManager.addLayer(mRayLayer);
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

    public void setRays(List<Ray> aRays)
    {
        mRayLayer.setRays(aRays);
    }

}
