package logic;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import wooow.gridstuff.ComplexGridItem;
import wooow.gridstuff.NestedGridItem;
import wooow.gridstuff.SimpleGridItem;

import java.util.Arrays;

/**
 * This view represents a
 */
public class RecursiveTileView extends Application
{
	private NestedGridItem rootGridItem;

	public static void main(String[] args)
	{
		System.out.println("Starting RecursiveTileView...");
		launch(args);
	}

	@Override
	public void start(Stage stage)
	{
		rootGridItem = new NestedGridItem();
		rootGridItem.setPrefHeight(1080);
		rootGridItem.setPrefWidth(1920);
		rootGridItem.setGridLinesVisible(true);
		rootGridItem.getColumnConstraints().addAll(new ColumnConstraints(300), new ColumnConstraints(300), new ColumnConstraints(300), new ColumnConstraints(300));
		rootGridItem.getRowConstraints().addAll(new RowConstraints(300), new RowConstraints(300), new RowConstraints(300));
		rootGridItem.styleProperty().set("-fx-background-color: purple;");

		stage.setTitle("Click tiles to either open them or trigger their action!");
		stage.setScene(new Scene(rootGridItem));
		stage.show();
		initializeItems();
	}

	private void initializeItems()
	{
		//root-nested tile
		Button b = new Button("Button test");
		b.setOnAction(actionEvent -> System.out.println("Hello Button"));
		SimpleGridItem simpleGridItemButton = new SimpleGridItem(b);
		simpleGridItemButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> System.out.println("Hello SimpleGridItem (Button)"));

		ListView<String> list = new ListView<>(FXCollections.observableList(Arrays.asList("Licht 1", "Licht 2", "Licht 3","Licht 4","Licht 1", "Licht 2", "Licht 3","Licht 4","Licht 1", "Licht 2", "Licht 3","Licht 4","Licht 1", "Licht 2", "Licht 3","Licht 4","Licht 5", "Licht 3","Licht 4","Licht 1", "Licht 2", "Licht 3","Licht 4","Licht 5", "Licht 3","Licht 4","Licht 1", "Licht 2", "Licht 3","Licht 4","Licht 5", "Licht 3","Licht 4","Licht 1", "Licht 2", "Licht 3","Licht 4","Licht 5", "Licht 3","Licht 4","Licht 1", "Licht 2", "Licht 3","Licht 4","Licht 5")));
		ComplexGridItem complexGridItem2 = new ComplexGridItem(list,2,0);
		ComplexGridItem complexGridItem1 = new ComplexGridItem(list,3,2);
		ComplexGridItem complexGridItem = new ComplexGridItem(list,0,0,2,2);
		complexGridItem.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> System.out.println("Hello ComplexListItem (List<String>)"));

		SimpleGridItem simpleGridItem1 = new SimpleGridItem(new Pane(),2,1);
		SimpleGridItem simpleGridItem2 = new SimpleGridItem(new org.controlsfx.control.ToggleSwitch(),1,2);
		SimpleGridItem simpleGridItem3 = new SimpleGridItem(new Spinner<Number>(),3,0);
		SimpleGridItem simpleGridItem4 = new SimpleGridItem(new Slider(),2,2);

		rootGridItem.add(complexGridItem,0,0, 2, 2);
		rootGridItem.add(complexGridItem2,2,0);
		rootGridItem.add(simpleGridItem3,3,0);
		rootGridItem.add(simpleGridItem1,2,1);
		rootGridItem.add(simpleGridItem2,1,2);
		rootGridItem.add(simpleGridItemButton,0,2);
		rootGridItem.add(simpleGridItem4,2,2);
		rootGridItem.add(complexGridItem1,3,2);

		//nested tile_1
		SimpleGridItem simpleGridItem_1 = new SimpleGridItem(new Pane(),0,0);
		SimpleGridItem simpleGridItem_2 = new SimpleGridItem(new Pane(),1,0);
		ComplexGridItem complexGridItem_1 = new ComplexGridItem(new Pane(),2,0);
		SimpleGridItem simpleGridItem_3 = new SimpleGridItem(new Pane(),0,1);
		NestedGridItem nestedGridItem_1 = new NestedGridItem(Arrays.asList(new ComplexGridItem(new Pane())),1,1);
		nestedGridItem_1.setStyle("-fx-background-color: yellow;");
		NestedGridItem nestedPoolBefuellung = new NestedGridItem(Arrays.asList(simpleGridItem_1,simpleGridItem_2,complexGridItem_1, simpleGridItem_3, nestedGridItem_1));

		rootGridItem.add(nestedPoolBefuellung,3,1);
	}
}
