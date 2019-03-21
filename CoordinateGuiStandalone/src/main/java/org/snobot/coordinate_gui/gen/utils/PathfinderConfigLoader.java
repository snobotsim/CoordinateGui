package org.snobot.coordinate_gui.gen.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.snobot.coordinate_gui.model.Coordinate;

public class PathfinderConfigLoader
{
    private static final Logger sLOGGER = LogManager.getLogger(PathfinderConfigLoader.class);

    /**
     * Loads a pathweaver file.
     * 
     * @param aPath
     *            The file
     * @return The list of the coordinates in the file
     */
    public List<Coordinate> loadPathweaverFile(File aPath)
    {
        List<Coordinate> output = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(aPath), StandardCharsets.UTF_8))) // NOPMD
        {
            String line;
            boolean headerLine = true;

            while ((line = br.readLine()) != null) // NOPMD
            {
                if (headerLine)
                {
                    headerLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                double pathweaverX = Double.parseDouble(parts[0]);
                double pathweaverY = Double.parseDouble(parts[1]);
                double pathweaverTangentX = Double.parseDouble(parts[2]);
                double pathweaverTangentY = Double.parseDouble(parts[3]);

                double x = pathweaverY - 13.5;
                double y = pathweaverX - 27;
                double angle = Math.toDegrees(Math.atan2(pathweaverTangentY, pathweaverTangentX));

                output.add(new Coordinate(x, y, angle));
            }
        }
        catch (IOException ex)
        {
            sLOGGER.log(Level.ERROR, "Error loading pathfinder config", ex);
        }

        return output;
    }
}
