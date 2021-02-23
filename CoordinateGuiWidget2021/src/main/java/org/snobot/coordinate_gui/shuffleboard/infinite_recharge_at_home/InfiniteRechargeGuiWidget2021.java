
package org.snobot.coordinate_gui.shuffleboard.infinite_recharge_at_home;

import org.snobot.coordinate_gui.game.infinite_recharge_at_home.InfiniteRechargeAtHomeController;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.shuffleboard.infinite_recharge_at_home.data.InfiniteRechargeCoordinateGuiData;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import javafx.fxml.FXML;
import org.snobot.coordinate_gui.shuffleboard.widgets.BaseCoordinateGuiWidget;

@Description(name = "CoordinateGuiInfiniteRecharge", dataTypes = {InfiniteRechargeCoordinateGuiData.class})
@ParametrizedController("InfiniteRechargeGuiWidget.fxml")
public class InfiniteRechargeGuiWidget2021 extends BaseCoordinateGuiWidget<InfiniteRechargeCoordinateGuiData, InfiniteRechargeAtHomeController>
{
    @FXML
    protected InfiniteRechargeAtHomeController mFieldController;

    public InfiniteRechargeGuiWidget2021()
    {
        super(Distance.Unit.METERS);
    }
}
