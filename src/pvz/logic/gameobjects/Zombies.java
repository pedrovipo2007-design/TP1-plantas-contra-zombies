package pvz.logic.gameobjects;

import pvz.logic.GameObject;
import pvz.logic.GameWorld;
import utils.Position;

public abstract class Zombies extends GameObject { // Zombies es la clase abstracta, Zombie es zombie normal (objeto)
	protected int damage;
	protected Zombies(GameWorld game, Position p) { // xq lo hacemos protected??   Por si lo vuelves a leer es porque luego tenemos distintos tipos de zombie
		super(game, p);
		// TODO
	}

	protected Zombies() {
		super();
	}

	@Override
	public boolean plantAtackReceived(int damage) {

		health = health - damage;
		return true;
	}

	@Override
	public boolean zombieAtackReceived(int damage) {
		return false;
	}
}
