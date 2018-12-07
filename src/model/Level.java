package model;


/**
 * The level class, contains all info about the current game being played
 * @author Boyan Siromahov, Simon Krol, Gordon MacDonald
 * @version Nov 24, 2018
 */

import java.util.LinkedList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class Level
{
	private Lane[] grid;
	private String name;
	private int balance;
	private int width;
	private int height;
	private int numTurns;
	private int passiveGeneration;
	private String[] availablePlants;
	private JsonObject zombieSpawns;
	private int turn;
	LinkedList<Plant> doneList;
	LinkedList<Plant> undoneList;

	/**
	 * Construct a level
	 *
	 * @param name 	   The level's name
	 * @param width    The width in spots, of the level
	 * @param height   The height in spots, of the level
	 * @param balance  The player's starting sun balance
	 * @param plants   The plant's the player may use during this level
	 * @param turns	   The zombies that are set to spawn
	 * @param numTurns The number of turns until all zombies have spawned
	 */
	public Level(String name, int width, int height, int balance, String[] plants, JsonObject turns, int numTurns, int passiveGen)
	{
		this.name = name;
		this.width = width;
		this.height = height;
		this.balance = balance;
		this.availablePlants = plants;
		this.zombieSpawns = turns;
		this.numTurns = numTurns;
		this.passiveGeneration = passiveGen;

		grid = new Lane[height];
		for (int i = 0; i < height; i++)
		{
			grid[i] = new Lane(width, 2);
		}

		doneList = new LinkedList<Plant>();
		undoneList = new LinkedList<Plant>();
	}

	/**
	 * Check the levelData and spawn any zombies intended for the given turn
	 */
	public void spawnZombies()
	{

		JsonElement curTurn = zombieSpawns.get(Integer.toString(turn));
		if (curTurn == null)
			return;
		for (int lane = 0; lane < height; lane++)
		{
			JsonArray curLane = curTurn.getAsJsonObject().getAsJsonArray(Integer.toString(lane));
			if (curLane == null)
				continue;
			for (int i = 0; i < curLane.size(); i++)
			{
				try
				{
					Class<?> cls = Class.forName("model." + curLane.get(i).getAsString());
					Zombie newZombie = (Zombie) cls.newInstance();
					if (newZombie instanceof Zombie)
					{
						grid[lane].addZombie(newZombie);
					}
				} catch (Exception e)
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
	public Lane getLane(int laneIndex)
	{
		return grid[laneIndex];
	}

	/**
	 * Return all the lanes
	 * @return The level's lanes
	 */
	public Lane[] getLanes()
	{
		return grid;
	}

	/**
	 * Get the current turn
	 * @return The current turn
	 */
	public int getTurn()
	{
		return turn;
	}

	/**
	 * Set the current turn
	 * @param newTurn The new turn
	 */
	public void setTurn(int newTurn)
	{
		turn = newTurn;
	}

	/**
	 * Try to place a plant at the given indices.
	 *
	 * @param plant The plant being placed
	 * @param laneI The lane index of the plants location
	 * @param spotI The spot index of the plants location
	 * @return True if successful, false otherwise
	 */
	public boolean placePlant(Plant plant, int laneI, int spotI)
	{
		if (laneI < 0 || laneI > getWidth() - 1 || spotI < 0 || spotI > getHeight() - 1)
		{
			return false;
		}
		if (plant.getValue() > balance) // check if player has enough to purchase the plant
		{
			return false;
		}
		if (getLane(laneI).placePlant(plant, spotI))
		{
			this.addToBalance(-plant.getValue());
			undoneList.clear();
			this.doneList.add(plant);// update done action list
			return true;
		}
		return false;
	}

	/**
	 * Undo last plant placement.
	 */
	public void undo()
	{
		if (doneList.size() >= 1)//make sure there is something to undo
		{
			Plant p = doneList.removeLast(); //get last move done
			p.getLocation().killPlant(); //reverse last move done
			balance += p.getValue();
			undoneList.push(p); //add to redo list 
		}
	}

	/**
	 * Redo last undone plant placement.
	 */
	public void redo()
	{
		if (undoneList.size() >= 1) //make sure there is something to redo
		{
			Plant p = undoneList.pop(); //get last undo done
			p.getLocation().addPlant(p); //redo the last undo
			balance -= p.getValue();
			doneList.add(p);//add to actions done
		}
	}

	/**
	 * Clears the done and undone structures.
	 * 
	 */
	public void wipeTurnHist()
	{
		doneList.clear();
		undoneList.clear();
	}

	/**
	 * Add sun to the player's balance
	 *
	 * @param toAdd The amount fo be added
	 */
	protected void addToBalance(int toAdd)
	{
		balance += toAdd;
	}

	/**
	 * Return the width in spots, of the level
	 *
	 * @return Width of the level
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * Return the height in spots, of the level
	 *
	 * @return Height of the level
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * Iterate through all lanes in the level and run all their turns, also spawn
	 * any new zombies
	 */
	public void allTurn()
	{
		
		for (Lane lane : grid)
		{
			lane.allTurn(this);
		}
		turn++;
		balance += passiveGeneration;
		try
		{
			spawnZombies();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Return the user's current balance
	 *
	 * @return Users balance
	 */
	public int getBalance()
	{
		return balance;
	}

	/**
	 * Check if the level has been failed. The level has been failed if any lane has
	 * failed.
	 *
	 * @return true if failed, false otherwise
	 */
	public boolean checkFail()
	{
		for (int i = 0; i < grid.length; i++)
		{
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
	public boolean checkWin()
	{
		if (turn < numTurns)
			return false;
		for (int i = 0; i < grid.length; i++)
		{
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
	public int getNumZombies()
	{
		int sum = 0;
		for (int i = 0; i < grid.length; i++)
		{
			sum += grid[i].getNumZombies();
		}
		return sum;
	}

	/**
	 * Return all the plants available to the player in this level
	 * @return A list of available plants
	 */
	public String[] getAvailablePlants()
	{
		return availablePlants;
	}

	/**
	 * Get the levels name
	 * @return The level's name
	 */
	public String getName()
	{
		return name;
	}
	
	public void setReferences()
	{
		for(Lane lane: grid)
		{
			lane.setReferences();
		}
	}
	
	public boolean equals(Object toCheck)
	{
		if(!(toCheck instanceof Level))return false;
		Level toCompare = (Level)toCheck;
		if(balance != toCompare.balance)return false;
		if(width != toCompare.width)return false;
		if(height != toCompare.height)return false;
		if(numTurns != toCompare.numTurns)return false;
		if(passiveGeneration != toCompare.passiveGeneration)return false;
		if(turn != toCompare.turn)return false;
		if(grid.length != toCompare.grid.length)return false;
		for(int i=0; i<grid.length; i++)
		{
			if(!grid[i].equals(toCompare.grid[i]))return false;
		}
		//if(zombieSpawns != toCompare.zombieSpawns)return false;
		if(doneList.size() != toCompare.doneList.size())return false;
		for(int i=0; i<doneList.size(); i++)
		{
			if(!doneList.get(i).equals(toCompare.doneList.get(i)))return false;
		}
		if(undoneList.size() != toCompare.undoneList.size())return false;
		for(int i=0; i<undoneList.size(); i++)
		{
			if(!undoneList.get(i).equals(toCompare.undoneList.get(i)))return false;
		}
		//if(!availablePlants.equals(toCompare.availablePlants))return false;
		return true;
	}
	
}
