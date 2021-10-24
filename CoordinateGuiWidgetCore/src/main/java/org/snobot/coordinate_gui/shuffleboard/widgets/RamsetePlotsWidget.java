package org.snobot.coordinate_gui.shuffleboard.widgets;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Velocity;
import org.snobot.coordinate_gui.shuffleboard.data.RamseteControllerData;
import org.snobot.coordinate_gui.shuffleboard.data.SmartDashboardNames;
import org.snobot.nt.ramsete_plotter.RamsetePlotsController;

import java.util.Map;

@Description(name = "Ramsete Plots", dataTypes = RamseteControllerData.class)
@ParametrizedController("RamsetePlotsWidget.fxml")
public class RamsetePlotsWidget extends SimpleAnnotatedWidget<RamseteControllerData>
{
    private final Distance.Unit mDistanceUnit;
    private final Velocity.Unit mVelocityUnit;

    @FXML
    private Pane mRoot;

    @FXML
    private RamsetePlotsController mOverviewContainerController;

    public RamsetePlotsWidget()
    {
        mDistanceUnit = Distance.Unit.Inch;
        mVelocityUnit = Velocity.Unit.InchPerSec;
    }

    /**
     * Called after the JavaFX things have been initialized.
     */
    @FXML
    public void initialize()
    {
        dataOrDefault.addListener((observable, oldData, newData) ->
        {
            final Map<String, Object> changes = newData.changesFrom(oldData);
            if (changes.containsKey(SmartDashboardNames.sRAMSETE_IDEAL_POINTS))
            {
                mOverviewContainerController.setPath(newData.getIdealPoints(mDistanceUnit, mVelocityUnit), mDistanceUnit, mVelocityUnit);
            }
            if (changes.containsKey(SmartDashboardNames.sRAMSETE_REAL_POINT))
            {
                mOverviewContainerController.addMeasuredPoint(newData.getRealPoints(mDistanceUnit, mVelocityUnit), mDistanceUnit, mVelocityUnit);
            }
        });
    }

    @Override
    public Pane getView()
    {
        return mRoot;
    }
}
