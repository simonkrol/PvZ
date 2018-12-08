package controller;

/**
 * The Controller Class
 * @author Boyan Siromahov, Gordon MacDonald, Simon Krol and Shaun Gordon
 * @version Dec 7, 2018
 */
import model.*;
import view.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Controller implements ActionListener, MouseListener
{
	protected Level level;
	protected View view;
	private JButton button;
	private int x, y;
	private int posY;
	private int posX;

	// The save name used last time
	protected static String lastSave = "";
	// Where files are saved
	protected final static String saveLocation = "res/saves/";

	// Allow for deserialization of abstract objects like Plant, Zombie and
	// Projectile
	private static RuntimeTypeAdapterFactory<Plant> plantAdapter = RuntimeTypeAdapterFactory.of(Plant.class)
			.registerSubtype(Sunflower.class).registerSubtype(Peashooter.class).registerSubtype(Chomper.class)
			.registerSubtype(Torchwood.class).registerSubtype(Wallnut.class);
	private static RuntimeTypeAdapterFactory<Zombie> zombieAdapter = RuntimeTypeAdapterFactory.of(Zombie.class)
			.registerSubtype(BasicZombie.class).registerSubtype(ImpZombie.class).registerSubtype(BucketZombie.class);
	private static RuntimeTypeAdapterFactory<Projectile> projectileAdapter = RuntimeTypeAdapterFactory
			.of(Projectile.class).registerSubtype(PeaProjectile.class);
	protected static Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(plantAdapter)
			.registerTypeAdapterFactory(zombieAdapter).registerTypeAdapterFactory(projectileAdapter).create();

	/**
	 * Create a controller for the current level
	 * @param lvl The current level
	 * @param view The GUI associated with the level
	 */
	public Controller(Level lvl, View view)
	{
		level = lvl;
		this.view = view;
	}

	@Override
	/**
	 * The Action Performed method, deals with user inputs
	 */
	public void actionPerformed(ActionEvent e)
	{
		button = (JButton) e.getSource();
		if (button.getActionCommand().equals("End"))
		{
			level.allTurn();
			level.wipeTurnHist(); // clear the previous recorded actions at the end of each turn
			if (level.checkWin())
			{
				view.update();
				JOptionPane.showMessageDialog(null, "You killed them all!", "WIN", JOptionPane.PLAIN_MESSAGE);
				button.setEnabled(false);
				return;
			}
			if (level.checkFail())
			{
				view.update();
				JOptionPane.showMessageDialog(null, "Zombies have gotten past!  Game over", "LOSS",
						JOptionPane.PLAIN_MESSAGE);
				button.setEnabled(false);
				return;
			} else
			{
				view.update();
			}
		} else if (button.getActionCommand().equals("Quit"))
		{
			view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
			System.exit(0);
		} else if (button.getActionCommand().startsWith("Plants"))
		{
			if (view.getCanvas().getHighlight())
			{
				String plantType = button.getActionCommand().split("/")[1];
				try
				{
					Class<?> cls = Class.forName("model." + plantType);
					Plant newPlant = (Plant) cls.newInstance();
					level.placePlant(newPlant, view.getCanvas().getHLY(), view.getCanvas().getHLX());
					view.getCanvas().setHighlight(false);
					view.update();
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		}

		view.getCanvas().setHighlight(false);
	}

	@Override
	/**
	 * Listens for mouseclicks
	 */
	public void mouseClicked(MouseEvent arg0)
	{
		Point m = MouseInfo.getPointerInfo().getLocation();
		Point s = view.getLocationOnScreen();
		x = m.x - s.x;
		y = m.y - s.y - 50;
		posY = (int) Math.floor(y / view.getBlockHeight());
		posX = (int) Math.floor(x / view.getBlockWidth());
		view.getCanvas().highLight(posX, posY);
		view.update();
	}

	@Override
	/**
	 * Overridden event method, not being used
	 */
	public void mouseEntered(MouseEvent arg0)
	{
	}

	@Override
	/**
	 * Overridden event method, not being used
	 */
	public void mouseExited(MouseEvent arg0)
	{
	}

	@Override
	/**
	 * Overridden event method, not being used
	 */
	public void mousePressed(MouseEvent arg0)
	{
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{

	}

	/**
	 * Save the game to our saves directory with the given name
	 * @param gameName The name the level should be saved to
	 */
	protected void saveGame(String gameName)
	{
		lastSave = gameName;
		FileWriter fileW;
		String jLvl = gson.toJson(level);
		try
		{
			fileW = new FileWriter(saveLocation + gameName + ".json");
			fileW.write(jLvl.toString());
			fileW.close();
		} catch (IOException e)
		{
			System.out.println("error on update LL - 120");
		}
	}

	/**
	 * Load the game from our saves directory with the given name
	 * @param gameName The name of the file to load
	 */
	protected void loadGame(String gameName)
	{
		if (gameName == null)
			return;
		FileReader fileR;
		try
		{
			fileR = new FileReader(saveLocation + gameName);

			String json = "{";
			int i = fileR.read();
			while ((i = fileR.read()) != -1)
			{
				json += (char) i;
			}
			fileR.close();
			level = gson.fromJson(json, Level.class);
			if (view instanceof View)
				view.dispose();
			view = new View(level);

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Open a file searcher and return the name of the selected file
	 * @return The selected file's name
	 */
	protected String getName(JFrame showframe)
	{
		FileDialog fd = new FileDialog(showframe, "Choose a file", FileDialog.LOAD);
		fd.setDirectory("/saves");
		fd.setFile("*.json");
		fd.setVisible(true);
		return fd.getFile();
	}
}
