package org.snobot.coordinate_gui.ui.layers;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.DataProvider;
import org.snobot.coordinate_gui.model.PixelConverter;
import org.snobot.coordinate_gui.ui.render_props.MovementArrowLayerRenderProps;

public class MovementArrowLayer implements ILayer
{
    protected final DataProvider<Coordinate> mDataProvider;
    protected final PixelConverter mPixelConverter;
    protected final MovementArrowLayerRenderProps mRenderProperties;

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
    public MovementArrowLayer(DataProvider<Coordinate> aDataProvider, MovementArrowLayerRenderProps aRenderProps, PixelConverter aPixelConverter)
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

    protected void renderCoordinates(Graphics2D aGraphics, DataProvider<Coordinate> aDataProvider, MovementArrowLayerRenderProps aRenderProperties)
    {
        Iterator<Coordinate> revIterator = aDataProvider.getReverseIterator();

        while (revIterator.hasNext())
        {
            Coordinate coord = revIterator.next();

            double rotation = Math.toRadians(coord.mAngle);

            int x = mPixelConverter.convertXFeetToPixels(coord.mX);
            int y = mPixelConverter.convertYFeetToPixels(coord.mY);

            BufferedImage image = aRenderProperties.getImage();

            double locationX = image.getWidth() / 2.0;
            double locationY = image.getHeight() / 2.0;
            AffineTransform tx = AffineTransform.getRotateInstance(rotation, locationX, locationY);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

            aGraphics.drawImage(op.filter(image, null), x - image.getWidth() / 2, y - image.getHeight() / 2, null);
        }
    }

    @Override
    public Dimension getPreferredSize()
    {
        return null;
    }
}
