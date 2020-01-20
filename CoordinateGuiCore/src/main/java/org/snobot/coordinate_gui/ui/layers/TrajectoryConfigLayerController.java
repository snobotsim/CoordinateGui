package org.snobot.coordinate_gui.ui.layers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.snobot.coordinate_gui.model.Coordinate;
import org.snobot.coordinate_gui.model.PixelConverter;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.DataFormat;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import org.snobot.coordinate_gui.model.Position2dDistance;
import org.snobot.coordinate_gui.model.Position2dPixels;

public class TrajectoryConfigLayerController
{

    /**
     * Data format for dragging waypoints.
     */
    public static final DataFormat WAYPOINT = new DataFormat("coordinate_gui/waypoint");

    /**
     * Data format for dragging waypoints.
     */
    public static final DataFormat ANGLE_LINE = new DataFormat("coordinate_gui/angle_line");

    @FXML
    protected Group mPathPoints;

    private final List<Coordinate> mCoordinates;
    private final List<CoodrinateWrapper> mWrapper;

    private CoodrinateWrapper mCurrentCoordinate;

    public static class CoodrinateWrapper
    {
        private static final double SIZE = 30;
        private static final double TANGENT_LENGTH = 100;
        private static final double X_OFFSET = (SIZE * 3.0 / 5.0) / 16.5;

        public final Coordinate mCoordinate;
        private final PixelConverter mPixelConverter;
        private final DoubleProperty mX;
        private final DoubleProperty mY;
        private final DoubleProperty mAngle;
        private final Polygon mIcon;
        private final Line mTangentLine;

        /**
         * Constructor.
         * 
         * @param aPixelConverter
         *            The pixel converter
         * @param aCoordinate
         *            The coordinate for this wrapper
         */
        public CoodrinateWrapper(PixelConverter aPixelConverter, Coordinate aCoordinate)
        {
            mPixelConverter = aPixelConverter;
            mCoordinate = aCoordinate;

            mX = new SimpleDoubleProperty();
            mY = new SimpleDoubleProperty();
            mAngle = new SimpleDoubleProperty();

            setPosition(mCoordinate.mPosition);
            setAngle(mCoordinate.mAngle);

            mIcon = new Polygon(0.0, SIZE / 3, SIZE, 0.0, 0.0, -SIZE / 3);
            mIcon.setLayoutX(-(mIcon.getLayoutBounds().getMaxX() + mIcon.getLayoutBounds().getMinX()) / 2 - X_OFFSET);
            mIcon.setLayoutY(-(mIcon.getLayoutBounds().getMaxY() + mIcon.getLayoutBounds().getMinY()) / 2);

            mIcon.setFill(javafx.scene.paint.Color.RED);
            mIcon.translateXProperty().bind(mX);
            mIcon.translateYProperty().bind(mY);
            mIcon.rotateProperty().bind(mAngle);

            mTangentLine = new Line();
            mTangentLine.setStroke(javafx.scene.paint.Color.GREENYELLOW);
            mTangentLine.setStrokeWidth(4);
            mTangentLine.startXProperty().bind(mX);
            mTangentLine.startYProperty().bind(mY);
            mTangentLine.endXProperty().bind(Bindings.createObjectBinding(() ->
            {
                return mX.get() + TANGENT_LENGTH * Math.cos(Math.toRadians(mAngle.get()));
            }, mAngle, mX));
            mTangentLine.endYProperty().bind(Bindings.createObjectBinding(() ->
            {
                return mY.get() + TANGENT_LENGTH * Math.sin(Math.toRadians(mAngle.get()));
            }, mAngle, mY));
        }

        /**
         * Sets a new position for the coordinate, in feet.
         * 
         * @param aPosition
         *            The position
         */
        public final void setPosition(Position2dDistance aPosition)
        {
            mCoordinate.mPosition = aPosition;
            Position2dPixels asPixels = mPixelConverter.convertDistanceToPixels(aPosition);
            mX.set(asPixels.mX);
            mY.set(asPixels.mY);
        }

        public final void setAngle(double aAngle)
        {
            mCoordinate.mAngle = aAngle;
            mAngle.set(aAngle - 90);
        }

        public Polygon getIcon()
        {
            return mIcon;
        }
    }

    public TrajectoryConfigLayerController()
    {
        mWrapper = new ArrayList<>();
        mCoordinates = new ArrayList<>();
    }

    /**
     * Sets the trajectory "turning points" for the layer to draw.
     * 
     * @param aPixelConverter
     *            The pixel converter utility
     * @param aTrajectoryPoints
     *            The points to draw
     */
    public void setTrajectoryPoints(PixelConverter aPixelConverter, List<Coordinate> aTrajectoryPoints)
    {
        mCoordinates.clear();
        mCoordinates.addAll(aTrajectoryPoints);

        mWrapper.clear();
        mPathPoints.getChildren().clear();

        for (Coordinate coord : mCoordinates)
        {
            CoodrinateWrapper coordinateWrapper = new CoodrinateWrapper(aPixelConverter, coord);

            coordinateWrapper.mIcon.setOnDragDetected(event ->
            {
                mCurrentCoordinate = coordinateWrapper;
                coordinateWrapper.mIcon.startDragAndDrop(TransferMode.ANY).setContent(Map.of(WAYPOINT, "point"));
                event.consume();
            });

            coordinateWrapper.mTangentLine.setOnDragDetected(event ->
            {
                mCurrentCoordinate = coordinateWrapper;
                coordinateWrapper.mTangentLine.startDragAndDrop(TransferMode.MOVE).setContent(Map.of(ANGLE_LINE, "vector"));
            });

            mWrapper.add(coordinateWrapper);
            mPathPoints.getChildren().add(coordinateWrapper.mTangentLine);
            mPathPoints.getChildren().add(coordinateWrapper.mIcon);
        }
    }

    public void addPoint(PixelConverter aPixelConverter, Coordinate aCoordinate)
    {
        mCoordinates.add(aCoordinate);
        setTrajectoryPoints(aPixelConverter, new ArrayList<>(mCoordinates));
    }

    public List<Coordinate> getWaypoints()
    {
        return mCoordinates;
    }

    public CoodrinateWrapper getSelectedWaypoint()
    {
        return mCurrentCoordinate;
    }
}
