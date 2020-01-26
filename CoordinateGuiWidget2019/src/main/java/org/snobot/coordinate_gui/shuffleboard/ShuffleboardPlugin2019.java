package org.snobot.coordinate_gui.shuffleboard;

import java.util.List;

import org.snobot.coordinate_gui.shuffleboard.data.CoordinateDataType;
import org.snobot.coordinate_gui.shuffleboard.data.DeepSpaceCoordinateGuiDataType;
import org.snobot.coordinate_gui.shuffleboard.data.PurePursuitDataType;
import org.snobot.coordinate_gui.shuffleboard.data.TrajectoryDataType;
import org.snobot.coordinate_gui.shuffleboard.data.VisionDataType;
import org.snobot.coordinate_gui.shuffleboard.widgets.PurePursuitPlotsWidget;
import org.snobot.coordinate_gui.shuffleboard.widgets.TrajectoryPlotsWidget;

import com.google.common.collect.ImmutableList;

import edu.wpi.first.shuffleboard.api.data.DataType;
import edu.wpi.first.shuffleboard.api.plugin.Description;
import edu.wpi.first.shuffleboard.api.plugin.Plugin;
import edu.wpi.first.shuffleboard.api.widget.ComponentType;
import edu.wpi.first.shuffleboard.api.widget.WidgetType;

@Description(group = "org.snobot.coordinate_gui", name = "CoordinateGuiPlugins", version = PluginVersion.VERSION, summary = "Coordinate GUI Utilities")
public class ShuffleboardPlugin2019 extends Plugin
{

    @Override
    public List<ComponentType> getComponents()
    {
        return ImmutableList.of(
                WidgetType.forAnnotatedWidget(CoordinateGuiWidget2019.class),
                WidgetType.forAnnotatedWidget(TrajectoryPlotsWidget.class),
                WidgetType.forAnnotatedWidget(PurePursuitPlotsWidget.class));

    }

    @Override
    public List<DataType> getDataTypes()
    {
        return ImmutableList.of(
                DeepSpaceCoordinateGuiDataType.INSTANCE, CoordinateDataType.INSTANCE, VisionDataType.INSTANCE, TrajectoryDataType.INSTANCE, PurePursuitDataType.INSTANCE);
    }

}
