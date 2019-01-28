package org.snobot.coordinate_gui.shuffleboard.data;

import java.util.Map;
import java.util.function.Function;

import edu.wpi.first.shuffleboard.api.data.ComplexDataType;

public class TrajectoryDataType extends ComplexDataType<TrajectoryData>
{
    public static final String NAME = SmartDashboardNames.sSPLINE_NAMESPACE;

    public TrajectoryDataType()
    {
        super(NAME, TrajectoryData.class);
    }

    @Override
    public Function<Map<String, Object>, TrajectoryData> fromMap()
    {
        return map ->
        {
            return new TrajectoryData(map);
        };
    }

    @Override
    public TrajectoryData getDefaultValue()
    {
        return new TrajectoryData();
    }
}
