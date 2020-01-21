package org.snobot.coordinate_gui.gen.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.snobot.coordinate_gui.gen.pure_pursuit.model.PurePursuitConfig;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.yaml.snakeyaml.Yaml;

public class PurePursuitConfigLoader
{
    private static final Logger sLOGGER = LogManager.getLogger(PurePursuitConfigLoader.class);

    private PurePursuitConfig mConfig;
    private List<Coordinate> mCoordinates;

    /**
     * Loads a pathweaver file.
     * 
     * @param aPath
     *            The file
     */
    @SuppressWarnings("unchecked")
    public void loadFile(File aPath)
    {
        mCoordinates = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(aPath.toPath()))
        {
            Yaml yaml = new Yaml();
            List<Map<String, Object>> contents = (List<Map<String, Object>>) yaml.load(reader);
            for (Map<String, Object> yamlContents : contents)
            {
                if (yamlContents.get("type").equals("PurePursuit"))
                {
                    double upSampleSpacing = ((Number) yamlContents.get("path_separation")).doubleValue();
                    double turnSmoothing = ((Number) yamlContents.get("smoothing_wieght")).doubleValue();

                    mConfig = new PurePursuitConfig(upSampleSpacing, turnSmoothing);
                    for (List<Number> waypointYaml : (List<List<Number>>) yamlContents.get("waypoints"))
                    {
                        mCoordinates.add(new Coordinate(new Position2dDistance(waypointYaml.get(0).doubleValue(), waypointYaml.get(1).doubleValue(), Distance.Unit.FEET), 0));
                    }
                }
            }
        }
        catch (IOException ex)
        {
            sLOGGER.log(Level.ERROR, "Error loading pathfinder config", ex);
        }
    }

    public List<Coordinate> getCoordinates()
    {
        return mCoordinates;
    }

    public PurePursuitConfig getConfig()
    {
        return mConfig;
    }
}
