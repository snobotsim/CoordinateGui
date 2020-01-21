
package org.snobot.coordinate_gui.shuffleboard.infinite_recharge;

import java.util.Map;

import org.snobot.coordinate_gui.game.infinite_recharge.InfiniteRechargeController;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.shuffleboard.data.CoordinateDataType;
import org.snobot.coordinate_gui.shuffleboard.data.SmartDashboardNames;
import org.snobot.coordinate_gui.shuffleboard.data.VisionDataType;
import org.snobot.coordinate_gui.shuffleboard.infinite_recharge.data.InfiniteRechargeCoordinateGuiData;

import edu.wpi.first.shuffleboard.api.widget.ComplexAnnotatedWidget;
import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

@Description(name = "Coordinate GUI", dataTypes = {InfiniteRechargeCoordinateGuiData.class})
@ParametrizedController("CoordinateGuiWidget.fxml")
public class CoordinateGuiWidget2020 extends ComplexAnnotatedWidget<InfiniteRechargeCoordinateGuiData>
{
    private static final Distance.Unit DISTANCE_UNIT = Distance.Unit.INCH;

    @FXML
    private Pane mRoot;

    @FXML
    protected InfiniteRechargeController mFieldController;

    /**
     * Called after JavaFX initialization.
     */
    @FXML
    public void initialize()
    {
        dataOrDefault.addListener((__, oldData, newData) ->
        {
            final Map<String, Object> changes = newData.changesFrom(oldData);

            if (changes.containsKey(CoordinateDataType.NAME + "/" + SmartDashboardNames.sROBOT_POSITION_CTR))
            {
                mFieldController.addRobotPosition(newData.getRobotPosition().toCoord(DISTANCE_UNIT));
            }

            if (changes.containsKey(VisionDataType.NAME + "/" + SmartDashboardNames.sCAMERA_POSITIONS))
            {
                mFieldController.setCameraRays(newData.getVisionData().toRays(DISTANCE_UNIT));
            }
        });
    }

    @Override
    public Pane getView()
    {
        return mRoot;
    }

}
