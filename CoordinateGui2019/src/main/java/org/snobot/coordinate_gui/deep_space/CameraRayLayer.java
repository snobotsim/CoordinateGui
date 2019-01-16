package org.snobot.coordinate_gui.deep_space;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import org.snobot.coordinate_gui.model.PixelConverter;
import org.snobot.coordinate_gui.ui.layers.ILayer;

public class CameraRayLayer implements ILayer
{

    protected final PixelConverter mPixelConverter;
    private final Color mUnambiguousRayColor = Color.green;
    private final List<Ray> mRays;

    public CameraRayLayer(PixelConverter aConverter)
    {
        mPixelConverter = aConverter;
        mRays = new ArrayList<>();
    }

    @Override
    public void render(Graphics2D aGraphics)
    {
        for (Ray ray : mRays)
        {
            int xStart = mPixelConverter.convertXFeetToPixels(ray.mXStart / 12);
            int yStart = mPixelConverter.convertYFeetToPixels(ray.mYStart / 12);
            int xEnd = mPixelConverter.convertXFeetToPixels(ray.mXEnd / 12);
            int yEnd = mPixelConverter.convertYFeetToPixels(ray.mYEnd / 12);

            aGraphics.setColor(mUnambiguousRayColor);
            aGraphics.drawLine(xStart, yStart, xEnd, yEnd);
        }
    }

    @Override
    public Dimension getPreferredSize()
    {
        return null;
    }

    public void setRays(List<Ray> aRays)
    {
        mRays.clear();
        mRays.addAll(aRays);
    }

    public static class Ray
    {
        public double mXStart;
        public double mYStart;
        public double mXEnd;
        public double mYEnd;

        @Override
        public String toString()
        {
            return "Ray [mXStart=" + mXStart + ", mYStart=" + mYStart + ", mXEnd=" + mXEnd + ", mYEnd=" + mYEnd + "]";
        }
    }

}
