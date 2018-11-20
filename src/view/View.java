package view;

/**
 * The View Class, creates and displays the GUI to the user
 * @author Boyan Siromahov and Gordon MacDonald
 * @version Nov 16, 2018
 */
import java.awt.*;

import javax.swing.*;

import controller.*;
import model.*;

/**
 * The SWING based GUI being used to play Milestone 2
 * 
 * @author Boyan Siromahov
 * @version Nov 16, 2019
 */
public class View extends JFrame
{
	private static final long serialVersionUID = 1L;
	public GameCanvas canvas;
	private Level level;
	private int blockWidth, blockHeight;
	JLabel info;
	JPanel information;

	/**
	 * Create a new view for a given level
	 * @param lvl The level the view is created for
	 */
	public View(Level lvl)
	{
		level = lvl;
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		calcBlockSize();
		canvas = new GameCanvas(level, blockWidth, blockHeight);

		setLayout(new BorderLayout());
		setTitle("Plants Vs Zombies");
		add("Center", canvas);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		plants.setSize(level.getWidth() * blockWidth, (int) (Math.floor(1.5 * blockHeight)));

		JButton sunflowerBtn = new JButton("Sunflower");
		sunflowerBtn.setSize(blockWidth, blockHeight);
		sunflowerBtn.setIcon(getScaledImage(new ImageIcon("Assets/Pictures/rsz_unknown.png"), blockWidth, blockHeight));
		plants.add(sunflowerBtn);

		JButton peashooterBtn = new JButton("Peashooter");
		peashooterBtn.setSize(blockWidth, blockHeight);
		peashooterBtn.setIcon(getScaledImage(new ImageIcon("Assets/Pictures/peaShooter.png"), blockWidth, blockHeight));
		plants.add(peashooterBtn);

		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());

		JButton end = new JButton("End Turn");
		end.setSize((int) Math.floor(blockWidth / 2.5), (int) Math.floor(blockHeight / 1.25));
		buttons.add(end);

		JButton quit = new JButton("Quit Game");
		quit.setSize((int) Math.floor(blockWidth / 2.5), (int) Math.floor(blockHeight / 1.25));
		buttons.add(quit);

		selections.add(buttons);
		selections.add(plants);
		add(selections, BorderLayout.PAGE_END);

		revalidate();

		// action listeners
		canvas.addMouseListener(new Controller(level, this));
		end.addActionListener(new Controller(level, this));
		sunflowerBtn.addActionListener(new Controller(level, this));
		peashooterBtn.addActionListener(new Controller(level, this));
		quit.addActionListener(new Controller(level, this));

		setVisible(true);
		this.setResizable(false);
	}

	/**
	 * Repaint the canvas with the new model information
	 * Reset the text showing the players balance and the turn number
	 */
	public void update()
	{
		canvas.repaint();
		info.setText("SUN: " + level.getBalance() + "  Turn: " + level.turn);
	}

	/**
	 * Calculate the size of each spot on the grid, setting the 
	 * blockWidth and blockHeight values
	 */
	public void calcBlockSize()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// Get the width of each spot on the grid
		blockWidth = screenSize.width / level.getWidth();
		// Get the height of each spot on the grid, allowing 3.5 additional spots for
		// the menu
		blockHeight = (int) (screenSize.height / (level.getHeight() + 3.5));
	}

	/**
	 * Get a scale ImageIcon of an ImageIcon with the given size values
	 * @param srcImg The src ImageIcon
	 * @param w The new Width
	 * @param h The new Height
	 * @return Scaled ImageIcon
	 */
	private ImageIcon getScaledImage(ImageIcon srcImg, int w, int h)
	{
		Image image = srcImg.getImage(); // transform it
		Image newimg = image.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		return new ImageIcon(newimg); // transform it back
	}

	/**
	 * Get the blockWidth of the current GUI
	 * @return width of each spot
	 */
	public int getBlockWidth()
	{
		return blockWidth;
	}

	/**
	 * Get the blockHeight of the current GUI
	 * @return height of each spot
	 */
	public int getBlockHeight()
	{
		return blockHeight;
	}
}
