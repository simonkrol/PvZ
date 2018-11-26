package view;

/**
 * The View Class, creates and displays the GUI to the user
 * @author Boyan Siromahov and Gordon MacDonald
 * @version Nov 16, 2018
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

import controller.*;
import model.*;

/**
 * The SWING based GUI being used to play Milestone 2
 * 
 * @author Boyan Siromahov
 * @version Nov 16, 2019
 */
public class View extends JFrame {
	private static final long serialVersionUID = 1L;
	public GameCanvas canvas;
	private Level level;
	private int blockWidth, blockHeight;
	JLabel info;
	JPanel information, selections;
	private JButton sunflowerBtn;
	private JButton peashooterBtn;
	private JButton wallnutBtn;
	private JButton chomperBtn;
	private JButton torchwoodBtn;
	private JButton end;
	private JButton quit;

	/**
	 * Create a new view for a given level
	 * 
	 * @param lvl The level the view is created for
	 */
	public View(Level lvl) {
		level = lvl;

		setLayout(new BorderLayout());
		setTitle("Plants Vs Zombies");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		information = new JPanel();
		information.setLayout(new BorderLayout());
		info = new JLabel();
		info.setText("SUN: " + level.getBalance() + "  Turn: " + level.turn);
		info.setFont(new Font(info.getName(), Font.PLAIN, 20));
		information.add(info, BorderLayout.CENTER);
		add(information, BorderLayout.PAGE_START);
		
		// Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_M);
		menuBar.add(menu);
		information.add(menuBar, BorderLayout.PAGE_START);

		JMenuItem  quitItem = new JMenuItem("Quit", KeyEvent.VK_Q);
		quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		quitItem.setActionCommand("Quit");
		menu.add(quitItem);

		JMenuItem  undoItem = new JMenuItem("Undo", KeyEvent.VK_Z);
		undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		undoItem.setActionCommand("Undo");
		menu.add(undoItem);
		
		JMenuItem  redoItem = new JMenuItem("Redo", KeyEvent.VK_Y);
		redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		redoItem.setActionCommand("Redo");
		menu.add(redoItem);		
		
		selections = new JPanel();
		selections.setLayout(new BoxLayout(selections, BoxLayout.PAGE_AXIS));

		JPanel plants = new JPanel();
		plants.setLayout(new FlowLayout());

		sunflowerBtn = new JButton("Sunflower(" + Sunflower.DEFAULT_VALUE+")");
		sunflowerBtn.setSize(125, 125);
		sunflowerBtn.setIcon(getScaledImage(Sunflower.sprite, 125, 125));
		sunflowerBtn.setActionCommand("Plants/Sunflower");
		plants.add(sunflowerBtn);

		peashooterBtn = new JButton("Peashooter(" + Peashooter.DEFAULT_VALUE+")");
		peashooterBtn.setSize(125, 125);
		peashooterBtn.setIcon(getScaledImage(Peashooter.sprite, 125, 125));
		peashooterBtn.setActionCommand("Plants/Peashooter");
		plants.add(peashooterBtn);

		wallnutBtn = new JButton("Wallnut("+ Wallnut.DEFAULT_VALUE+")");
		wallnutBtn.setSize(125, 125);
		wallnutBtn.setIcon(getScaledImage(Wallnut.sprite, 125, 125));
		wallnutBtn.setActionCommand("Plants/Wallnut");
		plants.add(wallnutBtn);

		chomperBtn = new JButton("Chomper("+Chomper.DEFAULT_VALUE+")");
		chomperBtn.setSize(125, 125);
		chomperBtn.setIcon(getScaledImage(Chomper.sprite[0], 125, 125));
		chomperBtn.setActionCommand("Plants/Chomper");
		plants.add(chomperBtn);
		
		torchwoodBtn = new JButton("Torchwood("+Torchwood.DEFAULT_VALUE+")");
		torchwoodBtn.setSize(125, 125);
		torchwoodBtn.setIcon(getScaledImage(Torchwood.sprite, 125, 125));
		torchwoodBtn.setActionCommand("Plants/Torchwood");
		plants.add(torchwoodBtn);

		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());

		end = new JButton("End Turn");
		end.setActionCommand("End");
		end.setSize(50, 100);
		buttons.add(end);

		quit = new JButton("Quit Game");
		quit.setActionCommand("Quit");
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
		chomperBtn.addActionListener(new Controller(level, this));
		torchwoodBtn.addActionListener(new Controller(level, this));
		quit.addActionListener(new Controller(level, this));
		quitItem.addActionListener(new MenuController(level, this));
		undoItem.addActionListener(new MenuController(level, this));
		redoItem.addActionListener(new MenuController(level, this));
	}

	/**
	 * Repaint the canvas with the new model information Reset the text showing the
	 * players balance and the turn number
	 */
	public void update() {
		canvas.repaint();
		info.setText("SUN: " + level.getBalance() + "  Turn: " + level.turn);
	}

	/**
	 * Calculate the size of each spot on the grid, setting the blockWidth and
	 * blockHeight values
	 */
	public void calcBlockSize() {
		Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// Get the width of each spot on the grid
		blockWidth = (int) (screenSize.getWidth() / level.getWidth());
		// Get the height of each spot on the grid, not counting the Info and Plant
		// menus or the taskbar
		// We subtract the info menu twice to also get rid of the top bar from this
		// calculation
		blockHeight = (int) (screenSize.getHeight() - scnMax.bottom - scnMax.top - 2 * information.getHeight()
				- selections.getHeight()) / level.getHeight();
	}

	/**
	 * Get a scale ImageIcon of an ImageIcon with the given size values
	 * 
	 * @param srcImg The src ImageIcon
	 * @param w      The new Width
	 * @param h      The new Height
	 * @return Scaled ImageIcon
	 */
	private ImageIcon getScaledImage(Image srcImg, int w, int h) {
		Image newimg = srcImg.getScaledInstance(w, h, Image.SCALE_DEFAULT); // scale it the smooth way
		return new ImageIcon(newimg); // transform it back
	}

	/**
	 * Get the blockWidth of the current GUI
	 * 
	 * @return width of each spot
	 */
	public int getBlockWidth() {
		return blockWidth;
	}

	/**
	 * Get the blockHeight of the current GUI
	 * 
	 * @return height of each spot
	 */
	public int getBlockHeight() {
		return blockHeight;
	}
  
  
  public JButton getSunflowerBtn()
	{
		return sunflowerBtn;
	}
	public JButton getPeashooterBtn()
	{
		return peashooterBtn;
	}
	public JButton getWallnutBtn()
	{
		return wallnutBtn;
	}
	public JButton getChomperBtn()
	{
		return chomperBtn;
	}
  	public JButton getTorchwoodBtn()
	{
		return torchwoodBtn;
	}
	public JButton getEndBtn()
	{
		return end;
	}
	public JButton getQuitBtn()
	{
		return quit;
	}
}
