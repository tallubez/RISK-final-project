package Utils;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import board.Territory;

public class ColorRenderer extends JLabel implements ListCellRenderer {

	public ColorRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		Territory t = (Territory) value;
		Color c = new Color(t.getRGB());
		this.setText(t.toString());
		this.setForeground(c.darker());
		this.setBackground(Color.white);
		return this;
	}

}
