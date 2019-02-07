package org.snobot.coordinate_gui;

import org.snobot.coordinate_gui.trajectory_gen.model.PathConfig;

public class SavedPreferences
{

    private final String mTrajectoryDirectory;
    private final String mGenerationDirectory;
    private final PathConfig mDefaultPathConfig;

    /**
     * Constructor.
     */
    public SavedPreferences()
    {
        mTrajectoryDirectory = "F:\\git\\FIRST\\2019\\191\\software-2019\\DeepSpace2019\\trajectories";
        mGenerationDirectory = ".";

        mDefaultPathConfig = new PathConfig(12, 12, false);
    }

    public String getTrajectoryDirectory()
    {
        return mTrajectoryDirectory;
    }

    public String getGenerationDirectory()
    {
        return mGenerationDirectory;
    }

    public PathConfig getDefaultPathConfig()
    {
        return mDefaultPathConfig;
    }

}
