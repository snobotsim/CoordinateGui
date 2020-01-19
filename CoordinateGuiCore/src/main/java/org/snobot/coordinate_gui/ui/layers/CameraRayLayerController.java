package org.snobot.coordinate_gui.ui.layers;

import java.util.List;

import org.snobot.coordinate_gui.model.PixelConverter;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

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
            double xStart = aPixelConerter.convertFieldXFeetToPixels(ray.mXStart);
            double yStart = aPixelConerter.convertFieldYFeetToPixels(ray.mYStart);
            double xEnd = aPixelConerter.convertFieldXFeetToPixels(ray.mXEnd);
            double yEnd = aPixelConerter.convertFieldYFeetToPixels(ray.mYEnd);

            Line line = new Line();
            line.setStartX(xStart);
            line.setStartY(yStart);
            line.setEndX(xEnd);
            line.setEndY(yEnd);
            line.setStrokeWidth(3.0);
            line.setStroke(Color.GREENYELLOW);
            
            mRays.getChildren().add(line);

        }
    }

    @SuppressWarnings("PMD.DataClass")
    public static class Ray
    {
        public double mXStart;
        public double mYStart;
        public double mXEnd;
        public double mYEnd;

        public Ray()
        {

        }

        /**
         * Constructor.
         * 
         * @param aXStart
         *            Starting x position in feet. Typically would be the robot
         *            position
         * @param aYStart
         *            Starting y position in feet. Typically would be the robot
         *            position
         * @param aXEnd
         *            End x position in feet. Typically would be the target
         *            position
         * @param aYEnd
         *            End y position in feet. Typically would be the target
         *            position
         */
        public Ray(double aXStart, double aYStart, double aXEnd, double aYEnd)
        {
            mXStart = aXStart;
            mYStart = aYStart;
            mXEnd = aXEnd;
            mYEnd = aYEnd;
        }

        @Override
        public String toString()
        {
            return "Ray [mXStart=" + mXStart + ", mYStart=" + mYStart + ", mXEnd=" + mXEnd + ", mYEnd=" + mYEnd + "]";
        }
    }
}
