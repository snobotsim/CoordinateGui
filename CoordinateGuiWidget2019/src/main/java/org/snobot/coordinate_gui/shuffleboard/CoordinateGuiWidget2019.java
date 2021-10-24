
package org.snobot.coordinate_gui.shuffleboard;

import org.snobot.coordinate_gui.game.deep_space.DeepSpaceController;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.shuffleboard.data.DeepSpaceCoordinateGuiData;
import org.snobot.coordinate_gui.shuffleboard.widgets.BaseCoordinateGuiWidget;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;

@Description(name = "Coordinate GUI", dataTypes = {DeepSpaceCoordinateGuiData.class})
@ParametrizedController("CoordinateGuiWidget.fxml")
public class CoordinateGuiWidget2019 extends BaseCoordinateGuiWidget<DeepSpaceCoordinateGuiData, DeepSpaceController>
{
    public CoordinateGuiWidget2019()
    {
        super(Distance.Unit.Inch);
    }
}
