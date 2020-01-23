
package org.snobot.coordinate_gui.shuffleboard.infinite_recharge;

import org.snobot.coordinate_gui.game.infinite_recharge.InfiniteRechargeController;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.shuffleboard.infinite_recharge.data.InfiniteRechargeCoordinateGuiData;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import javafx.fxml.FXML;
import org.snobot.coordinate_gui.shuffleboard.widgets.BaseCoordinateGuiWidget;

@Description(name = "Coordinate GUI", dataTypes = {InfiniteRechargeCoordinateGuiData.class})
@ParametrizedController("CoordinateGuiWidget.fxml")
public class CoordinateGuiWidget2020 extends BaseCoordinateGuiWidget<InfiniteRechargeCoordinateGuiData, InfiniteRechargeController>
{
    @FXML
    protected InfiniteRechargeController mFieldController;

    public CoordinateGuiWidget2020()
    {
        super(Distance.Unit.INCH);
    }
}
