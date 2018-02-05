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

    public void setConfig(Config trajectoryConfig)
    {
        mMaxVelocityField.setText(Double.toString(trajectoryConfig.max_vel));
        mMaxAccelerationField.setText(Double.toString(trajectoryConfig.max_acc));
        mMaxJerkField.setText(Double.toString(trajectoryConfig.max_jerk));
    }

    public TrajectoryGenerator.Config getConfig()
    {
        double dt = .02;
        double maxVel = Double.parseDouble(mMaxVelocityField.getText());
        double maxAccel = Double.parseDouble(mMaxAccelerationField.getText());
        double maxJerk = Double.parseDouble(mMaxJerkField.getText());

        return new Config(dt, maxVel, maxAccel, maxJerk, mIsBackwardsBox.isSelected());
    }

    private void initComponents()
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
        GridBagConstraints gbc_lblMaxVelocity = new GridBagConstraints();
        gbc_lblMaxVelocity.anchor = GridBagConstraints.EAST;
        gbc_lblMaxVelocity.insets = new Insets(0, 0, 5, 5);
        gbc_lblMaxVelocity.gridx = 0;
        gbc_lblMaxVelocity.gridy = 0;
        add(lblMaxVelocity, gbc_lblMaxVelocity);

        mMaxVelocityField = new JTextField();
        GridBagConstraints gbc_mMaxVelocityField = new GridBagConstraints();
        gbc_mMaxVelocityField.insets = new Insets(0, 0, 5, 0);
        gbc_mMaxVelocityField.fill = GridBagConstraints.HORIZONTAL;
        gbc_mMaxVelocityField.gridx = 1;
        gbc_mMaxVelocityField.gridy = 0;
        add(mMaxVelocityField, gbc_mMaxVelocityField);
        mMaxVelocityField.setColumns(10);

        JLabel lblMaxAcceleration = new JLabel("Max Acceleration");
        GridBagConstraints gbc_lblMaxAcceleration = new GridBagConstraints();
        gbc_lblMaxAcceleration.anchor = GridBagConstraints.EAST;
        gbc_lblMaxAcceleration.insets = new Insets(0, 0, 5, 5);
        gbc_lblMaxAcceleration.gridx = 0;
        gbc_lblMaxAcceleration.gridy = 1;
        add(lblMaxAcceleration, gbc_lblMaxAcceleration);

        mMaxAccelerationField = new JTextField();
        GridBagConstraints gbc_mMaxAccelerationField = new GridBagConstraints();
        gbc_mMaxAccelerationField.insets = new Insets(0, 0, 5, 0);
        gbc_mMaxAccelerationField.fill = GridBagConstraints.HORIZONTAL;
        gbc_mMaxAccelerationField.gridx = 1;
        gbc_mMaxAccelerationField.gridy = 1;
        add(mMaxAccelerationField, gbc_mMaxAccelerationField);
        mMaxAccelerationField.setColumns(10);

        JLabel lblMaxJerk = new JLabel("Max Jerk");
        GridBagConstraints gbc_lblMaxJerk = new GridBagConstraints();
        gbc_lblMaxJerk.anchor = GridBagConstraints.EAST;
        gbc_lblMaxJerk.insets = new Insets(0, 0, 5, 5);
        gbc_lblMaxJerk.gridx = 0;
        gbc_lblMaxJerk.gridy = 2;
        add(lblMaxJerk, gbc_lblMaxJerk);

        mMaxJerkField = new JTextField();
        GridBagConstraints gbc_mMaxJerkField = new GridBagConstraints();
        gbc_mMaxJerkField.insets = new Insets(0, 0, 5, 0);
        gbc_mMaxJerkField.fill = GridBagConstraints.HORIZONTAL;
        gbc_mMaxJerkField.gridx = 1;
        gbc_mMaxJerkField.gridy = 2;
        add(mMaxJerkField, gbc_mMaxJerkField);
        mMaxJerkField.setColumns(10);

        JLabel lblIsBackwards = new JLabel("Is Backwards");
        GridBagConstraints gbc_lblIsBackwards = new GridBagConstraints();
        gbc_lblIsBackwards.insets = new Insets(0, 0, 0, 5);
        gbc_lblIsBackwards.gridx = 0;
        gbc_lblIsBackwards.gridy = 3;
        add(lblIsBackwards, gbc_lblIsBackwards);

        mIsBackwardsBox = new JCheckBox("");
        GridBagConstraints gbc_mIsBackwardsBox = new GridBagConstraints();
        gbc_mIsBackwardsBox.gridx = 1;
        gbc_mIsBackwardsBox.gridy = 3;
        add(mIsBackwardsBox, gbc_mIsBackwardsBox);
    }

}
