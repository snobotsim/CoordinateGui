package org.snobot.coordinate_gui.game.steamworks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.ui.layers.BaseGuiController;

import java.io.IOException;

public final class StandaloneMain
{
    public static class PseudoMain extends Application
    {

        @Override
        public void start(Stage aPrimaryStage) throws IOException
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("steamworks_field.fxml"));

            Pane root = loader.load();
            BaseGuiController robotController = loader.getController();

            Scene scene = new Scene(root);
            aPrimaryStage.setScene(scene);

            aPrimaryStage.show();


//            robotController.addRobotPosition(new Coordinate(new Position2dDistance(Distance.fromFeet(0), Distance.fromFeet(0)), 0));
//            robotController.addRobotPosition(new Coordinate(new Position2dDistance(Distance.fromFeet(30), Distance.fromFeet(0)), 0));
//            robotController.addRobotPosition(new Coordinate(new Position2dDistance(Distance.fromFeet(30), Distance.fromFeet(15)), 0));
//            robotController.addRobotPosition(new Coordinate(new Position2dDistance(Distance.fromFeet(0), Distance.fromFeet(15)), 0));
//            robotController.addRobotPosition(new Coordinate(new Position2dDistance(Distance.fromFeet(5), Distance.fromFeet(5)), 0));

//            robotController.addRobotPosition(new Coordinate(new Position2dDistance(Distance.fromFeet(0), Distance.fromFeet(0)), 0));
            robotController.addRobotPosition(new Coordinate(new Position2dDistance(Distance.fromFeet(0), Distance.fromFeet(27)), 0));
//            robotController.addRobotPosition(new Coordinate(new Position2dDistance(Distance.fromFeet(52), Distance.fromFeet(0)), 0));
//            robotController.addRobotPosition(new Coordinate(new Position2dDistance(Distance.fromFeet(52), Distance.fromFeet(13.5)), 0));
//            robotController.addRobotPosition(new Coordinate(new Position2dDistance(Distance.fromFeet(52), Distance.fromFeet(26)), 0));
//            robotController.addRobotPosition(new Coordinate(new Position2dDistance(Distance.fromFeet(0), Distance.fromFeet(26)), 0));
        }
    }

    private StandaloneMain()
    {

    }

    @SuppressWarnings("JavadocMethod")
    public static void main(String[] aArgs)
    {
        // JavaFX 11+ uses GTK3 by default, and has problems on some display
        // servers
        // This flag forces JavaFX to use GTK2
        // System.setProperty("jdk.gtk.version", "2");
        Application.launch(PseudoMain.class, aArgs);
    }
}
