package pvz.logic.gameobjects;

import pvz.logic.GameObject;
import pvz.logic.GameWorld;
import utils.Position;

public abstract class Zombies extends GameObject { // Zombies es la clase abstracta, Zombie es zombie normal (objeto)

	protected Zombies(GameWorld game, Position p) {
		super(game, p);
		// TODO
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
