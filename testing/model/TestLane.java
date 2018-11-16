package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestLane
{
	Lane lane1, lane2;
	BasicZombie peter;
	Sunflower sunny;
	Peashooter penny;
	@Before
	public void setUp() throws Exception
	{
		lane1 = new Lane();
		lane2 = new Lane(4, 0);
		peter = new BasicZombie();
		sunny = new Sunflower();
		penny = new Peashooter();
		lane1.addZombie(peter);
		
	}

	@Test
	public void testGetDistance()
	{
		assertEquals("Lane1, should have distance = 8*250", 2000, lane1.getDistance());
		assertEquals("Lane2, should have distance = 4*250", 1000, lane2.getDistance());
	}

	@Test
	public void testDamageZombie()
	{
		int currentHP = peter.getCurrentHP();
		int damage = 2;
		lane1.damageZombie(damage);
		assertEquals("Peters health should have dropped by damage", currentHP - damage, peter.getCurrentHP());
	}

	@Test
	public void testSpawnZombie()
	{
		assertEquals("Lane should be empty", 0, lane2.getNumZombies());
		lane2.spawnZombie();
		lane1.spawnZombie();
		assertEquals("Lane should have 1 zombie", 1, lane2.getNumZombies());
		assertEquals("Lane should have 2 zombies", 2, lane1.getNumZombies());
	}

	@Test
	public void testHitEnd()
	{
		assertEquals("End state should be 0", 0, lane1.getEndState());
		lane1.hitEnd();
		assertEquals("End state should be 1", 1, lane1.getEndState());
		lane1.hitEnd();
		assertEquals("End state should be 1", 1, lane1.getEndState());
		lane1.allTurn(null);
		lane1.hitEnd();
		assertEquals("End state should be 2", 2, lane1.getEndState());
	}

	@Test
	public void testCheckFrontPlant()
	{
		
		lane1.placePlant(sunny, 0);
		assertTrue("Lane has a front plant", lane1.checkFrontPlant(1500));
		
	}

	@Test
	public void testGetFrontPlant()
	{
		assertTrue("Front plant is null", lane1.getFrontPlant() == null);
		lane1.placePlant(sunny, 0);
		assertTrue("Sunny is the front plant", sunny.equals(lane1.getFrontPlant()));
		lane1.placePlant(penny, 1);
		assertTrue("Penny is the front plant", penny.equals(lane1.getFrontPlant()));
	}

	@Test
	public void testCheckFail()
	{
		assertFalse("Not yet failed", lane2.checkFail());
		lane2.hitEnd();
		assertFalse("Not yet failed", lane2.checkFail());
		lane2.hitEnd();
		assertFalse("Not yet failed", lane2.checkFail());
		lane2.allTurn(null);
		lane2.hitEnd();
		assertTrue("Failed", lane2.checkFail());
	}

	@Test
	public void testNoZombies()
	{
		assertTrue("Lane shoud be empty", lane2.noZombies());
		lane2.addZombie(peter);
		assertFalse("Lane should not be empty", lane2.noZombies());
		peter.die();
		assertTrue("Lane shoud be empty", lane2.noZombies());
	}


	@Test
	public void testKillZombie()
	{
		assertEquals("Lane should have 0 zombies", 0, lane2.getNumZombies());
		lane2.addZombie(peter);
		assertEquals("Lane should have 1 zombie", 1, lane2.getNumZombies());
		lane2.killZombie(peter);
		assertEquals("Lane should have 0 zombies", 0, lane2.getNumZombies());
	}

	@Test
	public void testGetNumZombies()
	{
		assertEquals("Lane should have 0 zombies", 0, lane2.getNumZombies());
		lane2.spawnZombie();
		assertEquals("Lane should have 1 zombie", 1, lane2.getNumZombies());
		lane2.spawnZombie();
		assertEquals("Lane should have 2 zombies", 2, lane2.getNumZombies());
	}

}
