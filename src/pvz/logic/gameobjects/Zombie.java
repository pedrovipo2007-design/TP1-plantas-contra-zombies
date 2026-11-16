package pvz.logic.gameobjects;

import pvz.logic.GameWorld;
import pvz.view.Messages;
import utils.Position;

public class Zombie extends Zombies {

	public static final int INITIAL_HEALTH = 5;
	public static final int DAMAGE = 1;
	public static final int MOVE_AFTER_CYCLES = 2;

	public Zombie(Position position, GameWorld game) {
		super(game, position, INITIAL_HEALTH, DAMAGE, MOVE_AFTER_CYCLES);
	}

	public Zombie() {
		super(INITIAL_HEALTH, DAMAGE, MOVE_AFTER_CYCLES);
	}

	@Override
	public String getName() {
		return Messages.ZOMBIE_NAME;
	}

	@Override
	protected String getIcon() {
		return String.format(Messages.ZOMBIE_ICON, health);
	}
}
