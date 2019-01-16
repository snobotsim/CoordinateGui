package org.snobot.coordinate_gui.ui.layers;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.snobot.coordinate_gui.model.PixelConverter;

public class FieldImageLayer implements ILayer
{
	private static final Logger sLOGGER = LogManager.getLogger(FieldImageLayer.class);

    protected BufferedImage mFieldImage;
    protected PixelConverter mPixelConverter;
    protected Dimension mPreferredSize;
    protected double mFieldWidth;
    protected double mFieldHeight;

    /**
     * Constructor.
     * 
     * @param aImagePath
     *            The resource name for the image to load
     * @param aPixelConverter
     *            The pixel converter
     * @param aFieldWidth
     *            The field width, in feet
     * @param aFieldHeight
     *            The field height, in feet
     */
    public FieldImageLayer(String aImagePath, PixelConverter aPixelConverter, double aFieldWidth, double aFieldHeight)
    {
        readFieldImage(aImagePath);
        mPixelConverter = aPixelConverter;
        mFieldWidth = aFieldWidth;
        mFieldHeight = aFieldHeight;
    }

    /**
     * Reads the image file. If it is not found nothing happens
     */
    private void readFieldImage(String aImagePath)
    {
        try
        {
            InputStream in = getClass().getResourceAsStream(aImagePath);

            // Image exists
            if (in == null)
            {
                sLOGGER.log(Level.ERROR, "Could not find image file : '" + aImagePath + "'");
            }
            else
            {
                mFieldImage = ImageIO.read(in);

                if (mFieldImage == null)
                {
                    sLOGGER.log(Level.ERROR, "Could not find image file : '" + aImagePath + "'");
                }
                else
                {
                    mPreferredSize = new Dimension(mFieldImage.getWidth(), mFieldImage.getHeight());
                }
            }
        }
        catch (IOException ex)
        {
            sLOGGER.log(Level.ERROR, "Could not load field image", ex);
        }
    }

    @Override
    public void render(Graphics2D aGraphics)
    {
        if (mFieldImage != null)
        {
            int width = mPixelConverter.convertFeetToPixels(mFieldWidth);
            int height = mPixelConverter.convertFeetToPixels(mFieldHeight);
            aGraphics.drawImage(mFieldImage, 0, 0, width, height, null);
        }

    }

    @Override
    public Dimension getPreferredSize()
    {
        return mPreferredSize;
    }
}
