package org.snobot.coordinate_gui.model;

import com.google.gson.Gson;
import edu.wpi.fields.FieldImages;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import org.snobot.coordinate_gui.ui.layers.BaseGuiController;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FieldLoader
{

    public enum FieldsConfig
    {
        Year2018(FieldImages.k2018PowerUpFieldConfig),
        Year2019(FieldImages.k2019DeepSpaceFieldConfig),
        Year2020(FieldImages.k2020InfiniteRechargeFieldConfig),
        Year2021InfiniteRechargeAtHome(FieldImages.k2021InfiniteRechargeFieldConfig),
        Year2021Barrel(FieldImages.k2021BarrelFieldConfig),
        Year2021Bounce(FieldImages.k2021BounceFieldConfig),
        Year2021GalacticSearchA(FieldImages.k2021GalacticSearchAFieldConfig),
        Year2021GalacticSearchB(FieldImages.k2021GalacticSearchBFieldConfig),
        Year2021Slalom(FieldImages.k2021SlalomFieldConfig),
        Year2022(FieldImages.k2022RapidReactFieldConfig);

        private String mConfigPath;

        FieldsConfig(String aConfigPath)
        {
            mConfigPath = aConfigPath;
        }
    }

    private final Image mImage;
    private final Distance mShortDistance;
    private final Distance mLongDistance;

    /**
     * Utility class to load a field.
     * @param aConfig The field config to load
     */
    public FieldLoader(FieldsConfig aConfig)
    {

        // create a reader
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(this.getClass().getResourceAsStream(aConfig.mConfigPath))))
        {
            Gson gson = new Gson();
            Map<?, ?> map = gson.fromJson(reader, Map.class);

            List<Double> fieldSize = (List<Double>) map.get("field-size");
            Map<String, ?> fieldCorners = (Map<String, ?>) map.get("field-corners");
            List<Double> pixelTopLeft = (List<Double>) fieldCorners.get("top-left");
            List<Double> pixelBotRight = (List<Double>) fieldCorners.get("bottom-right");
            double fieldPixelWidth = pixelBotRight.get(0) - pixelTopLeft.get(0);
            double fieldPixelHeight = pixelBotRight.get(1) - pixelTopLeft.get(1);

            String imageUrl = "/edu/wpi/first/fields/" + map.get("field-image");
            Image rawImage = new Image(BaseGuiController.class.getResource(imageUrl).toExternalForm());
            mImage = new WritableImage(rawImage.getPixelReader(), pixelTopLeft.get(0).intValue(), pixelTopLeft.get(1).intValue(), (int) fieldPixelWidth, (int) fieldPixelHeight);

            String fieldUnit = (String) map.get("field-unit");

            switch (fieldUnit)
            {
            case "feet":
            case "foot":
                mShortDistance = Distance.fromFeet(fieldSize.get(0));
                mLongDistance = Distance.fromFeet(fieldSize.get(1));
                break;
            default:
                throw new IllegalArgumentException("Invalid field unit " + fieldUnit);
            }

        }
        catch (IOException ex)
        {
            throw new IllegalArgumentException(ex);
        }
    }


    public Image getImage()
    {
        return mImage;
    }

    public Distance getShortDim()
    {
        return mShortDistance;
    }

    public Distance getLongDim()
    {
        return mLongDistance;
    }
}
