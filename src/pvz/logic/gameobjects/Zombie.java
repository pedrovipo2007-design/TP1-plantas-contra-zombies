package pvz.logic.gameobjects;

import pvz.logic.Game;
import utils.Position;

public class Zombie {

	private Position position;
	public static final int INITIAL_HEALTH = 5;
	public static final int DAMAGE = 1;
	public static final int MOVE_AFTER_CYCLES = 2;
	private int health;
	private int cyclesNotMoving;
	private Game game;

	public Zombie(Position p, Game game) {
		position = p;
		health = INITIAL_HEALTH;
		cyclesNotMoving = 0;
		this.game = game;
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

	private void move() {
		if (cyclesNotMoving == MOVE_AFTER_CYCLES) {
			position = position.left();
			cyclesNotMoving = 1;

		} else
			cyclesNotMoving++;
	}

	private void atack() {
		game.attackPlant(this.getPosition().left(), DAMAGE);
	}

	public void update() {
		if (position.getCol() == 0)
			return; // ya llegó a la casa
		Position left = getPosition().left();
		if (game.isEmpty(left) && game.isEmptyOfZombies(left))
			move();
		left = getPosition().left(); // al parecer ataca despues de moverse en los test
		if (!game.isEmpty(left) && game.isEmptyOfZombies(left))
			atack();
	}

	public boolean isAlive() {
		return health > 0;
	}
}
