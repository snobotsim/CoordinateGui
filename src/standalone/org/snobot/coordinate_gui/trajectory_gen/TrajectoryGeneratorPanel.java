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
    private JTable mTable;
    private DefaultTableModel mTableModel;

    /**
     * Constructor.
     * 
     * @param aCoordinateDataProvider
     *            The data provider
     */
    public TrajectoryGeneratorPanel(DataProvider<Coordinate> aCoordinateDataProvider)
    {
        initComponents();

        aCoordinateDataProvider.addDataListener(mDataListener);
    }

    private void onSave()
    {
        // TODO implement
    }

    private void onReset()
    {
        // TODO implement
    }

    private void onRowSelected()
    {
        System.out.println("Selected row" + mTableModel.getValueAt(mTable.getSelectedRow(), 0));
    }

    private void initComponents()
    {
        mTableModel = new DefaultTableModel(new Object[] {"X",  "Y", "Angle"}, 0);
        mTable = new JTable(mTableModel);
        mTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();

        JButton resetButton = new JButton("Reset");
        JButton saveButton = new JButton("Save");
        buttonPanel.add(saveButton);
        buttonPanel.add(resetButton);
        add(mTable, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        saveButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent aEvent)
            {
                onSave();
            }
        });

        resetButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent aEvent)
            {
                onReset();
            }
        });
        mTable.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {

            @Override
            public void valueChanged(ListSelectionEvent aEvent)
            {
                if (!aEvent.getValueIsAdjusting())
                {
                    onRowSelected();
                }
            }
        });
    }

    private final DataProviderListener<Coordinate> mDataListener = new DataProviderListener<Coordinate>()
    {

        @Override
        public void onDataAdded(Coordinate aData)
        {
            mTableModel.addRow(new Object[] {aData.mX, aData.mY, aData.mAngle});
        }
    };

}
