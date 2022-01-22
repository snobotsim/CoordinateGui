package org.snobot.coordinate_gui.ui.layers;

import org.snobot.coordinate_gui.model.PixelConverter;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.shape.Polygon;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.model.Position2dPixels;

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
     * @param aPosition
     *            The position, in feet
     */
    public void setGoToXYPosition(PixelConverter aPixelConverter, Position2dDistance aPosition)
    {
        mPosition.getChildren().clear();
        if (aPosition != null)
        {
            Position2dPixels asPixels = aPixelConverter.convertDistanceToPixels(aPosition);
            mIcon.setTranslateX(asPixels.mX);
            mIcon.setTranslateY(asPixels.mY);
            mPosition.getChildren().add(mIcon);
        }
    }
}
