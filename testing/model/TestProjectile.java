package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests Lane class
 * @author Simon Krol
 * @version Nov 16, 2018
 */
public class TestProjectile
{
	Lane testLane;
	PeaProjectile peaProj;
	BasicZombie peter;
	@Before
	public void setUp() throws Exception
	{
		testLane = new Lane(4,0);
		peaProj = new PeaProjectile(1, 0, testLane);
		peter = new BasicZombie();
		testLane.createProjectile(peaProj);
	}

	@Test
	public void testDie()
	{
		assertTrue("Lane should have projectiles", testLane.getProjectiles().size()>=1);
		peaProj.die();
		assertTrue("Lane should have projectiles", testLane.getProjectiles().size()>=1);
		testLane.clearUsedProj();
		assertEquals("Lane should have no projectiles", 0, testLane.getProjectiles().size());
	}
	@Test
	public void testAttack()
	{
		int currentHP = peter.getCurrentHP();
		peaProj.attack(peter);
		assertEquals("Peters HP should have gone down by peaProj's attack", currentHP - peaProj.getAttack(), peter.getCurrentHP());
	}
	@Test
	public void testExploded()
	{
		peaProj.attack(peter);
		assertTrue("PeaProj should have exploded", peaProj.getExploded());
	}

	@Test
	public void testTurn()
	{
		testLane.addZombie(peter);
		peter.setPosition(testLane.getLength()-2.5);
		peaProj.turn(null);
		assertFalse("PeaProj should not have exploded", peaProj.getExploded());
		peter.setPosition(testLane.getLength()-3.4);
		peaProj.turn(null);
		assertTrue("PeaProj should have exploded", peaProj.getExploded());
	}

}
