package org.snobot.coordinate_gui.ui.render_props;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MovementArrowLayerRenderProps
{
    private static final Logger sLOGGER = LogManager.getLogger(MovementArrowLayerRenderProps.class);

    private static final String FIELD_IMAGE_PATH = "/org/snobot/coordinate_gui/ui/render_props/movementArrow.png";
    protected BufferedImage mFieldImage;

    /**
     * Constructor.
     */
    public MovementArrowLayerRenderProps()
    {
        try
        {
            InputStream in = getClass().getResourceAsStream(FIELD_IMAGE_PATH);
            mFieldImage = ImageIO.read(in);
        }
        catch (IOException ex)
        {
            sLOGGER.log(Level.ERROR, ex);
        }
    }

    public BufferedImage getImage()
    {
        return mFieldImage;
    }

}
