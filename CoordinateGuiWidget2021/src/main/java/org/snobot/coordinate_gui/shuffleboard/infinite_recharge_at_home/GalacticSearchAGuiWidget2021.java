
package org.snobot.coordinate_gui.shuffleboard.infinite_recharge_at_home;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import javafx.fxml.FXML;
import org.snobot.coordinate_gui.game.infinite_recharge_at_home.GalacticSearchAController;
import org.snobot.coordinate_gui.game.infinite_recharge_at_home.InfiniteRechargeAtHomeController;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.shuffleboard.infinite_recharge_at_home.data.InfiniteRechargeCoordinateGuiData;
import org.snobot.coordinate_gui.shuffleboard.widgets.BaseCoordinateGuiWidget;

@Description(name = "CoordinateGuiGalacticSearchA", dataTypes = {InfiniteRechargeCoordinateGuiData.class})
@ParametrizedController("GalacticSearchAGuiWidget.fxml")
public class GalacticSearchAGuiWidget2021 extends BaseCoordinateGuiWidget<InfiniteRechargeCoordinateGuiData, InfiniteRechargeAtHomeController>
{
    @FXML
    protected GalacticSearchAController mFieldController;

    public GalacticSearchAGuiWidget2021()
    {
        super(Distance.Unit.METERS);
    }
}
