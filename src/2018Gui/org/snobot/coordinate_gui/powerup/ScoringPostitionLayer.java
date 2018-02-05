package org.snobot.coordinate_gui.powerup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import org.snobot.coordinate_gui.model.PixelConverter;
import org.snobot.coordinate_gui.ui.layers.ILayer;

public class ScoringPostitionLayer implements ILayer
{
    private static final double sPANEL_WIDTH = 3;
    private static final double sPANEL_HEIGHT = 4;

    protected final PixelConverter mPixelConverter;

    private boolean mCloseSwitchRight;
    private boolean mScaleRight;
    private boolean mFarSwitchRight;

    private final Color mOurColor;
    private final Color mTheirColor;

    /**
     * Constructor.
     * 
     * @param aPixelConverter
     *            The pixel converter
     */
    public ScoringPostitionLayer(PixelConverter aPixelConverter)
    {
        mPixelConverter = aPixelConverter;

        mOurColor = Color.green;
        mTheirColor = new Color(0, 0, 0, 0);

        mCloseSwitchRight = true;
        mScaleRight = false;
        mFarSwitchRight = true;
    }

    /**
     * Sets the position of our scoring platforms.
     * 
     * @param aCloseSwitchRight
     *            True if the platform for our switch is on the right side
     * @param aScaleRight
     *            True if the platform for our scale is on the right side
     * @param aFarSwitchRight
     *            True if the platform for our opponents switch is on the right
     *            side
     */
    public void setData(boolean aCloseSwitchRight, boolean aScaleRight, boolean aFarSwitchRight)
    {
        mCloseSwitchRight = aCloseSwitchRight;
        mScaleRight = aScaleRight;
        mFarSwitchRight = aFarSwitchRight;
    }

    @Override
    public void render(Graphics2D aGraphics)
    {
        drawBox(aGraphics, mCloseSwitchRight, 3, -11);
        drawBox(aGraphics, mScaleRight, 4.5, 2);
        drawBox(aGraphics, mFarSwitchRight, 3, 15);
    }

    private void drawBox(Graphics aGraphics, boolean aIsRight, double aStartX, double aStartY)
    {
        int leftStartXPixels = mPixelConverter.convertXFeetToPixels(aStartX);
        int leftStartYPixels = mPixelConverter.convertYFeetToPixels(aStartY);

        int rightStartXPixels = mPixelConverter.convertXFeetToPixels(-aStartX - sPANEL_WIDTH);
        int rightStartYPixels = mPixelConverter.convertYFeetToPixels(aStartY);

        int width = mPixelConverter.convertFeetToPixels(sPANEL_WIDTH);
        int height = mPixelConverter.convertFeetToPixels(sPANEL_HEIGHT);

        aGraphics.setColor(getColor(!aIsRight));
        aGraphics.fillRect(rightStartXPixels, rightStartYPixels, width, height);
        aGraphics.setColor(Color.black);
        aGraphics.drawRect(rightStartXPixels, rightStartYPixels, width, height);

        aGraphics.setColor(getColor(aIsRight));
        aGraphics.fillRect(leftStartXPixels, leftStartYPixels, width, height);
        aGraphics.setColor(Color.black);
        aGraphics.drawRect(leftStartXPixels, leftStartYPixels, width, height);
    }

    private Color getColor(boolean aIsOurs)
    {
        return aIsOurs ? mOurColor : mTheirColor;
    }

    @Override
    public Dimension getPreferredSize()
    {
        return null;
    }

}
