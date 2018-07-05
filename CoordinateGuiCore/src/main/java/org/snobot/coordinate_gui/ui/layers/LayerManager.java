package org.snobot.coordinate_gui.ui.layers;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.snobot.coordinate_gui.model.PixelConverter;

public class LayerManager extends JPanel implements ILayerManager
{
    protected final List<IFieldClickListener> mFieldClickListeners;
    protected final List<ILayer> mLayers;
    protected PixelConverter mConverter;
    protected Object mLock;

    protected MouseAdapter mMouseListener = new MouseAdapter()
    {
        @Override
        public void mouseClicked(MouseEvent aEvent)
        {
            double xFeet = mConverter.convertXPixelsToFeet(aEvent.getX());
            double yFeet = mConverter.convertYPixelsToFeet(aEvent.getY());

            for (IFieldClickListener listener : mFieldClickListeners)
            {
                listener.onClicked(xFeet, yFeet);
            }
        }

        @Override
        public void mouseDragged(MouseEvent aEvent)
        {
            double xFeet = mConverter.convertXPixelsToFeet(aEvent.getX());
            double yFeet = mConverter.convertYPixelsToFeet(aEvent.getY());

            for (IFieldClickListener listener : mFieldClickListeners)
            {
                listener.onDrag(xFeet, yFeet);
            }
        }

        @Override
        public void mouseMoved(MouseEvent aEvent)
        {
            double xFeet = mConverter.convertXPixelsToFeet(aEvent.getX());
            double yFeet = mConverter.convertYPixelsToFeet(aEvent.getY());

            for (IFieldClickListener listener : mFieldClickListeners)
            {
                listener.onHover(xFeet, yFeet);
            }
        }
    };

    protected ComponentAdapter mResizeListener = new ComponentAdapter()
    {
        @Override
        public void componentResized(ComponentEvent aEvent)
        {
            mConverter.updateScaleFactor(getWidth(), getHeight(), 27, 54);
            repaint();
        }
    };

    /**
     * Constructor.
     * 
     * @param aConverter
     *            The pixel converter used to translate swing coordinates to
     *            real world distances
     * @param aLock
     *            Lock for data
     */
    public LayerManager(PixelConverter aConverter, Object aLock)
    {
        mFieldClickListeners = new ArrayList<>();
        mLayers = new ArrayList<>();
        mConverter = aConverter;
        mLock = aLock;
        addComponentListener(mResizeListener);
        addMouseListener(mMouseListener);
        addMouseMotionListener(mMouseListener);
    }

    public void addFieldClickListener(IFieldClickListener aListener)
    {
        mFieldClickListeners.add(aListener);
    }

    public void removeFieldClickListener(IFieldClickListener aListener)
    {
        mFieldClickListeners.remove(aListener);
    }

    public void addLayer(ILayer aLayer)
    {
        mLayers.add(aLayer);
    }

    @Override
    public Dimension getPreferredSize()
    {
        Dimension output = null;

        for (ILayer layer : mLayers)
        {
            Dimension d = layer.getPreferredSize();
            if (d != null)
            {
                output = d;
            }
        }

        return output;
    }

    @Override
    public void paint(Graphics aGraphics)
    {
        synchronized (mLock)
        {
            Graphics2D graphics = (Graphics2D) aGraphics;

            aGraphics.clearRect(0, 0, getWidth(), getHeight());

            for (ILayer layer : mLayers)
            {
                layer.render(graphics);
            }
        }
    }

    public void render()
    {
        repaint();
    }

}
