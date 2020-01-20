package org.snobot.coordinate_gui.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snobot.coordinate_gui.TestablePixelConverterFactory;

public class PixelConverterTest
{
    private static final double EPSILON = 1e-4;

    @Test
    public void testPortraitOrientationWithCenterAtFieldCenter()
    {
        PixelConverter converter = TestablePixelConverterFactory.setupPortraitMode();

        Position2dPixels asPixels;
        Position2dDistance asDistance;

        // Top Left Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(-13.5, 27));
        asDistance = converter.convertPixelsToFeet(new Position2dPixels(0, 0));
        Assertions.assertEquals(0, asPixels.mX, EPSILON);
        Assertions.assertEquals(0, asPixels.mY, EPSILON);
        Assertions.assertEquals(-13.5, asDistance.mX, EPSILON);
        Assertions.assertEquals(27, asDistance.mY, EPSILON);

        // Top Right Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(13.5, 27));
        asDistance = converter.convertPixelsToFeet(new Position2dPixels(270, 0));
        Assertions.assertEquals(270, asPixels.mX, EPSILON);
        Assertions.assertEquals(0, asPixels.mY, EPSILON);
        Assertions.assertEquals(13.5, asDistance.mX, EPSILON);
        Assertions.assertEquals(27, asDistance.mY, EPSILON);

        // Bottom Left Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(-13.5, -27));
        asDistance = converter.convertPixelsToFeet(new Position2dPixels(0, 540));
        Assertions.assertEquals(0, asPixels.mX, EPSILON);
        Assertions.assertEquals(540, asPixels.mY, EPSILON);
        Assertions.assertEquals(-13.5, asDistance.mX, EPSILON);
        Assertions.assertEquals(-27, asDistance.mY, EPSILON);

        // Bottom Right Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(13.5, -27));
        asDistance = converter.convertPixelsToFeet(new Position2dPixels(270, 540));
        Assertions.assertEquals(270, asPixels.mX, EPSILON);
        Assertions.assertEquals(540, asPixels.mY, EPSILON);
        Assertions.assertEquals(13.5, asDistance.mX, EPSILON);
        Assertions.assertEquals(-27, asDistance.mY, EPSILON);
    }
}
