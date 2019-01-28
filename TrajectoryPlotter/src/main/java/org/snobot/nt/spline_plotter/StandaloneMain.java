package org.snobot.nt.spline_plotter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public final class StandaloneMain extends Application
{
    @Override
    public void start(Stage aPrimaryStage) throws IOException
    {
        Pane root = FXMLLoader.load(getClass().getResource("trajectory_overview.fxml"));
        Scene scene = new Scene(root);
        aPrimaryStage.setScene(scene);
        aPrimaryStage.show();
    }

    /**
     * Main Runner.
     * 
     * @param aArgs
     *            optional arguments
     */
    public static void main(String[] aArgs)
    {
        launch(aArgs);
    }

    /**
     * Test function that can populate the view with data.
     */
    public void tempFillOutData() // NOPMD
    {

        final List<SplineSegment> path_points = new ArrayList<SplineSegment>();

        SplineSegment p;

        p = new SplineSegment();
        path_points.add(p);

        double radius = 1.7;
        double angleMult = .1;

        for (int i = 1; i < 10; ++i)
        {
            p = new SplineSegment();
            p.mLeftSideVelocity = 0 + i * .7;
            p.mLeftSidePosition = path_points.get(path_points.size() - 1).mLeftSidePosition + p.mLeftSideVelocity * .02;
            p.mRightSideVelocity = p.mLeftSideVelocity;
            p.mRightSidePosition = p.mLeftSidePosition;
            p.mRobotHeading = i;
            p.mAverageX = (radius * i) * Math.sin(angleMult * i);
            p.mAverageY = (radius * i) * Math.cos(angleMult * i);

            path_points.add(p);
        }
        for (int i = 0; i < 20; ++i)
        {
            p = new SplineSegment();
            p.mLeftSideVelocity = 7.0;
            p.mLeftSidePosition = path_points.get(path_points.size() - 1).mLeftSidePosition + p.mLeftSideVelocity * .02;
            p.mRightSideVelocity = p.mLeftSideVelocity;
            p.mRightSidePosition = p.mLeftSidePosition;
            p.mRobotHeading = i;
            p.mAverageX = (radius * i) * Math.sin(angleMult * i);
            p.mAverageY = (radius * i) * Math.cos(angleMult * i);
            path_points.add(p);
        }
        for (int i = 0; i < 10; ++i)
        {
            p = new SplineSegment();
            p.mLeftSideVelocity = 7 - i * .7;
            p.mLeftSidePosition = path_points.get(path_points.size() - 1).mLeftSidePosition + p.mLeftSideVelocity * .02;
            p.mRightSideVelocity = p.mLeftSideVelocity;
            p.mRightSidePosition = p.mLeftSidePosition;
            p.mRobotHeading = i;
            p.mAverageX = (radius * i) * Math.sin(angleMult * i);
            p.mAverageY = (radius * i) * Math.cos(angleMult * i);
            path_points.add(p);
        }

        p = new SplineSegment();
        p.mLeftSideVelocity = 0;
        p.mLeftSidePosition = path_points.get(path_points.size() - 1).mLeftSidePosition + p.mLeftSideVelocity * .02;
        p.mRightSideVelocity = p.mLeftSideVelocity;
        p.mRightSidePosition = p.mLeftSidePosition;
        p.mRobotHeading = 0;
        path_points.add(p);

        // setPath(path_points);

        Thread t = new Thread(new Runnable() // NOPMD
        {

            @Override
            public void run()
            {
                List<SplineSegment> actuals = new ArrayList<SplineSegment>();

                for (int i = 0; i < path_points.size(); ++i)
                {
                    SplineSegment p = path_points.get(i);
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

//                for (int i = 0; i < actuals.size(); ++i)
//                {
//                    // setPoint(i, actuals.get(i));
//
//                    try
//                    {
//                        Thread.sleep(500);
//                    }
//                    catch (InterruptedException ex)
//                    {
//                        ex.printStackTrace(); // NOPMD
//                    }
//                }
            }
        });
        t.start();
    }
}
