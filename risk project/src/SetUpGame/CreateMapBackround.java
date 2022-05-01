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
		panel = new JPanel();
		label = new JLabel();
		lowerLabel = new JLabel();
		GenerateFrameService.setGameWindow(panel, gameWindow, 170, 0, gameWindow.getWidth() - 370,
				gameWindow.getHeight());
		randomButton = GenerateFrameService.createButton("random.jpg", panel.getWidth() - 150, panel.getHeight() - 100,
				150, 50, "devide randomly", panel, ui.sCountries);
		finishTurnButton = GenerateFrameService.createButton("finishturn.png", panel.getWidth() - 150,
				panel.getHeight() - 100, 150, 50, "finish turn", panel, ui.pTurn);

		GenerateFrameService.createBackround(label, "mainmapnew.png", panel, 0, 0, gameWindow.getWidth() - 370,
				gameWindow.getHeight() - 100);
		GenerateFrameService.createLabel(lowerLabel, panel, 0, gameWindow.getHeight() - 100, gameWindow.getWidth(),
				100);

		panel.setBackground(Color.WHITE);

	}

	public void createSideLabels() {
		DefaultListModel<Territory> listModel1 = new DefaultListModel<>();
		rightlist = new SideList(listModel1);
		setList(rightlist, 0, 0, 140, gameWindow.getHeight());
		DefaultListModel<Territory> listModel2 = new DefaultListModel<>();
		leftList = new SideList(listModel2);
		setList(leftList, 0, 0, 140, gameWindow.getHeight());
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		rightlist.setBorder(border);
		leftList.setBorder(border);
		leftList.setVisibleRowCount(5);
		rightlist.setVisibleRowCount(5);
		ui.getPlayer(1).setTerritoryList(leftList);
		ui.getPlayer(2).setTerritoryList(rightlist);
		leftSP = new JScrollPane(leftList);
		setScrollPanel(leftSP, gameWindow, 5, 5, leftList.getWidth(), leftList.getHeight(), leftList);
		rightSP = new JScrollPane(rightlist);
		setScrollPanel(rightSP, gameWindow, gameWindow.getWidth() - 180, 5, rightlist.getWidth(), rightlist.getHeight(),
				rightlist);
	}

	public void setScrollPanel(JScrollPane panel, JFrame gameWindow, int start_x, int start_y, int x, int y,
			SideList list) {
		panel.setBounds(start_x, start_y, x, y);
		gameWindow.getContentPane().add(panel);
	}

	public void setList(SideList list, int start_x, int start_y, int x, int y) {
		list.setBounds(0, 0, 150, gameWindow.getHeight() - 110);
		list.setDragEnabled(false);

	}

}
