package Utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GenerateFrameService {
	public void createGameWindow(JFrame gameWindow) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth() - 100;
		int height = (int) screenSize.getHeight() - 100;
		gameWindow.setSize(width, height);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.getContentPane().setBackground(Color.black);
		gameWindow.setLayout(null);
		gameWindow.setTitle("Risk by Tal");

	}

	public void setGameWindow(JPanel panel, JFrame gameWindow, int x, int y) {
		panel.setBounds(0, 0, x, y);
		panel.setBackground(null);
		panel.setLayout(null);
		gameWindow.add(panel);
	}

	public void createBackround(JLabel label, String path, JPanel panel, int x, int y) {

		label.setBounds(0, 0, x, y);
		ImageIcon bgIcon = new ImageIcon(getClass().getClassLoader().getResource(path));
		Image img = bgIcon.getImage();
		img = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(img);
		label.setIcon(scaledIcon);
		panel.add(label);
	}

	public void createLowerLabel(JLabel label, JPanel panel, int startX, int startY, int x, int y) {
		label.setBounds(startX, startY, x, y);
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
