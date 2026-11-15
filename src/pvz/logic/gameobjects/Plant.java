package pvz.logic.gameobjects;

import pvz.logic.GameObject;
import pvz.logic.GameWorld;
import utils.Position;

public abstract class Plant extends GameObject {

	protected int price;
	protected int health;
	public Plant(GameWorld game, Position p) {
		super(game, p);
		
	}

	public Plant() {
		super();

	}

	public int getPrice() {
		return price;
	}

	@Override
	public boolean plantAtackReceived(int damage) {
		return false;
	}

	@Override
	public boolean zombieAtackReceived(int damage) {
		health = health - damage;
		return true;
	}
}
