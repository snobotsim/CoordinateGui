package org.snobot.coordinate_gui.gen.pure_pursuit.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.snobot.coordinate_gui.game.deep_space.DeepSpaceController;
import org.snobot.coordinate_gui.gen.pure_pursuit.model.PurePursuitConfig;
import org.snobot.coordinate_gui.gen.pure_pursuit.model.SavedPurePursuitPreferences;
import org.snobot.coordinate_gui.gen.ui.PathTreeController;
import org.snobot.coordinate_gui.gen.utils.PurePursuitConfigLoader;
import org.snobot.coordinate_gui.model.Coordinate;

import frc.robot.pure_pursuit.PurePursuitPathGenerator;
import frc.robot.pure_pursuit.PurePursuitWaypoint;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class PurePursuitGenController
{
    private static final Logger sLOGGER = LogManager.getLogger(PurePursuitGenController.class);

    @FXML
    private DeepSpaceController mFieldController;

    @FXML
    private Pane mField;

    @FXML
    private PathTreeController mPathTreeController;

    @FXML
    private PurePursuitParamsController mPathParamsController;

    private final SavedPurePursuitPreferences mPreferences;

    private List<Coordinate> mActiveWaypoints;

    public PurePursuitGenController()
    {
        mPreferences = SavedPurePursuitPreferences.load();
    }

    /**
     * Called after JavaFX initialization.
     */
    @FXML
    public void initialize()
    {
        mField.setOnDragDone(event ->
        {
            generatePath();
        });

        mPathParamsController.setParams(mPreferences.getDefaultConfig());
        mPathParamsController.addChangedListener(() -> generatePath());

        mPathTreeController.setAddPathListener(mPreferences.getWaypointsDirectory(), this::loadPathFile);
        mPathTreeController.setuPathsView(mPreferences.getWaypointsDirectory());
    }

    private void loadPathFile(File aFile)
    {
        PurePursuitConfigLoader configLoader = new PurePursuitConfigLoader();
        configLoader.loadFile(aFile);

        mActiveWaypoints = configLoader.getCoordinates();
        mPathParamsController.setParams(configLoader.getConfig());
        generatePath();
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
    }

    private List<PurePursuitWaypoint> convertToWaypoint(List<Coordinate> aInput)
    {
        List<PurePursuitWaypoint> waypoints = new ArrayList<>();
        for (Coordinate coordinate : aInput)
        {
            waypoints.add(new PurePursuitWaypoint(coordinate.mX * 12.0, coordinate.mY * 12));
        }
        
        return waypoints;
    }

    private List<Coordinate> convertToCoordinate(List<PurePursuitWaypoint> aInput)
    {
        List<Coordinate> waypoints = new ArrayList<>();
        for (PurePursuitWaypoint coordinate : aInput)
        {
            waypoints.add(new Coordinate(coordinate.mX / 12, coordinate.mY / 12, 0));
        }

        return waypoints;
    }

    private void generatePath()
    {
        List<Coordinate> coordinates = mActiveWaypoints;
        PurePursuitConfig config = mPathParamsController.getPathParams();

        PurePursuitPathGenerator generator = new PurePursuitPathGenerator(convertToWaypoint(coordinates), config.mUpSampleSpacing, config.mTurnFactor,
                .001);
        mFieldController.setPurePursuitWaypoints(coordinates, convertToCoordinate(generator.getUpsampled()),
                convertToCoordinate(generator.getSmoothed()));
    }
}
