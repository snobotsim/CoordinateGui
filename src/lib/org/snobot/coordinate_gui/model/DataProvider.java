package org.snobot.coordinate_gui.model;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DataProvider<DataType>
{
    public static final int sABSOLUTE_MAX_POINT_MEMORY = 1500; //50 updates/sec * 30 seconds

    protected final List<DataProviderListener<DataType>> mDataListeners;
    protected final Deque<DataType> mData;
    protected final int mMaxPoints;


    public DataProvider()
    {
        this(sABSOLUTE_MAX_POINT_MEMORY);
    }

    public DataProvider(int aMaxPoints)
    {
        if (aMaxPoints > sABSOLUTE_MAX_POINT_MEMORY)
        {
            throw new IndexOutOfBoundsException("Max memory (" + aMaxPoints + ") must be last than the absolute max (" + sABSOLUTE_MAX_POINT_MEMORY
                    + ")");
        }

        mDataListeners = new LinkedList<>();
        mData = new LinkedList<>();
        mMaxPoints = aMaxPoints;
    }

    public void addData(DataType aData)
    {
        mData.add(aData);
        trim();

        for (DataProviderListener<DataType> listener : mDataListeners)
        {
            listener.onDataAdded(aData);
        }
    }

    protected void trim()
    {
        while (mData.size() > mMaxPoints)
        {
            mData.remove();
        }
    }

    public DataType getMostRecentData()
    {
        return mData.peekLast();
    }

    public Iterator<DataType> getReverseIterator()
    {
        return mData.descendingIterator();
    }

    public Deque<DataType> getAllData()
    {
        return mData;
    }

    public void clear()
    {
        mData.clear();
    }

    public void addDataListener(DataProviderListener<DataType> aDataListener)
    {
        mDataListeners.add(aDataListener);
    }
}
