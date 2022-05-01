package Utils;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import board.Territory;

public class SideList extends JList<Territory> {

	public int leangth;
	MyCallRender renderer;

	public SideList(DefaultListModel<Territory> defaultListModel) {
		super(defaultListModel);
		leangth = 0;
		renderer = new MyCallRender();
		this.setCellRenderer(renderer);
	}

	public void add(Territory t) {
		DefaultListModel<Territory> listModel = (DefaultListModel<Territory>) this.getModel();
		listModel.addElement(t);
		this.setModel(listModel);
		Object value = null;
		t.setIndex(leangth);
		Component c = renderer.getListCellRendererComponent(this, value, leangth, false, false);
		c.setForeground(Color.red);
		c.setEnabled(this.isEnabled());
		c.setFont(this.getFont());
		leangth++;
	}

	public Territory get(int index) {
		DefaultListModel<Territory> listModel = (DefaultListModel<Territory>) this.getModel();
		return listModel.get(index);
	}

	@Override
	public void remove(int index) {
		DefaultListModel<Territory> listModel = (DefaultListModel<Territory>) this.getModel();
		for (int i = index + 1; i < leangth; i++) {
			listModel.get(i).index--;
		}
		listModel.remove(index);
		leangth--;
		this.setModel(listModel);
	}

	public void updateText(Territory t) {
		DefaultListModel<Territory> listModel = (DefaultListModel<Territory>) this.getModel();
		listModel.set(t.index, t);
		this.setModel(listModel);
	}
}
