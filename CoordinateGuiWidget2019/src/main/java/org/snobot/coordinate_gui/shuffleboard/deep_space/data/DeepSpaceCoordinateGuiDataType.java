package org.snobot.coordinate_gui.shuffleboard.deep_space.data;

import java.util.Map;
import java.util.function.Function;

import edu.wpi.first.shuffleboard.api.data.ComplexDataType;
import org.snobot.coordinate_gui.shuffleboard.data.SmartDashboardNames;

public final class DeepSpaceCoordinateGuiDataType extends ComplexDataType<DeepSpaceCoordinateGuiData>
{
    public static final DeepSpaceCoordinateGuiDataType INSTANCE = new DeepSpaceCoordinateGuiDataType();
    private static final String NAME = SmartDashboardNames.sCOORDINATE_GUI_TABLE_TYPE;

    private DeepSpaceCoordinateGuiDataType()
    {
        super(NAME, DeepSpaceCoordinateGuiData.class);
    }

    @Override
    public Function<Map<String, Object>, DeepSpaceCoordinateGuiData> fromMap()
    {
        return map ->
            new DeepSpaceCoordinateGuiData(map);
    }

    @Override
    public DeepSpaceCoordinateGuiData getDefaultValue()
    {
        return new DeepSpaceCoordinateGuiData();
    }

}
