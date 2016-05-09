package common.helpers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import datas.models.TypePokemon;

public class CellHelper extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -2521113953392753820L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		final TypePokemon type = (TypePokemon) value;

		if (type.isLegendaire()) {
			setText("Legendaire");
			setBackground(Color.GREEN);
		} else {
			setText("Normale");
			setBackground(Color.YELLOW);
		}
		return this;
	}
}
