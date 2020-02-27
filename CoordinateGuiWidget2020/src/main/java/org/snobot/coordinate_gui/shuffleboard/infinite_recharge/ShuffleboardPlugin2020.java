package org.snobot.coordinate_gui.shuffleboard.infinite_recharge;

import java.util.List;

import org.snobot.coordinate_gui.shuffleboard.data.CoordinateDataType;
import org.snobot.coordinate_gui.shuffleboard.data.PurePursuitDataType;
import org.snobot.coordinate_gui.shuffleboard.data.RamseteControllerDataType;
import org.snobot.coordinate_gui.shuffleboard.data.TrajectoryDataType;
import org.snobot.coordinate_gui.shuffleboard.data.VisionDataType;
import org.snobot.coordinate_gui.shuffleboard.infinite_recharge.data.InfiniteRechargeCoordinateGuiDataType;
import org.snobot.coordinate_gui.shuffleboard.widgets.PurePursuitPlotsWidget;
import org.snobot.coordinate_gui.shuffleboard.widgets.RamsetePlotsWidget;
import org.snobot.coordinate_gui.shuffleboard.widgets.TrajectoryPlotsWidget;

import com.google.common.collect.ImmutableList;

import edu.wpi.first.shuffleboard.api.data.DataType;
import edu.wpi.first.shuffleboard.api.plugin.Description;
import edu.wpi.first.shuffleboard.api.plugin.Plugin;
import edu.wpi.first.shuffleboard.api.widget.ComponentType;
import edu.wpi.first.shuffleboard.api.widget.WidgetType;

@Description(group = "org.snobot.coordinate_gui", name = "CoordinateGuiPlugins2020", version = PluginVersion.VERSION, summary = "Coordinate GUI Utilities")
public class ShuffleboardPlugin2020 extends Plugin
{

    @Override
    public List<ComponentType> getComponents()
    {
        return ImmutableList.of(
                WidgetType.forAnnotatedWidget(CoordinateGuiWidget2020.class),
                WidgetType.forAnnotatedWidget(TrajectoryPlotsWidget.class),
                WidgetType.forAnnotatedWidget(PurePursuitPlotsWidget.class),
                WidgetType.forAnnotatedWidget(RamsetePlotsWidget.class));

    }

    @Override
    public List<DataType> getDataTypes()
    {
        return ImmutableList.of(
                InfiniteRechargeCoordinateGuiDataType.INSTANCE,
                CoordinateDataType.INSTANCE,
                VisionDataType.INSTANCE,
                TrajectoryDataType.INSTANCE,
                PurePursuitDataType.INSTANCE,
                RamseteControllerDataType.INSTANCE);
    }

}
