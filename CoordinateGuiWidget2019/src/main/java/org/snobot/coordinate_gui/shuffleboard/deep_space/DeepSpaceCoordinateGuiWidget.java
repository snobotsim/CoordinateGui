package org.snobot.coordinate_gui.shuffleboard.deep_space;

import org.snobot.coordinate_gui.game.deep_space.DeepSpaceController;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.shuffleboard.deep_space.data.DeepSpaceCoordinateGuiData;
import org.snobot.coordinate_gui.shuffleboard.widgets.BaseCoordinateGuiWidget;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;

@Description(name = "DeepSpace Coordinate GUI", dataTypes = {DeepSpaceCoordinateGuiData.class})
@ParametrizedController("DeepSpaceCoordinateGuiWidget.fxml")
public class DeepSpaceCoordinateGuiWidget extends BaseCoordinateGuiWidget<DeepSpaceCoordinateGuiData, DeepSpaceController>
{
    public DeepSpaceCoordinateGuiWidget()
    {
        super(Distance.Unit.Inch);
    }
}
