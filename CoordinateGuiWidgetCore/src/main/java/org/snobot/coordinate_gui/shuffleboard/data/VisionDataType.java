package org.snobot.coordinate_gui.shuffleboard.data;

import java.util.Map;
import java.util.function.Function;

import edu.wpi.first.shuffleboard.api.data.ComplexDataType;

public final class VisionDataType extends ComplexDataType<VisionData>
{
    public static final VisionDataType INSTANCE = new VisionDataType();
    public static final String NAME = SmartDashboardNames.sCAMERA_RAY_TABLE_TYPE;

    private VisionDataType()
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
