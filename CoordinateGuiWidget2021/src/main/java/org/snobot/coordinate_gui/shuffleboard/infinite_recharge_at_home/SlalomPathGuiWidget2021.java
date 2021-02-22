
package org.snobot.coordinate_gui.shuffleboard.infinite_recharge_at_home;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import javafx.fxml.FXML;
import org.snobot.coordinate_gui.game.infinite_recharge_at_home.InfiniteRechargeAtHomeController;
import org.snobot.coordinate_gui.game.infinite_recharge_at_home.SlalomPathController;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.shuffleboard.infinite_recharge_at_home.data.InfiniteRechargeCoordinateGuiData;
import org.snobot.coordinate_gui.shuffleboard.widgets.BaseCoordinateGuiWidget;

@Description(name = "SlalomPathGui", dataTypes = {InfiniteRechargeCoordinateGuiData.class})
@ParametrizedController("SlalomPathGuiWidget.fxml")
public class SlalomPathGuiWidget2021 extends BaseCoordinateGuiWidget<InfiniteRechargeCoordinateGuiData, InfiniteRechargeAtHomeController>
{
    @FXML
    protected SlalomPathController mFieldController;

    public SlalomPathGuiWidget2021()
    {
        super(Distance.Unit.METERS);
    }
}
