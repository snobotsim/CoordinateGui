package org.snobot.coordinate_gui.shuffleboard.infinite_recharge_at_home.data;

import java.util.Map;
import java.util.function.Function;

import edu.wpi.first.shuffleboard.api.data.ComplexDataType;
import org.snobot.coordinate_gui.shuffleboard.data.SmartDashboardNames;

public final class InfiniteRechargeCoordinateGuiDataType extends ComplexDataType<InfiniteRechargeCoordinateGuiData>
{
    public static final InfiniteRechargeCoordinateGuiDataType INSTANCE = new InfiniteRechargeCoordinateGuiDataType();
    private static final String NAME = SmartDashboardNames.sCOORDINATE_GUI_TABLE_TYPE;

    private InfiniteRechargeCoordinateGuiDataType()
    {
        super(NAME, InfiniteRechargeCoordinateGuiData.class);
    }

    @Override
    public Function<Map<String, Object>, InfiniteRechargeCoordinateGuiData> fromMap()
    {
        return map ->
            new InfiniteRechargeCoordinateGuiData(map);
    }

    @Override
    public InfiniteRechargeCoordinateGuiData getDefaultValue()
    {
        return new InfiniteRechargeCoordinateGuiData();
    }

}
