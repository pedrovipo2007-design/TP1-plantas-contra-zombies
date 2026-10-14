package pvz.logic.gameobjects;

import pvz.logic.Game;
import pvz.view.Messages;
import utils.Position;

public class SunflowerList {

	private Sunflower[] sunflowers;
	private int numberOfSunflowers;

	public SunflowerList() {
		sunflowers = new Sunflower[Game.NUM_COLS * Game.NUM_ROWS];
		numberOfSunflowers = 0;
	}

	public void reset() {
		numberOfSunflowers = 0;
		for (int i = 0; i < Game.NUM_COLS * Game.NUM_ROWS; i++) {
			sunflowers[i] = null;
		}
	}

	public String iconInPosition(Position p) {
		return String.format(Messages.SUNFLOWER_ICON, sunflowers[p.getRow() * Game.NUM_COLS + p.getCol()].getHealth());
	}

	public void add(Sunflower s) {
		sunflowers[s.getPosition().hashcode()] = s;
	}

	public boolean isEmpty(Position p) {
		return (sunflowers[p.hashcode()] == null);
	}

	public void update() {
		for (int i = 0; i < Game.NUM_COLS; i++) {
			for (int j = 0; j < Game.NUM_ROWS; j++) {
				Position position;
				position = new Position(j, i);
				if (!isEmpty(position))
					sunflowers[position.hashcode()].update();
			}
		}
		removeDead();
	}

	public void removeDead() {
		for (int i = 0; i < Game.NUM_COLS; i++) {
			for (int j = 0; j < Game.NUM_ROWS; j++) {
				Position position;
				position = new Position(j, i);
				if (!isEmpty(position)) {
					if (!sunflowers[position.hashcode()].isAlive()) {
						sunflowers[position.hashcode()] = null;
					}
				}
			}
		}
	}

	public void receiveDamage(int damage, Position pos) {
		sunflowers[pos.hashcode()].receiveDamage(damage);
	}
}
