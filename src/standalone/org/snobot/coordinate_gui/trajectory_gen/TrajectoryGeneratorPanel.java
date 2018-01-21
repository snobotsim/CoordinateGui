package org.snobot.coordinate_gui.trajectory_gen;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.DataProvider;
import org.snobot.coordinate_gui.model.DataProviderListener;

public class TrajectoryGeneratorPanel extends JPanel
{
    private JButton mResetButton;
    private JButton mSaveButton;
    private JTable mTable;
    private DefaultTableModel mTableModel;

    public TrajectoryGeneratorPanel(DataProvider<Coordinate> coordinateDataProvider)
    {
        initComponents();

        coordinateDataProvider.addDataListener(mDataListener);
    }

    private void onSave()
    {

    }

    private void onReset()
    {

    }

    private void onRowSelected()
    {
        System.out.println("Selected row" + mTableModel.getValueAt(mTable.getSelectedRow(), 0));
    }

    private void initComponents()
    {
        mTableModel = new DefaultTableModel(new Object[] {"X",  "Y", "Angle"}, 0);
        mResetButton = new JButton("Reset");
        mSaveButton = new JButton("Save");
        mTable = new JTable(mTableModel);
        mTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(mSaveButton);
        buttonPanel.add(mResetButton);
        add(mTable, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        mSaveButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                onSave();
            }
        });

        mResetButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                onReset();
            }
        });
        mTable.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {

            @Override
            public void valueChanged(ListSelectionEvent arg0)
            {
                if (!arg0.getValueIsAdjusting())
                {
                    onRowSelected();
                }
            }
        });
    }

    private DataProviderListener<Coordinate> mDataListener = new DataProviderListener<Coordinate>()
    {

        @Override
        public void onDataAdded(Coordinate data)
        {
            mTableModel.addRow(new Object[] {data.x, data.y, data.angle});
        }
    };

}
