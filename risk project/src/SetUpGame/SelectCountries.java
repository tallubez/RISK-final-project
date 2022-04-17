package SetUpGame;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Utils.ColorsMatch;
import Utils.SideList;
import board.Territory;
import board.WorldMap;
import main.Player;
import main.UI;

public class SelectCountries implements ActionListener {

	public JFrame gameWindow;
	public JPanel panel;
	public JLabel label;
	public JLabel lowerLabel;
	private UI ui;
	private WorldMap worldMap;
	public SideList leftList;
	public JScrollPane leftSP;
	public SideList rightList;
	public JScrollPane rightSP;
	public JButton finishTurnButton;
	public CreateMapBackround cMapBackround;
	private int x, y;
	private int currentPlayerNum;

	public SelectCountries(UI ui) {
		this.ui = ui;
		this.gameWindow = ui.gameWindow;
		this.worldMap = ui.worldMap;
	}

	public void devide() {
		InitBackround();
		devideTerritory();
	}

	public void InitBackround() {
		cMapBackround = new CreateMapBackround(ui);
		this.label = cMapBackround.label;
		this.panel = cMapBackround.panel;
		this.lowerLabel = cMapBackround.lowerLabel;
		this.leftList = cMapBackround.leftList;
		this.leftSP = cMapBackround.leftSP;
		this.rightList = cMapBackround.rightlist;
		this.rightSP = cMapBackround.rightSP;
		this.finishTurnButton = cMapBackround.finishTurnButton;
		ui.leftList = this.leftList;
		ui.rightList = this.rightList;

	}

	public void devideTerritory() {
		lowerLabel.setText("player1 turn");
		currentPlayerNum = 1;
		ui.mouseAdapter = new MouseAdapter() {

			int selected = 0;

			Player currentPlayer;

			@Override

			public void mousePressed(MouseEvent e) {

				x = e.getX();
				y = e.getY();
				String s;

				int rgb = ColorsMatch.getColorAt(panel, new Point(x, y));
				s = (new Color(rgb, true)).toString();
				if (worldMap.colorsMatch.ConatinsColor(new Color(rgb))
						&& worldMap.colorsMatch.getTerritory(new Color(rgb)) != null) {
					Territory temp = worldMap.colorsMatch.getTerritory(new Color(rgb));
					if (temp.getPlayer_controling() == 0) {

						int result = JOptionPane.showConfirmDialog(gameWindow,
								"Sure you want to select: " + temp.getName(), "validate the chosen country",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (result == JOptionPane.YES_OPTION) {
							selected++;
							temp.getContinent().TerrChosen(currentPlayerNum);
							temp.setPlayer_controling(currentPlayerNum);
							currentPlayer = ui.getPlayer(currentPlayerNum);
							if (currentPlayerNum == 1) {
								addToSidelist(leftList, temp, currentPlayer);
								currentPlayerNum++;
							} else {
								addToSidelist(rightList, temp, currentPlayer);
								currentPlayerNum--;
							}
							if (selected < 41) {
								lowerLabel.setText("player" + currentPlayerNum + " turn");
							} else {
								panel.removeMouseListener(this);
								cont();
							}
						}

					} else {
						lowerLabel.setText(
								"player" + currentPlayerNum + " turn. " + temp.getName() + " already selected");
					}
				} else {
					s = String.valueOf(rgb);
					lowerLabel.setText(s);
				}
			}

		};
		for (int i = 0; i < panel.getMouseListeners().length; i++) {
			panel.removeMouseListener(panel.getMouseListeners()[i]);
		}
		panel.addMouseListener(ui.mouseAdapter);
	}

	public void addToSidelist(SideList list, Territory temp, Player currPlayer) {
		temp.setIndex(currPlayer.getAmount_controling());
		currPlayer.addTerritory(temp);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Player currentPlayer;
		int counter = 0;
		int result = JOptionPane.showConfirmDialog(gameWindow,
				"Are you sure you want to devide the remainig territorys randomly?", "validate the chosen country",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {
			lowerLabel.setText("random!");
			for (Territory t : worldMap.getAllTerritories()) {
				counter++;
				if (t.getPlayer_controling() == 0) {
					t.setPlayer_controling(currentPlayerNum);
					currentPlayer = ui.getPlayer(currentPlayerNum);
					if (currentPlayerNum == 1) {
						addToSidelist(leftList, t, currentPlayer);
						currentPlayerNum++;
					} else {
						addToSidelist(rightList, t, currentPlayer);
						currentPlayerNum--;
					}
				}
			}
			lowerLabel.setText(String.valueOf(counter));
			cMapBackround.randomButton.setVisible(false);
			cont();

		}

	}

	public void cont() {
		panel.removeMouseListener(ui.mouseAdapter);
		lowerLabel.setText("selected all");
		ui.PositionStartUnits();
	}
}
