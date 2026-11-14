package pvz.logic;

import pvz.logic.gameobjects.Zombie;
import utils.Position;

public interface GameWorld {

	// metodos que usan los objetos del juego (plantas, zombies, etc...) para
	// interactuar

	public static final int NUM_ROWS = 4;
	public static final int NUM_COLS = 8;

	boolean isEmpty(Position position);

	boolean isInsideBoard(Position position);

	void generateCoins(int sunsPerGeneration);

	boolean plantAtackReceived(Position atackPos, int damage);

	Position newZombiePosition(int row);

	boolean isEmptyOfZombies(Position pos);

	void attackZombie(Position p, int damage);

	public void attackPlant(Position p, int damage);
	
	public void zombieWasAdded();

	
	public void zombieWasKilled();

	void add(Zombie zombie);
}
