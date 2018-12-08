package controller;

/**
 * The main class, creates the gui object and the level object
 * @author Gordon MacDonald and Simon Krol
 * @version Dec 7, 2018
 */
import java.io.IOException;

import view.MainMenu;

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
		new MainMenu();
	}
}
