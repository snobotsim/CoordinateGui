package org.snobot.coordinate_gui;

import javafx.scene.transform.Scale;
import org.snobot.coordinate_gui.model.PixelConverter;

public final class TestablePixelConverterFactory
{
    private TestablePixelConverterFactory()
    {

    }

    /**
     * Sets up a pixel converter with (0, 0) being in the center of the field, and in portrait mode.
     * @return The pixel converter
     */
    public static PixelConverter setupPortraitMode()
    {
        final double longDim = 54;
        final double shortDim = 27;

        PixelConverter converter = new PixelConverter(shortDim / 2, longDim / 2);
        converter.setImageScale(new Scale(), shortDim * 10, longDim * 10, shortDim, longDim);

        return converter;
    }
}
