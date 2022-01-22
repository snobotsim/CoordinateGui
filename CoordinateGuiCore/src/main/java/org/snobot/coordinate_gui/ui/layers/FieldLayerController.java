package org.snobot.coordinate_gui.ui.layers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FieldLayerController
{
    @FXML
    private ImageView mBackgroundImage;

    public void setFieldParameters(Image aFieldImage)
    {
        mBackgroundImage.setImage(aFieldImage);
    }

}
