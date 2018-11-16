package controller;
/**
 * The main class, creates the gui object and the level object
 * @author Gordon MacDonald and Simon Krol
 * @version Oct 29, 2018
 */
import java.io.IOException;
import java.util.Scanner;

import model.Level;
import view.View;

public class Main
{
	/**
	 * Start the game
	 * @throws IOException If reading from the file fails
	 */
	private static void startGame() throws IOException
	{
		Level lvl = new Level(8, 6, 125, "Assets/Levels/Level.txt");
		View gui = new View(lvl);

	}

	/**
	 * Main method, accept user input and start the game
	 * @param args Main arguments
	 * @throws IOException If reading from data file fails
	 */
	public static void main(String[] args) throws IOException
	{
		Scanner input = new Scanner(System.in);
		String s;
		boolean idle = true;
		while(idle)
		{
			System.out.println("Enter (n)ew game or (q)uit:");
			s = input.nextLine();
			if(s.equals("n")) 
			{
				idle = false;
				startGame();
			}
			else if(s.equals("q"))System.exit(0);
		}
		input.close();
	}

}
