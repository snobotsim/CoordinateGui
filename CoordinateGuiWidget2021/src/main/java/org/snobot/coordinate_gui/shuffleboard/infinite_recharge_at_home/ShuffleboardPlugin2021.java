package org.snobot.coordinate_gui.shuffleboard.infinite_recharge_at_home;

import java.util.List;

import org.snobot.coordinate_gui.game.infinite_recharge_at_home.GalacticSearchBController;
import org.snobot.coordinate_gui.shuffleboard.data.CoordinateDataType;
import org.snobot.coordinate_gui.shuffleboard.data.PurePursuitDataType;
import org.snobot.coordinate_gui.shuffleboard.data.RamseteControllerDataType;
import org.snobot.coordinate_gui.shuffleboard.data.TrajectoryDataType;
import org.snobot.coordinate_gui.shuffleboard.data.VisionDataType;
import org.snobot.coordinate_gui.shuffleboard.infinite_recharge_at_home.data.InfiniteRechargeCoordinateGuiDataType;
import org.snobot.coordinate_gui.shuffleboard.widgets.PurePursuitPlotsWidget;
import org.snobot.coordinate_gui.shuffleboard.widgets.RamsetePlotsWidget;
import org.snobot.coordinate_gui.shuffleboard.widgets.TrajectoryPlotsWidget;

import com.google.common.collect.ImmutableList;

import edu.wpi.first.shuffleboard.api.data.DataType;
import edu.wpi.first.shuffleboard.api.plugin.Description;
import edu.wpi.first.shuffleboard.api.plugin.Plugin;
import edu.wpi.first.shuffleboard.api.widget.ComponentType;
import edu.wpi.first.shuffleboard.api.widget.WidgetType;

@Description(group = "org.snobot.coordinate_gui", name = "CoordinateGuiPlugins2021", version = PluginVersion.VERSION, summary = "Coordinate GUI Utilities")
public class ShuffleboardPlugin2021 extends Plugin
{

    @Override
    public List<ComponentType> getComponents()
    {
        return ImmutableList.of(
//                WidgetType.forAnnotatedWidget(BarrelPathGuiWidget2021.class),
//                WidgetType.forAnnotatedWidget(BouncePathGuiWidget2021.class),
//                WidgetType.forAnnotatedWidget(GalacticSearchAGuiWidget2021.class),
//                WidgetType.forAnnotatedWidget(GalacticSearchBGuiWidget2021.class),
                WidgetType.forAnnotatedWidget(InfiniteRechargeGuiWidget2021.class),
//                WidgetType.forAnnotatedWidget(SlalomPathGuiWidget2021.class),

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
