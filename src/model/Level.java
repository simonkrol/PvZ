package model;

/**
 * The level class, contains all info about the current game being played
 * @author Boyan Siromahov and Simon Krol
 * @version Nov 16, 2018
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Level {
	public Lane[] grid;
	private Integer balance;
	private int width;
	private int height;
	public int turn;
	private BufferedReader levelData;
	private String curInstruction;


	/**
	 * Construct a level
	 *
	 * @param width    The width in spots, of the level
	 * @param height   The height in spots, of the level
	 * @param balance  The player's starting sun balance
	 * @param fileName The file storing the level's zombie data
	 * @throws IOException If readline fails
	 */
	public Level(int width, int height, int balance, String fileName) throws IOException {
		grid = new Lane[height];
		for (int i = 0; i < height; i++) {
			grid[i] = new Lane(width, 2);
		}
		this.balance = balance;
		this.width = width;
		this.height = height;
		levelData = new BufferedReader(new FileReader(fileName));
		curInstruction = levelData.readLine();
	}


	/**
	 * Check the levelData and spawn any zombies intended for the given turn
	 *
	 * @throws IOException If readline fails
	 */
	public void spawnZombies() throws IOException {
		if (curInstruction == null)
			return;
		if (curInstruction.split("-")[0].equals(Integer.toString(turn))) {
			String[] lanes = curInstruction.split("-");
			for (int i = 1; i < lanes.length; i++) {
				grid[Integer.parseInt(lanes[i]) - 1].spawnZombie();
			}
			curInstruction = levelData.readLine();
		}
	}

	/**
	 * Return a given lane
	 *
	 * @param laneIndex The index of the lane to be returned
	 * @return a given Lane
	 */
	public Lane getLane(int laneIndex) {
		return grid[laneIndex];
	}

	/**
	 * Try to place a plant at the given indices.
	 *
	 * @param plant The plant being placed
	 * @param laneI The lane index of the plants location
	 * @param spotI The spot index of the plants location
	 * @return True if successful, false otherwise
	 */
	public boolean placePlant(Plant plant, int laneI, int spotI) {
		if (laneI < 0 || laneI > getWidth() - 1 || spotI < 0 || spotI > getHeight() - 1) {
			//System.out.println("Index out of bounds");
			return false;
		}
		if (plant.getValue() > balance) // check if player has enough to purchase the plant
		{
			//System.out.println("Insufficient funds");
			return false;
		}
		if (getLane(laneI).placePlant(plant, spotI)) {
			this.addToBalance(-plant.getValue());
			return true;
		}
		return false;
	}

	/**
	 * Add sun to the player's balance
	 *
	 * @param toAdd The amount fo be added
	 */
	protected void addToBalance(int toAdd) {
		balance += toAdd;
	}

	/**
	 * Return the width in spots, of the level
	 *
	 * @return Width of the level
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Return the height in spots, of the level
	 *
	 * @return Height of the level
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Iterate through all lanes in the level and run all their turns, also spawn any new zombies
	 *
	 * @throws IOException If readline fails
	 */
	public void allTurn() throws IOException {
		for (Lane lane : grid) {
			lane.allTurn(this);
		}
		turn++;
		spawnZombies();
	}

	/**
	 * Return the user's current balance
	 *
	 * @return Users balance
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * Check if the level has been failed. The level has been failed if any lane has failed.
	 *
	 * @return true if failed, false otherwise
	 */
	public boolean checkFail() {
		for (int i = 0; i < grid.length; i++) {
			if (grid[i].checkFail())
				return true;
		}
		return false;
	}

	/**
	 * Check if the player has won
	 *
	 * @return true if won, false otherwise
	 */
	public boolean checkWin() {
		if (curInstruction != null)
			return false;
		for (int i = 0; i < grid.length; i++) {
			if (!grid[i].noZombies())
				return false;

		}
		return true;
	}

	/**
	 * Get the total number of zombies alive in the level
	 *
	 * @return Number of live zombies
	 */
	public int getNumZombies() {
		int sum = 0;
		for (int i = 0; i < grid.length; i++) {
			sum += grid[i].getNumZombies();
		}
		return sum;
	}
}
