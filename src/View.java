import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

/**
 * The SWING based GUI being used to play Milestone 2
 * 
 * @author Boyan Siromahov
 * @version Nov 16, 2019
 */
public class View extends JFrame {
	protected GameCanvas canvas;
	private Level level;
	JLabel info;
	JPanel information;

	public View(Level lvl) {
		level = lvl;
		canvas = new GameCanvas(lvl);
		setLayout(new BorderLayout());
		setSize(lvl.getWidth() * 127, lvl.getHeight() * 125 + 300);
		setTitle("Plants Vs Zombies - Ghetto Edition");
		add("Center", canvas);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		information = new JPanel();
		information.setLayout(new FlowLayout());
		info = new JLabel();
		info.setText("SUN: " + level.getBalance() + "  Turn: " + level.turn);
		information.add(info);
		add(information, BorderLayout.PAGE_START);

		JPanel selections = new JPanel();
		selections.setLayout(new BoxLayout(selections, BoxLayout.PAGE_AXIS));

		JPanel plants = new JPanel();
		plants.setLayout(new FlowLayout());

		JButton sunflowerBtn = new JButton("Sunflower");
		sunflowerBtn.setSize(125, 125);
		sunflowerBtn.setIcon(new ImageIcon("Assets/Pictures/rsz_unknown.png"));
		plants.add(sunflowerBtn);

		JButton peashooterBtn = new JButton("Peashooter");
		peashooterBtn.setSize(125, 125);
		peashooterBtn.setIcon(new ImageIcon("Assets/Pictures/peaShooter.png"));
		plants.add(peashooterBtn);

		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());

		JButton add = new JButton("Add plant");
		add.setSize(50, 100);
		buttons.add(add);

		JButton end = new JButton("End Turn");
		end.setSize(50, 100);
		buttons.add(end);

		JButton quit = new JButton("Quit Game");
		quit.setSize(50, 100);
		buttons.add(quit);

		selections.add(buttons);
		selections.add(plants);
		add(selections, BorderLayout.PAGE_END);

		revalidate();

		// action listeners
		canvas.addMouseListener(new Controller(level, this));
		add.addActionListener(new Controller(level, this));
		end.addActionListener(new Controller(level, this));
		sunflowerBtn.addActionListener(new Controller(level, this));
		peashooterBtn.addActionListener(new Controller(level, this));
		quit.addActionListener(new Controller(level, this));
	}

	public void update() {
		canvas.repaint();
		info.setText("SUN: " + level.getBalance() + "  Turn: " + level.turn);
	}
}

class GameCanvas extends Canvas {
	private Level level;
	private Graphics g;
	Image grass = new ImageIcon("Assets/Pictures/grass.jpg").getImage();
	Image zombie = new ImageIcon("Assets/Pictures/zombie.png").getImage();
	Image sunflower = new ImageIcon("Assets/Pictures/rsz_unknown.png").getImage();
	Image peashooter = new ImageIcon("Assets/Pictures/peaShooter.png").getImage();
	Image grassHL = new ImageIcon("Assets/Pictures/HLgrass.jpg").getImage();
	protected boolean highlight = false;
	protected int hLX = 0, hLY = 0;

	public GameCanvas(Level lvl) {
		level = lvl;
	}

	@Override
	public void paint(Graphics g) {
		this.g = g;

		int x = 0, y = 0;
		for (Lane lane : level.grid) {
			for (Spot spot : lane.spots) // draw plants and spots
			{
				if (x == hLX*125 && y == hLY*125 && highlight) {
					g.drawImage(grassHL, x, y, this);
					highlight = false;
					
					
				} else {
					g.drawImage(grass, x, y, this);
				}

				if (spot.getPlant() instanceof Sunflower) {
					g.drawImage(sunflower, x, y, this);
				} else if (spot.getPlant() instanceof Peashooter) {
					g.drawImage(peashooter, x, y, this);
				}

				x += 125;
			}

			for (Zombie zmb : lane.liveZombies) {
				int pos = zmb.getPosition();
				if (level.getWidth() * 125 - zmb.getPosition() < 0) {
					pos = 0;
				} else {
					pos = level.getWidth() * 125 - zmb.getPosition();
				}
				g.drawImage(zombie, pos, y, this);
			}
			x = 0;
			y += 125;
		}

	}

	protected void highLight(int x, int y) {
		highlight = true;
		hLX = x;
		hLY = y;
	}

}
