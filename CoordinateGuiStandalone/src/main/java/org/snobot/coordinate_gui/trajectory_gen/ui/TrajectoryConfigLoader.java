package org.snobot.coordinate_gui.trajectory_gen.ui;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.trajectory_gen.model.PathConfig;
import org.yaml.snakeyaml.Yaml;

public final class TrajectoryConfigLoader
{
    private static final Logger sLOGGER = LogManager.getLogger(TrajectoryConfigLoader.class);

    private PathConfig mPathConfig;
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
        try (Reader fileReader = new InputStreamReader(new FileInputStream(aPath), "UTF-8"))
        {
            Yaml yaml = new Yaml();
            Map<String, Object> config = (Map<String, Object>) yaml.load(fileReader);

            mPathConfig = (PathConfig) config.get("config");
            mCoordinates = (List<Coordinate>) config.get("waypoints");
        }
        catch (ClassCastException | IOException ex)
        {
            sLOGGER.log(Level.ERROR, ex);
        }
    }


    public List<Coordinate> getCoordinates()
    {
        return mCoordinates;
    }

    public PathConfig getConfig()
    {
        return mPathConfig;
    }
}
