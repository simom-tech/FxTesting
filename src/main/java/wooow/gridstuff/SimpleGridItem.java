package wooow.gridstuff;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * This item displays only one simple control.
 */
public class SimpleGridItem extends GridItem
{
	private final Node simpleControl;

	public SimpleGridItem(Node simpleControl)
	{
		super(simpleControl instanceof Button ?
				DirectViewType.BUTTON :
				simpleControl instanceof Toggle ? DirectViewType.TOGGLE :
						simpleControl instanceof Slider ? DirectViewType.SLIDER : DirectViewType.SPINNER);

		this.simpleControl = simpleControl;
		setStyle("-fx-background-color: red;");
		GridPane.setHalignment(simpleControl, HPos.CENTER);
		GridPane.setValignment(simpleControl, VPos.CENTER);
		setHgrow(simpleControl, Priority.ALWAYS);
		setVgrow(simpleControl, Priority.ALWAYS);
		setMargin(simpleControl, new Insets(10,10,10,10));
		setGridLinesVisible(true);
		add(simpleControl, 0, 0, 1, 1);
	}

	public SimpleGridItem(Node complexNode, int gridColumnIndex, int gridRowIndex, int gridColumnSpan, int gridRowSpan)
	{
		this(complexNode);
		setGridColumnIndex(gridColumnIndex);
		setGridRowIndex(gridRowIndex);
		setGridColumnSpan(gridColumnSpan);
		setGridRowSpan(gridRowSpan);
	}

	public SimpleGridItem(Node complexNode, int gridColumnIndex, int gridRowIndex)
	{
		this(complexNode);
		setGridColumnIndex(gridColumnIndex);
		setGridRowIndex(gridRowIndex);
	}

	public Node getSimpleControl()
	{
		return simpleControl;
	}
}
