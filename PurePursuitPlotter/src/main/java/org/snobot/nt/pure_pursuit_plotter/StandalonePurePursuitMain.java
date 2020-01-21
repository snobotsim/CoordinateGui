package org.snobot.nt.pure_pursuit_plotter;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.snobot.coordinate_gui.model.Coordinate;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Position2dDistance;

public final class StandalonePurePursuitMain
{
    public static class PseudoMain extends Application
    {

        @Override
        public void start(Stage aPrimaryStage) throws IOException
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pure_pursuit_plots.fxml"));
            Pane root = loader.load();
            Scene scene = new Scene(root);
            aPrimaryStage.setScene(scene);
            aPrimaryStage.show();

            PurePursuitPlotsController overviewController = loader.getController();
            tempFillOutData(overviewController);
        }
    }

    private StandalonePurePursuitMain()
    {

    }

    /**
     * Main Runner.
     * 
     * @param aArgs
     *            optional arguments
     */
    public static void main(String[] aArgs)
    {
        // JavaFX 11+ uses GTK3 by default, and has problems on some display
        // servers
        // This flag forces JavaFX to use GTK2
        System.setProperty("jdk.gtk.version", "2");
        Application.launch(PseudoMain.class, aArgs);
    }

    /**
     * Test function that can populate the view with data.
     */
    public static void tempFillOutData(PurePursuitPlotsController aOverviewController) // NOPMD
    {

        final List<Coordinate> pathPoints = new ArrayList<Coordinate>();

        for (int i = -20; i < 20; ++i)
        {
            double x = i + 5;
            pathPoints.add(new Coordinate(new Position2dDistance(x, i * i, Distance.Unit.FEET), 0));
        }

        aOverviewController.setIdealPath(pathPoints);

        Thread t = new Thread(new Runnable() // NOPMD
        {

            @Override
            public void run()
            {
                List<PurePursuitPointInfo> actuals = new ArrayList<PurePursuitPointInfo>();

                for (int i = 0; i < pathPoints.size(); ++i)
                {
                    Coordinate ideal = pathPoints.get(i);
                    PurePursuitPointInfo point = new PurePursuitPointInfo(i);
                    point.mX = ideal.mPosition.mX.as(Distance.Unit.FEET) * .8;
                    point.mY = ideal.mPosition.mY.as(Distance.Unit.FEET) * .7;
                    point.mLeftVelocity = i * .8;
                    point.mLeftGoalVelocity = i * i * .7;
                    point.mRightVelocity = ideal.mPosition.mX.as(Distance.Unit.FEET) * .8 + 6;
                    point.mRightGoalVelocity = i * i + 9;

                    actuals.add(point);
                }

                for (int i = 0; i < actuals.size(); ++i)
                {
                    final int ii = i;
                    Platform.runLater(() ->
                    {
                        PurePursuitPointInfo actual = actuals.get(ii);
                        aOverviewController.addPoint(actual);
                    });

                    try
                    {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException ex)
                    {
                        ex.printStackTrace(); // NOPMD
                    }
                }
            }
        });
        t.start();
    }
}
