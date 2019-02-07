package org.snobot.coordinate_gui.ui.layers;

import org.snobot.coordinate_gui.model.PixelConverter;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.shape.Polygon;

public class GoToPositionController
{

    @FXML
    private Group mPosition;

    private Polygon mIcon;

    @FXML
    public void initialize()
    {
        mIcon = new Polygon(0.0, 30 / 3, 30, 0.0, 0.0, -30 / 3);
        mIcon.setFill(javafx.scene.paint.Color.HOTPINK);
    }

    /**
     * Sets the position of the display icon. Inputs can be null to indicate no
     * position.
     * 
     * @param aX
     *            The x coordinate, in feet
     * @param aY
     *            The y coordinate, in feet
     */
    public void setGoToXYPosition(PixelConverter aPixelConverter, Double aX, Double aY)
    {
        mPosition.getChildren().clear();
        if (aX != null && aY != null)
        {
            mIcon.setTranslateX(aPixelConverter.convertFieldXFeetToPixels(aX));
            mIcon.setTranslateY(aPixelConverter.convertFieldYFeetToPixels(aY));
            mPosition.getChildren().add(mIcon);
        }
    }
}
