package org.snobot.coordinate_gui.shuffleboard.data;

import java.util.Map;
import java.util.function.Function;

import edu.wpi.first.shuffleboard.api.data.ComplexDataType;

public final class CoordinateDataType extends ComplexDataType<CoordinateData>
{
    public static final CoordinateDataType INSTANCE = new CoordinateDataType();

    public static final String NAME = SmartDashboardNames.sROBOT_POSITION_TABLE_TYPE;

    private CoordinateDataType()
    {
        super(NAME, CoordinateData.class);
    }

    @Override
    public Function<Map<String, Object>, CoordinateData> fromMap()
    {
        return map ->
        {
            return new CoordinateData(map);
        };
    }

    @Override
    public CoordinateData getDefaultValue()
    {
        return new CoordinateData();
    }

}
