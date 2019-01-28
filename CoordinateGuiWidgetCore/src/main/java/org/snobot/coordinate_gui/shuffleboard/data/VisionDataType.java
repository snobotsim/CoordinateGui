package org.snobot.coordinate_gui.shuffleboard.data;

import java.util.Map;
import java.util.function.Function;

import edu.wpi.first.shuffleboard.api.data.ComplexDataType;

public class VisionDataType extends ComplexDataType<VisionData>
{
    public static final String NAME = SmartDashboardNames.sCAMERA_RAY_TABLE_TYPE;

    public VisionDataType()
    {
        super(NAME, VisionData.class);
    }

    @Override
    public Function<Map<String, Object>, VisionData> fromMap()
    {
        return map ->
        {
            return new VisionData(map);
        };
    }

    @Override
    public VisionData getDefaultValue()
    {
        return new VisionData();
    }
}
