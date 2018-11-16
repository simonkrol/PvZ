package controller;
import model.*;
/**
 * The main class, creates the gui object and the level object and accepts user input (Pretty much the controller)
 * @author Gordon MacDonald and Simon Krol
 * @version Oct 29, 2018
 */
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.IOException;
import java.util.Scanner;

import model.Level;
import model.Peashooter;
import model.Sunflower;
import view.View;

public class Main
{
	private boolean inProgress;
	private Level lvl;
	private Scanner scan;
	private View gui;

	/**
	 * Creates a new main object
	 * @throws IOException If reading from datafile fails
	 */
	private Main() throws IOException
	{
		scan = new Scanner(System.in);

		while (inProgress == false)
		{
			System.out.println("Enter (n)ew game or (q)uit:");
			String s = scan.next();
			if (s.equals("n"))
			{
				this.startGame();
			}
		}
	}

	/**
	 * Start the game
	 * @throws IOException If reading from the file fails
	 */
	private void startGame() throws IOException
	{
		this.inProgress = true;
		lvl = new Level(8, 6, 125, "Assets/Levels/Level.txt");
		gui = new View(lvl);
		//gui.paint();

		System.out.println("Game Started. Prepare defenses. Balance: " + lvl.getBalance());

		boolean editing = true;

		while (editing)
		{
			System.out.println("Commands: (p)lace plant, (e)nd turn, (n)ew game or (q)uit");
			String c = scan.next();
			switch (c)
			{
				case "p": //Place plants
					System.out.println("Enter lane number: ");
					int laneNum = scan.nextInt() - 1;
					System.out.println("Enter spot position: ");
					int spot = scan.nextInt() - 1;

					System.out.println("Enter Type of Plant, (s)unflower[" + Sunflower.DEFAULT_VALUE
							+ "] or (p)eashooter[" + Peashooter.DEFAULT_VALUE + "]: ");
					String type = scan.next();
					switch (type)
					{
						case "s":
							lvl.placePlant(new Sunflower(), laneNum, spot);
							gui.update();
							System.out.println("Current Balance:" + lvl.getBalance());
							break;
						case "p":
							lvl.placePlant(new Peashooter(), laneNum, spot);
							gui.update();
							break;
						default:
							System.out.println("Invalid Plant Type.");
					}

					break;
				case "e": //End turn
					lvl.allTurn();
					lvl.spawnZombies();
					if (lvl.checkWin())
					{
						gui.update();
						System.out.println("You Killed all the zombies! \n Congratulations you won!");
						return;
					}
					if (lvl.checkFail())
					{
						gui.update();
						System.out.println("Zombies have gotten past! \nGame over! ");
						return;
					} else
					{
						gui.update();
					}
					break;
				case "n": //New game
					System.out.println("Not implimented in this milestone");
					break;

				case "q": //Quit game 
					return;
			}
		}
	}

	/**
	 * Main method, creates a new main object and allows the user to play
	 * @param args Main arguments
	 * @throws IOException If reading from data file fails
	 */
	public static void main(String[] args) throws IOException
	{
		new Main();
	}

}
