package logic;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

/** Example of dragging anchors around to manipulate a line. */
public class LineManipulator extends Application
{
	public static void main(String[] args)
	{
		System.err.println("Starting LineManipulator...");
		launch(args);
	}

	@Override
	public void start(Stage stage)
	{
		DoubleProperty startX = new SimpleDoubleProperty(100);
		DoubleProperty startY = new SimpleDoubleProperty(100);
		DoubleProperty endX = new SimpleDoubleProperty(300);
		DoubleProperty endY = new SimpleDoubleProperty(200);

		Anchor start = new Anchor(Color.PALEGREEN, startX, startY);
		Anchor end = new Anchor(Color.TOMATO, endX, endY);

		Line line = new BoundLine(startX, startY, endX, endY);
		stage.setTitle("Line Manipulation Sample");
		stage.setScene(new Scene(new Group(line, start, end), 400, 400, Color.ALICEBLUE));
		stage.show();
	}

	public static class BoundLine extends Line
	{
		BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY)
		{
			startXProperty().bind(startX);
			startYProperty().bind(startY);
			endXProperty().bind(endX);
			endYProperty().bind(endY);
			setStrokeWidth(2);
			setStrokeLineCap(StrokeLineCap.BUTT);
			getStrokeDashArray().setAll(10.0, 5.0);
			styleProperty().set("-fx-stroke: linear-gradient(to left, gold, red)");
			setMouseTransparent(true);
		}
	}

	// a draggable anchor displayed around a point.
	public static class Anchor extends Circle
	{
		public Anchor(Color color, DoubleProperty x, DoubleProperty y)
		{
			super(x.get(), y.get(), 10);
			setFill(color.deriveColor(1, 1, 1, 0.5));
			setStroke(color);
			setStrokeWidth(2);
			setStrokeType(StrokeType.OUTSIDE);

			x.bind(centerXProperty());
			y.bind(centerYProperty());
			enableDrag();
		}

		// make a node movable by dragging it around with the mouse.
		private void enableDrag()
		{
			final Delta dragDelta = new Delta();

			setOnMousePressed(mouseEvent -> {
				// record a delta distance for the drag and drop operation.
				dragDelta.x = getCenterX() - mouseEvent.getX();
				dragDelta.y = getCenterY() - mouseEvent.getY();
				getScene().setCursor(Cursor.MOVE);
			});

			setOnMouseReleased(mouseEvent -> getScene().setCursor(Cursor.HAND));

			setOnMouseDragged(mouseEvent -> {
				double newX = mouseEvent.getX() + dragDelta.x;
				double newY = mouseEvent.getY() + dragDelta.y;

				if(newX > 0 && newX < getScene().getWidth())
					setCenterX(newX);

				if(newY > 0 && newY < getScene().getHeight())
					setCenterY(newY);
			});

			setOnMouseEntered(mouseEvent -> {
				if(!mouseEvent.isPrimaryButtonDown())
					getScene().setCursor(Cursor.HAND);
			});

			setOnMouseExited(mouseEvent -> {
				if(!mouseEvent.isPrimaryButtonDown())
					getScene().setCursor(Cursor.DEFAULT);
			});
		}

		// records relative x and y co-ordinates.
		private static class Delta
		{
			double x, y;
		}
	}
}