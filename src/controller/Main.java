package controller;

/**
 * The main class, creates the gui object and the level object
 * @author Gordon MacDonald and Simon Krol
 * @version Nov 16, 2018
 */
import java.io.IOException;

import javax.swing.JOptionPane;

import model.Level;
import view.View;

public class Main
{
	/**
	 * Start the game
	 * 
	 * @throws IOException If reading from the file fails
	 */
	private static void startGame() throws IOException
	{
		Level lvl = new Level(8, 6, 125, "res/levels/Level.txt");
		@SuppressWarnings("unused")
		View gui = new View(lvl);

	}

	/**
	 * Main method, accept user input and start the game
	 * 
	 * @param args Main arguments
	 * @throws IOException If reading from data file fails
	 */
	public static void main(String[] args) throws IOException
	{
		startGame();
		JOptionPane.showMessageDialog(null,
				"To place a plant: click the tile you wish to play on, then the type of plant", "Instructions",
				JOptionPane.PLAIN_MESSAGE);
	}
}
