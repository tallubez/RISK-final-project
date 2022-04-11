package SetUpGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
	public JList<String> leftList;
	public JScrollPane leftSP;
	public JList<String> rightList;
	public JScrollPane rightSP;
	CreateMapBackround cMapBackround;
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
		ui.leftList = this.leftList;
		ui.rightList = this.rightList;

	}

	public void devideTerritory() {
		lowerLabel.setText("player1 turn");
		currentPlayerNum = 1;
		panel.addMouseListener(new MouseAdapter() {

			int selected = 0;
			boolean currently_deviding = true;
			DefaultListModel<String> listModel;
			Player currentPlayer;

			@Override

			public void mousePressed(MouseEvent e) {

				if (currently_deviding) {
					x = e.getX();
					y = e.getY();
					String s;

					int rgb = getColorAt(panel, new Point(x, y));
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
								temp.setPlayer_controling(currentPlayerNum);
								currentPlayer = ui.getPlayer(currentPlayerNum);
								if (currentPlayerNum == 1) {
									addToJlist(leftList, temp, currentPlayer);
									currentPlayerNum++;
								} else {
									addToJlist(rightList, temp, currentPlayer);
									currentPlayerNum--;
								}
								if (selected < 41) {
									lowerLabel.setText("player" + currentPlayerNum + " turn");
								} else {
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

			}
		});

	}

	public void addToJlist(JList<String> list, Territory temp, Player currPlayer) {
		currPlayer.addTerritory(temp);
		DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();
		listModel.addElement(temp.toString());
		list.setModel(listModel);

	}

	public static int getColorAt(JPanel panel, Point p) {
		BufferedImage img = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();
		panel.paint(g);
		g.dispose();
		return img.getRGB(p.x, p.y);
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
						addToJlist(leftList, t, currentPlayer);
						currentPlayerNum++;
					} else {
						addToJlist(rightList, t, currentPlayer);
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
		lowerLabel.setText("selected all");
		ui.PositionStartUnits();
	}
}
