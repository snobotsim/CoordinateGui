package org.snobot.coordinate_gui.ui.layers;

import org.snobot.coordinate_gui.model.PixelConverter;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;

public class BaseGuiController
{
    private final String mFieldImageUrl;
    private final double mFieldShortDimension;
    private final double mFieldLongDimension;

    protected final PixelConverter mPixelConverter;

    @FXML
    protected Pane mTopPane;

    @FXML
    protected FieldLayerController mFieldController;

    @FXML
    protected Group mLayers;

    protected BaseGuiController(String aFieldImageUrl, double aShortDirection, double aLongDirection, PixelConverter.Orientation aOrientation, PixelConverter.OriginPosition aOriginPosition)
    {
        mFieldImageUrl = aFieldImageUrl;
        mFieldShortDimension = aShortDirection;
        mFieldLongDimension = aLongDirection;
        mPixelConverter = new PixelConverter(mFieldShortDimension, mFieldLongDimension, aOrientation, aOriginPosition);
    }

    /**
     * Called after FXML fields have been initialized.
     */
    public void initialize()
    {
        Image fieldImage = new Image(getClass().getResource(mFieldImageUrl).toExternalForm());
        mFieldController.setFieldParameters(fieldImage);

        Scale scale = new Scale();
        scale.xProperty()
                .bind(Bindings.createDoubleBinding(
                    () -> Math.min(mTopPane.getWidth() / fieldImage.getWidth(), mTopPane.getHeight() / fieldImage.getHeight()),
                        mTopPane.widthProperty(), mTopPane.heightProperty()));

        scale.yProperty()
                .bind(Bindings.createDoubleBinding(
                    () -> Math.min(mTopPane.getWidth() / fieldImage.getWidth(), mTopPane.getHeight() / fieldImage.getHeight()),
                        mTopPane.widthProperty(), mTopPane.heightProperty()));

        mLayers.getTransforms().add(scale);
        mPixelConverter.setImageScale(scale, fieldImage.getWidth(), fieldImage.getHeight(), mFieldShortDimension, mFieldLongDimension);
    }

    public PixelConverter getPixelConverter()
    {
        return mPixelConverter;
    }

}
