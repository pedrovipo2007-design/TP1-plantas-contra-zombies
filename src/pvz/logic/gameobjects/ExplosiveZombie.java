package pvz.logic.gameobjects;

import pvz.logic.GameWorld;
import pvz.view.Messages;
import utils.Position;

public class ExplosiveZombie extends Zombies {

	public static final int INITIAL_HEALTH = 5;
	public static final int DAMAGE = 1;
	public static final int MOVE_AFTER_CYCLES = 2;
	public static final int EXPLOSION_DAMAGE = 3;

	public ExplosiveZombie(Position position, GameWorld game) {
		super(game, position, INITIAL_HEALTH, DAMAGE, MOVE_AFTER_CYCLES);
	}

	public ExplosiveZombie() {
		super(INITIAL_HEALTH, DAMAGE, MOVE_AFTER_CYCLES);
	}

	@Override
	public String getName() {
		return Messages.EXPLOSIVE_ZOMBIE_NAME;
	}

	@Override
	public void onExit() {
		super.onExit();
		game.explodeZombie(getPosition(), EXPLOSION_DAMAGE);
	}

	@Override
	protected String getIcon() {
		return String.format(Messages.EXPLOSIVE_ZOMBIE_ICON, health);
	}
}
