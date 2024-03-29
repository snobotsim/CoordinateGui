package org.snobot.coordinate_gui.gen.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.snobot.coordinate_gui.gen.trajectory.model.TrajectoryConfig;
import org.snobot.coordinate_gui.model.Coordinate;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

public final class TrajectoryConfigLoader
{
    private static final Logger sLOGGER = LogManager.getLogger(TrajectoryConfigLoader.class);

    private TrajectoryConfig mPathConfig;
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

        try (Reader fileReader = new InputStreamReader(Files.newInputStream(aFile.toPath()), "UTF-8"))
        {
            Yaml yaml = new Yaml();
            Map<String, Object> config = (Map<String, Object>) yaml.load(fileReader);

            mPathConfig = (TrajectoryConfig) config.get("config");
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
    public void dumpFile(File aPath, List<Coordinate> aCoordinates, TrajectoryConfig aPathConfig)
    {
        try (OutputStreamWriter fileWriter = new OutputStreamWriter(Files.newOutputStream(aPath.toPath()), StandardCharsets.UTF_8))
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

    public TrajectoryConfig getConfig()
    {
        return mPathConfig;
    }
}
