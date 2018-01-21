package org.snobot.coordinate_gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.DataProvider;
import org.snobot.coordinate_gui.powerup.CoordinateGui2018;
import org.snobot.coordinate_gui.trajectory_gen.TrajectoryGeneratorPanel;
import org.snobot.coordinate_gui.ui.layers.CreatePointsLayer;
import org.snobot.coordinate_gui.ui.renderProps.CreatePointsLayerRenderProps;

public class Main
{
    public Main()
    {
        CreatePointsLayerRenderProps createTrajectoryLayerRenderProps = new CreatePointsLayerRenderProps();
        DataProvider<Coordinate> createTrajectoryDataProvider = new DataProvider<>();
        DataProvider<Coordinate> previewTrajectoryDataProvider = new DataProvider<>();

        CoordinateGui2018 coordinateGuiPanel = new CoordinateGui2018();

        CreatePointsLayer createTrajectoryLayer = new CreatePointsLayer(coordinateGuiPanel.mLayerManager, createTrajectoryDataProvider,
                previewTrajectoryDataProvider, createTrajectoryLayerRenderProps, coordinateGuiPanel.mConverter);

        coordinateGuiPanel.mLayerManager.addLayer(createTrajectoryLayer);

        JPanel configPanel = new JPanel();
        configPanel.add(new TrajectoryGeneratorPanel(createTrajectoryDataProvider));

        JFrame frame = new JFrame();
        frame.add(coordinateGuiPanel.getComponent(), BorderLayout.CENTER);
        frame.add(configPanel, BorderLayout.EAST);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(400, 400));
    }

    public static void main(String[] args)
    {
        new Main();
    }

}
