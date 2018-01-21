package org.snobot.coordinate_gui;

import java.awt.Component;

import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.DataProvider;
import org.snobot.coordinate_gui.model.PixelConverter;
import org.snobot.coordinate_gui.ui.layers.ILayerManager;
import org.snobot.coordinate_gui.ui.layers.LayerManager;

public class BaseCoordinateGui
{
    protected LayerManager mLayerManager;
    protected DataProvider<Coordinate> mCoordinateDataProvider;
    protected PixelConverter mConverter;

    /**
     * "Mutex" to make sure data doesn't get added while we are iterating, like
     * during rendering
     */
    protected Object mDataLock = new Object();

    public BaseCoordinateGui(double aCenterPointX, double aCenterPointY)
    {
        mCoordinateDataProvider = new DataProvider<Coordinate>();
        mConverter = new PixelConverter(aCenterPointX, aCenterPointY);
        mLayerManager = new LayerManager(mConverter, mDataLock);
    }

    public void addCoordinate(Coordinate aCoordinate)
    {
        synchronized (mDataLock)
        {
            mCoordinateDataProvider.addData(aCoordinate);
        }
        mLayerManager.repaint();
    }

    public ILayerManager getLayerManager()
    {
        return mLayerManager;
    }

    public Component getComponent()
    {
        return mLayerManager;
    }

    public void clearCoordinates()
    {
        synchronized (mDataLock)
        {
            mCoordinateDataProvider.clear();
        }
    }
}
