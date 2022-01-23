package org.snobot.coordinate_gui.shuffleboard.rapid_react.data;

import java.util.Map;
import java.util.function.Function;

import edu.wpi.first.shuffleboard.api.data.ComplexDataType;
import org.snobot.coordinate_gui.shuffleboard.data.SmartDashboardNames;

public final class RapidReactCoordinateGuiDataType extends ComplexDataType<RapidReactCoordinateGuiData>
{
    public static final RapidReactCoordinateGuiDataType INSTANCE = new RapidReactCoordinateGuiDataType();
    private static final String NAME = SmartDashboardNames.sCOORDINATE_GUI_TABLE_TYPE;

    private RapidReactCoordinateGuiDataType()
    {
        super(NAME, RapidReactCoordinateGuiData.class);
    }

    @Override
    public Function<Map<String, Object>, RapidReactCoordinateGuiData> fromMap()
    {
        return map ->
            new RapidReactCoordinateGuiData(map);
    }

    @Override
    public RapidReactCoordinateGuiData getDefaultValue()
    {
        return new RapidReactCoordinateGuiData();
    }

}
