package org.snobot.coordinate_gui.trajectory_gen;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.snobot.coordinate_gui.model.Coordinate;
import org.yaml.snakeyaml.Yaml;

import com.team254.lib.trajectory.gen.TrajectoryGenerator.Config;

public final class TrajectoryConfigLoader
{
    private static final Logger sLOGGER = Logger.getLogger(TrajectoryConfigLoader.class);

    private Config mTrajectoryConfig;
    private List<Coordinate> mCoordinates;

    /**
     * Loads a config file.
     * 
     * @param aPath
     *            The path to the config file
     */
    @SuppressWarnings("unchecked")
    public void loadFile(String aPath)
    {
        try (FileReader fileReader = new FileReader(aPath))
        {
            Yaml yaml = new Yaml();
            Map<String, Object> config = (Map<String, Object>) yaml.load(fileReader);

            mTrajectoryConfig = (Config) config.get("config");
            mCoordinates = (List<Coordinate>) config.get("waypoints");
        }
        catch (ClassCastException | IOException ex)
        {
            sLOGGER.log(Level.ERROR, ex);
        }
    }

    public Config getConfig()
    {
        return mTrajectoryConfig;
    }

    public List<Coordinate> getCoordinates()
    {
        return mCoordinates;
    }
}
