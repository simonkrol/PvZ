package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Tests Lane class
 * @author Simon Krol
 * @version Nov 16, 2018
 */
public class TestSpot
{

	Spot spot1;
	Spot spot2;
	Sunflower sunny;
	@Before
	public void setUp() throws Exception
	{
		spot1 = new Spot(false);
		spot2 = new Spot(true);
		sunny = new Sunflower();
	}

	@Test
	public void testGetOccupied()
	{
		assertFalse("Spot not occupied", spot2.getOccupied());
		spot2.addPlant(sunny);
		assertTrue("Spot occupied", spot2.getOccupied());
	}

	@Test
	public void testGetPlant()
	{
		assertEquals("Spot is null", null, spot2.getPlant());
		spot2.addPlant(sunny);
		assertTrue("Sunny is in spot", sunny.equals(spot2.getPlant()));
	}


	@Test(expected = NullPointerException.class)
	public void testKillPlant()
	{
		assertEquals("Spot is null", null, spot2.getPlant());
		spot2.addPlant(sunny);
		assertTrue("Sunny is in spot", sunny.equals(spot2.getPlant()));
		spot2.killPlant();
		assertEquals("Spot is null", null, spot2.getPlant());
		spot2.killPlant();
	}
	
	@Test
	public void testPlaceable()
	{
		assertEquals("Spot is null", null, spot1.getPlant());
		assertFalse(spot1.addPlant(sunny));
		assertEquals("Spot is null", null, spot1.getPlant());
	}

}
