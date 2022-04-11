package RunGame;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import Utils.ColorsMatch;
import board.Territory;
import main.Player;
import main.UI;

public class PlayTurn implements ActionListener {
	public UI ui;

	public PlayTurn(UI ui) {
		this.ui = ui;
		CreateRuningGameMap cGameMap = new CreateRuningGameMap(ui);
	}

	public void runTurn(Player player) {
		ui.lowerLabel.setText("player1 turn");
		int currentPlayerNum = 1;
		ui.mainPanel.addMouseListener(new MouseAdapter() {

			int selected = 0;
			boolean currently_deviding = true;
			Player currentPlayer;

			@Override

			public void mousePressed(MouseEvent e) {

					int x = e.getX();
					int y = e.getY();
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

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
