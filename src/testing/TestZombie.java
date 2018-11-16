package testing;
import model.*;


import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.junit.Before;

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
		sunny = new Sunflower(testLane);
		peter = new BasicZombie(testLane);
		testLane.liveZombies.add(peter);
	}
	@Test
	public void testMovement()
	{
		assertEquals("Current position should be 0", 0, peter.getPosition());
		peter.move();
		assertEquals("Current position should have increased by movespeed", 250, peter.getPosition());
		peter.move();
		assertEquals("Current position should have increased by movespeed", 500, peter.getPosition());
		testLevel.placePlant(sunny, 0, 0);
		int currentHP = sunny.getCurrentHP();
		peter.move();
		assertEquals("Current position should be blocked by sunflower", 500, peter.getPosition());
		assertEquals("Sunny's HP should have decreased by peters attack", currentHP - peter.getAttack(), sunny.getCurrentHP());
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
		peter.move();
		peter.move();
		peter.move();
		assertEquals("Current position should have increased by 3 movespeed", 750, peter.getPosition());
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
	public void testTurn()
	{
		
		testLevel.placePlant(sunny, 0, 1);
		peter.move();
		peter.move();
		assertEquals("Current position should only have increased once", 250, peter.getPosition());
		int numTurn = (int) Math.ceil(1.0/peter.getAttackSpeed());
		int sunnyHP = sunny.getCurrentHP();
		while(numTurn>0)
		{
			peter.turn(null);
			numTurn--;
		}
		assertFalse("Sunny's hp should have dropped", sunnyHP <= sunny.getCurrentHP());
	}
	@Test 
	public void testClear() throws IOException
	{
		peter.move();
		peter.move();
		peter.move();
		
		assertEquals("LiveZombies should contain only 1 zombie", 1, testLane.getNumZombies());
		testLevel.allTurn();
		assertEquals("LiveZombies should contain no zombies", 0, testLane.getNumZombies());
	}
	
	

}
