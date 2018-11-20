package model;


import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.junit.Before;
/**
 * Tests Zombie class
 * @author Simon Krol
 * @version Nov 16, 2018
 */
public class TestZombie
{
	Level testLevel;
	BasicZombie peter;
	Lane testLane;
	Sunflower sunny;
	
	@Before
	public void setUp() throws Exception
	{
		testLevel = new Level(4, 1, 100, "Assets/levels/nullLevel.txt");
		testLane = testLevel.getLane(0);
		sunny = new Sunflower();
		peter = new BasicZombie();
		testLane.addZombie(peter);
	}
	@Test
	public void testMovement()
	{
		assertEquals("Current position should be 0", 0, peter.getPosition());
		peter.move();
		assertEquals("Current position should have increased by 1 movespeed", (int)peter.getMoveSpeed(), peter.getPosition());
		peter.move();
		assertEquals("Current position should have increased by 1 movespeed", (int)(2*peter.getMoveSpeed()), peter.getPosition());
	
	}
	@Test
	public void testAttack()
	{
		
		testLevel.placePlant(sunny, 0, 0);
		int currentHP = sunny.getCurrentHP();
		peter.attack(testLevel);
		assertEquals("Sunny's HP should have decreased by peters attack", currentHP - peter.getAttack(), sunny.getCurrentHP());
	}
	@Test
	public void testLawnMower()
	{
		peter.setPosition(999);
		assertEquals("Current position should be 999", 999, peter.getPosition());
		assertEquals("EndState should be 0", 0, testLane.getEndState());
		peter.move();
		assertEquals("EndState should be 1", 1, testLane.getEndState());
	}
	@Test
	public void testDeath()
	{
		assertEquals("Lane should have 1 zombie", 1, testLane.getNumZombies());
		peter.die();
		assertEquals("Lane should have 0 zombies", 0, testLane.getNumZombies());
	}
	@Test 
	public void testClear() throws IOException
	{	
		peter.setPosition(999);
		assertEquals("LiveZombies should contain only 1 zombie", 1, testLane.getNumZombies());
		testLane.allTurn(null);
		assertEquals("LiveZombies should contain no zombies", 0, testLane.getNumZombies());
	}
	
}
