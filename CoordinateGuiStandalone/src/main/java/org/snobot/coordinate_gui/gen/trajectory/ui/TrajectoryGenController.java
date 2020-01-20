package org.snobot.coordinate_gui.gen.trajectory.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;
import jaci.pathfinder.Waypoint;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
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

        if (coordinates != null)
        {
            Waypoint[] pointsArray = new Waypoint[coordinates.size()];
            for (int i = 0; i < coordinates.size(); ++i)
            {
                Coordinate coord = coordinates.get(i);
                pointsArray[i] = new Waypoint(coord.mPosition.mY, coord.mPosition.mX, Math.toRadians(coord.mAngle));
            }

            Trajectory.Config config = new Trajectory.Config(
                    Trajectory.FitMethod.HERMITE_CUBIC, 
                    Trajectory.Config.SAMPLES_FAST, 
                    .02,
                    pathConfig.mMaxVelocity, 
                    pathConfig.mMaxAcceleration, 
                    1e6);

            Trajectory trajectory = Pathfinder.generate(pointsArray, config);

            List<Coordinate> idealCoordinates = new ArrayList<>();
            for (Segment segment : trajectory.segments)
            {
                idealCoordinates.add(new Coordinate(new Position2dDistance(segment.y, segment.x), Math.toDegrees(segment.heading)));
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
