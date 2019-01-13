package org.snobot.coordinate_gui.trajectory_gen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.team254.lib.trajectory.gen.TrajectoryGenerator;
import com.team254.lib.trajectory.gen.TrajectoryGenerator.Config;

public class TrajectoryConfigPanel extends JPanel
{
    private JTextField mMaxVelocityField;
    private JTextField mMaxAccelerationField;
    private JTextField mMaxJerkField;
    private JCheckBox mIsBackwardsBox;

    public TrajectoryConfigPanel()
    {
        initComponents();
        setConfig(new Config());
    }

    /**
     * Sets the trajectory configuration.
     * 
     * @param aTrajectoryConfig
     *            The configuration
     */
    public void setConfig(Config aTrajectoryConfig)
    {
        mMaxVelocityField.setText(Double.toString(aTrajectoryConfig.max_vel));
        mMaxAccelerationField.setText(Double.toString(aTrajectoryConfig.max_acc));
        mMaxJerkField.setText(Double.toString(aTrajectoryConfig.max_jerk));
    }

    /**
     * Gets the configuration represented in the panel.
     * 
     * @return The config
     */
    public TrajectoryGenerator.Config getConfig()
    {
        double dt = .02;
        double maxVel = Double.parseDouble(mMaxVelocityField.getText());
        double maxAccel = Double.parseDouble(mMaxAccelerationField.getText());
        double maxJerk = Double.parseDouble(mMaxJerkField.getText());

        return new Config(dt, maxVel, maxAccel, maxJerk, mIsBackwardsBox.isSelected());
    }

    private void initComponents() // NOPMD
    {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]
        { 0, 0, 0 };
        gridBagLayout.rowHeights = new int[]
        { 0, 0, 0, 0, 0 };
        gridBagLayout.columnWeights = new double[]
        { 0.0, 1.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[]
        { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        setLayout(gridBagLayout);

        JLabel lblMaxVelocity = new JLabel("Max Velocity");
        GridBagConstraints gbcLblMaxVelocity = new GridBagConstraints();
        gbcLblMaxVelocity.anchor = GridBagConstraints.EAST;
        gbcLblMaxVelocity.insets = new Insets(0, 0, 5, 5);
        gbcLblMaxVelocity.gridx = 0;
        gbcLblMaxVelocity.gridy = 0;
        add(lblMaxVelocity, gbcLblMaxVelocity);

        mMaxVelocityField = new JTextField();
        GridBagConstraints gbcMaxVelocityField = new GridBagConstraints();
        gbcMaxVelocityField.insets = new Insets(0, 0, 5, 0);
        gbcMaxVelocityField.fill = GridBagConstraints.HORIZONTAL;
        gbcMaxVelocityField.gridx = 1;
        gbcMaxVelocityField.gridy = 0;
        add(mMaxVelocityField, gbcMaxVelocityField);
        mMaxVelocityField.setColumns(10);

        JLabel lblMaxAcceleration = new JLabel("Max Acceleration");
        GridBagConstraints gbcLblMaxAcceleration = new GridBagConstraints();
        gbcLblMaxAcceleration.anchor = GridBagConstraints.EAST;
        gbcLblMaxAcceleration.insets = new Insets(0, 0, 5, 5);
        gbcLblMaxAcceleration.gridx = 0;
        gbcLblMaxAcceleration.gridy = 1;
        add(lblMaxAcceleration, gbcLblMaxAcceleration);

        mMaxAccelerationField = new JTextField();
        GridBagConstraints gbcMaxAccelerationField = new GridBagConstraints();
        gbcMaxAccelerationField.insets = new Insets(0, 0, 5, 0);
        gbcMaxAccelerationField.fill = GridBagConstraints.HORIZONTAL;
        gbcMaxAccelerationField.gridx = 1;
        gbcMaxAccelerationField.gridy = 1;
        add(mMaxAccelerationField, gbcMaxAccelerationField);
        mMaxAccelerationField.setColumns(10);

        JLabel lblMaxJerk = new JLabel("Max Jerk");
        GridBagConstraints gbcLblMaxJerk = new GridBagConstraints();
        gbcLblMaxJerk.anchor = GridBagConstraints.EAST;
        gbcLblMaxJerk.insets = new Insets(0, 0, 5, 5);
        gbcLblMaxJerk.gridx = 0;
        gbcLblMaxJerk.gridy = 2;
        add(lblMaxJerk, gbcLblMaxJerk);

        mMaxJerkField = new JTextField();
        GridBagConstraints gbcMaxJerkField = new GridBagConstraints();
        gbcMaxJerkField.insets = new Insets(0, 0, 5, 0);
        gbcMaxJerkField.fill = GridBagConstraints.HORIZONTAL;
        gbcMaxJerkField.gridx = 1;
        gbcMaxJerkField.gridy = 2;
        add(mMaxJerkField, gbcMaxJerkField);
        mMaxJerkField.setColumns(10);

        JLabel lblIsBackwards = new JLabel("Is Backwards");
        GridBagConstraints gbcLblIsBackwards = new GridBagConstraints();
        gbcLblIsBackwards.insets = new Insets(0, 0, 0, 5);
        gbcLblIsBackwards.gridx = 0;
        gbcLblIsBackwards.gridy = 3;
        add(lblIsBackwards, gbcLblIsBackwards);

        mIsBackwardsBox = new JCheckBox("");
        GridBagConstraints gbcIsBackwardsBox = new GridBagConstraints();
        gbcIsBackwardsBox.gridx = 1;
        gbcIsBackwardsBox.gridy = 3;
        add(mIsBackwardsBox, gbcIsBackwardsBox);
    }

}
