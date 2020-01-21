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
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(-13.5, 27, Distance.Unit.FEET));
        asDistance = converter.convertPixelsToDistance(new Position2dPixels(0, 0));
        Assertions.assertEquals(0, asPixels.mX, EPSILON);
        Assertions.assertEquals(0, asPixels.mY, EPSILON);
        Assertions.assertEquals(Distance.fromFeet(-13.5), asDistance.mX);
        Assertions.assertEquals(Distance.fromFeet(27), asDistance.mY);

        // Top Right Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(13.5, 27, Distance.Unit.FEET));
        asDistance = converter.convertPixelsToDistance(new Position2dPixels(270, 0));
        Assertions.assertEquals(270, asPixels.mX, EPSILON);
        Assertions.assertEquals(0, asPixels.mY, EPSILON);
        Assertions.assertEquals(Distance.fromFeet(13.5), asDistance.mX);
        Assertions.assertEquals(Distance.fromFeet(27), asDistance.mY);

        // Bottom Left Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(-13.5, -27, Distance.Unit.FEET));
        asDistance = converter.convertPixelsToDistance(new Position2dPixels(0, 540));
        Assertions.assertEquals(0, asPixels.mX, EPSILON);
        Assertions.assertEquals(540, asPixels.mY, EPSILON);
        Assertions.assertEquals(Distance.fromFeet(-13.5), asDistance.mX);
        Assertions.assertEquals(Distance.fromFeet(-27), asDistance.mY);

        // Bottom Right Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(13.5, -27, Distance.Unit.FEET));
        asDistance = converter.convertPixelsToDistance(new Position2dPixels(270, 540));
        Assertions.assertEquals(270, asPixels.mX, EPSILON);
        Assertions.assertEquals(540, asPixels.mY, EPSILON);
        Assertions.assertEquals(Distance.fromFeet(13.5), asDistance.mX);
        Assertions.assertEquals(Distance.fromFeet(-27), asDistance.mY);
    }

    @Test
    public void testPortraitOrientationWithCenterAtBottomLeft()
    {
        PixelConverter converter = TestablePixelConverterFactory.setupPortraitModeOriginAtBottomLeft();

        Position2dPixels asPixels;
        Position2dDistance asDistance;

        // Bottom Left Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(0, 0, Distance.Unit.FEET));
        asDistance = converter.convertPixelsToDistance(new Position2dPixels(0, 540));
        Assertions.assertEquals(0, asPixels.mX, EPSILON);
        Assertions.assertEquals(540, asPixels.mY, EPSILON);
        Assertions.assertEquals(Distance.fromFeet(0), asDistance.mX);
        Assertions.assertEquals(Distance.fromFeet(0), asDistance.mY);

        // Top Left Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(0, 54, Distance.Unit.FEET));
        asDistance = converter.convertPixelsToDistance(new Position2dPixels(0, 0));
        Assertions.assertEquals(0, asPixels.mX, EPSILON);
        Assertions.assertEquals(0, asPixels.mY, EPSILON);
        Assertions.assertEquals(Distance.fromFeet(0), asDistance.mX);
        Assertions.assertEquals(Distance.fromFeet(54), asDistance.mY);

        // Top Right Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(27, 54, Distance.Unit.FEET));
        asDistance = converter.convertPixelsToDistance(new Position2dPixels(270, 0));
        Assertions.assertEquals(270, asPixels.mX, EPSILON);
        Assertions.assertEquals(0, asPixels.mY, EPSILON);
        Assertions.assertEquals(Distance.fromFeet(27), asDistance.mX);
        Assertions.assertEquals(Distance.fromFeet(54), asDistance.mY);

        // Bottom Left Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(27, 0, Distance.Unit.FEET));
        asDistance = converter.convertPixelsToDistance(new Position2dPixels(270, 540));
        Assertions.assertEquals(270, asPixels.mX, EPSILON);
        Assertions.assertEquals(540, asPixels.mY, EPSILON);
        Assertions.assertEquals(Distance.fromFeet(27), asDistance.mX);
        Assertions.assertEquals(Distance.fromFeet(0), asDistance.mY);
    }

    @Test
    public void testLandscapeOrientationWithCenterAtPathWeaver()
    {

        PixelConverter converter = TestablePixelConverterFactory.setupLandscapeModeOriginAtPathWeaver();

        Position2dPixels asPixels;
        Position2dDistance asDistance;

        // Top Left Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(0, 0, Distance.Unit.FEET));
        asDistance = converter.convertPixelsToDistance(new Position2dPixels(0, 0));
        Assertions.assertEquals(0, asPixels.mX, EPSILON);
        Assertions.assertEquals(0, asPixels.mY, EPSILON);
        Assertions.assertEquals(Distance.fromFeet(0), asDistance.mX);
        Assertions.assertEquals(Distance.fromFeet(0), asDistance.mY);

        // Top Right Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(54, 0, Distance.Unit.FEET));
        asDistance = converter.convertPixelsToDistance(new Position2dPixels(540, 0));
        Assertions.assertEquals(540, asPixels.mX, EPSILON);
        Assertions.assertEquals(0, asPixels.mY, EPSILON);
        Assertions.assertEquals(Distance.fromFeet(54), asDistance.mX);
        Assertions.assertEquals(Distance.fromFeet(0), asDistance.mY);

        // Bottom Right Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(54, -27, Distance.Unit.FEET));
        asDistance = converter.convertPixelsToDistance(new Position2dPixels(540, 270));
        Assertions.assertEquals(540, asPixels.mX, EPSILON);
        Assertions.assertEquals(270, asPixels.mY, EPSILON);
        Assertions.assertEquals(Distance.fromFeet(54), asDistance.mX);
        Assertions.assertEquals(Distance.fromFeet(-27), asDistance.mY);

        // Bottom Left Corner
        asPixels = converter.convertDistanceToPixels(new Position2dDistance(0, -27, Distance.Unit.FEET));
        asDistance = converter.convertPixelsToDistance(new Position2dPixels(0, 270));
        Assertions.assertEquals(0, asPixels.mX, EPSILON);
        Assertions.assertEquals(270, asPixels.mY, EPSILON);
        Assertions.assertEquals(Distance.fromFeet(0), asDistance.mX);
        Assertions.assertEquals(Distance.fromFeet(-27), asDistance.mY);

    }
}
