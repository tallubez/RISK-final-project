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
	public static void createWindow(JFrame Window, int height, int width) {
		if (height == -1) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			height = (int) screenSize.getHeight() - 100;
			width = ((height - 100) * 1227) / 628;
			width += 300;
		}
		Window.setSize(width, height);
		Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Window.getContentPane().setBackground(Color.black);
		Window.setLayout(null);
		Window.setTitle("Risk by Tal");

	}

	public static void setGameWindow(JPanel panel, JFrame gameWindow, int start_x, int start_y, int x, int y) {
		panel.setBounds(start_x, start_y, x, y);
		panel.setBackground(null);
		panel.setLayout(null);
		gameWindow.add(panel);
	}

	public static void createBackround(JLabel label, String path, JPanel panel, int start_x, int start_y, int x,
			int y) {

		label.setBounds(start_x, start_y, x, y);
		ImageIcon bgIcon = new ImageIcon(GenerateFrameService.class.getClassLoader().getResource(path));
		Image img = bgIcon.getImage();
		img = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(img);
		label.setIcon(scaledIcon);
		panel.add(label);
	}

	public static void createLabel(JLabel label, JPanel panel, int startX, int startY, int x, int y) {
		label.setBounds(startX, startY, x, y);
		panel.add(label);
	}

	public static JButton createButton(String buttonName, int x, int y, int wigth, int hight, String command,
			JPanel panel, ActionListener actionListener) {
		JButton playButton = new JButton();
		playButton.setBounds(x, y, wigth, hight);
		playButton.setBackground(null);
		playButton.setContentAreaFilled(false);
		playButton.setFocusPainted(false);
		if (buttonName != null) {
			ImageIcon bgIcon = new ImageIcon(GenerateFrameService.class.getClassLoader().getResource(buttonName));
			Image img = bgIcon.getImage();
			img = img.getScaledInstance(playButton.getWidth(), playButton.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon scaledIcon = new ImageIcon(img);
			playButton.setIcon(scaledIcon);

		}
		playButton.addActionListener(actionListener);
		playButton.setActionCommand(command);
		panel.add(playButton);
		return playButton;

	}

}
