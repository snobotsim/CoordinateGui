package org.snobot.coordinate_gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
 
public class Main extends Application
{
    public static void main(String[] aArgs)
    {
        launch(aArgs);
    }

    @Override
    public void start(Stage aPrimaryStage) throws IOException
    {
        Pane root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(root);
        aPrimaryStage.setScene(scene);
        aPrimaryStage.show();
    }
}
