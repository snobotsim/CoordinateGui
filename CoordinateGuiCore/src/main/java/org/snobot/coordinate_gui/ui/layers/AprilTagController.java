package org.snobot.coordinate_gui.ui.layers;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.wpilibj.apriltag.AprilTagFieldLayout;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.PixelConverter;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.model.Position2dPixels;

public class AprilTagController
{
    private static final Distance TAG_WIDTH = Distance.fromInches(8.5);
    private static final Distance TAG_HEIGHT = Distance.fromInches(8.5);

    private static final Distance RECT_RENDER_WIDTH = Distance.fromInches(3);

    @FXML
    protected Group mRays;

    /**
     * Set up the controller.
     *
     * @param aPixelConverter The pixel converter
     * @param aLayout The april tag config
     */
    public void setup(PixelConverter aPixelConverter, AprilTagFieldLayout aLayout)
    {
        mRays.getChildren().clear();

        for (var tag : aLayout.getTags())
        {
            Pose3d pose = tag.pose;

            Distance tagX = Distance.fromMeters(pose.getX());
            Distance tagY = Distance.fromMeters(pose.getY());

            Position2dDistance position = new Position2dDistance(tagX, tagY);
            Position2dPixels pixelPose = aPixelConverter.convertDistanceToPixels(position);

            {
                Circle tagBackground = new Circle();
                tagBackground.setCenterX(pixelPose.mX);
                tagBackground.setCenterY(pixelPose.mY);
                tagBackground.setRadius(aPixelConverter.convertFeetToPixels(TAG_WIDTH));
                tagBackground.setRadius(aPixelConverter.convertFeetToPixels(TAG_HEIGHT));
                tagBackground.setFill(Color.BLUE);
                mRays.getChildren().add(tagBackground);
            }

            {
                Text label = new Text(tag.name + "(" + tag.ID + ")");
                label.setX(pixelPose.mX - label.getLayoutBounds().getWidth());
                label.setY(pixelPose.mY);
                label.setTextAlignment(TextAlignment.CENTER);
                label.setFont(Font.font(aPixelConverter.convertFeetToPixels(TAG_WIDTH)));
                mRays.getChildren().add(label);
            }

            {
                Distance centeredTagX = tagX.subtract(RECT_RENDER_WIDTH.divide(2));
                Distance centeredTagY = tagY.add(TAG_HEIGHT.divide(2));

                Position2dPixels centerPixel = aPixelConverter.convertDistanceToPixels(new Position2dDistance(centeredTagX, centeredTagY));
                double javafxAngle = -(Math.toDegrees(pose.getRotation().getZ()));

                Rectangle tagImage = new Rectangle();
                tagImage.setX(centerPixel.mX);
                tagImage.setY(centerPixel.mY);
                tagImage.setWidth(aPixelConverter.convertFeetToPixels(RECT_RENDER_WIDTH));
                tagImage.setHeight(aPixelConverter.convertFeetToPixels(TAG_HEIGHT));
                tagImage.setRotate(javafxAngle);
                mRays.getChildren().add(tagImage);
            }
        }

    }
}
