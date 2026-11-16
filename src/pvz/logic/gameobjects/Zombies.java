package pvz.logic.gameobjects;

import pvz.logic.GameObject;
import pvz.logic.GameWorld;
import pvz.view.Messages;
import utils.Position;

public abstract class Zombies extends GameObject {

	protected int damage;
	protected int moveAfterCycles;
	protected int cyclesNotMoving;
	protected int initialHealth;

	protected Zombies(GameWorld game, Position position, int health, int damage, int moveAfterCycles) {
		super(game, position);
		initialize(health, damage, moveAfterCycles);
	}

	protected Zombies(int health, int damage, int moveAfterCycles) {
		super();
		initialize(health, damage, moveAfterCycles);
	}

	private void initialize(int health, int damage, int moveAfterCycles) {
		this.health = health;
		this.initialHealth = health;
		this.damage = damage;
		this.moveAfterCycles = moveAfterCycles;
		this.cyclesNotMoving = 0;
	}

	@Override
	public boolean isAlive() {
		return health > 0;
	}

	@Override
	public boolean update() {
		Position nextPosition = position.left();

		if (!game.isEmpty(nextPosition)) {
			game.zombieAtackReceived(nextPosition, damage);
			return false;
		}

		if (++cyclesNotMoving >= moveAfterCycles) {
			position = nextPosition;
			cyclesNotMoving = 0;
			return true;
		}
		return false;
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
	public String getDescription() {
		return String.format(Messages.ZOMBIE_DESCRIPTION, getName(), moveAfterCycles, damage, initialHealth);
	}

	@Override
	public boolean plantAtackReceived(int damage) {
		health -= damage;
		return true;
	}

	@Override
	public boolean zombieAtackReceived(int damage) {
		return false;
	}
}
