package controller;

/**
 * The main class, creates the gui object and the level object
 * @author Gordon MacDonald and Simon Krol
 * @version Nov 16, 2018
 */
import java.io.IOException;

import javax.swing.JOptionPane;

import model.Level;
import view.MainMenu;
import view.View;

public class Main
{

	/**
	 * Main method, accept user input and start the game
	 * 
	 * @param args Main arguments
	 * @throws IOException If reading from data file fails
	 */
	public static void main(String[] args) throws IOException
	{
		LevelLoader levels = new LevelLoader();
		Level level = levels.getLevel("Level1.json");
		new MainMenu();
		//@SuppressWarnings("unused")
		//View levelGui = new View(level);
		//JOptionPane.showMessageDialog(null,
		//		"To place a plant: click the tile you wish to play on, then the type of plant\n. Undo/Redo can be found in the menu",
		//		"Instructions", JOptionPane.PLAIN_MESSAGE);
	}
}
