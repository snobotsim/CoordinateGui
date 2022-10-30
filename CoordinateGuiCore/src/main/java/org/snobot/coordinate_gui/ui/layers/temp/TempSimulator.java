package org.snobot.coordinate_gui.ui.layers.temp;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.apriltag.AprilTagFieldLayout;
import org.photonvision.SimVisionSystem;
import org.photonvision.SimVisionSystem2;
import org.photonvision.SimVisionTarget2;

import java.io.IOException;
import java.nio.file.Path;

import static org.snobot.coordinate_gui.ui.layers.AprilTagController.TEMP_FILE;

public class TempSimulator {

    double camDiagFOV = 75.0; // degrees
    double camPitch = 15.0; // degrees
    double camHeightOffGround = 0.85; // meters
    double maxLEDRange = 20; // meters
    int camResolutionWidth = 640; // pixels
    int camResolutionHeight = 480; // pixels
    double minTargetArea = 10; // square pixels

    public static final String camName = "Camera";

    SimVisionSystem2 simVision =
            new SimVisionSystem2(
                    camName,
                    camDiagFOV,
                    camPitch,
                    new Transform2d(),
                    camHeightOffGround,
                    maxLEDRange,
                    camResolutionWidth,
                    camResolutionHeight,
                    minTargetArea);

    public TempSimulator() {

        try {
            AprilTagFieldLayout layout = AprilTagFieldLayout.fromFile(Path.of(TEMP_FILE));

            for (var tag : layout.getTags()) {
                simVision.addSimVisionTarget(new SimVisionTarget2(
                        tag.pose.toPose2d(),
                        tag.pose.getZ(),
                        Units.inchesToMeters(8.5),
                        Units.inchesToMeters(8.5),
                        tag.ID
                ));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void updatePose(Pose2d pose) {
        simVision.processFrame(pose);
    }
}
