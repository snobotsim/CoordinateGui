
package org.snobot.coordinate_gui.shuffleboard.infinite_recharge_at_home;

import org.snobot.coordinate_gui.game.infinite_recharge_at_home.InfiniteRechargeAtHomeController;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.shuffleboard.infinite_recharge_at_home.data.InfiniteRechargeAtHomeCoordinateGuiData;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import javafx.fxml.FXML;
import org.snobot.coordinate_gui.shuffleboard.widgets.BaseCoordinateGuiWidget;

@Description(name = "CoordinateGuiInfiniteRecharge", dataTypes = {InfiniteRechargeAtHomeCoordinateGuiData.class})
@ParametrizedController("InfiniteRechargeGuiWidget.fxml")
public class InfiniteRechargeAtHomeGuiWidget2021 extends BaseCoordinateGuiWidget<InfiniteRechargeAtHomeCoordinateGuiData, InfiniteRechargeAtHomeController>
{
    @FXML
    protected InfiniteRechargeAtHomeController mFieldController;

    public InfiniteRechargeAtHomeGuiWidget2021()
    {
        super(Distance.Unit.Meters);
    }
}
