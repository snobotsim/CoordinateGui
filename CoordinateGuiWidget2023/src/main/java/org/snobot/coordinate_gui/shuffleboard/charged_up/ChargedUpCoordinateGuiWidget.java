package org.snobot.coordinate_gui.shuffleboard.charged_up;

import org.snobot.coordinate_gui.game.charged_up.ChargedUpController;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.shuffleboard.charged_up.data.ChargedUpCoordinateGuiData;
import org.snobot.coordinate_gui.shuffleboard.widgets.BaseCoordinateGuiWidget;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;

@Description(name = "ChargedUp Coordinate GUI", dataTypes = {ChargedUpCoordinateGuiData.class})
@ParametrizedController("ChargedUpCoordinateGuiWidget.fxml")
public class ChargedUpCoordinateGuiWidget extends BaseCoordinateGuiWidget<ChargedUpCoordinateGuiData, ChargedUpController>
{
    public ChargedUpCoordinateGuiWidget()
    {
        super(Distance.Unit.Meters);
    }
}
