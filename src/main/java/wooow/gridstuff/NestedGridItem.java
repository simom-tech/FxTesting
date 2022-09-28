package wooow.gridstuff;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * This item simply contains all kind of {@link GridItem}s.
 */
public class NestedGridItem extends GridItem
{

	public NestedGridItem()
	{
		super(DirectViewType.NESTED);
		setStyle("-fx-background-color: green;");
		setVgap(20);
		setHgap(20);
	}

	public NestedGridItem(List<? extends GridItem> items)
	{
		this();
		addItems(items);
	}

	public NestedGridItem(List<? extends GridItem> items, int gridColumnIndex, int gridRowIndex)
	{
		this(items);
		setGridColumnIndex(gridColumnIndex);
		setGridRowIndex(gridRowIndex);
	}

	private void addItems(List<? extends GridItem> items)
	{
		int maxColumnIndex = items.stream().map(GridItem::getGridColumnIndex).mapToInt(v -> v).max().orElseThrow(NoSuchElementException::new)+1;
		int maxRowIndex = items.stream().map(GridItem::getGridRowIndex).mapToInt(v -> v).max().orElseThrow(NoSuchElementException::new)+1;

		System.out.println("MaxColumn: "+maxColumnIndex+", MaxRow: "+maxRowIndex);

		for(int i = 0; i < maxColumnIndex; i++)
		{
			ColumnConstraints columnConstraints = new ColumnConstraints();
			columnConstraints.setPercentWidth(maxColumnIndex >= 4 ? 20 : maxColumnIndex == 3 ? 40 : maxColumnIndex == 2 ? 60 : 80);
//			columnConstraints.setPrefWidth(getHeight()* (maxColumnIndex >= 4 ? 0.2 : maxColumnIndex == 3 ? 0.4 : maxColumnIndex == 2 ? 0.6 : 0.8));
			getColumnConstraints().add(columnConstraints);
			System.out.println("Adding ColumnConstraint: "+i);
		}
		for(int i = 0; i < maxRowIndex; i++)
		{
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setPercentHeight(maxRowIndex >= 4 ? 20 : maxRowIndex == 3 ? 40 : maxRowIndex == 2 ? 60 : 80);
			getRowConstraints().add(rowConstraints);
			System.out.println("Adding RowConstraint: "+i);
		}

		items.forEach(gridItem -> add(gridItem, gridItem.getGridColumnIndex(), gridItem.getGridRowIndex(), gridItem.getGridColumnSpan(), getGridRowSpan()));
	}
}
