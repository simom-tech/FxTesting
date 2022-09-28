package wooow.gridstuff;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * This item can display more complex data e.g. graphs, lists, video streams, etc..
 */
public class ComplexGridItem extends GridItem
{
	private Node complexNode = null;

	public ComplexGridItem(Node complexControl)
	{
		super(complexControl instanceof ListView<?> ?
				DirectViewType.LIST :
				complexControl instanceof TableView<?> ? DirectViewType.TABLE : DirectViewType.GRAPH);

		this.complexNode = complexControl;
		GridPane.setHalignment(complexNode, HPos.CENTER);
		GridPane.setValignment(complexNode, VPos.CENTER);
		setHgrow(complexNode, Priority.ALWAYS);
		setVgrow(complexNode, Priority.ALWAYS);
		setMargin(complexNode, new Insets(10,10,10,10));
		add(complexNode, 0, 0, 1, 1);
		setStyle("-fx-background-color: blue;");
	}

	public ComplexGridItem(Node complexNode, int gridColumnIndex, int gridRowIndex, int gridColumnSpan, int gridRowSpan)
	{
		this(complexNode);
		setGridColumnIndex(gridColumnIndex);
		setGridRowIndex(gridRowIndex);
		setGridColumnSpan(gridColumnSpan);
		setGridRowSpan(gridRowSpan);
	}

	public ComplexGridItem(Node complexNode, int gridColumnIndex, int gridRowIndex)
	{
		this(complexNode);
		setGridColumnIndex(gridColumnIndex);
		setGridRowIndex(gridRowIndex);
	}

	public Node getComplexNode()
	{
		return complexNode;
	}
}
