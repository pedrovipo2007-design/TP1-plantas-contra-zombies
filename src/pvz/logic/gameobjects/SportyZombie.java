package pvz.logic.gameobjects;

import pvz.logic.GameWorld;
import pvz.view.Messages;
import utils.Position;

public class SportyZombie extends Zombies {

	public static final int INITIAL_HEALTH = 2;
	public static final int DAMAGE = 1;
	public static final int MOVE_AFTER_CYCLES = 1;

	public SportyZombie(Position position, GameWorld game) {
		super(game, position, INITIAL_HEALTH, DAMAGE, MOVE_AFTER_CYCLES);
	}

	public SportyZombie() {
		super(INITIAL_HEALTH, DAMAGE, MOVE_AFTER_CYCLES);
	}

	@Override
	public String getName() {
		return Messages.SPORTY_ZOMBIE_NAME;
	}

	@Override
	protected String getIcon() {
		return String.format(Messages.SPORTY_ZOMBIE_ICON, health);
	}
}
