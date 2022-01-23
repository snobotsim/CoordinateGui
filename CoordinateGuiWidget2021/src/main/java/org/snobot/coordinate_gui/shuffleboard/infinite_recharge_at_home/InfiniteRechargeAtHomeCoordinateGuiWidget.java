package org.snobot.coordinate_gui.shuffleboard.infinite_recharge_at_home;

import org.snobot.coordinate_gui.game.infinite_recharge_at_home.InfiniteRechargeAtHomeController;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.shuffleboard.infinite_recharge_at_home.data.InfiniteRechargeAtHomeCoordinateGuiData;
import org.snobot.coordinate_gui.shuffleboard.widgets.BaseCoordinateGuiWidget;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;

@Description(name = "InfiniteRechargeAtHome Coordinate GUI", dataTypes = {InfiniteRechargeAtHomeCoordinateGuiData.class})
@ParametrizedController("InfiniteRechargeAtHomeCoordinateGuiWidget.fxml")
public class InfiniteRechargeAtHomeCoordinateGuiWidget extends BaseCoordinateGuiWidget<InfiniteRechargeAtHomeCoordinateGuiData, InfiniteRechargeAtHomeController>
{
    public InfiniteRechargeAtHomeCoordinateGuiWidget()
    {
        super(Distance.Unit.Inch);
    }
}
