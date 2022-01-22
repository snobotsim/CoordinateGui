package org.snobot.coordinate_gui.shuffleboard.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import edu.wpi.first.shuffleboard.api.data.ComplexData;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.ui.layers.CameraRayLayerController;

public class VisionData extends ComplexData<VisionData>
{
    private static final double[] DEFAULT_DATA = {};
    private final double[] mData;

    public VisionData()
    {
        this(DEFAULT_DATA);
    }

    /**
     * Constructor.
     *
     * @param aMap
     *            The map potentially containing the coordinate data
     */
    public VisionData(Map<String, Object> aMap)
    {
        this("", aMap);
    }

    /**
     * Constructor.
     *
     * @param aPrefix
     *            Optional prefix to prepend the names with in the map
     * @param aMap
     *            The map potentially containing the coordinate data
     */
    public VisionData(String aPrefix, Map<String, Object> aMap)
    {
        this((double[]) aMap.getOrDefault(aPrefix + SmartDashboardNames.sCAMERA_POSITIONS, DEFAULT_DATA));
    }

    /**
     * Constructor.
     *
     * @param aData
     *            The vision data
     */
    public VisionData(double[] aData)
    {
        mData = Arrays.copyOf(aData, aData.length);
    }

    @Override
    public Map<String, Object> asMap()
    {
        return asMap("");
    }

    /**
     * Gets a representation of this data as a map.
     *
     * @param aPrefix
     *            The prefix to prepend to the field names
     * @return The map representation
     */
    public Map<String, Object> asMap(String aPrefix)
    {
        Map<String, Object> map = new HashMap<>();
        map.put(aPrefix + SmartDashboardNames.sCAMERA_POSITIONS, mData);
        return map;
    }

    @Override
    public String toString()
    {
        return "VisionData [mValue=" + Arrays.toString(mData) + "]";
    }

    /**
     * Converts this to the data model the gui core understands.
     * @param aDistanceUnit the distance units to use
     * @return The new value
     */
    @SuppressWarnings("PMD.AvoidReassigningLoopVariables")
    public List<CameraRayLayerController.Ray> toRays(Distance.Unit aDistanceUnit)
    {
        List<CameraRayLayerController.Ray> rays = new ArrayList<>();
        Logger.getLogger(VisionData.class.getName()).severe("Getting camera data... " + Arrays.toString(mData));

        for (int i = 0; i < mData.length;)
        {
            CameraRayLayerController.Ray ray = new CameraRayLayerController.Ray();
            ray.mStart.mX = Distance.from(mData[i++], aDistanceUnit);
            ray.mStart.mY = Distance.from(mData[i++], aDistanceUnit);
            ray.mEnd.mX = Distance.from(mData[i++], aDistanceUnit);
            ray.mEnd.mY = Distance.from(mData[i++], aDistanceUnit);
            rays.add(ray);
        }

        Logger.getLogger(VisionData.class.getName()).severe("--Got " + rays);
        return rays;
    }
}
