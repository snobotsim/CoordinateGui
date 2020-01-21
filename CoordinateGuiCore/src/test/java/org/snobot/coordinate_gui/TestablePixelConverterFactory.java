package org.snobot.coordinate_gui;

import javafx.scene.transform.Scale;
import org.snobot.coordinate_gui.model.Distance;
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
    public static PixelConverter setupPortraitModeOriginAtCenter()
    {
        final Distance longDim = Distance.fromFeet(54);
        final Distance shortDim = Distance.fromFeet(27);

        PixelConverter converter = new PixelConverter(shortDim, longDim, PixelConverter.Orientation.Portrait, PixelConverter.OriginPosition.CenterField);
        converter.setImageScale(new Scale(), shortDim.asFeet() * 10, longDim.asFeet() * 10, shortDim, longDim);

        return converter;
    }

    /**
     * Sets up a pixel converter with (0, 0) being in the bottom left of the field, and in portrait mode.
     * @return The pixel converter
     */
    public static PixelConverter setupPortraitModeOriginAtBottomLeft()
    {
        final Distance longDim = Distance.fromFeet(54);
        final Distance shortDim = Distance.fromFeet(27);

        PixelConverter converter = new PixelConverter(shortDim, longDim, PixelConverter.Orientation.Portrait, PixelConverter.OriginPosition.AlwaysIncreasing);
        converter.setImageScale(new Scale(), shortDim.asFeet() * 10, longDim.asFeet() * 10, shortDim, longDim);

        return converter;
    }

    /**
     * Sets up a pixel converter with (0, 0) being in the center of the field, and in landscpe mode.
     * @return The pixel converter
     */
    public static PixelConverter setupLandscapeModeOriginAtPathWeaver()
    {
        final Distance longDim = Distance.fromFeet(54);
        final Distance shortDim = Distance.fromFeet(27);

        PixelConverter converter = new PixelConverter(shortDim, longDim, PixelConverter.Orientation.Landscape, PixelConverter.OriginPosition.AlwaysIncreasing);
        converter.setImageScale(new Scale(), longDim.asFeet() * 10, shortDim.asFeet() * 10, longDim, shortDim);

        return converter;
    }
}
