package org.snobot.coordinate_gui.gen.pure_pursuit.ui;


import org.snobot.coordinate_gui.gen.pure_pursuit.model.PurePursuitConfig;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@SuppressWarnings("PMD.DoNotUseThreads")
public class PurePursuitParamsController
{
    @FXML
    private TextField mUpSampleSpacing;
    @FXML
    private TextField mTurnFactor;

    private Runnable mChangeCallback;

    private final ChangeListener<Boolean> mFocusListener = (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
    {
        if (newValue)
        {
            handleChange();
        }
    };

    @FXML
    public void initialize()
    {
        mUpSampleSpacing.focusedProperty().addListener(mFocusListener);
        mTurnFactor.focusedProperty().addListener(mFocusListener);
    }

    /**
     * Sets the path configuration parameters.
     *
     * @param aParams
     *            The parameters
     */
    public void setParams(PurePursuitConfig aParams)
    {
        mUpSampleSpacing.setText(Double.toString(aParams.mUpSampleSpacing));
        mTurnFactor.setText(Double.toString(aParams.mTurnFactor));
    }

    /**
     * Gets the params represented by the view.
     *
     * @return The config
     */
    public PurePursuitConfig getPathParams()
    {
        PurePursuitConfig output = new PurePursuitConfig();
        output.mUpSampleSpacing = Double.parseDouble(mUpSampleSpacing.getText());
        output.mTurnFactor = Double.parseDouble(mTurnFactor.getText());
        return output;
    }

    public void addChangedListener(Runnable aCallback)
    {
        mChangeCallback = aCallback;
    }

    @FXML
    public void handleChange()
    {
        mChangeCallback.run();
    }
}
