package model;


import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import controller.LevelLoader;

import org.junit.Before;
/**
 * Tests Zombie class
 * @author Simon Krol
 * @version Nov 16, 2018
 */
public class TestZombie
{
	LevelLoader load;
	Level testLevel;
	BasicZombie peter;
	Lane testLane;
	Sunflower sunny;
	
	@Before
	public void setUp() throws Exception
	{
		load = new LevelLoader();
		testLevel = load.getLevel("nullLevel.json");
		testLane = testLevel.getLane(0);
		sunny = new Sunflower();
		peter = new BasicZombie();
		testLane.addZombie(peter);
	}
	@Test
	public void testGetMoveSpeed()
	{
		assertEquals("Peter should be moving at 1 unit per second", 1, (int)peter.getMoveSpeed());
	}
	@Test
	public void testMovement()
	{
		assertEquals("Current position should be 0", 0, (int)peter.getPosition());
		peter.move();
		assertEquals("Current position should have increased by 1 movespeed", (int)peter.getMoveSpeed(),(int) peter.getPosition());
		peter.move();
		assertEquals("Current position should have increased by 1 movespeed", (int)(2*peter.getMoveSpeed()), (int)peter.getPosition());
	
	}
	@Test
	public void testAttack()
	{
		
		testLevel.placePlant(sunny, 0, 0);
		int currentHP = sunny.getCurrentHP();
		peter.attack(sunny);
		assertEquals("Sunny's HP should have decreased by peters attack", currentHP - peter.getAttack(), sunny.getCurrentHP());
	}
	@Test
	public void testLawnMower()
	{
		peter.setPosition(testLane.getLength());
		assertEquals("Current position should be 8", testLane.getLength(),(int) peter.getPosition());
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
		peter.setPosition(testLane.getLength());
		assertEquals("LiveZombies should contain only 1 zombie", 1, testLane.getNumZombies());
		testLane.allTurn(null);
		assertEquals("LiveZombies should contain no zombies", 0, testLane.getNumZombies());
	}
	@Test
	public void testSetPosition()
	{
		peter.setPosition(3);
		assertEquals("Peter should be at position 3", 3, (int)peter.getPosition());
	}
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidAttack() {
		peter.attack(testLevel);
	}
	
}
