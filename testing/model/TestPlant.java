package model;
import static org.junit.Assert.*;

import org.junit.Test;

import controller.LevelLoader;

import org.junit.Before;

/**
 * Tests Plants class
 * @author Shaun Gordon
 * @version Nov 16, 2018
 */
public class TestPlant 
{
	LevelLoader load;
	Level testLevel;
	BasicZombie chad;
	Lane testLane;
	Peashooter shooty;
	Sunflower flower;
	Spot spot;
	Spot spot2;
	
	
	@Before
	public void setUp() throws Exception
	{
		load = new LevelLoader();
		testLevel = load.getLevel("nullLevel.json");
		flower = new Sunflower();
		testLane = testLevel.getLane(0);
		chad = new BasicZombie();
		shooty = new Peashooter();
		testLane.addZombie(chad);
		spot = new Spot(true);
		spot2 = new Spot(false);
	}
	@Test
	public void testLocation()
	{
		assertNull(flower.getLocation());
		flower.setLocation(spot);
		assertEquals("Flower should be on the spot", spot, flower.getLocation());

	}
	@Test
	public void testValue()
	{
		assertEquals("Sunflower should cost 50", 50, flower.getValue());
		assertEquals("Peashooter should cost 100", 100, shooty.getValue());
	}
	@Test
	public void testTurn() 
	{
		testLevel.placePlant(flower, 1, 0);
		int balance = testLevel.getBalance();
		flower.turn(testLevel);
		assertEquals("Current balance should be the previous + 12", balance +flower.getAttack(), testLevel.getBalance());
	}
	@Test
	public void testDeath()
	{
		flower.setLocation(spot);
		spot.addPlant(flower);
		assertTrue("There should be a flower here", spot.getOccupied());
		flower.die();
		assertFalse("There should be no flower here", spot.getOccupied());
	}
}
