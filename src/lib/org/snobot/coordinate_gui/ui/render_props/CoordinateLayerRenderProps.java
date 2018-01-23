package org.snobot.coordinate_gui.ui.render_props;

import java.awt.Color;

public class CoordinateLayerRenderProps
{

    protected int mSize;
    protected int mPointMemory;
    protected Color mColor;
    protected boolean mFadeOverTime;

    /**
     * Constructor.
     */
    public CoordinateLayerRenderProps()
    {
        mSize = 10;
        mPointMemory = 100;
        mColor = Color.green;
        mFadeOverTime = true;
    }

    public int getPointMemory()
    {
        return mPointMemory;
    }

    public Color getPointColor()
    {
        return mColor;
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
