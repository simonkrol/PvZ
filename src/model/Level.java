package model;

/**
 * The level class, contains all info about the current game being played
 * @author Boyan Siromahov, Simon Krol, Gordon MacDonald
 * @version Nov 24, 2018
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Level {
	public Lane[] grid;
	private String name;
	private int balance;
	private int width;
	private int height;
	private int numTurns;
	private String[] availablePlants;
	private JsonObject zombieSpawns;
	public int turn;
	LinkedList<Plant> doneList;
	LinkedList<Plant> undoneList;

	/**
	 * Construct a level
	 *
	 * @param width    The width in spots, of the level
	 * @param height   The height in spots, of the level
	 * @param balance  The player's starting sun balance
	 * @param fileName The file storing the level's zombie data
	 * @throws IOException If readline fails
	 */
	public Level(String name, int width, int height, int balance, String[] plants, JsonObject turns, int numTurns) {
		this.name = name;
		this.width = width;
		this.height = height;
		this.balance = balance;
		this.availablePlants = plants;
		this.zombieSpawns = turns;
		this.numTurns = numTurns;
		
		grid = new Lane[height];
		for (int i = 0; i < height; i++) {
			grid[i] = new Lane(width, 2);
		}

		doneList = new LinkedList<Plant>();
		undoneList = new LinkedList<Plant>();
	}

	/**
	 * Check the levelData and spawn any zombies intended for the given turn
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void spawnZombies() {
		
		JsonElement curTurn = zombieSpawns.get(Integer.toString(turn));
		if(curTurn == null)return;
		for (int lane = 0; lane< height; lane++)
		{
			JsonArray curLane = curTurn.getAsJsonObject().getAsJsonArray(Integer.toString(lane));
			if(curLane == null) continue;
			for(int i = 0; i < curLane.size(); i++)
			{
				try {
					Class<?> cls = Class.forName("model."+curLane.get(i).getAsString());
					Zombie newZombie = (Zombie) cls.newInstance();
					if(newZombie instanceof Zombie)
					{
						grid[lane].addZombie(newZombie);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				
			}
			
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
			// System.out.println("Index out of bounds");
			return false;
		}
		if (plant.getValue() > balance) // check if player has enough to purchase the plant
		{
			// System.out.println("Insufficient funds");
			return false;
		}
		if (getLane(laneI).placePlant(plant, spotI)) {
			this.addToBalance(-plant.getValue());
			undoneList.clear();
			this.doneList.add(plant);// update done action list
			return true;
		}
		return false;
	}

	/**
	 * Undo last plant placement.
	 *
	 * 
	 * @return True if successful, false otherwise
	 */
	public void undo() {
		if (doneList.size() >= 1) 
		{
			Plant p = doneList.removeLast();
			p.getLocation().killPlant();
			balance += p.getValue();
			undoneList.push(p);
		}
	}

	/**
	 * Redo last undone plant placement.
	 *
	 * 
	 * @return True if successful, false otherwise
	 */
	public void redo() {
		if (undoneList.size() >= 1) 
		{
			Plant p = undoneList.pop();
			p.getLocation().addPlant(p);
			balance -= p.getValue();
			doneList.add(p);
		}
	}

	/**
	 * Clears the done and undone structures.
	 * 
	 */
	public void wipeTurnHist() {
		doneList.clear();
		undoneList.clear();
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
	 * Iterate through all lanes in the level and run all their turns, also spawn
	 * any new zombies
	 *
	 * @throws IOException If readline fails
	 */
	public void allTurn() {
		for (Lane lane : grid) {
			lane.allTurn(this);
		}
		turn++;
		try {
		spawnZombies();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
	 * Check if the level has been failed. The level has been failed if any lane has
	 * failed.
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
		if (turn < numTurns)
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
