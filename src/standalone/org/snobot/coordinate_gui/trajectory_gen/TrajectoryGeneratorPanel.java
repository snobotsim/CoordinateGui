package org.snobot.coordinate_gui.trajectory_gen;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.snobot.coordinate_gui.GuiProperties;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.DataProvider;
import org.snobot.coordinate_gui.model.DataProviderListener;
import org.snobot.coordinate_gui.ui.layers.LayerManager;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.Waypoint;
import com.team254.lib.trajectory.gen.SnobotTrajectoryGen;
import com.team254.lib.trajectory.gen.TrajectoryGenerator.Config;

public class TrajectoryGeneratorPanel extends JPanel
{
    private static final Logger sLOGGER = Logger.getLogger(TrajectoryGeneratorPanel.class);

    private TrajectoryConfigPanel mConfigPanel;
    private JTable mTable;
    private DefaultTableModel mTableModel;
    private final DataProvider<Coordinate> mCoordinateDataProvider;
    private final DataProvider<Coordinate> mTrajectoryDataProvider;
    private final LayerManager mLayerManager;
    private final double mWheelbaseWidth; // Should be consistant with waypoint
                                          // numbers

    private final GuiProperties mGuiProperties;

    /**
     * Constructor.
     * 
     * @param aCoordinateDataProvider
     *            The data provider for coordinates.
     * @param aTrajectoryDataProvider
     *            The data provider for the full trajectory
     */
    public TrajectoryGeneratorPanel(GuiProperties aGuiProperties, LayerManager aLayerManager, DataProvider<Coordinate> aCoordinateDataProvider,
            DataProvider<Coordinate> aTrajectoryDataProvider, double aWheelbaseWidth)
    {
        initComponents();

        mWheelbaseWidth = aWheelbaseWidth;
        mGuiProperties = aGuiProperties;

        mCoordinateDataProvider = aCoordinateDataProvider;
        mTrajectoryDataProvider = aTrajectoryDataProvider;
        mLayerManager = aLayerManager;
        mCoordinateDataProvider.addDataListener(mDataListener);
    }

    /**
     * Saves the drawn trajectory.
     */
    private void onSave()
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(mGuiProperties.getTrajectoryConfigDirectory()));
        chooser.setDialogTitle("");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Yaml Files", "yaml", "yml");
        chooser.setFileFilter(filter);

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = chooser.getSelectedFile();
            writeFile(selectedFile.getAbsolutePath());
            mGuiProperties.setTrajectoryConfigDirectory(selectedFile);
        }
    }

    /**
     * Makes the Load button load selected trajectory.
     */
    private void onLoad()
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(mGuiProperties.getTrajectoryConfigDirectory()));
        chooser.setDialogTitle("");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Yaml Files", "yaml", "yml");
        chooser.setFileFilter(filter);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = chooser.getSelectedFile();
            loadFile(selectedFile.getAbsolutePath());
            mGuiProperties.setTrajectoryConfigDirectory(selectedFile);
        }
    }

    private void writeFile(String aPath)
    {

        try (FileWriter fileWriter = new FileWriter(aPath))
        {
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml yaml = new Yaml(options);
            Map<String, Object> config = new HashMap<>();
            config.put("config", mConfigPanel.getConfig());
            config.put("waypoints", mCoordinateDataProvider.getAllData());

            yaml.dump(config, fileWriter);
        }
        catch (Exception ex)
        {
            sLOGGER.log(Level.ERROR, ex);
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFile(String aPath)
    {
        onReset();

        TrajectoryConfigLoader configLoader = new TrajectoryConfigLoader();
        configLoader.loadFile(aPath);

        mConfigPanel.setConfig(configLoader.getConfig());

        for (Coordinate coord : configLoader.getCoordinates())
        {
            mCoordinateDataProvider.addData(coord);
        }

        mLayerManager.render();
    }

    /**
     * Resets the entire table.
     */
    private void onReset()
    {
        mCoordinateDataProvider.clear();
        mTrajectoryDataProvider.clear();
        mLayerManager.render();

        while (mTableModel.getRowCount() > 0)
        {
            mTableModel.removeRow(0);
        }
    }

    /**
     * Deletes selected rows.
     */
    private void onDelete()
    {
        int rowNumbers = mTable.getSelectedRow();

        while (rowNumbers != -1)
        {
            int modelRow = mTable.convertRowIndexToModel(rowNumbers);
            mTableModel.removeRow(modelRow);
            rowNumbers = mTable.getSelectedRow();
        }
        mLayerManager.render();
    }

    private void onGenerate()
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(mGuiProperties.getTrajectoryDumpPathDirectory()));
        chooser.setDialogTitle("");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        chooser.setFileFilter(filter);

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            Config config = mConfigPanel.getConfig();
            SnobotTrajectoryGen gen = new SnobotTrajectoryGen();

            List<Waypoint> waypointSequence = new ArrayList<>();
            for (Coordinate coord : mCoordinateDataProvider.getAllData())
            {
                waypointSequence.add(new Waypoint(coord.mX * 12, coord.mY * 12, coord.mAngle));
            }

            File selectedFile = chooser.getSelectedFile();
            Path path = gen.generate(config, waypointSequence, selectedFile, "GeneratedTrajectory", mWheelbaseWidth);
            plotTrajectory(path);
            sLOGGER.log(Level.INFO, "Trajectory will take " + path.getLeftWheelTrajectory().getNumSegments() * config.dt + " seconds to complete");

            mGuiProperties.setTrajectoryDumpPath(selectedFile);
        }
    }

    private void plotTrajectory(Path aPath)
    {
        Trajectory left = aPath.getLeftWheelTrajectory();
        Trajectory right = aPath.getRightWheelTrajectory();

        for (int i = 0; i < left.getNumSegments(); ++i)
        {
            double averageX = (left.getSegment(i).y + right.getSegment(i).y) / 2;
            double averageY = (left.getSegment(i).x + right.getSegment(i).x) / 2;

            mTrajectoryDataProvider.addData(new Coordinate(averageX / 12.0, averageY / 12.0, right.getSegment(i).heading));
        }
    }

    private void initComponents()
    {
        mTableModel = new DefaultTableModel(new Object[][]
        {}, new Object[]
        { "X", "Y", "Angle" });
        mTable = new JTable(mTableModel);
        mTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        mConfigPanel = new TrajectoryConfigPanel();

        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();

        JButton resetButton = new JButton("Reset");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton deleteButton = new JButton("Delete");
        JButton generateButton = new JButton("Generate");

        buttonPanel.add(resetButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(generateButton);

        JScrollPane scrollPane = new JScrollPane(mTable);

        add(mConfigPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        saveButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent aEvent)
            {
                onSave();
            }
        });

        resetButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent aEvent)
            {
                onReset();
            }
        });

        loadButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent aEvent)
            {
                onLoad();
            }
        });

        deleteButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent aEvent)
            {
                onDelete();
            }
        });

        generateButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent aEvent)
            {
                onGenerate();
            }
        });
    }

    private final DataProviderListener<Coordinate> mDataListener = new DataProviderListener<Coordinate>()
    {

        @Override
        public void onDataAdded(Coordinate aData)
        {
            mTableModel.addRow(new Object[]
            { aData.mX, aData.mY, aData.mAngle });
        }
    };
}
