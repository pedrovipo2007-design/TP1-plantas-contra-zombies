package pvz.logic;

import utils.Position;

public abstract class GameObject implements GameItem { // tipo de objetos que maneja game

	protected GameWorld game;
	protected Position position;
	protected int health;
	protected GameObject(GameWorld game, Position p) {
		this.game = game;
		this.position = p;
	}

	public GameObject() {
		// TODO Auto-generated constructor stub
	}//LO QUEREMOS UNICAMENTE PARA GETS DESCRIPTIONS CUANDO NO HAY MODEL O POSITION

	public boolean isInPosition(Position other) {
		return this.position.equals(other);
	}
	
	public int getHealth() {
		return health;
	}

	public Position getPosition() {
		return null;
	}

	public String getName() {
		return null;
	}

	abstract public String getDescription();

	public boolean isAlive() {
		return false;
	}
	abstract public void onEnter();
	abstract public void update();
	abstract public void onExit();

	abstract public boolean plantAtackReceived(int damage);
}
