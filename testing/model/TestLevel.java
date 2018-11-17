package model;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
/**
 * Tests Level class
 * @author Simon Krol
 * @version Nov 16, 2018
 */
public class TestLevel
{
	Level testLevel;
	Level emptyLevel;
	Sunflower sunny;

	@Before
	public void setUp() throws Exception
	{
		emptyLevel = new Level(5,6,50, "Assets/levels/nullLevel.txt");
		testLevel = new Level(5, 6, 50, "Assets/levels/Level.txt");
		sunny = new Sunflower();
		
	}

	@Test
	public void testSpawnZombies() throws IOException
	{
		assertEquals("No zombies should be present", 0, testLevel.getNumZombies());
		testLevel.turn=5;
		testLevel.spawnZombies();
		assertEquals("3 zombies should be present", 3, testLevel.getNumZombies());
		
		
	}

	@Test
	public void testPlacePlant()
	{
		assertFalse("Sunny is out of bounds be placed", testLevel.placePlant(sunny, 3, 3));
		assertTrue("Sunny can be placed", testLevel.placePlant(sunny, 0, 0));
		assertFalse("No money left", testLevel.placePlant(sunny, 0, 0));
		
	}

	@Test
	public void testAddToBalance()
	{
		assertEquals("Should have 50 money", 50, testLevel.getBalance());
		testLevel.addToBalance(16);
		assertEquals("Should have 66 money", 66, testLevel.getBalance());
	}

	@Test
	public void testGetWidth()
	{
		assertEquals("Width is 5", 5, testLevel.getWidth());
	}

	@Test
	public void testGetHeight()
	{
		assertEquals("Height is 6", 6, testLevel.getHeight());
	}

	@Test
	public void testCheckFail()
	{
		assertFalse("Not yet failed", testLevel.checkFail());
		testLevel.getLane(0).hitEnd();
		testLevel.getLane(0).allTurn(testLevel);
		assertFalse("Not yet failed", testLevel.checkFail());
		testLevel.getLane(0).hitEnd();
		assertTrue("Failed", testLevel.checkFail());
	}

	@Test
	public void testCheckWin() throws IOException
	{
		emptyLevel.getLane(0).spawnZombie();
		assertFalse("Not yet won", emptyLevel.checkWin());
		emptyLevel.getLane(0).hitEnd();
		emptyLevel.allTurn();
		assertTrue("Won", emptyLevel.checkWin());
	}

}
