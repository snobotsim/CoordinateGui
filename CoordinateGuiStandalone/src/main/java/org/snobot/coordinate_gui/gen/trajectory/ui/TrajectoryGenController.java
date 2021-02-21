package org.snobot.coordinate_gui.gen.trajectory.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.snobot.coordinate_gui.game.deep_space.DeepSpaceController;
import org.snobot.coordinate_gui.gen.trajectory.model.SavedTrajectoryPreferences;
import org.snobot.coordinate_gui.gen.trajectory.model.TrajectoryConfig;
import org.snobot.coordinate_gui.gen.ui.PathTreeController;
import org.snobot.coordinate_gui.gen.utils.PathfinderConfigLoader;
import org.snobot.coordinate_gui.gen.utils.TrajectoryConfigLoader;
import org.snobot.coordinate_gui.model.Coordinate;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Position2dDistance;

public class TrajectoryGenController
{
    private static final Logger sLOGGER = LogManager.getLogger(TrajectoryGenController.class);

    @FXML
    private TrajectoryParamsController mPathParamsController;

    @FXML
    private DeepSpaceController mFieldController;

    @FXML
    private Pane mField;

    @FXML
    private PathTreeController mPathTreeController;

    private final SavedTrajectoryPreferences mPreferences;

    public TrajectoryGenController()
    {
        mPreferences = SavedTrajectoryPreferences.load();
    }

    /**
     * Called after JavaFX initialization.
     */
    @FXML
    public void initialize()
    {
        new TrajectoryConfigDragHandler(mField, mFieldController);

        mField.setOnDragDone(event ->
        {
            generatePath();
        });

        mPathParamsController.setPathParams(mPreferences.getDefaultPathConfig());

        mPathTreeController.setAddPathListener(mPreferences.getTrajectoryDirectory(), this::loadPathFile);
        mPathTreeController.setuPathsView(mPreferences.getTrajectoryDirectory());
    }

    /**
     * Called when the user wants to load a path.
     */
    @FXML
    public void handleLoadPath()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File(mPreferences.getTrajectoryDirectory()));
        File choosenFile = fileChooser.showOpenDialog(null);
        if (choosenFile != null)
        {
            loadPathFile(choosenFile);
            generatePath();
        }
    }

    /**
     * Called when the user wants to load a pathfinder file.
     */
    @FXML
    public void handlePathfinderLoad()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Pathfinder File");
        File choosenFile = fileChooser.showOpenDialog(null);
        if (choosenFile != null)
        {
            loadPathfinderFile(choosenFile.toPath());
        }
    }

    /**
     * Called when the build load button is clicked.
     */
    @FXML
    public void handleBulkPathfinderLoad()
    {
        DirectoryChooser fileChooser = new DirectoryChooser();
        fileChooser.setTitle("Open Pathfinder File");
        File choosenFile = fileChooser.showDialog(null);
        if (choosenFile != null)
        {
            try
            {
                Files.walk(choosenFile.toPath()).filter(Files::isRegularFile).forEach(this::loadPathfinderFile);
            }
            catch (IOException ex)
            {
                sLOGGER.log(Level.ERROR, "Error crawling pathfinder directory", ex);
            }
        }
    }

    private void loadPathfinderFile(Path aPath)
    {
        Path fileName = aPath.getFileName();
        if (fileName == null)
        {
            sLOGGER.log(Level.ERROR, "Error getting path name from " + aPath);
            return;
        }

        Path copiedPath = Paths.get(mPreferences.getTrajectoryDirectory(), fileName.toString());
        PathfinderConfigLoader configLoader = new PathfinderConfigLoader();
        List<Coordinate> waypoints = configLoader.loadPathweaverFile(aPath.toFile());
        new TrajectoryConfigLoader().dumpFile(copiedPath.toFile(), waypoints, mPreferences.getDefaultPathConfig());

        mFieldController.setWaypoints(waypoints);
        mPathTreeController.handleNewPath(copiedPath);
        generatePath();
    }

    private void loadPathFile(File aPath)
    {
        TrajectoryConfigLoader configLoader = new TrajectoryConfigLoader();
        configLoader.loadFile(aPath);

        mPathParamsController.setPathParams(configLoader.getConfig());
        mFieldController.setWaypoints(configLoader.getCoordinates());
        generatePath();
    }

    private void generatePath()
    {
        List<Coordinate> coordinates = mFieldController.getWaypoints();
        TrajectoryConfig pathConfig = mPathParamsController.getPathParams();

        Distance.Unit unit = Distance.Unit.INCH;

        if (coordinates != null)
        {
            List<Pose2d> poses = new ArrayList<>();
            for (Coordinate coordinate : coordinates)
            {
                poses.add(new Pose2d(coordinate.mPosition.mX.as(unit), coordinate.mPosition.mY.as(unit), Rotation2d.fromDegrees(coordinate.mAngle)));
            }
            Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
                    poses.get(0),
                    List.of(),
                    poses.get(poses.size() - 1),
                    new edu.wpi.first.wpilibj.trajectory.TrajectoryConfig(pathConfig.mMaxVelocity, pathConfig.mMaxAcceleration)
            );

            List<Coordinate> idealCoordinates = new ArrayList<>();
            for (Trajectory.State segment : trajectory.getStates())
            {
                idealCoordinates.add(new Coordinate(new Position2dDistance(segment.poseMeters.getX(), segment.poseMeters.getY(), Distance.Unit.FEET), segment.poseMeters.getRotation().getDegrees()));
            }

            mFieldController.setIdealTrajectory(idealCoordinates);
            mField.requestLayout();
        }
    }

    public void handleSavePath()
    {
        generatePath();
    }
}
