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
		if (position.getCol() > 0)
			game.attackPlant(this.getPosition().left(), DAMAGE);
	}

	public void update() {

		Position left = getPosition().left();
		boolean house = left.getCol() < 0; // está en la columna 0: el siguiente paso cruza la casa

		if (house || (game.isEmpty(left) && game.isEmptyOfZombies(left)))
			move();

		if (position.getCol() >= 0 && !house) { // ataque en el mismo ciclo en que llega
			left = getPosition().left();
			if (!game.isEmpty(left) && game.isEmptyOfZombies(left))
				atack();
		}
	}

	public boolean isAlive() {
		return health > 0;
	}
}
