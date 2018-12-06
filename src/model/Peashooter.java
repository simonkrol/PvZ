package model;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * The Peashooter class, contains information about the Peashooter plant
 * @author Simon Krol
 * @version Nov 25, 2018
 */
public class Peashooter extends Plant
{
	private static final int DEFAULT_HP = 5;
	private static final int DEFAULT_ATTACK = 2;
	private static final double DEFAULT_ATTACKSPEED = 1; // Attacks per turn
	private static final int DEFAULT_VALUE = 100;
	private static final int DEFAULT_DELAY = 5;
	private static Image sprite = new ImageIcon("res/assets/PvZ_G/Peashooter.gif").getImage();
	private static boolean resized = false;

	/**
	 * Creates a peashooter with default values
	 */
	public Peashooter()
	{
		super(DEFAULT_HP, DEFAULT_ATTACK, DEFAULT_ATTACKSPEED, DEFAULT_VALUE, DEFAULT_DELAY);
	}

	/**
	 * Shoot a pea at the closest zombie in the lane
	 */
	protected void attack(Object toAttack)
	{
		lane.createProjectile(new PeaProjectile(attack, this. distance+0.3, lane));
	}

	@Override
	/**
	 * Get the classes sprite
	 */
	public Image getSprite()
	{
		return sprite;
		
	}
	@Override
	/**
	 * Get if the sprite has been resized already
	 */
	public boolean getResized()
	{
		return resized;
	}
	@Override
	/**
	 * Set the sprite size
	 */
	public void setSpriteSize(int blockWidth, int blockHeight)
	{
		sprite = sprite.getScaledInstance(blockWidth/2, blockHeight, Image.SCALE_DEFAULT);
		resized = true;
	}
	

}
