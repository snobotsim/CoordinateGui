package org.snobot.coordinate_gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.DataProvider;
import org.snobot.coordinate_gui.powerup.CoordinateGui2018;
import org.snobot.coordinate_gui.trajectory_gen.BulkGenerateAction;
import org.snobot.coordinate_gui.trajectory_gen.TrajectoryGeneratorPanel;
import org.snobot.coordinate_gui.ui.layers.CreatePointsLayer;
import org.snobot.coordinate_gui.ui.render_props.CreatePointsLayerRenderProps;

public final class Main
{
    /**
     * Constructor.
     */
    private Main()
    {
        CreatePointsLayerRenderProps createTrajectoryLayerRenderProps = new CreatePointsLayerRenderProps();
        DataProvider<Coordinate> previewTrajectoryDataProvider = new DataProvider<>();

        GuiProperties properties = new GuiProperties();
        BaseYearSpecificGui coordinateGuiPanel = new CoordinateGui2018();
        double robotWheelBase = 12; // inches

        CreatePointsLayer createTrajectoryLayer = new CreatePointsLayer(coordinateGuiPanel.mLayerManager,
                coordinateGuiPanel.getTrajectoryWaypointDataProvider(),
                previewTrajectoryDataProvider, createTrajectoryLayerRenderProps, coordinateGuiPanel.mConverter);

        coordinateGuiPanel.mLayerManager.addLayer(createTrajectoryLayer);

        JPanel configPanel = new JPanel();
        configPanel.add(new TrajectoryGeneratorPanel(properties, coordinateGuiPanel.mLayerManager,
                coordinateGuiPanel.getTrajectoryWaypointDataProvider(), coordinateGuiPanel.getTrajectoryDataProvider(), robotWheelBase));

        JFrame frame = new JFrame();
        frame.add(coordinateGuiPanel.getComponent(), BorderLayout.CENTER);
        frame.add(configPanel, BorderLayout.EAST);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 800));

        JMenuBar menuBar = new JMenuBar();
        JMenu generateMenu = new JMenu("Bulk Generate");
        JMenuItem generateItem = new JMenuItem(new BulkGenerateAction(properties, robotWheelBase));
        generateMenu.add(generateItem);
        menuBar.add(generateMenu);

        frame.setJMenuBar(menuBar);
    }

    public static void main(String[] aArgs)
    {
        new Main();
    }

}
