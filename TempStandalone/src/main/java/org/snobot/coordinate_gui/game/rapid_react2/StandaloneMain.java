package org.snobot.coordinate_gui.game.rapid_react2;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.apriltag.AprilTagFieldLayout;
import edu.wpi.first.wpilibj.apriltag.AprilTagFields;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.snobot.coordinate_gui.game.rapid_react.RapidReactController;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.ui.layers.CameraRayLayerController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class StandaloneMain
{
    public static class PseudoMain extends Application
    {
        private final PhotonCamera mCam = new PhotonCamera(TempSimulator.CAMERA_NAME);

        private Coordinate mPosition;
        private RapidReactController mRobotController;
        private TempSimulator mSimulator;

        private AprilTagFieldLayout mLayout;

        @Override
        public void start(Stage aPrimaryStage) throws IOException
        {
            final NetworkTableInstance inst = NetworkTableInstance.getDefault(); // NOPMD
            inst.setNetworkIdentity("Robot");
            inst.startServer();

            mLayout = AprilTagFieldLayout.fromField(AprilTagFields.k2022RapidReact);
            mSimulator = new TempSimulator(mLayout);

            FXMLLoader loader = new FXMLLoader(RapidReactController.class.getResource("rapid_react_field.fxml"));
            Pane root = loader.load();
            mRobotController = loader.getController();

            Scene scene = new Scene(root);
            aPrimaryStage.setScene(scene);

            aPrimaryStage.show();

            mPosition = new Coordinate();
            mRobotController.addRobotPosition(mPosition);


            PhotonCamera.setVersionCheckEnabled(false);

            scene.setOnKeyPressed(event ->
            {
                KeyCode code = event.getCode();
                switch (code)
                {
                case A:
                    mPosition.mPosition.mX = mPosition.mPosition.mX.subtract(Distance.fromInches(2));
                    break;
                case D:
                    mPosition.mPosition.mX = mPosition.mPosition.mX.add(Distance.fromInches(2));
                    break;

                case W:
                    mPosition.mPosition.mY = mPosition.mPosition.mY.add(Distance.fromInches(2));
                    break;
                case S:
                    mPosition.mPosition.mY = mPosition.mPosition.mY.subtract(Distance.fromInches(2));
                    break;


                case J:
                    mPosition.mAngle += 2;
                    break;
                case L:
                    mPosition.mAngle -= 2;
                    break;

                default:
                        // ignored
                }

                handleUpdate();
            });
        }



        private void handleUpdate()
        {
            mRobotController.addRobotPosition(mPosition);

            Pose2d pose = new Pose2d(mPosition.mPosition.mX.asMeters(), mPosition.mPosition.mY.asMeters(), Rotation2d.fromDegrees(mPosition.mAngle));
            mSimulator.updatePose(pose);

            PhotonPipelineResult res = mCam.getLatestResult();

            List<CameraRayLayerController.Ray> rays = new ArrayList<>();

            if (res.hasTargets())
            {
                for (var target : res.getTargets())
                {
                    var camToTargetTrans = target.getCameraToTarget();

                    var camPose = mLayout.getTagPose(target.getFiducialId()).transformBy(camToTargetTrans.inverse());
                    Pose2d xxx =
                            camPose.transformBy(new Transform3d()).toPose2d();

                    rays.add(new CameraRayLayerController.Ray(mPosition.mPosition, new Position2dDistance(xxx.getX(), xxx.getY(), Distance.Unit.Meters)));

                }
            }

            mRobotController.setCameraRays(rays);
//            System.out.println(result.hasTargets() + ",, " + result.getTargets());
        }
    }

    private StandaloneMain()
    {

    }

    @SuppressWarnings("JavadocMethod")
    public static void main(String[] aArgs)
    {
        // JavaFX 11+ uses GTK3 by default, and has problems on some display
        // servers
        // This flag forces JavaFX to use GTK2
        // System.setProperty("jdk.gtk.version", "2");
        Application.launch(PseudoMain.class, aArgs);
    }
}
