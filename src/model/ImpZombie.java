package model;

import java.awt.Image;

import javax.swing.ImageIcon;

import controller.Main;

/**
 * The Imp Zombie class, they have slightly less health thanr basic zombies
 * @author Simon Krol
 * @version Nov 16, 2018
 */
public class ImpZombie extends Zombie
{
	private static final int DEFAULT_HP = 4;
	private static final int DEFAULT_ATTACK = 1;
	private static final double DEFAULT_MOVESPEED = 1.0; // Units per turn
	private static final double DEFAULT_ATTACKSPEED = 0.8; // Attacks per turn
	private static Image sprite = new ImageIcon(Main.class.getResource("/assets/PvZ_G/ImpZombie.gif")).getImage();
	private static boolean resized = false;

	/**
	 * Create a basicZombie without a starting lane
	 */
	public ImpZombie()
	{
		this(null);
	}

	/**
	 * Constructor to create a basicZombie
	 * @param lane The lane the zombie will exist in
	 */
	public ImpZombie(Lane lane)
	{
		super(DEFAULT_HP, DEFAULT_ATTACK, DEFAULT_MOVESPEED, DEFAULT_ATTACKSPEED, lane);
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
		sprite = sprite.getScaledInstance(blockWidth / 2, blockHeight, Image.SCALE_DEFAULT);
		resized = true;
	}
	
	public boolean equals(Object toCheck)
	{
		if(!(toCheck instanceof ImpZombie))return false;
		ImpZombie toCompare = (ImpZombie)toCheck;
		if(!((Zombie)this).equals(toCompare))return false;
		return true;
	}
}
