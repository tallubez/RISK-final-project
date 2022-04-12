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
	private MouseAdapter mAdapter;

	public PlayTurn(UI ui) {
		this.ui = ui;
		runTurn(1);

	}

	public void runTurn(int currentPlayerNum) {
		ui.lowerLabel.setText("player1 turn");
		mAdapter = new MouseAdapter() {
			Player currentPlayer = ui.getPlayer(currentPlayerNum);

			@Override

			public void mousePressed(MouseEvent e) {

				int x = e.getX();
				int y = e.getY();
				int rgb = ColorsMatch.getColorAt(ui.mainPanel, new Point(x, y));
				if (ui.worldMap.colorsMatch.ConatinsColor(new Color(rgb))
						&& ui.worldMap.colorsMatch.getTerritory(new Color(rgb)) != null) {
					Territory temp = ui.worldMap.colorsMatch.getTerritory(new Color(rgb));
					if (temp.getPlayer_controling() == currentPlayerNum) {
						int result = JOptionPane.showConfirmDialog(ui.gameWindow,
								"Sure you want to attack from: " + temp.getName(), "validate the chosen country",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (result == JOptionPane.YES_OPTION) {

						}

					} else {
						ui.lowerLabel.setText(
								"player" + currentPlayerNum + " turn. " + temp.getName() + "isn't controlled by you");
					}
				} else {
					String s = String.valueOf(rgb);
					ui.lowerLabel.setText(s);
				}
			}

		};
		ui.mainPanel.addMouseListener(mAdapter);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
