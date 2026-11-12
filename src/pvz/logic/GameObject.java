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
		// LO QUEREMOS UNICAMENTE PARA GETS DESCRIPTIONS CUANDO NO HAY MODEL O POSITION
		// - Que??
		// TODO
	}

	public boolean isInPosition(Position other) {
		return this.position.equals(other);
	}

	public int getHealth() {
		return health;
	}

	public Position getPosition() {
		Position aux = new Position(position.getRow(), position.getCol());
		return aux;
	}

	abstract public String getName();

	abstract public String getDescription();

	public boolean isAlive() {

		return health != 0;
	}

	abstract public void onEnter();

	abstract public void update();

	abstract public void onExit();
	
	@Override
	abstract public boolean plantAtackReceived(int damage);
	// EN UN INICIO LO DECLARE AQUI PERO ES QUE NO SE CUAL ES LA VENTAJA DE DECLARARLO EN UNA INTERFAZ,
	// PREGUNTAR LA DIFERENCIA SI ESTE METODO SIEMPRE ES LLAMADO POR UN OBJETO DE ESTA CLASE O SUBCLASES
	
	@Override
	abstract public boolean zombieAtackReceived(int damage);


	protected abstract String getIcon();
}
