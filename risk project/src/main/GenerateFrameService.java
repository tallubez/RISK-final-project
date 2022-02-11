package main;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GenerateFrameService {
	public void setGameWindow(JPanel panel, JFrame gameWindow, int x, int y) {
		panel.setBounds(0, 0, x, y);
		panel.setBackground(null);
		panel.setLayout(null);
		gameWindow.add(panel);
	}

	public void createBackround(JLabel label, String path, JPanel panel, int x, int y) {
		label.setBounds(0, 0, x, y);
		ImageIcon bgIcon = new ImageIcon(getClass().getClassLoader().getResource(path));
		label.setIcon(bgIcon);
		panel.add(label);
	}

	public void createButton(String buttonName, int x, int y, int wigth, int hight, String command, JPanel panel,
			ActionListener actionListener) {
		ImageIcon play1v1Icon = new ImageIcon(getClass().getClassLoader().getResource(buttonName));
		JButton playButton = new JButton();
		playButton.setBounds(x, y, wigth, hight);
		playButton.setBackground(null);
		playButton.setContentAreaFilled(false);
		playButton.setFocusPainted(false);
		playButton.setIcon(play1v1Icon);
		playButton.addActionListener(actionListener);
		playButton.setActionCommand(command);
		panel.add(playButton);

	}

}
