package com.snobot.coordinate_gui;

import java.awt.Color;

import javax.swing.JFrame;

import com.snobot.coordinate_gui.steamworks.CoordinateGui2017;
import com.snobot.coordinate_gui.ui.renderProps.CoordinateLayerRenderProps;
import com.snobot.coordinate_gui.ui.renderProps.RobotLayerRenderProps;

public class Main
{
    public Main()
    {
        CoordinateLayerRenderProps trajectoryLayerRenderProps = new CoordinateLayerRenderProps();
        CoordinateLayerRenderProps coordinateLayerRenderProps = new CoordinateLayerRenderProps();
        RobotLayerRenderProps robotLayerRenderProps = new RobotLayerRenderProps();

        trajectoryLayerRenderProps.setFadeOverTime(false);
        trajectoryLayerRenderProps.setPointSize(5);
        trajectoryLayerRenderProps.setPointMemory(-1);
        trajectoryLayerRenderProps.setPointColor(Color.red);

        CoordinateGui2017 coordinateGuiPanel = new CoordinateGui2017(trajectoryLayerRenderProps, coordinateLayerRenderProps, robotLayerRenderProps);

        JFrame frame = new JFrame();
        frame.getContentPane().add(coordinateGuiPanel.getComponent());
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        new Main();
    }

}
