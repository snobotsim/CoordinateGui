package org.snobot.nt.ramsete_plotter;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.snobot.coordinate_gui.model.Distance;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.model.Velocity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class StandaloneRamseteMain
{
    public static class PseudoMain extends Application
    {

        @Override
        public void start(Stage aPrimaryStage) throws IOException
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ramsete_plots.fxml"));
            Pane root = loader.load();
            Scene scene = new Scene(root);
            aPrimaryStage.setScene(scene);
            aPrimaryStage.show();

            RamsetePlotsController overviewController = loader.getController();
            tempFillOutData(overviewController);
        }
    }

    private StandaloneRamseteMain()
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
    public static void tempFillOutData(RamsetePlotsController aOverviewController) // NOPMD
    {
        Distance.Unit distanceUnit = Distance.Unit.Feet;
        Velocity.Unit velocityUnit = Velocity.Unit.FeetPerSec;

        final List<RamsetePointInfo> pathPoints = new ArrayList<>();

        for (int i = 0; i < 20; ++i)
        {
            double x = i + 5;
            pathPoints.add(new RamsetePointInfo(
                i * .05,
                Velocity.from(-i * .8, velocityUnit),
                new Position2dDistance(x, i * i, distanceUnit),
                i * 10.0));
        }

        aOverviewController.setIdeal(pathPoints, distanceUnit, velocityUnit);

        Thread t = new Thread(new Runnable() // NOPMD
        {

            @Override
            public void run()
            {
                List<RamseteInstantaneousPoint> actuals = new ArrayList<>();

                for (RamsetePointInfo ideal : pathPoints)
                {
                    RamseteInstantaneousPoint point = new RamseteInstantaneousPoint(
                        ideal.mTime + .01,
                        ideal.mHeading * 1.2,
                        new Position2dDistance(
                            ideal.mPosition.mX.as(Distance.Unit.Feet) + 5,
                            ideal.mPosition.mY.as(Distance.Unit.Feet), Distance.Unit.Feet),
                        Velocity.from(ideal.mVelocity.as(velocityUnit) * .8, velocityUnit),
                        Velocity.from(ideal.mVelocity.as(velocityUnit) * -.8, velocityUnit),
                        Velocity.from(ideal.mVelocity.as(velocityUnit) * .4, velocityUnit),
                        Velocity.from(ideal.mVelocity.as(velocityUnit) * -.4, velocityUnit)
                        );

                    actuals.add(point);
                }

                for (int i = 0; i < actuals.size(); ++i)
                {
                    final int ii = i;
                    Platform.runLater(() ->
                    {
                        RamseteInstantaneousPoint actual = actuals.get(ii);
                        aOverviewController.addPoint(actual, distanceUnit, velocityUnit);
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
