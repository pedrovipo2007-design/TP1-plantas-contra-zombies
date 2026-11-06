package pvz.logic;

import utils.Position;

public abstract class GameObject implements GameItem { // tipo de objetos que maneja game

	protected GameWorld game;
	protected Position position;

	GameObject(GameWorld game, Position p) {
		this.game = game;
		this.position = p;
	}

	public boolean isInPosition(Position other) {
		return this.position.equals(other);
	}

	public Position getPosition();

	public String getName();

	abstract public String getDescription();

	public boolean isAlive();

	abstract public void onEnter();

	abstract public void onExit();
}
