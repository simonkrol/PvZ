package controller;

/**
 * The menu controller, controls the menu.
 * @author Boyan Siromahov
 * @version Nov 25th, 2018
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.BasicZombie;
import model.BucketZombie;
import model.Chomper;
import model.ImpZombie;
import model.Level;
import model.PeaProjectile;
import model.Peashooter;
import model.Plant;
import model.Projectile;
import model.Sunflower;
import model.Torchwood;
import model.Wallnut;
import model.Zombie;
import view.RuntimeTypeAdapterFactory;
import view.View;

public class MenuController implements ActionListener
{
	JMenuItem menuButton;
	private Level level;
	private View view;
	
	private static String lastSave = "";
	private final static String saveLocation = "res/saves/";
	private static RuntimeTypeAdapterFactory<Plant> plantAdapter = 
            RuntimeTypeAdapterFactory
           .of(Plant.class)
           .registerSubtype(Sunflower.class)
           .registerSubtype(Peashooter.class)
           .registerSubtype(Chomper.class)
           .registerSubtype(Torchwood.class)
           .registerSubtype(Wallnut.class);
	private static RuntimeTypeAdapterFactory<Zombie> zombieAdapter = 
            RuntimeTypeAdapterFactory
           .of(Zombie.class)
           .registerSubtype(BasicZombie.class)
           .registerSubtype(ImpZombie.class)
           .registerSubtype(BucketZombie.class);
	private static RuntimeTypeAdapterFactory<Projectile> projectileAdapter =
			RuntimeTypeAdapterFactory
			.of(Projectile.class)
			.registerSubtype(PeaProjectile.class);
	private static Gson gson = new GsonBuilder().setPrettyPrinting()
			.registerTypeAdapterFactory(plantAdapter)
			.registerTypeAdapterFactory(zombieAdapter)
			.registerTypeAdapterFactory(projectileAdapter).create();

	public MenuController(Level lvl, View view)
	{
		level = lvl;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		menuButton = (JMenuItem) e.getSource();
		switch (menuButton.getActionCommand())
		{
			case "Quit":
				view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
				System.exit(0);
				break;

			case "Undo":
				level.undo();
				view.update();
				break;

			case "Redo":
				level.redo();
				view.update();
				break;
				
			case "Save":
				String s = (String)JOptionPane.showInputDialog(
	                    view,
	                    "Save as:",
	                    lastSave);
				saveGame(s);
				break;
		}

	}
	
	public void saveGame(String gameName)
	{
		lastSave = gameName;
		FileWriter fileW;
		String jLvl = gson.toJson(this);
		try
		{
			fileW = new FileWriter(saveLocation+gameName);
			fileW.write(jLvl.toString());
			fileW.close();
		} catch (IOException e)
		{
			System.out.println("error on update LL - 120");
		}
	}
}
