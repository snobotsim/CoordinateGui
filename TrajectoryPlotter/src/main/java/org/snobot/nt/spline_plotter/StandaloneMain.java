package org.snobot.nt.spline_plotter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public final class StandaloneMain
{
    public static class PseudoMain extends Application
    {

        @Override
        public void start(Stage aPrimaryStage) throws IOException
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("trajectory_plots.fxml"));
            Pane root = loader.load();
            Scene scene = new Scene(root);
            aPrimaryStage.setScene(scene);
            aPrimaryStage.show();

            TrajectoryPlotsController overviewController = loader.getController();
            tempFillOutData(overviewController);
        }
    }

    private StandaloneMain()
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
    public static void tempFillOutData(TrajectoryPlotsController aOverviewController) // NOPMD
    {

        final List<SplineSegment> pathPoints = new ArrayList<>();

        SplineSegment p;

        p = new SplineSegment();
        pathPoints.add(p);

        double radius = 1.7;
        double angleMult = .1;

        for (int i = 1; i < 10; ++i)
        {
            p = new SplineSegment();
            p.mLeftSideVelocity = 0 + i * .7;
            p.mLeftSidePosition = pathPoints.get(pathPoints.size() - 1).mLeftSidePosition + p.mLeftSideVelocity * .02;
            p.mRightSideVelocity = p.mLeftSideVelocity;
            p.mRightSidePosition = p.mLeftSidePosition;
            p.mRobotHeading = i;
            p.mAverageX = (radius * i) * Math.sin(angleMult * i);
            p.mAverageY = (radius * i) * Math.cos(angleMult * i);

            pathPoints.add(p);
        }
        for (int i = 0; i < 20; ++i)
        {
            p = new SplineSegment();
            p.mLeftSideVelocity = 7.0;
            p.mLeftSidePosition = pathPoints.get(pathPoints.size() - 1).mLeftSidePosition + p.mLeftSideVelocity * .02;
            p.mRightSideVelocity = p.mLeftSideVelocity;
            p.mRightSidePosition = p.mLeftSidePosition;
            p.mRobotHeading = i;
            p.mAverageX = (radius * i) * Math.sin(angleMult * i);
            p.mAverageY = (radius * i) * Math.cos(angleMult * i);
            pathPoints.add(p);
        }
        for (int i = 0; i < 10; ++i)
        {
            p = new SplineSegment();
            p.mLeftSideVelocity = 7 - i * .7;
            p.mLeftSidePosition = pathPoints.get(pathPoints.size() - 1).mLeftSidePosition + p.mLeftSideVelocity * .02;
            p.mRightSideVelocity = p.mLeftSideVelocity;
            p.mRightSidePosition = p.mLeftSidePosition;
            p.mRobotHeading = i;
            p.mAverageX = (radius * i) * Math.sin(angleMult * i);
            p.mAverageY = (radius * i) * Math.cos(angleMult * i);
            pathPoints.add(p);
        }

        p = new SplineSegment();
        p.mLeftSideVelocity = 0;
        p.mLeftSidePosition = pathPoints.get(pathPoints.size() - 1).mLeftSidePosition + p.mLeftSideVelocity * .02;
        p.mRightSideVelocity = p.mLeftSideVelocity;
        p.mRightSidePosition = p.mLeftSidePosition;
        p.mRobotHeading = 0;
        pathPoints.add(p);

        aOverviewController.setPath(pathPoints);

        Thread t = new Thread(new Runnable() // NOPMD
        {

            @Override
            public void run()
            {
                List<SplineSegment> actuals = new ArrayList<SplineSegment>();

                for (int i = 0; i < pathPoints.size(); ++i)
                {
                    SplineSegment p = pathPoints.get(i);
                    p.mLeftSideVelocity *= .9;
                    p.mLeftSidePosition = 0;
                    p.mRightSideVelocity = p.mLeftSideVelocity * .5;
                    p.mRightSidePosition = 0;
                    p.mRobotHeading = 0;

                    if (i != 0)
                    {
                        p.mLeftSidePosition = actuals.get(i - 1).mLeftSidePosition + p.mLeftSideVelocity * .02;
                        p.mRightSidePosition = p.mLeftSidePosition * .5;
                        p.mRobotHeading = i * .8;
                        p.mAverageX = p.mAverageX * .8;
                        p.mAverageY = p.mAverageY * .8;
                    }

                    actuals.add(p);
                }

                for (int i = 0; i < actuals.size(); ++i)
                {
                    int xx = i;
                    Platform.runLater(() ->
                    {
                        aOverviewController.setPoint(xx, actuals.get(xx));
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
