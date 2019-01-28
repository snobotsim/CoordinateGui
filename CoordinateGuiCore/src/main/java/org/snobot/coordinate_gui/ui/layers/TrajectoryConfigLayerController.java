package org.snobot.coordinate_gui.ui.layers;

import java.util.List;

import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.PixelConverter;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.shape.Polygon;

public class TrajectoryConfigLayerController
{
    private static final double SIZE = 30;

    @FXML
    private Group mPathPoints;

    /**
     * Sets the trajectory "turning points" for the layer to draw.
     * 
     * @param aPixelConverter
     *            The pixel converter utility
     * @param aTrajectoryPoints
     *            The points to draw
     */
    public void setTrajectoryPoints(PixelConverter aPixelConverter, List<Coordinate> aTrajectoryPoints)
    {
        mPathPoints.getChildren().clear();

        for (Coordinate coord : aTrajectoryPoints)
        {
            double pixelX = aPixelConverter.convertFieldXFeetToPixels(coord.mX);
            double pixelY = aPixelConverter.convertFieldYFeetToPixels(coord.mY);
            double xOffset = (SIZE * 3.0 / 5.0) / 16.5;

            Polygon icon = new Polygon(0.0, SIZE / 3, SIZE, 0.0, 0.0, -SIZE / 3);
            icon.setLayoutX(-(icon.getLayoutBounds().getMaxX() + icon.getLayoutBounds().getMinX()) / 2 - xOffset);
            icon.setLayoutY(-(icon.getLayoutBounds().getMaxY() + icon.getLayoutBounds().getMinY()) / 2);

            icon.setFill(javafx.scene.paint.Color.RED);
            icon.setTranslateX(pixelX);
            icon.setTranslateY(pixelY);
            icon.setRotate(coord.mAngle - 90);

            mPathPoints.getChildren().add(icon);
        }
    }
}
