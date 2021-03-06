package model;

import java.awt.Image;

import javax.swing.ImageIcon;

import controller.Main;

/**
 * The Peashooter class, contains information about the Peashooter plant
 * @author Simon Krol
 * @version Dec 7, 2018
 */
public class Chomper extends Plant
{
	private static final int DEFAULT_HP = 5;
	private static final int DEFAULT_ATTACK = 2;
	private static final double DEFAULT_ATTACKSPEED = 1; // Attacks per turn
	private static final int DEFAULT_VALUE = 60;
	private static final int DEFAULT_DELAY = 5;
	public static Image[] sprite = { new ImageIcon(Main.class.getResource("/assets/PvZ_G/Chomper.gif")).getImage(),
			new ImageIcon(Main.class.getResource("/assets/PvZ_G/ChomperEating.gif")).getImage() };
	private static boolean resized = false;
	private Zombie eating;

	/**
	 * Creates a chomper with default values
	 */
	public Chomper()
	{
		super(DEFAULT_HP, DEFAULT_ATTACK, DEFAULT_ATTACKSPEED, DEFAULT_VALUE, DEFAULT_DELAY);
	}

	/**
	 * Try to eat the closest zombie in the lane
	 */
	protected void attack(Object toAttack)
	{
		if (status.equals(Status.EATING))
		{
			statusDelay--;
			if (statusDelay <= 0)
				status = Status.NORMAL;
			return;
		}
		if (toAttack instanceof Zombie)
		{
			status = Status.EATING;
			eating = (Zombie) toAttack;
			statusDelay = eating.getCurrentHP() / attack;
			lane.killZombie(eating);
		}
	}

	protected void turn(Level curLevel)
	{
		Zombie closest = lane.closeZombies(distance, 1.2);
		this.attack(closest);
	}

	@Override
	/**
	 * Get the classes sprite
	 */
	public Image getSprite()
	{
		if (status.equals(Status.EATING))
		{
			return sprite[1];
		}
		return sprite[0];

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
		for (int i = 0; i < sprite.length; i++)
		{
			sprite[i] = sprite[i].getScaledInstance(blockWidth / 2, blockHeight, Image.SCALE_DEFAULT);
		}
		resized = true;
	}

	@Override
	/**
	 * Check if Chompers are equivalent
	 */
	public boolean equals(Object toCheck)
	{
		if (!(toCheck instanceof Chomper))
			return false;
		Chomper toCompare = (Chomper) toCheck;
		if (!super.equals(toCompare))
			return false;
		if (eating == null && toCompare.eating != null)
			return false;
		if (!eating.equals(toCompare.eating))
			return false;
		return true;
	}

}
