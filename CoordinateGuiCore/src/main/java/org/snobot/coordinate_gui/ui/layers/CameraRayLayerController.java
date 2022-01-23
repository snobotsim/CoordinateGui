package org.snobot.coordinate_gui.ui.layers;

import java.util.List;

import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.PixelConverter;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.model.Position2dPixels;

public class CameraRayLayerController
{
    @FXML
    protected Group mRays;

    /**
     * Sets the camera rays to draw.
     *
     * @param aPixelConerter
     *            The pixel-to-real world converter
     * @param aRays
     *            The rays
     */
    public void setRays(PixelConverter aPixelConerter, List<Ray> aRays)
    {
        mRays.getChildren().clear();

        for (Ray ray : aRays)
        {
            Position2dPixels start = aPixelConerter.convertDistanceToPixels(ray.mStart);
            Position2dPixels end = aPixelConerter.convertDistanceToPixels(ray.mEnd);

            Line line = new Line();
            line.setStartX(start.mX);
            line.setStartY(start.mY);
            line.setEndX(end.mX);
            line.setEndY(end.mY);
            line.setStrokeWidth(3.0);
            line.setStroke(Color.GREENYELLOW);

            mRays.getChildren().add(line);

        }
    }

    @SuppressWarnings({"PMD.DataClass", "PMD.ShortClassName"})
    public static class Ray
    {
        public Position2dDistance mStart;
        public Position2dDistance mEnd;

        public Ray()
        {
            mStart = new Position2dDistance(0, 0, Distance.Unit.Feet);
            mEnd = new Position2dDistance(0, 0, Distance.Unit.Feet);
        }

        /**
         * Constructor.
         *
         * @param aStart
         *            Starting position in feet. Typically would be the robot
         *            position
         * @param aEnd
         *            End position in feet. Typically would be the target
         *            position
         */
        public Ray(Position2dDistance aStart, Position2dDistance aEnd)
        {
            mStart = aStart;
            mEnd = aEnd;
        }

        @Override
        public String toString()
        {
            return "Ray [mStart=" + mStart + ", mEnd=" + mEnd + "]";
        }
    }
}
