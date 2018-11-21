package model;

public class PeaProjectile extends Projectile
{
	private static final double DEFAULT_MOVESPEED = 1;

	protected PeaProjectile(int att, double position, Lane lane)
	{
		super(att, lane, DEFAULT_MOVESPEED, position);
	}

}
