package org.snobot.coordinate_gui.ui.layers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Iterator;

import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.DataProvider;
import org.snobot.coordinate_gui.model.PixelConverter;
import org.snobot.coordinate_gui.ui.render_props.CoordinateLayerRenderProps;

public class CoordinateLayer implements ILayer
{
    protected final DataProvider<Coordinate> mDataProvider;
    protected final PixelConverter mPixelConverter;
    protected final CoordinateLayerRenderProps mRenderProperties;

    /**
     * Standard coordinate layer.
     * 
     * @param aDataProvider
     *            The data provider for the coordinates
     * @param aRenderProps
     *            The render properties for standard coordinates
     * @param aPixelConverter
     *            The pixel converter
     */
    public CoordinateLayer(DataProvider<Coordinate> aDataProvider, CoordinateLayerRenderProps aRenderProps, PixelConverter aPixelConverter)
    {
        mDataProvider = aDataProvider;
        mRenderProperties = aRenderProps;
        mPixelConverter = aPixelConverter;
    }

    @Override
    public void render(Graphics2D aGraphics)
    {
        renderCoordinates(aGraphics, mDataProvider, mRenderProperties);
    }

    protected void renderCoordinates(Graphics2D aGraphics, DataProvider<Coordinate> aDataProvider, CoordinateLayerRenderProps aRenderProperties)
    {
        Iterator<Coordinate> revIterator = aDataProvider.getReverseIterator();
        int coordinateCtr = 0;

        while (revIterator.hasNext())
        {
            int pointMemory = aRenderProperties.getPointMemory();
            if (pointMemory != -1 && coordinateCtr >= pointMemory)
            {
                break;
            }


            float opacity = 1.0f - ((float) coordinateCtr / pointMemory);
            opacity = Math.min(1, opacity);
            opacity = Math.max(0, opacity);
            Color defaultColor = aRenderProperties.getPointColor();
            Color color;

            if (aRenderProperties.isFadeOverTime())
            {
                color = new Color(defaultColor.getRed() / 255.0f, defaultColor.getGreen() / 255.0f, defaultColor.getBlue() / 255.0f, opacity);
            }
            else
            {
                color = defaultColor;
            }

            Coordinate coord = revIterator.next();
            paintCoordinate(aGraphics, coord, color, aRenderProperties.getPointSize());
            ++coordinateCtr;
        }
    }

    private void paintCoordinate(Graphics2D aGraphics, Coordinate aCoordinate, Color aColor, int aSize)
    {
        if (aCoordinate != null)
        {
            int x = mPixelConverter.convertXFeetToPixels(aCoordinate.mX);
            int y = mPixelConverter.convertYFeetToPixels(aCoordinate.mY);

            aGraphics.setColor(aColor);
            aGraphics.fillOval(x - aSize / 2, y - aSize / 2, aSize, aSize);
        }
    }

    @Override
    public Dimension getPreferredSize()
    {
        return null;
    }
}
