package org.snobot.coordinate_gui.trajectory_gen;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.snobot.coordinate_gui.GuiProperties;
import org.snobot.coordinate_gui.model.Coordinate;

import com.team254.lib.trajectory.Waypoint;
import com.team254.lib.trajectory.gen.SnobotTrajectoryGen;

public class BulkGenerateAction extends AbstractAction
{
    private static final Logger sLOGGER = Logger.getLogger(TrajectoryGeneratorPanel.class);

    private final GuiProperties mGuiProperties;
    private final double mRobotWheelBase;

    /**
     * Constructor.
     * 
     * @param aGuiProperties
     *            The properties container for hte GUI
     * @param aRobotWheelBase
     *            The wheelbase of the robot
     */
    public BulkGenerateAction(GuiProperties aGuiProperties, double aRobotWheelBase)
    {
        super("Bulk Generate");
        mGuiProperties = aGuiProperties;
        mRobotWheelBase = aRobotWheelBase;
    }

    private File askForDirectory(String aDefaultPath, String aFilterName, String... aFilterExtensions)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(aDefaultPath));
        chooser.setDialogTitle("");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        FileNameExtensionFilter filter = new FileNameExtensionFilter(aFilterName, aFilterExtensions);
        chooser.setFileFilter(filter);

        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            return chooser.getSelectedFile();
        }
        else
        {
            return null;
        }
    }


    @Override
    public void actionPerformed(ActionEvent aEvent)
    {
        File configDir = askForDirectory(mGuiProperties.getBulkDumpConfigDirectory(), "Yaml Files", "yaml", "yml");
        if (configDir == null)
        {
            return;
        }

        File dumpDir = askForDirectory(mGuiProperties.getBulkDumpOutputDirectory(), "CSV Files", "csv");
        if (dumpDir == null)
        {
            return;
        }

        mGuiProperties.setBulkDumpConfigDirectory(configDir);
        mGuiProperties.setBulkDumpOutputDirectory(dumpDir);

        generate(configDir.getAbsolutePath(), dumpDir.getAbsolutePath());
    }

    private Map<String, String> findFiles(String aInputPath)
    {
        Path startingDir = Paths.get(aInputPath);

        TrajectoryConfigFileCrawler finder = new TrajectoryConfigFileCrawler(startingDir);
        try
        {
            Files.walkFileTree(startingDir, finder);
        }
        catch (IOException ex)
        {
            sLOGGER.log(Level.ERROR, ex);
        }

        return finder.mFiles;
    }

    private void generate(String aInputPath, String aOutputPath)
    {
        Map<String, String> files = findFiles(aInputPath);

        for (Entry<String, String> pair : files.entrySet())
        {
            String configFile = pair.getKey();
            File outputFile = new File(aOutputPath, pair.getValue());

            TrajectoryConfigLoader configLoader = new TrajectoryConfigLoader();
            configLoader.loadFile(configFile);

            SnobotTrajectoryGen gen = new SnobotTrajectoryGen();

            List<Waypoint> waypointSequence = new ArrayList<>();
            for (Coordinate coord : configLoader.getCoordinates())
            {
                waypointSequence.add(new Waypoint(coord.mX * 12, coord.mY * 12, coord.mAngle));
            }

            gen.generate(configLoader.getConfig(), waypointSequence, outputFile, "GeneratedTrajectory", mRobotWheelBase);
        }
    }

    private static class TrajectoryConfigFileCrawler extends SimpleFileVisitor<Path>
    {
        private final Path mInputPath;

        private final Map<String, String> mFiles;

        TrajectoryConfigFileCrawler(Path aInputPath)
        {
            mInputPath = aInputPath;
            mFiles = new HashMap<>();
        }

        // Invoke the pattern matching
        // method on each file.
        @Override
        public FileVisitResult visitFile(Path aFile, BasicFileAttributes aAttrs)
        {

            Path relativePath = mInputPath.relativize(aFile);
            String dumpPath = relativePath.toFile().getPath();

            if (dumpPath.indexOf('.') > 0)
            {
                dumpPath = dumpPath.substring(0, dumpPath.lastIndexOf('.')) + ".csv";
            }

            mFiles.put(aFile.toString(), dumpPath);

            return FileVisitResult.CONTINUE;
        }
    }
}
