package wooow.gridstuff;

import javafx.scene.layout.GridPane;

/**
 * Children of {@link logic.RecursiveTileView}
 */
public abstract class GridItem extends GridPane
{
	public enum DirectViewType
	{
		NONE,
		/** This child is having n sub children. **/
		NESTED,
		/** Some control **/
		BUTTON, TOGGLE, SLIDER, SPINNER,
		/** TableView **/
		TABLE, LIST, GRAPH,
		/** Video or RTP-Stream **/
		VIDEO
	}
	//	private GridItem gridChild;
	private DirectViewType viewType;
	//todo implement enum with different action types - triggered on click
	//	private Action ??

	private int gridColumnIndex, gridRowIndex, gridColumnSpan=1, gridRowSpan=1;

	public GridItem(DirectViewType viewType)
	{
		this.viewType = viewType;
		setStyle("-fx-background-color: yellow");
	}

	public GridItem(DirectViewType viewType, int gridColumnIndex, int gridRowIndex, int gridColumnSpan, int gridRowSpan)
	{
		this(viewType);
		this.gridColumnIndex = gridColumnIndex;
		this.gridRowIndex = gridRowIndex;
		this.gridColumnSpan = gridColumnSpan;
		this.gridRowSpan = gridRowSpan;
	}

	public DirectViewType getViewType()
	{
		return viewType;
	}

	public void setViewType(DirectViewType viewType)
	{
		this.viewType = viewType;
	}

	public int getGridRowIndex()
	{
		return gridRowIndex;
	}

	public void setGridRowIndex(int gridRowIndex)
	{
		this.gridRowIndex = gridRowIndex;
	}

	public int getGridColumnIndex()
	{
		return gridColumnIndex;
	}

	public void setGridColumnIndex(int gridColumnIndex)
	{
		this.gridColumnIndex = gridColumnIndex;
	}

	public int getGridRowSpan()
	{
		return gridRowSpan;
	}

	public void setGridRowSpan(int gridRowSpan)
	{
		this.gridRowSpan = gridRowSpan;
	}

	public int getGridColumnSpan()
	{
		return gridColumnSpan;
	}

	public void setGridColumnSpan(int gridColumnSpan)
	{
		this.gridColumnSpan = gridColumnSpan;
	}
}
