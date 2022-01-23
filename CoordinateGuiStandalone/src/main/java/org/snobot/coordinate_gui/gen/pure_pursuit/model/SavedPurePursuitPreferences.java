package org.snobot.coordinate_gui.gen.pure_pursuit.model;

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

public class SavedPurePursuitPreferences
{

    private static final String PREFERENCES_FILE = ".PurePursuitPreferences.yml";
    private static final Logger LOGGER = LogManager.getLogger(SavedPurePursuitPreferences.class);

    private String mWaypointsDirectory;
    private PurePursuitConfig mDefaultConfig;

    /**
     * Constructor.
     */
    public SavedPurePursuitPreferences()
    {
        mWaypointsDirectory = "F:\\git\\FIRST\\2019\\191\\software-2019\\DeepSpace2019\\src\\main\\deploy\\pure_pursuit";
        mDefaultConfig = new PurePursuitConfig(6, .85);
    }

    public String getWaypointsDirectory()
    {
        return mWaypointsDirectory;
    }

    public void setWaypointsDirectory(String aWaypointsDirectory)
    {
        this.mWaypointsDirectory = aWaypointsDirectory;
    }

    public PurePursuitConfig getDefaultConfig()
    {
        return mDefaultConfig;
    }

    public void setDefaultConfig(PurePursuitConfig aDefaultConfig)
    {
        mDefaultConfig = aDefaultConfig;
    }

    /**
     * Loads the preferences.
     *
     * @return The loaded preferences
     */
    public static SavedPurePursuitPreferences load()
    {
        LOGGER.log(Level.INFO, "Loading config file " + PREFERENCES_FILE);

        Yaml yaml = new Yaml();
        File configFile = new File(PREFERENCES_FILE);

        SavedPurePursuitPreferences output = new SavedPurePursuitPreferences();

        if (configFile.exists())
        {
            try (Reader stream = Files.newBufferedReader(configFile.toPath()))
            {
                output = (SavedPurePursuitPreferences) yaml.load(stream);
            }
            catch (IOException ex)
            {
                LOGGER.log(Level.ERROR, "Could not load config file", ex);
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
     * Saves the preferences.
     *
     * @param aPreferences
     *            The preferences to save
     */
    public static void save(SavedPurePursuitPreferences aPreferences)
    {
        LOGGER.log(Level.INFO, "Saving config file " + PREFERENCES_FILE);
        try (Writer writer = new OutputStreamWriter(Files.newOutputStream(Paths.get(PREFERENCES_FILE)), "UTF-8"))
        {
            Yaml yaml = new Yaml();
            yaml.dump(aPreferences, writer);
        }
        catch (IOException ex)
        {
            LOGGER.log(Level.ERROR, "Could not save config file", ex);
        }
    }
}
