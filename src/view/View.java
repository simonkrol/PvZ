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
	JPanel information, selections;

	/**
	 * Create a new view for a given level
	 * @param lvl The level the view is created for
	 */
	public View(Level lvl)
	{
		level = lvl;

		setLayout(new BorderLayout());



		setTitle("Plants Vs Zombies");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		information = new JPanel();
		information.setLayout(new FlowLayout());
		info = new JLabel();
		info.setText("SUN: " + level.getBalance() + "  Turn: " + level.turn);
		information.add(info);
		add(information, BorderLayout.PAGE_START);

		selections = new JPanel();
		selections.setLayout(new BoxLayout(selections, BoxLayout.PAGE_AXIS));

		JPanel plants = new JPanel();
		plants.setLayout(new FlowLayout());

		JButton sunflowerBtn = new JButton("Sunflower(50)");
		sunflowerBtn.setSize(125, 125);
		sunflowerBtn.setIcon(getScaledImage(Sunflower.sprite, 125, 125));
		//sunflowerBtn.setIcon(getScaledImage(new ImageIcon("res/assets/PvZ/Sunflower.png"), 125, 125));
		plants.add(sunflowerBtn);

		JButton peashooterBtn = new JButton("Peashooter(40)");
		peashooterBtn.setSize(125, 125);
		peashooterBtn.setIcon(getScaledImage(Peashooter.sprite, 125, 125));
		//peashooterBtn.setIcon(getScaledImage(new ImageIcon("res/assets/PvZ/Peashooter.png"),125, 125));
		plants.add(peashooterBtn);
		
		JButton wallnutBtn = new JButton("Wallnut(50)");
		wallnutBtn.setSize(125, 125);
		wallnutBtn.setIcon(getScaledImage(Wallnut.sprite, 125, 125));
		//peashooterBtn.setIcon(getScaledImage(new ImageIcon("res/assets/PvZ/Peashooter.png"),125, 125));
		plants.add(wallnutBtn);

		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());

		JButton end = new JButton("End Turn");
		end.setSize(50, 100);
		buttons.add(end);

		JButton quit = new JButton("Quit Game");
		quit.setSize(50, 100);
		buttons.add(quit);

		selections.add(buttons);
		selections.add(plants);
		add(selections, BorderLayout.PAGE_END);


		setExtendedState(JFrame.MAXIMIZED_BOTH);
		pack();
		setVisible(true);
		setResizable(false);
		calcBlockSize();

		canvas = new GameCanvas(level, blockWidth, blockHeight);
		add(canvas, BorderLayout.CENTER);


		revalidate();

		// action listeners
		canvas.addMouseListener(new Controller(level, this));
		end.addActionListener(new Controller(level, this));
		sunflowerBtn.addActionListener(new Controller(level, this));
		peashooterBtn.addActionListener(new Controller(level, this));
		wallnutBtn.addActionListener(new Controller(level, this));
		quit.addActionListener(new Controller(level, this));


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
		Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// Get the width of each spot on the grid
		blockWidth = (int)(screenSize.getWidth() / level.getWidth());
		// Get the height of each spot on the grid, not counting the Info and Plant menus or the taskbar
		// We subtract the info menu twice to also get rid of the top bar from this calculation
		blockHeight = (int)(screenSize.getHeight()- scnMax.bottom - scnMax.top - 2*information.getHeight() - selections.getHeight()) / level.getHeight();
	}

	/**
	 * Get a scale ImageIcon of an ImageIcon with the given size values
	 * @param srcImg The src ImageIcon
	 * @param w The new Width
	 * @param h The new Height
	 * @return Scaled ImageIcon
	 */
	private ImageIcon getScaledImage(Image srcImg, int w, int h)
	{
		Image newimg = srcImg.getScaledInstance(w, h, Image.SCALE_DEFAULT); // scale it the smooth way
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
