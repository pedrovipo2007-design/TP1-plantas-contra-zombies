package pvz.logic;
import pvz.control.Level;
import utils.Position;

public class Game {

	
	public static final int NUM_ROWS = 4;
	public static final int NUM_COLS = 8;
	public static final int INITIAL_COINS = 50;
	
	private int cycles;
	private int coins;
	private long longSeed;
	private int seed; // random??
	private boolean playerQuit;

	public Game(long seed, Level level) {
		// a 
	}

	public static Position newZombiePosition(int row) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEmpty(Position zombiePosition) {
		// TODO Auto-generated method stub
		return false;
	}

	public String positionToString(Position position) {
		// TODO Auto-generated method stub
		return null;
	}
}
