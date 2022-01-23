package org.snobot.coordinate_gui.ui.layers;

import java.util.Iterator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.DataProvider;
import org.snobot.coordinate_gui.model.PixelConverter;
import org.snobot.coordinate_gui.model.Position2dPixels;
import org.snobot.coordinate_gui.ui.render_props.CoordinateLayerRenderProps;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CoordinateLayerController
{
    private static final Logger sLOGGER = LogManager.getLogger(CoordinateLayerController.class);

    @FXML
    protected Group mCoordinates;

    protected PixelConverter mPixelConverter;
    protected DataProvider<Coordinate> mDataProvider;
    protected CoordinateLayerRenderProps mRenderProperties;

    /**
     * Sets the controller up.
     *
     * @param aRenderProperties
     *            The render properties used to draw the coordinates
     * @param aDataProvider
     *            The data provider that contains the data to draw
     * @param aPixelConverter
     *            The coordinate converter
     */
    public void setup(CoordinateLayerRenderProps aRenderProperties, DataProvider<Coordinate> aDataProvider, PixelConverter aPixelConverter)
    {
        mDataProvider = aDataProvider;
        mPixelConverter = aPixelConverter;
        mRenderProperties = aRenderProperties;
    }

    /**
     * Uses the history in the data provider to draw fading coordinates.
     */
    public void render()
    {
        mCoordinates.getChildren().clear();

        Iterator<Coordinate> revIterator = mDataProvider.getReverseIterator();
        int coordinateCtr = 0;

        while (revIterator.hasNext())
        {
            Color color = mRenderProperties.getPointColor(coordinateCtr);
            Coordinate coord = revIterator.next();

            Circle circle = new Circle(mRenderProperties.getPointSize());
            circle.setFill(color);
            Position2dPixels asPixels = mPixelConverter.convertDistanceToPixels(coord.mPosition);
            circle.setCenterX(asPixels.mX);
            circle.setCenterY(asPixels.mY);
            mCoordinates.getChildren().add(circle);

            ++coordinateCtr;
        }
        sLOGGER.log(Level.TRACE, "Rendered " + coordinateCtr + " coordinates");
    }

}
