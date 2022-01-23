package org.snobot.coordinate_gui.shuffleboard.powerup;

import org.snobot.coordinate_gui.game.powerup.PowerupController;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.shuffleboard.powerup.data.PowerupCoordinateGuiData;
import org.snobot.coordinate_gui.shuffleboard.widgets.BaseCoordinateGuiWidget;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;

@Description(name = "Powerup Coordinate GUI", dataTypes = {PowerupCoordinateGuiData.class})
@ParametrizedController("PowerupCoordinateGuiWidget.fxml")
public class PowerupCoordinateGuiWidget extends BaseCoordinateGuiWidget<PowerupCoordinateGuiData, PowerupController>
{
    public PowerupCoordinateGuiWidget()
    {
        super(Distance.Unit.Inch);
    }
}
