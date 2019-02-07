package org.snobot.coordinate_gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The true main class. This bypasses module boot layer introspection by the
 * Java launcher that attempts to reflectively access the JavaFX application
 * launcher classes - this will fail because there is no module path; everything
 * is in the same, unnamed module.
 */
@SuppressWarnings("PMD.UseUtilityClass") // Nope.
public final class Main
{
    public static class PseudoMain extends Application
    {

        @Override
        public void start(Stage aPrimaryStage) throws IOException
        {
            Pane root = FXMLLoader.load(getClass().getResource("main.fxml"));
            Scene scene = new Scene(root);
            aPrimaryStage.setScene(scene);
            aPrimaryStage.show();
        }
    }

    @SuppressWarnings("JavadocMethod")
    public static void main(String[] aArgs)
    {
        // JavaFX 11+ uses GTK3 by default, and has problems on some display
        // servers
        // This flag forces JavaFX to use GTK2
        System.setProperty("jdk.gtk.version", "2");
        Application.launch(PseudoMain.class, aArgs);
    }
}
