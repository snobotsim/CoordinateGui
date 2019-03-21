package org.snobot.coordinate_gui.shuffleboard.data;

import java.util.Map;
import java.util.function.Function;

import edu.wpi.first.shuffleboard.api.data.ComplexDataType;

public class PurePursuitDataType extends ComplexDataType<PurePursuitData>
{

    public static final String NAME = SmartDashboardNames.sPURE_PERSUIT_TABLE_TYPE;

    public PurePursuitDataType()
    {
        super(NAME, PurePursuitData.class);
    }

    @Override
    public Function<Map<String, Object>, PurePursuitData> fromMap()
    {
        return map ->
        {
            return new PurePursuitData(map);
        };
    }

    @Override
    public PurePursuitData getDefaultValue()
    {
        return new PurePursuitData();
    }
}
