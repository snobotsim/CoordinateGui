package org.snobot.coordinate_gui.game.infinite_recharge_at_home;

import javafx.fxml.FXMLLoader;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.ui.layers.BaseGuiController;
import org.snobot.coordinate_gui.ui.layers.PurePursuitLayerController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.testfx.framework.junit5.ApplicationTest;


public final class TestLoadControllers extends ApplicationTest
{

    /**
     * Gets the resources to load and test.
     *
     * @return The parameterized args
     */
    public static List<String> getFields()
    {
        List<String> output = new ArrayList<>();
        output.add("barrel_roll_field.fxml");
        output.add("bounce_field.fxml");
        output.add("galactic_search_a.fxml");
        output.add("galactic_search_b.fxml");
        output.add("infinite_recharge_field.fxml");
        return output;
    }

    /**
     * Actual tester.
     *
     * @param aResource the resource to load
     * @throws IOException If test fails
     */
    @ParameterizedTest
    @MethodSource("getFields")
    public void testLoad(String aResource) throws IOException
    {


        FXMLLoader loader = new FXMLLoader(getClass().getResource(aResource));
        loader.load();

        BaseGuiController robotController = loader.getController();

        Position2dDistance dummyDistance = new Position2dDistance(0, 0, Distance.Unit.FEET);
        robotController.setRamseteWaypoints(new ArrayList<>());
        robotController.setPurePursuitWaypoints(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        robotController.addRobotPosition(new Coordinate());
        robotController.setCameraRays(new ArrayList<>());
        robotController.setWaypoints(new ArrayList<>());
        robotController.setIdealTrajectory(new ArrayList<>());
        robotController.setIdealRamsete(new ArrayList<>());
        robotController.setRamseteWaypoints(new ArrayList<>());
        robotController.addIdealTrajectory(new Coordinate());
        robotController.setGoToXYPosition(dummyDistance);
        robotController.setPurePursuitLookahead(new PurePursuitLayerController.PurePursuitLookaheadData(dummyDistance, dummyDistance));
    }

}
