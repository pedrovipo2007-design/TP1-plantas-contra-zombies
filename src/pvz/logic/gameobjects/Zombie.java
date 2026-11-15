package pvz.logic.gameobjects;

import pvz.logic.GameWorld;
import pvz.view.Messages;
import utils.Position;

public class Zombie extends Zombies {

	private Position position;
	public static final int INITIAL_HEALTH = 5;
	public static final int DAMAGE = 1;
	public static final int MOVE_AFTER_CYCLES = 2;
	private int cyclesNotMoving;

	public Zombie(Position p, GameWorld game) {

		super(game, p);
		position = p;
		health = INITIAL_HEALTH;
		cyclesNotMoving = 0;
	}

	public Zombie() {

		super();
	}

	public int getHealth() {
		return health;
	}

	public Position getPosition() {
		return new Position(position.getRow(), position.getCol());
	}

	public void receiveDamage(int damage) {
		health = health - damage;
	}

	private boolean move() {
		if (cyclesNotMoving == MOVE_AFTER_CYCLES) {
			position = position.left();
			cyclesNotMoving = 1;
			return true;
		} else
			cyclesNotMoving++;
			return false;
	}

	@Override
	public boolean update() {

		Position left = getPosition().left();
		boolean hasMoved=false;
		if(left.getCol()>=0) {
			if(!game.zombieAtackReceived(left, DAMAGE)) {
				
				hasMoved=move();
		}
		else hasMoved=move();
		}
		
	
		return hasMoved;
	}

	public boolean isAlive() {
		return health > 0;
	}

	@Override
	public String getName() {
		return Messages.ZOMBIE_NAME;
	}

	@Override
	public String getDescription() {

		return Messages.ZOMBIE_DESCRIPTION;
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
	public String getIcon() {
		return String.format(Messages.ZOMBIE_ICON, this.health);
	}

}
