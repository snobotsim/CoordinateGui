
package org.snobot.coordinate_gui.shuffleboard.infinite_recharge_at_home;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import javafx.fxml.FXML;
import org.snobot.coordinate_gui.game.infinite_recharge_at_home.BouncPathController;
import org.snobot.coordinate_gui.game.infinite_recharge_at_home.InfiniteRechargeAtHomeController;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.shuffleboard.infinite_recharge_at_home.data.InfiniteRechargeCoordinateGuiData;
import org.snobot.coordinate_gui.shuffleboard.widgets.BaseCoordinateGuiWidget;

@Description(name = "CoordinateGuiBounce", dataTypes = {InfiniteRechargeCoordinateGuiData.class})
@ParametrizedController("BouncePathGuiWidget.fxml")
public class BouncePathGuiWidget2021 extends BaseCoordinateGuiWidget<InfiniteRechargeCoordinateGuiData, InfiniteRechargeAtHomeController>
{
    @FXML
    protected BouncPathController mFieldController;

    public BouncePathGuiWidget2021()
    {
        super(Distance.Unit.METERS);
    }
}
