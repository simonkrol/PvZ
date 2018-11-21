package model;

import java.util.Random;

public class Projectile extends Entity
{
	private static Random rand = new Random();
	private double moveSpeed;
	private double position;
	private double offset;
	private boolean exploded;
	

	protected Projectile(int att, Lane lane, double moveSpeed, double position)
	{
		super(1, att, 0, lane);
		this.moveSpeed = moveSpeed;
		this.offset = (rand.nextDouble()*0.7)-0.35;
		this.position = position;
		exploded = false;
	}

	@Override
	protected void die()
	{
		exploded = true;
		
	}

	@Override
	protected void turn(Level curLevel)
	{
		Zombie toAttack = lane.getProjZombie(moveSpeed, position);
		if(toAttack != null) 
		{
			attack(toAttack);
		}
		else
		{
			move();
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void attack(Object toAttack)
	{
		System.out.println(toAttack instanceof Zombie);
		if(toAttack instanceof Zombie)
		{
			((Zombie) toAttack).takeDamage(attack);
			this.die();
		}
		
	}
	protected void move()
	{
		position += moveSpeed;
	}
	public double getOffset()
	{
		return offset;
	}
	public double getPosition()
	{
		return position;
	}
	protected boolean getExploded()
	{
		return exploded;
	}
}
