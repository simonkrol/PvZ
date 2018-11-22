package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Tests Lane class
 * @author Simon Krol
 * @version Nov 16, 2018
 */
public class TestLane
{
	Lane lane1, lane2;
	BasicZombie peter;
	Sunflower sunny;
	Peashooter penny;
	PeaProjectile peaproj;

	@Before
	public void setUp() throws Exception
	{
		lane1 = new Lane();
		lane2 = new Lane(4, 0);
		peter = new BasicZombie();
		sunny = new Sunflower();
		penny = new Peashooter();
		peaproj = new PeaProjectile(1, 0, lane2);
		lane1.addZombie(peter);
		
	}
	@Test
	public void testGetDistance()
	{
		assertEquals("Lane1, should have distance = 8", 8, lane1.getLength());
		assertEquals("Lane2, should have distance = 4", 4, lane2.getLength());
	}

	@Test
	public void testGetFrontPlant()
	{
		lane1.placePlant(sunny, 0);
		assertNull("No plant should exist before peter", lane1.getFrontPlant(peter.getPosition()));
		peter.setPosition(lane1.getLength()-2);
		assertNull("No plant should exist before peter", lane1.getFrontPlant(peter.getPosition()));
		peter.setPosition(lane1.getLength()-1);
		assertEquals("Sunny should be in front of peter", sunny, lane1.getFrontPlant(peter.getPosition()));
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
	public void testAttackableZombies()
	{
		lane2.addZombie(peter);
		peter.setPosition(lane2.getLength()-2);
		assertFalse("Shouldnt be able to attack Peter from 2",lane2.attackableZombies(2));
		assertTrue("Should be able to attack peter from 1", lane2.attackableZombies(1));
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

	@Test
	public void testTurn()
	{

		lane2.addZombie(peter);
		lane2.placePlant(sunny, 0);
		peter.setPosition(lane2.getLength()-1);
		peter.turn(null);
		assertEquals("Current position should not have increased past the plant", lane2.getLength()-1, (int)peter.getPosition());
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
	public void testProjZombie()
	{
		lane2.createProjectile(peaproj);
		lane2.addZombie(peter);
		peter.setPosition(lane2.getLength()-2);
		assertNull("Projectile should be too far away", lane2.getProjZombie(peaproj));
		peter.setPosition(lane2.getLength()-1);
		assertEquals("Projectile should be close enough to peter", peter, lane2.getProjZombie(peaproj));
	}

}
