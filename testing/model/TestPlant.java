package model;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

/**
 * Tests Spot class
 * @author Shaun Gordon
 * @version Nov 16, 2018
 */
public class TestPlant 
{
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
		testLevel = new Level(4, 4, 100, "Assets/levels/nullLevel.txt");
		flower = new Sunflower();
		testLane = testLevel.getLane(0);
		chad = new BasicZombie();
		shooty = new Peashooter();
		testLane.addZombie(chad);
		spot = new Spot(true);
		spot2 = new Spot(true);
	}
	@Test
	public void testAttack()
	{
		testLevel.placePlant(flower, 2, 0);
		int balance = testLevel.getBalance();
		flower.attack(testLevel);
		assertEquals("Current balance should be the previous + 12", balance +12, testLevel.getBalance());
		testLevel.placePlant(shooty, 0, 0);
		int currentHP = chad.getCurrentHP();
		shooty.attack(testLevel);
		assertEquals("Chad's HP should decrease by shooty's attack", currentHP - shooty.getAttack(), chad.getCurrentHP());
	}
	@Test
	public void testTurn() 
	{
		testLevel.placePlant(flower, 1, 0);
		int balance = testLevel.getBalance();
		flower.turn(testLevel);
		assertEquals("Current balance should be the previous + 12", balance +12, testLevel.getBalance());
	}
	@Test
	public void testDeath()
	{
		flower.setLocation(spot);
		spot.addPlant(flower);
		flower.die();
		assertEquals("There should be no flower here", spot.getOccupied(), false);
		spot.addPlant(shooty);
		shooty.setLocation(spot2);
		shooty.die();
		assertEquals("There should be no peashooter here", spot2.getOccupied(), false);
	}
}
