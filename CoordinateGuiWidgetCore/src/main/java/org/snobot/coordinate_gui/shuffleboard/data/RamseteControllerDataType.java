package org.snobot.coordinate_gui.shuffleboard.data;

import edu.wpi.first.shuffleboard.api.data.ComplexDataType;

import java.util.Map;
import java.util.function.Function;

public class RamseteControllerDataType extends ComplexDataType<RamseteControllerData>
{
    public static final RamseteControllerDataType INSTANCE = new RamseteControllerDataType();
    public static final String NAME = SmartDashboardNames.sRAMSETE_NAMESPACE;

    private RamseteControllerDataType()
    {
        super(NAME, RamseteControllerData.class);
    }

    @Override
    public Function<Map<String, Object>, RamseteControllerData> fromMap()
    {
        return RamseteControllerData::new;
    }

    @Override
    public RamseteControllerData getDefaultValue()
    {
        return new RamseteControllerData();
    }
}
