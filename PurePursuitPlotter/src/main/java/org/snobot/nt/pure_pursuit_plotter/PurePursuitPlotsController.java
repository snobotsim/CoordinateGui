package org.snobot.nt.pure_pursuit_plotter;

import java.util.List;

import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.nt.pure_pursuit_plotter.plots.PurePursuitWheelGraphController;
import org.snobot.nt.pure_pursuit_plotter.plots.PurePursuitXYPositionGraphController;

import javafx.fxml.FXML;

public class PurePursuitPlotsController
{

    @FXML
    private PurePursuitXYPositionGraphController mXYController;
    @FXML
    private PurePursuitWheelGraphController mLeftWheelController;
    @FXML
    private PurePursuitWheelGraphController mRightWheelController;

    public void setIdealPath(List<Coordinate> aCoordinates)
    {
        mXYController.setPath(aCoordinates);
    }

    /**
     * Adds the current point to the graph.
     * 
     * @param aPoint
     *            The new point
     */
    public void addPoint(PurePursuitPointInfo aPoint)
    {
        mXYController.addPoint(aPoint.mX, aPoint.mY);
        mLeftWheelController.addPoint(aPoint.mIndex, aPoint.mLeftVelocity, aPoint.mLeftGoalVelocity);
        mRightWheelController.addPoint(aPoint.mIndex, aPoint.mRightVelocity, aPoint.mRightGoalVelocity);
    }

    /**
     * Clears the actuals from the plots.
     */
    public void clearActuals()
    {
        mXYController.clearActuals();
        mLeftWheelController.clear();
        mRightWheelController.clear();
    }

}
