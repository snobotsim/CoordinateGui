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
     * during rendering.
     */
    protected Object mDataLock = new Object();

    /**
     * Base class for the coordinate GUI.
     * 
     * @param aCenterPointX
     *            THe center of the field, in feet
     * @param aCenterPointY
     *            The center of the field, in feet
     */
    public BaseCoordinateGui(double aCenterPointX, double aCenterPointY)
    {
        mCoordinateDataProvider = new DataProvider<Coordinate>();
        mConverter = new PixelConverter(aCenterPointX, aCenterPointY);
        mLayerManager = new LayerManager(mConverter, mDataLock);
    }

    /**
     * Adds a coordinate to the data provider.
     * 
     * @param aCoordinate
     *            The coordinate
     */
    public void addCoordinate(Coordinate aCoordinate)
    {
        synchronized (mDataLock)
        {
            mCoordinateDataProvider.addData(aCoordinate);
        }
    }

    public ILayerManager getLayerManager()
    {
        return mLayerManager;
    }

    public Component getComponent()
    {
        return mLayerManager;
    }

    /**
     * Clears the data out of the data provider.
     */
    public void clearCoordinates()
    {
        synchronized (mDataLock)
        {
            mCoordinateDataProvider.clear();
        }
    }
}
