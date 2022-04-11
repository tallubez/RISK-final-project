package SetUpGame;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import Utils.GenerateFrameService;
import Utils.SideList;
import board.Territory;
import main.UI;

public class CreateMapBackround {
	public JFrame gameWindow;
	public JPanel panel;
	public JLabel label;
	public JLabel lowerLabel;
	public SideList leftList;
	public JScrollPane leftSP;
	public SideList rightlist;
	public JScrollPane rightSP;
	public JButton randomButton;
	public JButton finishTurnButton;
	private UI ui;

	public CreateMapBackround(UI ui) {
		this.ui = ui;
		this.gameWindow = ui.gameWindow;
		createMapBackround();
		createSideLabels();
	}

	public void createMapBackround() {
		ui.startMenu.panel.setVisible(false);
		GenerateFrameService generateFrameService = new GenerateFrameService();
		panel = new JPanel();
		label = new JLabel();
		lowerLabel = new JLabel();
		generateFrameService.setGameWindow(panel, gameWindow, 150, 0, gameWindow.getWidth() - 300,
				gameWindow.getHeight());
		randomButton = generateFrameService.createButton("random.jpg", panel.getWidth() - 150, panel.getHeight() - 100,
				150, 50, "devide randomly", panel, ui.sCountries);
		finishTurnButton = generateFrameService.createButton("finishturn.png", panel.getWidth() - 150,
				panel.getHeight() - 100, 150, 50, "finish turn", panel, ui.pTurn);

		generateFrameService.createBackround(label, "mainmapnew.png", panel, 0, 0, gameWindow.getWidth() - 300,
				gameWindow.getHeight() - 100);
		generateFrameService.createLabel(lowerLabel, panel, 0, gameWindow.getHeight() - 100, gameWindow.getWidth(),
				100);

		panel.setBackground(Color.WHITE);

	}

	public void createSideLabels() {
		DefaultListModel<Territory> listModel1 = new DefaultListModel<>();
		rightlist = new SideList(listModel1);
		setList(rightlist, 0, 0, 150, gameWindow.getHeight() + 1000);
		DefaultListModel<Territory> listModel2 = new DefaultListModel<>();
		leftList = new SideList(listModel2);
		setList(leftList, 0, 0, 150, gameWindow.getHeight() + 1000);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		rightlist.setBorder(border);
		leftList.setBorder(border);
		ui.getPlayer(1).setTerritoryList(leftList);
		ui.getPlayer(2).setTerritoryList(rightlist);
		leftSP = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setScrollPanel(leftSP, gameWindow, 0, 0, leftList.getWidth(), leftList.getHeight(), leftList);
		rightSP = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setScrollPanel(rightSP, gameWindow, gameWindow.getWidth() - 150, 0, rightlist.getWidth(), rightlist.getHeight(),
				rightlist);
	}

	public void setScrollPanel(JScrollPane panel, JFrame gameWindow, int start_x, int start_y, int x, int y,
			SideList list) {
		panel.setBounds(start_x, start_y, x, y);
		panel.setBackground(null);
		panel.setLayout(null);
		panel.add(list);
		gameWindow.getContentPane().add(panel);
	}

	public void setList(SideList list, int start_x, int start_y, int x, int y) {
		list.setBounds(0, 0, 150, gameWindow.getHeight() + 1000);
		list.setDragEnabled(false);

	}

}
