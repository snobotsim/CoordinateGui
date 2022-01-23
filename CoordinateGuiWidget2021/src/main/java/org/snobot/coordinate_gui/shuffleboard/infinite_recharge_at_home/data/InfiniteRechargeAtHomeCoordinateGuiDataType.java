package org.snobot.coordinate_gui.shuffleboard.infinite_recharge_at_home.data;

import java.util.Map;
import java.util.function.Function;

import edu.wpi.first.shuffleboard.api.data.ComplexDataType;
import org.snobot.coordinate_gui.shuffleboard.data.SmartDashboardNames;

public final class InfiniteRechargeAtHomeCoordinateGuiDataType extends ComplexDataType<InfiniteRechargeAtHomeCoordinateGuiData>
{
    public static final InfiniteRechargeAtHomeCoordinateGuiDataType INSTANCE = new InfiniteRechargeAtHomeCoordinateGuiDataType();
    private static final String NAME = SmartDashboardNames.sCOORDINATE_GUI_TABLE_TYPE;

    private InfiniteRechargeAtHomeCoordinateGuiDataType()
    {
        super(NAME, InfiniteRechargeAtHomeCoordinateGuiData.class);
    }

    @Override
    public Function<Map<String, Object>, InfiniteRechargeAtHomeCoordinateGuiData> fromMap()
    {
        return map ->
            new InfiniteRechargeAtHomeCoordinateGuiData(map);
    }

    @Override
    public InfiniteRechargeAtHomeCoordinateGuiData getDefaultValue()
    {
        return new InfiniteRechargeAtHomeCoordinateGuiData();
    }

}
