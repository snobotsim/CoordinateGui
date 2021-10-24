package org.snobot.coordinate_gui.gen.trajectory.model;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

public class SavedTrajectoryPreferences
{
    private static final String PREFERENCES_FILE = ".TrajectoryPreferences.yml";
    private static final Logger LOGGER = LogManager.getLogger(SavedTrajectoryPreferences.class);

    private String mTrajectoryDirectory;
    private String mGenerationDirectory;
    private TrajectoryConfig mDefaultPathConfig;

    /**
     * Constructor.
     */
    public SavedTrajectoryPreferences()
    {
        mTrajectoryDirectory = "C:\\Users\\PJ\\Documents\\GitHub\\frc2019\\191\\DeepSpace2019\\src\\main\\deploy\\pure_pursuit";
        mGenerationDirectory = ".";

        mDefaultPathConfig = new TrajectoryConfig(12, 12, false);
    }

    public String getTrajectoryDirectory()
    {
        return mTrajectoryDirectory;
    }

    public String getGenerationDirectory()
    {
        return mGenerationDirectory;
    }

    public TrajectoryConfig getDefaultPathConfig()
    {
        return mDefaultPathConfig;
    }

    public void setTrajectoryDirectory(String aTrajectoryDirectory)
    {
        mTrajectoryDirectory = aTrajectoryDirectory;
    }

    public void setGenerationDirectory(String aGenerationDirectory)
    {
        mGenerationDirectory = aGenerationDirectory;
    }

    public void setDefaultPathConfig(TrajectoryConfig aDefaultPathConfig)
    {
        mDefaultPathConfig = aDefaultPathConfig;
    }

    /**
     * Loads the last saved preferences.
     * 
     * @return The preferences
     */
    public static SavedTrajectoryPreferences load()
    {
        LOGGER.log(Level.INFO, "Loading config file " + PREFERENCES_FILE);

        Yaml yaml = new Yaml();
        File configFile = new File(PREFERENCES_FILE);

        SavedTrajectoryPreferences output = new SavedTrajectoryPreferences();

        if (configFile.exists())
        {
            try (Reader stream = Files.newBufferedReader(configFile.toPath()))
            {
                output = (SavedTrajectoryPreferences) yaml.load(stream);
            }
            catch (IOException ex)
            {
                LOGGER.log(Level.ERROR, "Couldn't load file", ex);
            }
        }
        else
        {
            LOGGER.log(Level.WARN, "Config file didn't exist");
            save(output);
        }

        return output;

    }

    /**
     * Saves the preferences to a file.
     * 
     * @param aPreferences
     *            The preferences to save
     */
    public static void save(SavedTrajectoryPreferences aPreferences)
    {
        LOGGER.log(Level.INFO, "Saving config file " + PREFERENCES_FILE);

        try (Writer writer = new OutputStreamWriter(Files.newOutputStream(Paths.get(PREFERENCES_FILE)), "UTF-8"))
        {
            Yaml yaml = new Yaml();
            yaml.dump(aPreferences, writer);
        }
        catch (IOException ex)
        {
            LOGGER.log(Level.ERROR, "Couldn't save file", ex);
        }
    }
}
