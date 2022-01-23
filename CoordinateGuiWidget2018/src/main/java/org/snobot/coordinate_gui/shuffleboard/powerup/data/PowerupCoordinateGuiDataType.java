package org.snobot.coordinate_gui.shuffleboard.powerup.data;

import java.util.Map;
import java.util.function.Function;

import edu.wpi.first.shuffleboard.api.data.ComplexDataType;
import org.snobot.coordinate_gui.shuffleboard.data.SmartDashboardNames;

public final class PowerupCoordinateGuiDataType extends ComplexDataType<PowerupCoordinateGuiData>
{
    public static final PowerupCoordinateGuiDataType INSTANCE = new PowerupCoordinateGuiDataType();
    private static final String NAME = SmartDashboardNames.sCOORDINATE_GUI_TABLE_TYPE;

    private PowerupCoordinateGuiDataType()
    {
        super(NAME, PowerupCoordinateGuiData.class);
    }

    @Override
    public Function<Map<String, Object>, PowerupCoordinateGuiData> fromMap()
    {
        return map ->
            new PowerupCoordinateGuiData(map);
    }

    @Override
    public PowerupCoordinateGuiData getDefaultValue()
    {
        return new PowerupCoordinateGuiData();
    }

}
