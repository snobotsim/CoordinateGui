package org.snobot.coordinate_gui.ui.render_props;

import java.awt.Color;

public class RobotLayerRenderProps
{
    protected int mDotSize;
    protected Color mReferencePointColor;
    protected Color mRobotColor;

    /**
     * Constructor.
     */
    public RobotLayerRenderProps()
    {
        mDotSize = 3;
        mReferencePointColor = Color.red;
        mRobotColor = Color.black;
    }

    public Color getRobotColor()
    {
        return mRobotColor;
    }

    public Color getReferencePointColor()
    {
        return mReferencePointColor;
    }

    public int getReferencePointSize()
    {
        return mDotSize;
    }
}