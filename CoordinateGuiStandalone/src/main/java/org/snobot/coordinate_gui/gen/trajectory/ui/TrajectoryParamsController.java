package org.snobot.coordinate_gui.gen.trajectory.ui;

import org.snobot.coordinate_gui.gen.trajectory.model.TrajectoryConfig;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class TrajectoryParamsController
{
    @FXML
    private TextField mMaxVelocity;

    @FXML
    private TextField mMaxAcceleration;

    @FXML
    private CheckBox mIsBackwards;


    /**
     * Sets the path configuration parameters.
     *
     * @param aPathConfig
     *            The parameters
     */
    public void setPathParams(TrajectoryConfig aPathConfig)
    {
        mMaxAcceleration.setText(Double.toString(aPathConfig.mMaxVelocity));
        mMaxVelocity.setText(Double.toString(aPathConfig.mMaxAcceleration));
        mIsBackwards.setSelected(aPathConfig.mIsBackwards);
    }

    /**
     * Gets the params represented by the view.
     *
     * @return The config
     */
    public TrajectoryConfig getPathParams()
    {
        TrajectoryConfig output = new TrajectoryConfig();
        output.mMaxAcceleration = Double.parseDouble(mMaxAcceleration.getText());
        output.mMaxVelocity = Double.parseDouble(mMaxVelocity.getText());
        output.mIsBackwards = mIsBackwards.isSelected();
        return output;
    }
}
