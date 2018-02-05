package org.snobot.coordinate_gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.PropertyConfigurator;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.DataProvider;
import org.snobot.coordinate_gui.steamworks.CoordinateGui2017;
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
        PropertyConfigurator.configure(Main.class.getClassLoader().getResource("org/snobot/coordinate_gui/log4j.properties"));

        CreatePointsLayerRenderProps createTrajectoryLayerRenderProps = new CreatePointsLayerRenderProps();
        DataProvider<Coordinate> previewTrajectoryDataProvider = new DataProvider<>();

        BaseYearSpecificGui coordinateGuiPanel = new CoordinateGui2017();

        CreatePointsLayer createTrajectoryLayer = new CreatePointsLayer(coordinateGuiPanel.mLayerManager,
                coordinateGuiPanel.getTrajectoryWaypointDataProvider(),
                previewTrajectoryDataProvider, createTrajectoryLayerRenderProps, coordinateGuiPanel.mConverter);

        coordinateGuiPanel.mLayerManager.addLayer(createTrajectoryLayer);

        JPanel configPanel = new JPanel();
        configPanel.add(new TrajectoryGeneratorPanel(coordinateGuiPanel.mLayerManager, coordinateGuiPanel.getTrajectoryWaypointDataProvider()));

        JFrame frame = new JFrame();
        frame.add(coordinateGuiPanel.getComponent(), BorderLayout.CENTER);
        frame.add(configPanel, BorderLayout.EAST);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 800));
    }

    public static void main(String[] aArgs)
    {
        new Main();
    }

}
