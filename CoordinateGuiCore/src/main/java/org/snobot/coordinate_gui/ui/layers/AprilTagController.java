package org.snobot.coordinate_gui.ui.layers;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.apriltag.AprilTag;
import edu.wpi.first.wpilibj.apriltag.AprilTagFieldLayout;
import edu.wpi.first.wpilibj.apriltag.AprilTagFields;
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

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AprilTagController {

    private static final String TEMP_FILE = "C:\\Users\\PJ\\Documents\\GitHub\\old_frc\\snobot_reusable\\CoordinateGui\\TempWpilib\\src\\main\\resources\\edu\\wpi\\first\\wpilibj\\apriltag\\rapid_react_tags.json";

    @FXML
    protected Group mRays;

    public AprilTagController() {
        System.out.println("Hello");
        List<AprilTag> aprilTags = new ArrayList<>();

        aprilTags.add(new AprilTag("Blue Hangar Panel",             0 , new Pose3d(Units.inchesToMeters(-0.139),       Units.inchesToMeters(298.383),     Units.inchesToMeters(34.876),     new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(0    ).getRadians(), Rotation2d.fromDegrees(0      ).getRadians()))));
        aprilTags.add(new AprilTag("Blue Hangar Truss - Hub",       1 , new Pose3d(Units.inchesToMeters(127.272),      Units.inchesToMeters(216.01),      Units.inchesToMeters(67.932),     new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(0    ).getRadians(), Rotation2d.fromDegrees(0      ).getRadians()))));
        aprilTags.add(new AprilTag("Blue Hangar Truss - Side",      2 , new Pose3d(Units.inchesToMeters(120.78),       Units.inchesToMeters(209.863),     Units.inchesToMeters(54.182),     new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(0    ).getRadians(), Rotation2d.fromDegrees(-90    ).getRadians()))));
        aprilTags.add(new AprilTag("Blue Station 2 Wall",           3 , new Pose3d(Units.inchesToMeters(0.157),        Units.inchesToMeters(199.155),     Units.inchesToMeters(31.75),      new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(0    ).getRadians(), Rotation2d.fromDegrees(0      ).getRadians()))));
        aprilTags.add(new AprilTag("Blue Station 3 Wall",           4 , new Pose3d(Units.inchesToMeters(0.157),        Units.inchesToMeters(138.287),     Units.inchesToMeters(31.75),      new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(0    ).getRadians(), Rotation2d.fromDegrees(0      ).getRadians()))));
        aprilTags.add(new AprilTag("Blue Terminal Near Station",    5 , new Pose3d(Units.inchesToMeters(4.768),        Units.inchesToMeters(67.631),      Units.inchesToMeters(35.063),     new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(0    ).getRadians(), Rotation2d.fromDegrees(46.25  ).getRadians()))));
        aprilTags.add(new AprilTag("Blue Mid Terminal",             6 , new Pose3d(Units.inchesToMeters(34.382),       Units.inchesToMeters(37.059),      Units.inchesToMeters(35.063),     new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(0    ).getRadians(), Rotation2d.fromDegrees(46.25  ).getRadians()))));
        aprilTags.add(new AprilTag("Blue End Terminal",             7 , new Pose3d(Units.inchesToMeters(63.586),       Units.inchesToMeters(6.191),       Units.inchesToMeters(35.063),     new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(0    ).getRadians(), Rotation2d.fromDegrees(46.25  ).getRadians()))));
        aprilTags.add(new AprilTag("Red Hangar Panel",              10, new Pose3d(Units.inchesToMeters(648.139),      Units.inchesToMeters(25.617),      Units.inchesToMeters(34.876),     new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(0    ).getRadians(), Rotation2d.fromDegrees(180    ).getRadians()))));
        aprilTags.add(new AprilTag("Red Hangar Truss - Hub",        11, new Pose3d(Units.inchesToMeters(521.063),      Units.inchesToMeters(108.01),      Units.inchesToMeters(67.932),     new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(0    ).getRadians(), Rotation2d.fromDegrees(180    ).getRadians()))));
        aprilTags.add(new AprilTag("Red Hangar Truss - Side",       12, new Pose3d(Units.inchesToMeters(527.22),       Units.inchesToMeters(114.167),     Units.inchesToMeters(54.182),     new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(0    ).getRadians(), Rotation2d.fromDegrees(90     ).getRadians()))));
        aprilTags.add(new AprilTag("Red Station 2 Wall",            13, new Pose3d(Units.inchesToMeters(647.843),      Units.inchesToMeters(125.02),      Units.inchesToMeters(31.75),      new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(0    ).getRadians(), Rotation2d.fromDegrees(180    ).getRadians()))));
        aprilTags.add(new AprilTag("Red Station 3 Wall",            14, new Pose3d(Units.inchesToMeters(647.843),      Units.inchesToMeters(185.714),     Units.inchesToMeters(31.75),      new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(0    ).getRadians(), Rotation2d.fromDegrees(180    ).getRadians()))));
        aprilTags.add(new AprilTag("Red Terminal Near Station",     15, new Pose3d(Units.inchesToMeters(643.111),      Units.inchesToMeters(256.495),     Units.inchesToMeters(35.188),     new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(0    ).getRadians(), Rotation2d.fromDegrees(223.8  ).getRadians()))));
        aprilTags.add(new AprilTag("Red Mid Terminal",              16, new Pose3d(Units.inchesToMeters(613.799),      Units.inchesToMeters(287.114),     Units.inchesToMeters(35.063),     new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(0    ).getRadians(), Rotation2d.fromDegrees(223.8  ).getRadians()))));
        aprilTags.add(new AprilTag("Red End Terminal",              17, new Pose3d(Units.inchesToMeters(584.535),      Units.inchesToMeters(317.682),     Units.inchesToMeters(35.063),     new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(0    ).getRadians(), Rotation2d.fromDegrees(223.8  ).getRadians()))));
        aprilTags.add(new AprilTag("Lower Hub Far Exit",            40, new Pose3d(Units.inchesToMeters(310.005),      Units.inchesToMeters(193.432),     Units.inchesToMeters(27.688),     new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(0    ).getRadians(), Rotation2d.fromDegrees(114    ).getRadians()))));
        aprilTags.add(new AprilTag("Lower Hub Blue Exit",           41, new Pose3d(Units.inchesToMeters(292.568),      Units.inchesToMeters(148.005),     Units.inchesToMeters(27.688),     new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(0    ).getRadians(), Rotation2d.fromDegrees(204    ).getRadians()))));
        aprilTags.add(new AprilTag("Lower Hub Near Exit",           42, new Pose3d(Units.inchesToMeters(337.995),      Units.inchesToMeters(130.568),     Units.inchesToMeters(27.688),     new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(0    ).getRadians(), Rotation2d.fromDegrees(-66    ).getRadians()))));
        aprilTags.add(new AprilTag("Lower Hub Red Exit",            43, new Pose3d(Units.inchesToMeters(355.432),      Units.inchesToMeters(175.995),     Units.inchesToMeters(27.688),     new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(0    ).getRadians(), Rotation2d.fromDegrees(24     ).getRadians()))));
        aprilTags.add(new AprilTag("Upper Hub Far-Blue",            50, new Pose3d(Units.inchesToMeters(302.324),      Units.inchesToMeters(170.321),     Units.inchesToMeters(95.186),     new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(26.75).getRadians(), Rotation2d.fromDegrees(159    ).getRadians()))));
        aprilTags.add(new AprilTag("Upper Hub Blue-Near",           51, new Pose3d(Units.inchesToMeters(315.679),      Units.inchesToMeters(140.324),     Units.inchesToMeters(95.186),     new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(26.75).getRadians(), Rotation2d.fromDegrees(249    ).getRadians()))));
        aprilTags.add(new AprilTag("Upper Hub Near-Red",            52, new Pose3d(Units.inchesToMeters(345.676),      Units.inchesToMeters(153.679),     Units.inchesToMeters(95.186),     new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(26.75).getRadians(), Rotation2d.fromDegrees(339    ).getRadians()))));
        aprilTags.add(new AprilTag("Upper Hub Red-Far",             53, new Pose3d(Units.inchesToMeters(332.321),      Units.inchesToMeters(183.676),     Units.inchesToMeters(95.186),     new Rotation3d(Rotation2d.fromDegrees(0).getRadians(),       Rotation2d.fromDegrees(26.75).getRadians(), Rotation2d.fromDegrees(69     ).getRadians()))));

        AprilTagFieldLayout layout = new AprilTagFieldLayout(aprilTags, Units.feetToMeters(54.0), Units.feetToMeters(27.0));

        try {
            layout.serialize(TEMP_FILE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void setup(PixelConverter pixelConverter) {
        try {
//            renderTags(AprilTagFieldLayout.fromField(AprilTagFields.k2022RapidReact));
            renderTags(AprilTagFieldLayout.fromFile(Path.of(TEMP_FILE)), pixelConverter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void renderTags(AprilTagFieldLayout layout, PixelConverter pixelConverter) {
        mRays.getChildren().clear();

        for (var tag : layout.getTags()) {
            Pose3d pose = tag.pose;
            Circle rect = new Circle();

            Distance tagX = Distance.fromMeters(pose.getX());
            Distance tagY = Distance.fromMeters(pose.getY());

            Position2dDistance position = new Position2dDistance(tagX, tagY);

            Position2dPixels pixelPose = pixelConverter.convertDistanceToPixels(position);
            rect.setCenterX(pixelPose.mX);
            rect.setCenterY(pixelPose.mY);
            rect.setRadius(pixelConverter.convertFeetToPixels(Distance.fromInches(8.5)));
            rect.setRadius(pixelConverter.convertFeetToPixels(Distance.fromInches(8.6)));
            rect.setFill(Color.BLUE);
            mRays.getChildren().add(rect);

            Text label = new Text(tag.name + "(" + tag.ID + ")");
            label.setX(pixelPose.mX - label.getLayoutBounds().getWidth());
            label.setY(pixelPose.mY);
            label.setTextAlignment(TextAlignment.CENTER);
            label.setFont(Font.font(pixelConverter.convertFeetToPixels(Distance.fromInches(8.5))));
            mRays.getChildren().add(label);

            {

                Distance tagWidth = Distance.fromInches(8.5);
                Distance tagHeight = Distance.fromInches(8.5);

//            tagWidth.divide(tagWidth, 2);

//            Position2dDistance tagPosition = new Position2dDistance(tagX, tagY);
                Distance centeredTagX = tagX.subtract(tagWidth.divide(2));
                Distance centeredTagY = tagY.add(tagWidth.divide(2));

                Position2dPixels centerPixel = pixelConverter.convertDistanceToPixels(new Position2dDistance(centeredTagX, centeredTagY));

                Rectangle rect2 = new Rectangle();
                rect2.setX(centerPixel.mX);
                rect2.setY(centerPixel.mY);
                rect2.setWidth(pixelConverter.convertFeetToPixels(Distance.fromInches(3)));
                rect2.setHeight(pixelConverter.convertFeetToPixels(Distance.fromInches(8.5)));
                double javafxAngle = -(Math.toDegrees(pose.getRotation().getZ()));
                System.out.println(tag.name + " " +  Math.toDegrees(pose.getRotation().getZ()) + " -> " + javafxAngle);
                rect2.setRotate(javafxAngle);
                mRays.getChildren().add(rect2);
            }

        }

    }
}
