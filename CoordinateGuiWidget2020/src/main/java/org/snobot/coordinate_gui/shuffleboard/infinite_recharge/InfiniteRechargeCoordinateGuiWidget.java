package org.snobot.coordinate_gui.shuffleboard.infinite_recharge;

import org.snobot.coordinate_gui.game.infinite_recharge.InfiniteRechargeController;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.shuffleboard.infinite_recharge.data.InfiniteRechargeCoordinateGuiData;
import org.snobot.coordinate_gui.shuffleboard.widgets.BaseCoordinateGuiWidget;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;

@Description(name = "InfiniteRecharge Coordinate GUI", dataTypes = {InfiniteRechargeCoordinateGuiData.class})
@ParametrizedController("InfiniteRechargeCoordinateGuiWidget.fxml")
public class InfiniteRechargeCoordinateGuiWidget extends BaseCoordinateGuiWidget<InfiniteRechargeCoordinateGuiData, InfiniteRechargeController>
{
    public InfiniteRechargeCoordinateGuiWidget()
    {
        super(Distance.Unit.Inch);
    }
}
