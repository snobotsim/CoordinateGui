package org.snobot.coordinate_gui.game.rapid_react;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Position2dDistance;

import java.io.IOException;

public final class StandaloneMain
{
    public static class PseudoMain extends Application
    {

        @Override
        public void start(Stage aPrimaryStage) throws IOException
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("rapid_react_field.fxml"));
            Pane root = loader.load();
            RapidReactController robotController = loader.getController();

            Scene scene = new Scene(root);
            aPrimaryStage.setScene(scene);

            aPrimaryStage.show();

            robotController.addRobotPosition(new Coordinate(new Position2dDistance(Distance.fromFeet(0), Distance.fromFeet(0)), 0));
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
