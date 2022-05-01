package Utils;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class MyCallRender extends DefaultListCellRenderer {

	public MyCallRender() {
		super();
	}

	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus, int pos) {
		SideList sd = (SideList) list;
		Color col = new Color(sd.get(pos).getRGB());
		Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		c.setBackground(col);
		c.setForeground(Color.red);
		return c;
	}

}
