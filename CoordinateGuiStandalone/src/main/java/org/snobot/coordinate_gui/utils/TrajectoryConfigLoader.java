package org.snobot.coordinate_gui.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.trajectory_gen.model.PathConfig;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

public final class TrajectoryConfigLoader
{
    private static final Logger sLOGGER = LogManager.getLogger(TrajectoryConfigLoader.class);

    private PathConfig mPathConfig;
    private List<Coordinate> mCoordinates;

    /**
     * Loads a config file.
     * 
     * @param aFile
     *            The path to the config file
     */
    @SuppressWarnings("unchecked")
    public void loadFile(File aFile)
    {
        try (Reader fileReader = new InputStreamReader(new FileInputStream(aFile), "UTF-8"))
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

    /**
     * Dumps a trajectory config file.
     * 
     * @param aPath
     *            The file to dump to
     * @param aCoordinates
     *            The coordinates to dump
     * @param aPathConfig
     *            The path config
     */
    public void dumpFile(File aPath, List<Coordinate> aCoordinates, PathConfig aPathConfig)
    {
        try (OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(aPath), StandardCharsets.UTF_8))
        {
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml yaml = new Yaml(options);
            Map<String, Object> config = new HashMap<>();
            config.put("config", aPathConfig);
            config.put("waypoints", aCoordinates);

            yaml.dump(config, fileWriter);
        }
        catch (IOException ex)
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
