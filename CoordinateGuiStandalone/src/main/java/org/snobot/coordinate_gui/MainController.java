package org.snobot.coordinate_gui;

import org.snobot.coordinate_gui.trajectory_gen.ui.PathParamsController;
import org.snobot.coordinate_gui.ui.layers.BaseGuiController;

import javafx.fxml.FXML;

public class MainController
{
    @FXML
    private PathParamsController mPathParamsController;

    @FXML
    private BaseGuiController mFieldController;

    /**
     * Called after JavaFX initialization.
     */
    @FXML
    public void initialize()
    {
    }
}
