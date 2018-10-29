
public class EmptyZombie extends Zombie {

	public EmptyZombie(int hp, int att, int def, int mov, double attSp, Lane lane) {
		super(hp, att, def, mov, attSp, lane);
	}

	public EmptyZombie(Lane lane) {
		super(0, 0, 0, 0, 0, lane);
	}

}
