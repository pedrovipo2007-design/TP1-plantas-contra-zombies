package pvz.logic.gameobjects;

import pvz.logic.GameWorld;
import pvz.view.Messages;
import utils.Position;

public class BucketHeadZombie extends Zombies {

	public static final int INITIAL_HEALTH = 8;
	public static final int DAMAGE = 1;
	public static final int MOVE_AFTER_CYCLES = 4;

	public BucketHeadZombie(Position position, GameWorld game) {
		super(game, position, INITIAL_HEALTH, DAMAGE, MOVE_AFTER_CYCLES);
	}

	public BucketHeadZombie() {
		super(INITIAL_HEALTH, DAMAGE, MOVE_AFTER_CYCLES);
	}

	@Override
	public String getName() {
		return Messages.BUCKET_HEAD_ZOMBIE_NAME;
	}

	@Override
	protected String getIcon() {
		return String.format(Messages.BUCKET_HEAD_ZOMBIE_ICON, health);
	}
}
