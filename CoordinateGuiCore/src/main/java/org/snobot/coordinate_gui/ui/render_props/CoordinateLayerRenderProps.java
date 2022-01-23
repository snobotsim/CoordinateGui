package org.snobot.coordinate_gui.ui.render_props;

import javafx.scene.paint.Color;

public class CoordinateLayerRenderProps
{

    protected int mSize;
    protected int mPointMemory;
    protected Color mColor;
    protected boolean mFadeOverTime;

    /**
     * Constructor.
     */
    public CoordinateLayerRenderProps(int aPointMemory, int aSize, Color aColor, boolean aFadeOverTime)
    {
        mSize = aSize;
        mPointMemory = aPointMemory;
        mColor = aColor;
        mFadeOverTime = aFadeOverTime;
    }

    public int getPointMemory()
    {
        return mPointMemory;
    }

    /**
     * Gets the point color, based on how deep in the list it is.
     *
     * @param aCoordinateCounter
     *            The counter of which data point this is. Affects the opacity
     * @return The color to draw with
     */
    public Color getPointColor(int aCoordinateCounter)
    {
        float opacity;

        if (mFadeOverTime)
        {
            opacity = 1.0f - ((float) aCoordinateCounter / mPointMemory);
            opacity = Math.min(1, opacity);
            opacity = Math.max(0, opacity);
        }
        else
        {
            opacity = 1;
        }

        return new Color(mColor.getRed(), mColor.getGreen(), mColor.getBlue(), opacity);
    }

    public boolean isFadeOverTime()
    {
        return mFadeOverTime;
    }

    public int getPointSize()
    {
        return mSize;
    }

    public void setPointSize(int aSize)
    {
        this.mSize = aSize;
    }

    public void setPointMemory(int aPointMemory)
    {
        this.mPointMemory = aPointMemory;
    }

    public void setPointColor(Color aColor)
    {
        this.mColor = aColor;
    }

    public void setFadeOverTime(boolean aFadeOverTime)
    {
        this.mFadeOverTime = aFadeOverTime;
    }

}
