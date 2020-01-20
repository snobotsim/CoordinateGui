package org.snobot.coordinate_gui.ui.layers;

import javafx.geometry.Point2D;
import org.snobot.coordinate_gui.model.PixelConverter;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.shape.Polygon;

public class GoToPositionController
{

    @FXML
    protected Group mPosition;

    protected Polygon mIcon;

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
     * @param aPoint
     *            The coordinate
     */
    public void setGoToXYPosition(PixelConverter aPixelConverter, Point2D aPoint)
    {
        mPosition.getChildren().clear();
        if (aPoint != null)
        {
            mIcon.setTranslateX(aPixelConverter.convertFieldXFeetToPixels(aPoint.getX()));
            mIcon.setTranslateY(aPixelConverter.convertFieldYFeetToPixels(aPoint.getY()));
            mPosition.getChildren().add(mIcon);
        }
    }
}
