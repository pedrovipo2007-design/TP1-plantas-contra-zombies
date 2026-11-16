package pvz.logic.gameobjects;

import pvz.logic.GameWorld;
import pvz.view.Messages;
import utils.Position;

public class BucketHeadZombie extends Zombies {

	public static final int INITIAL_HEALTH = 8;
	public static final int DAMAGE = 1;
	public static final int MOVE_AFTER_CYCLES = 4;
	private int cyclesNotMoving;

	public BucketHeadZombie(Position position, GameWorld game) {
		super(game, position);
		health = INITIAL_HEALTH;
		cyclesNotMoving = 0;
	}

	public BucketHeadZombie() {
		super();
	}

	@Override
	public String getName() {
		return Messages.BUCKET_HEAD_ZOMBIE_NAME;
	}

	@Override
	public String getDescription() {
		return String.format(Messages.ZOMBIE_DESCRIPTION, getName(), MOVE_AFTER_CYCLES, DAMAGE, INITIAL_HEALTH);
	}

	@Override
	public boolean isAlive() {
		return health > 0;
	}

	private boolean move() {
		if (++cyclesNotMoving >= MOVE_AFTER_CYCLES) {
			position = position.left();
			cyclesNotMoving = 0;
			return true;
		}
		return false;
	}

	@Override
	public boolean update() {
		Position left = getPosition().left();
		game.zombieAtackReceived(left, DAMAGE);
		return move();
	}

	@Override
	public void onEnter() {
		game.zombieWasAdded();
	}

	@Override
	public void onExit() {
		game.zombieWasKilled();
	}

	@Override
	protected String getIcon() {
		return String.format(Messages.BUCKET_HEAD_ZOMBIE_ICON, health);
	}
}
