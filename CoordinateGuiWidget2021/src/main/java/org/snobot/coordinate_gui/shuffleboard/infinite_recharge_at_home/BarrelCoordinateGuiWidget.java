package org.snobot.coordinate_gui.shuffleboard.infinite_recharge_at_home;

import org.snobot.coordinate_gui.game.infinite_recharge_at_home.InfiniteRechargeAtHomeController;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.shuffleboard.infinite_recharge_at_home.data.InfiniteRechargeAtHomeCoordinateGuiData;
import org.snobot.coordinate_gui.shuffleboard.widgets.BaseCoordinateGuiWidget;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;

@Description(name = "Barrel Coordinate GUI", dataTypes = {InfiniteRechargeAtHomeCoordinateGuiData.class})
@ParametrizedController("BarrelCoordinateGuiWidget.fxml")
public class BarrelCoordinateGuiWidget extends BaseCoordinateGuiWidget<InfiniteRechargeAtHomeCoordinateGuiData, InfiniteRechargeAtHomeController>
{
    public BarrelCoordinateGuiWidget()
    {
        super(Distance.Unit.Inch);
    }
}
