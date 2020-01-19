package org.snobot.coordinate_gui.model;

import org.junit.Assert;
import org.junit.Test;
import org.snobot.coordinate_gui.TestablePixelConverterFactory;

public class PixelConverterTest
{
    private static final double EPSILON = 1e-4;

    @Test
    public void testPortraitOrientationWithCenterAtFieldCenter()
    {
        PixelConverter converter = TestablePixelConverterFactory.setupPortraitMode();

        // Top Left Corner
        Assert.assertEquals(0, converter.convertFieldXFeetToPixels(-13.5), EPSILON);
        Assert.assertEquals(0, converter.convertFieldYFeetToPixels(27), EPSILON);
        Assert.assertEquals(-13.5, converter.convertFieldXPixelsToFeet(0), EPSILON);
        Assert.assertEquals(27, converter.convertFieldYPixelsToFeet(0), EPSILON);

        // Top Right Corner
        Assert.assertEquals(270, converter.convertFieldXFeetToPixels(13.5), EPSILON);
        Assert.assertEquals(0, converter.convertFieldYFeetToPixels(27), EPSILON);
        Assert.assertEquals(13.5, converter.convertFieldXPixelsToFeet(270), EPSILON);
        Assert.assertEquals(27, converter.convertFieldYPixelsToFeet(0), EPSILON);

        // Bottom Left Corner
        Assert.assertEquals(0, converter.convertFieldXFeetToPixels(-13.5), EPSILON);
        Assert.assertEquals(540, converter.convertFieldYFeetToPixels(-27), EPSILON);
        Assert.assertEquals(-13.5, converter.convertFieldXPixelsToFeet(0), EPSILON);
        Assert.assertEquals(-27, converter.convertFieldYPixelsToFeet(540), EPSILON);

        // Bottom Right Corner
        Assert.assertEquals(270, converter.convertFieldXFeetToPixels(13.5), EPSILON);
        Assert.assertEquals(540, converter.convertFieldYFeetToPixels(-27), EPSILON);
        Assert.assertEquals(13.5, converter.convertFieldXPixelsToFeet(270), EPSILON);
        Assert.assertEquals(-27, converter.convertFieldYPixelsToFeet(540), EPSILON);
    }
}
