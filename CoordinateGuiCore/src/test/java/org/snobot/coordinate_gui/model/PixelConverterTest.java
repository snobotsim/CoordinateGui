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
        PixelConverter converter = TestablePixelConverterFactory.setupPortraitModeOriginAtCenter();

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

    @Test
    public void testPortraitOrientationWithCenterAtBottomLeft()
    {
        PixelConverter converter = TestablePixelConverterFactory.setupPortraitModeOriginAtBottomLeft();

        Position2dPixels asPixels;
        Position2dDistance asDistance;

        // Bottom Left Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(0, 0));
        asDistance = converter.convertPixelsToFeet(new Position2dPixels(0, 540));
        Assertions.assertEquals(0, asPixels.mX, EPSILON);
        Assertions.assertEquals(540, asPixels.mY, EPSILON);
        Assertions.assertEquals(0, asDistance.mX, EPSILON);
        Assertions.assertEquals(0, asDistance.mY, EPSILON);

        // Top Left Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(0, 54));
        asDistance = converter.convertPixelsToFeet(new Position2dPixels(0, 0));
        Assertions.assertEquals(0, asPixels.mX, EPSILON);
        Assertions.assertEquals(0, asPixels.mY, EPSILON);
        Assertions.assertEquals(0, asDistance.mX, EPSILON);
        Assertions.assertEquals(54, asDistance.mY, EPSILON);

        // Top Right Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(27, 54));
        asDistance = converter.convertPixelsToFeet(new Position2dPixels(270, 0));
        Assertions.assertEquals(270, asPixels.mX, EPSILON);
        Assertions.assertEquals(0, asPixels.mY, EPSILON);
        Assertions.assertEquals(27, asDistance.mX, EPSILON);
        Assertions.assertEquals(54, asDistance.mY, EPSILON);

        // Bottom Left Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(27, 0));
        asDistance = converter.convertPixelsToFeet(new Position2dPixels(270, 540));
        Assertions.assertEquals(270, asPixels.mX, EPSILON);
        Assertions.assertEquals(540, asPixels.mY, EPSILON);
        Assertions.assertEquals(27, asDistance.mX, EPSILON);
        Assertions.assertEquals(0, asDistance.mY, EPSILON);
    }

    @Test
    public void testLandscapeOrientationWithCenterAtPathWeaver()
    {

        PixelConverter converter = TestablePixelConverterFactory.setupLandscapeModeOriginAtPathWeaver();

        Position2dPixels asPixels;
        Position2dDistance asDistance;

        // Top Left Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(0, 0));
        asDistance = converter.convertPixelsToFeet(new Position2dPixels(0, 0));
        Assertions.assertEquals(0, asPixels.mX, EPSILON);
        Assertions.assertEquals(0, asPixels.mY, EPSILON);
        Assertions.assertEquals(0, asDistance.mX, EPSILON);
        Assertions.assertEquals(0, asDistance.mY, EPSILON);

        // Top Right Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(54, 0));
        asDistance = converter.convertPixelsToFeet(new Position2dPixels(540, 0));
        Assertions.assertEquals(540, asPixels.mX, EPSILON);
        Assertions.assertEquals(0, asPixels.mY, EPSILON);
        Assertions.assertEquals(54, asDistance.mX, EPSILON);
        Assertions.assertEquals(0, asDistance.mY, EPSILON);

        // Bottom Right Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(54, -27));
        asDistance = converter.convertPixelsToFeet(new Position2dPixels(540, 270));
        Assertions.assertEquals(540, asPixels.mX, EPSILON);
        Assertions.assertEquals(270, asPixels.mY, EPSILON);
        Assertions.assertEquals(54, asDistance.mX, EPSILON);
        Assertions.assertEquals(-27, asDistance.mY, EPSILON);

        // Bottom Left Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(0, -27));
        asDistance = converter.convertPixelsToFeet(new Position2dPixels(0, 270));
        Assertions.assertEquals(0, asPixels.mX, EPSILON);
        Assertions.assertEquals(270, asPixels.mY, EPSILON);
        Assertions.assertEquals(0, asDistance.mX, EPSILON);
        Assertions.assertEquals(-27, asDistance.mY, EPSILON);

    }
}
