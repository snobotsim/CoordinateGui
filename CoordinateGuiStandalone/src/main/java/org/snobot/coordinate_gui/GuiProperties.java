package org.snobot.coordinate_gui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiProperties
{
    private static final Logger sLOGGER = LogManager.getLogger(GuiProperties.class);
    private static final String sSTANDALONE_PROPERTIES_FILE = "standalone.properties";
    private static final String sDEFAULT_CONFIG_DIRECTORY_PROP = "config_directory";
    private static final String sDEFAULT_TRAJECTORY_DIRECTORY_PROP = "trajectory_directory";
    private static final String sBULK_DUMP_CONFIG_DIRECTORY_PROP = "bulk_dump_config_directory";
    private static final String sBULK_DUMP_TRAJECTORY_DIRECTORY_PROP = "bulk_dump_trajectory_directory";

    private final Properties mDefaultProperties;

    public GuiProperties()
    {
        mDefaultProperties = loadProperties();
        saveProperties();
    }

    public void setTrajectoryConfigDirectory(File aSelectedFile)
    {
        mDefaultProperties.setProperty(sDEFAULT_CONFIG_DIRECTORY_PROP, aSelectedFile.getParent());
        saveProperties();
    }

    public void setTrajectoryDumpPath(File aSelectedFile)
    {
        mDefaultProperties.setProperty(sDEFAULT_TRAJECTORY_DIRECTORY_PROP, aSelectedFile.getParent());
        saveProperties();
    }

    public void setBulkDumpConfigDirectory(File aSelectedFile)
    {
        mDefaultProperties.setProperty(sBULK_DUMP_CONFIG_DIRECTORY_PROP, aSelectedFile.getAbsolutePath());
        saveProperties();
    }

    public void setBulkDumpOutputDirectory(File aSelectedFile)
    {
        mDefaultProperties.setProperty(sBULK_DUMP_TRAJECTORY_DIRECTORY_PROP, aSelectedFile.getAbsolutePath());
        saveProperties();
    }

    public String getTrajectoryConfigDirectory()
    {
        return mDefaultProperties.getProperty(sDEFAULT_CONFIG_DIRECTORY_PROP, ".");
    }

    public String getTrajectoryDumpPathDirectory()
    {
        return mDefaultProperties.getProperty(sDEFAULT_TRAJECTORY_DIRECTORY_PROP, ".");
    }

    public String getBulkDumpConfigDirectory()
    {
        return mDefaultProperties.getProperty(sBULK_DUMP_CONFIG_DIRECTORY_PROP, ".");
    }

    public String getBulkDumpOutputDirectory()
    {
        return mDefaultProperties.getProperty(sBULK_DUMP_TRAJECTORY_DIRECTORY_PROP, ".");
    }

    private Properties loadProperties()
    {
        Properties output = new Properties();
        try (FileReader fileReader = new FileReader(sSTANDALONE_PROPERTIES_FILE))
        {
            output.load(fileReader);
        }
        catch (Exception ex)
        {
            sLOGGER.error("", ex);
        }

        return output;
    }

    private void saveProperties()
    {
        try (FileOutputStream outputStream = new FileOutputStream(new File(sSTANDALONE_PROPERTIES_FILE)))
        {
            mDefaultProperties.store(outputStream, "Properties");
        }
        catch (Exception ex)
        {
            sLOGGER.error("", ex);
        }
    }
}
