package org.snobot.coordinate_gui.shuffleboard.charged_up.data;

import java.util.Map;
import java.util.function.Function;

import edu.wpi.first.shuffleboard.api.data.ComplexDataType;
import org.snobot.coordinate_gui.shuffleboard.data.SmartDashboardNames;

public final class ChargedUpCoordinateGuiDataType extends ComplexDataType<ChargedUpCoordinateGuiData>
{
    public static final ChargedUpCoordinateGuiDataType INSTANCE = new ChargedUpCoordinateGuiDataType();
    private static final String NAME = SmartDashboardNames.sCOORDINATE_GUI_TABLE_TYPE;

    private ChargedUpCoordinateGuiDataType()
    {
        super(NAME, ChargedUpCoordinateGuiData.class);
    }

    @Override
    public Function<Map<String, Object>, ChargedUpCoordinateGuiData> fromMap()
    {
        return ChargedUpCoordinateGuiData::new;
    }

    @Override
    public ChargedUpCoordinateGuiData getDefaultValue()
    {
        return new ChargedUpCoordinateGuiData();
    }

}
