package SetUpGame;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Utils.GenerateFrameService;
import board.WorldMap;
import main.UI;

public class SelectCountries extends JFrame {

	public JFrame gameWindow;
	public JPanel panel;
	public JLabel label;
	public JLabel lowerLabel;
	private UI ui;
	private WorldMap worldMap;
	private Robot robot;
	private int x, y;

	public SelectCountries(UI ui) {
		this.ui = ui;
		this.gameWindow = ui.gameWindow;
		this.worldMap = ui.worldMap;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createMapBackround();
	}

	public void createMapBackround() {
		ui.startMenu.panel.setVisible(false);
		GenerateFrameService generateFrameService = new GenerateFrameService();
		panel = new JPanel();
		label = new JLabel();
		lowerLabel = new JLabel();
		generateFrameService.setGameWindow(panel, gameWindow, gameWindow.getWidth(), gameWindow.getHeight());
		generateFrameService.createBackround(label, "Map.jpg", panel, gameWindow.getWidth(),
				gameWindow.getHeight() - 100);
		generateFrameService.createLowerLabel(lowerLabel, panel, 0, gameWindow.getHeight() - 100, gameWindow.getWidth(),
				100);

		panel.setBackground(Color.WHITE);
		lowerLabel.setText("hello world");
		devideTerritory();
	}

	public void devideTerritory() {
		int selected = 0;
		int playernum = 0;
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectTerritory(playernum);
				x = e.getX();
				y = e.getY();
				String s;

				int rgb = getColorAt(gameWindow, new Point(x, y));
				s = (new Color(rgb, true)).toString();
				// if (worldMap.colorsMatch.ConatinsRGB(rgb)) {
				// s = worldMap.colorsMatch.geTerritory(rgb).getName();
				// } else {
				// s = String.valueOf(rgb);
				// }
				lowerLabel.setText(s);

			}
		});

	}

	public static int getColorAt(JFrame frm, Point p) {
		Rectangle rect = frm.getContentPane().getBounds();
		BufferedImage img = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_ARGB);
		frm.getContentPane().paintAll(img.createGraphics());
		return img.getRGB(p.x, p.y);
	}

	public void selectTerritory(int playernum) {
		Color color;
		color = robot.getPixelColor(x, y);

	}

}
