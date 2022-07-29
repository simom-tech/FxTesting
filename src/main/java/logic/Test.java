package logic;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import wooow.SomeCustomObject;

public class Test extends Application
{
	private static final DataFormat dataFormatObject = new DataFormat(SomeCustomObject.class.getPackageName());

	public static void mainEmf(String[] args)
	{
		System.err.println("Starting Tester...");
		launch(args);
	}

	@Override
	public void start(Stage primaryStage)
	{
		Thread cmdLineThread = new Thread(this::checkKeyboardInputShutdown, "WatchDog");
		cmdLineThread.start();
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setTitle("mäFxTEST - Drag 'n Drop");
		primaryStage.setWidth(primaryScreenBounds.getWidth());
		primaryStage.setHeight(primaryScreenBounds.getHeight());
//		primaryStage.setAlwaysOnTop(true);
		primaryStage.setMaximized(true);
//		primaryStage.setFullScreen(true);
		Parent p = buildParent();
		primaryStage.setScene(new Scene(p));
		primaryStage.show();
	}

	private Parent buildParent()
	{
		Parent p = null;

		VBox v = new VBox();
		v.styleProperty().set("-fx-background-color: green");

		HBox h = new HBox();
		h.styleProperty().set("-fx-background-color: red");

		Button b = new Button("Drag me");
		TextField tfString = new TextField("String");
		TextField tfBoolean = new TextField("true");
		TextField tfInt = new TextField("5");
		StackPane stackPane = new StackPane();
		stackPane.setAlignment(Pos.TOP_LEFT);
		stackPane.styleProperty().set("-fx-background-color: yellow");

		b.setOnDragDetected((MouseEvent m) -> {
			SomeCustomObject s = new SomeCustomObject(tfString.getText(), tfBoolean.getText().equals("true"), Integer.parseInt(tfInt.getText()));

			Dragboard db = b.startDragAndDrop(TransferMode.ANY);

			ClipboardContent content = new ClipboardContent();
			content.put(dataFormatObject, s);

			db.setContent(content);
		});
		b.setOnMouseDragged((MouseEvent m) -> m.setDragDetect(true));

		stackPane.setOnDragOver(m -> {
			if(m.getGestureSource() != stackPane && m.getDragboard().hasContent(dataFormatObject))
				m.acceptTransferModes(TransferMode.COPY_OR_MOVE);

			m.consume();
		});
		stackPane.setOnDragDropped((DragEvent m) -> {
			Dragboard db = m.getDragboard();

			if(db.hasContent(dataFormatObject))
			{
				System.out.println("Dropped: " + db.getContent(dataFormatObject).toString());
				m.setDropCompleted(true);

				SomeCustomObject s = (SomeCustomObject) db.getContent(dataFormatObject);

				VBox vb = new VBox();
				vb.styleProperty().set("-fx-background-color: blue");
				vb.setTranslateX(m.getX());
				vb.setTranslateY(m.getY());

				vb.prefHeightProperty().set(60);
				vb.prefWidthProperty().set(120);

				Label lString = new Label(s.getString());
				Label lBoolean = new Label(s.isBool() ? "true" : "false");
				Label lInteger = new Label(String.valueOf(s.getAnInt()));

				vb.getChildren().addAll(lString, lBoolean, lInteger);

				stackPane.getChildren().add(vb);
			}
			else
				m.setDropCompleted(false);

			m.consume();
		});


		stackPane.getChildren().add(new Button("Test"));

		VBox.setVgrow(stackPane, Priority.ALWAYS);

		h.getChildren().add(b);
		h.getChildren().add(tfString);
		h.getChildren().add(tfBoolean);
		h.getChildren().add(tfInt);
		v.getChildren().add(h);
		v.getChildren().add(stackPane);

		p = v;

		return p;
	}

	private Circle createCircle(String strokeColor, String fillColor, double x)
	{
		Circle circle = new Circle();
		circle.setCenterX(x);
		circle.setCenterY(200);
		circle.setRadius(50);
		circle.setStroke(Color.valueOf(strokeColor));
		circle.setStrokeWidth(5);
		circle.setFill(Color.valueOf(fillColor));
		return circle;
	}

	private void checkKeyboardInputShutdown()
	{
		Runtime rt = Runtime.getRuntime();

		boolean cycle = true;
		long gcCounter = 0, used_memory_MB, counter = 500;

		while(cycle)
		{
			try
			{
				Thread.sleep(80);
			}
			catch(InterruptedException e)
			{
				System.err.println("Couldn't put thread to sleep." + e.getMessage());
			}

			if(++counter >= 500) //500 -> 40secs
			{
				counter = 0;
				used_memory_MB = (rt.totalMemory() - rt.freeMemory()) / 1000000;
				System.out.println("Used memory: " + used_memory_MB + " MB");
			}

			if(++gcCounter >= 1500)
			{
				gcCounter = 0;
				System.err.println("Running garbage collector.");
				System.gc();
			}
		}
	}
}