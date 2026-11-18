package pvz.logic;

import pvz.logic.actions.GameAction;
import pvz.logic.gameobjects.Zombies;
import utils.Position;

public interface GameWorld {

	// metodos que usan los objetos del juego (plantas, zombies, etc...) para
	// interactuar

	public static final int NUM_ROWS = 4;
	public static final int NUM_COLS = 8;

	boolean isEmpty(Position position);

	boolean isInsideBoard(Position position);

	void generateCoins(int sunsPerGeneration);

	void generateSun();

	boolean plantAtackReceived(Position atackPos, int damage);

	Position newZombiePosition(int row);

	boolean isEmptyOfZombies(Position pos);

	boolean zombieAtackReceived(Position p, int damage);


	
	void zombieWasAdded();

	void zombieReachedHouse();

	
	public void zombieWasKilled();

	void add(Zombies zombie);

	void explodePlant(Position center, int damage);
	void explodeZombie(Position center, int damage);

    void pushAction(GameAction action);
}
