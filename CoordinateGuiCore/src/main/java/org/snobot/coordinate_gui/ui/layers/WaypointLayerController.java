package org.snobot.coordinate_gui.ui.layers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.PixelConverter;
import org.snobot.coordinate_gui.model.Position2dPixels;

import java.util.List;

public class WaypointLayerController
{
    private static final double ARROW_SIZE = 15;

    @FXML
    protected Group mWaypoints;

    /**
     * Sets the waypoints.
     *
     * @param aPixelConverter pixel converter
     * @param aWaypoints The waypoints
     */
    public void setWaypoints(PixelConverter aPixelConverter, List<Coordinate> aWaypoints)
    {
        mWaypoints.getChildren().clear();

        for (Coordinate waypoint : aWaypoints)
        {

            Polygon directionArrow = new Polygon(0.0, ARROW_SIZE / 3, ARROW_SIZE, 0.0, 0.0, -ARROW_SIZE / 3);


            Position2dPixels asPixels = aPixelConverter.convertDistanceToPixels(waypoint.mPosition);
            directionArrow.setRotate(aPixelConverter.convertHeading(waypoint.mAngle) - 90);
            directionArrow.setTranslateX(asPixels.mX);
            directionArrow.setTranslateY(asPixels.mY);
            directionArrow.setFill(Color.BLUEVIOLET);

            mWaypoints.getChildren().add(directionArrow);
        }
    }
}
