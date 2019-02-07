package org.snobot.coordinate_gui.trajectory_gen.ui;

import org.snobot.coordinate_gui.game.deep_space.DeepSpaceController;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.ui.layers.TrajectoryConfigLayerController;
import org.snobot.coordinate_gui.ui.layers.TrajectoryConfigLayerController.CoodrinateWrapper;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Pane;

public class DragHandler
{

    /**
     * Constructor.
     * 
     * @param aField
     *            The pane representing the field.
     * @param aFieldController
     *            The field controller
     */
    public DragHandler(Pane aField, DeepSpaceController aFieldController)
    {

        aField.setOnDragOver(event ->
        {
            Dragboard dragboard = event.getDragboard();
            CoodrinateWrapper selectedCoordinate = aFieldController.getSelectedWaypoint();
            if (dragboard.hasContent(TrajectoryConfigLayerController.WAYPOINT))
            {
                if (selectedCoordinate != null)
                {
                    double x = aFieldController.getPixelConverter().convertFieldXPixelsToFeet(event.getX());
                    double y = aFieldController.getPixelConverter().convertFieldYPixelsToFeet(event.getY());
                    selectedCoordinate.setPosition(x, y);
                }
            }
            else if (dragboard.hasContent(TrajectoryConfigLayerController.ANGLE_LINE))
            {
                double x = aFieldController.getPixelConverter().convertFieldXPixelsToFeet(event.getX());
                double y = aFieldController.getPixelConverter().convertFieldYPixelsToFeet(event.getY());
                double angle = Math.toDegrees(Math.atan2(x - selectedCoordinate.mCoordinate.mX, y - selectedCoordinate.mCoordinate.mY));
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
                double x = aFieldController.getPixelConverter().convertFieldXPixelsToFeet(contextMenuEvent.getX());
                double y = aFieldController.getPixelConverter().convertFieldYPixelsToFeet(contextMenuEvent.getY());
                aFieldController.addIdealTrajectory(new Coordinate(x, y, 0));
            });

            menu.getItems().add(addPointItem);
            menu.show(aField.getScene().getWindow(), contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());
        });
    }
}
