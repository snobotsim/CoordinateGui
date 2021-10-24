package org.snobot.coordinate_gui.gen.trajectory.ui;

import org.snobot.coordinate_gui.game.deep_space.DeepSpaceController;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.model.Position2dPixels;
import org.snobot.coordinate_gui.ui.layers.TrajectoryConfigLayerController;
import org.snobot.coordinate_gui.ui.layers.TrajectoryConfigLayerController.CoodrinateWrapper;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Pane;

public class TrajectoryConfigDragHandler
{

    /**
     * Constructor.
     * 
     * @param aField
     *            The pane representing the field.
     * @param aFieldController
     *            The field controller
     */
    public TrajectoryConfigDragHandler(Pane aField, DeepSpaceController aFieldController)
    {

        aField.setOnDragOver(event ->
        {
            Dragboard dragboard = event.getDragboard();
            CoodrinateWrapper selectedCoordinate = aFieldController.getSelectedWaypoint();
            if (dragboard.hasContent(TrajectoryConfigLayerController.WAYPOINT))
            {
                if (selectedCoordinate != null)
                {
                    Position2dPixels asPixels = new Position2dPixels(event.getX(), event.getY());
                    selectedCoordinate.setPosition(aFieldController.getPixelConverter().convertPixelsToDistance(asPixels));
                }
            }
            else if (dragboard.hasContent(TrajectoryConfigLayerController.ANGLE_LINE))
            {
                Position2dPixels asPixels = new Position2dPixels(event.getX(), event.getY());
                Position2dDistance asDistance = aFieldController.getPixelConverter().convertPixelsToDistance(asPixels);
                double x = asDistance.mX.as(Distance.Unit.Feet);
                double y = asDistance.mY.as(Distance.Unit.Feet);
                double angle = Math.toDegrees(Math.atan2(x - selectedCoordinate.mCoordinate.mPosition.mX.as(Distance.Unit.Feet),
                    y - selectedCoordinate.mCoordinate.mPosition.mY.as(Distance.Unit.Feet)));
                selectedCoordinate.setAngle(angle);
            }
            event.consume();
        });

        aField.setOnContextMenuRequested(contextMenuEvent ->
        {
            ContextMenu menu = new ContextMenu();
            MenuItem addPointItem = new MenuItem("Append New Point");
            addPointItem.setOnAction(selectionEvent ->
            {
                Position2dPixels asPixels = new Position2dPixels(contextMenuEvent.getX(), contextMenuEvent.getY());
                aFieldController.addIdealTrajectory(new Coordinate(aFieldController.getPixelConverter().convertPixelsToDistance(asPixels), 0));
            });

            menu.getItems().add(addPointItem);
            menu.show(aField.getScene().getWindow(), contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());
        });
    }
}
