package org.snobot.coordinate_gui.shuffleboard.data;

import java.util.Map;
import java.util.function.Function;

import edu.wpi.first.shuffleboard.api.data.ComplexDataType;

public class CoordinateGuiDataType extends ComplexDataType<CoordinateGuiData>
{
    private static final String NAME = SmartDashboardNames.sCOORDINATE_GUI_TABLE_TYPE;

    public CoordinateGuiDataType()
    {
        super(NAME, CoordinateGuiData.class);
    }

    @Override
    public Function<Map<String, Object>, CoordinateGuiData> fromMap()
    {
        return map ->
        {
            return new CoordinateGuiData(map);
        };
    }

    @Override
    public CoordinateGuiData getDefaultValue()
    {
        return new CoordinateGuiData();
    }

}
