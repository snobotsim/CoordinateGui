package org.snobot.coordinate_gui.game.rapid_react2;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.apriltag.AprilTagFieldLayout;
import org.photonvision.SimVisionSystem2;
import org.photonvision.SimVisionTarget2;

@SuppressWarnings("PMD")
public class TempSimulator
{

    public static final String CAMERA_NAME = "Camera";

    private final double mCamDiagFOV = 75.0; // degrees
    private final double mCamPitch = 15.0; // degrees
    private final double mCamHeightOffGround = 0.85; // meters
    private final double mMaxLEDRange = 20; // meters
    private final int mCamResolutionWidth = 640; // pixels
    private final int mCamResolutionHeight = 480; // pixels
    private final double mMinTargetArea = 10; // square pixels

    private final SimVisionSystem2 mSimVision =
            new SimVisionSystem2(
                    CAMERA_NAME,
                    mCamDiagFOV,
                    mCamPitch,
                    new Transform2d(),
                    mCamHeightOffGround,
                    mMaxLEDRange,
                    mCamResolutionWidth,
                    mCamResolutionHeight,
                    mMinTargetArea);

    /**
     * Constructor.
     * @param aLayout layout
     */
    public TempSimulator(AprilTagFieldLayout aLayout)
    {
        for (var tag : aLayout.getTags())
        {
            mSimVision.addSimVisionTarget(new SimVisionTarget2(
                    tag.pose.toPose2d(),
                    tag.pose.getZ(),
                    Units.inchesToMeters(8.5),
                    Units.inchesToMeters(8.5),
                    tag.ID
            ));
        }
    }

    public void updatePose(Pose2d aPose)
    {
        mSimVision.processFrame(aPose);
    }
}
