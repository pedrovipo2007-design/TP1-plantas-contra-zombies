package pvz.logic;

import utils.Position;

public interface GameWorld {

	// metodos que usan los objetos del juego (plantas, zombies, etc...) para
	// interactuar

	public static final int NUM_ROWS = 4;
	public static final int NUM_COLS = 8;

	boolean isEmpty(Position position);

	boolean isInsideBoard(Position position);

	void generateCoins(int sunsPerGeneration);

	boolean  plantatackreceived(Position atackPos, int damage);
}
