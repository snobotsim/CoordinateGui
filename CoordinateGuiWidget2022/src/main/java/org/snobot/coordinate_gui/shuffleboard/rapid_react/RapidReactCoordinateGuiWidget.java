package org.snobot.coordinate_gui.shuffleboard.rapid_react;

import org.snobot.coordinate_gui.game.rapid_react.RapidReactController;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.shuffleboard.rapid_react.data.RapidReactCoordinateGuiData;
import org.snobot.coordinate_gui.shuffleboard.widgets.BaseCoordinateGuiWidget;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;

@Description(name = "RapidReact Coordinate GUI", dataTypes = {RapidReactCoordinateGuiData.class})
@ParametrizedController("RapidReactCoordinateGuiWidget.fxml")
public class RapidReactCoordinateGuiWidget extends BaseCoordinateGuiWidget<RapidReactCoordinateGuiData, RapidReactController>
{
    public RapidReactCoordinateGuiWidget()
    {
        super(Distance.Unit.Meters);
    }
}
