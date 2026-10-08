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
	private int seed; // random?? NO SABEMOS PARA QUE SIRVE
	private boolean playerQuit;
	private Level level;

	public Game(long seed, Level level) {
		
		playerQuit = false;
		longSeed = seed;
		coins = INITIAL_COINS;
		cycles = 0;
	}

	public String positionToString(Position position) {
		// TODO Auto-generated method stub
		return " ";
	}
	
	public boolean checkGameObject(String objectName) {
		
		return false; // NO SABEMOS QUE HACE
	}
	
	public int getCycles() {
		
		return cycles;
	}
	
	public int getCoins() {
		
		return coins;
	}
	
	public int getRemainingZombies() {
		
		return level.getNumberOfZombies();
	}
	
	public boolean hasGameFinished() { // sustituimos a playerQuits VOLVER - quit
		
		return playerQuit;
	}
	
	public boolean playerWins() {
		
		return level.getNumberOfZombies() == 0;
	}

	public void update() {
		
		// VOLVER
	}
	
	public void reset() {
		
	}
	
	public void addGameObject(String plantType, Position position) {
		
	}
	
	public void generateCoins(int cost) {
		
	}
	
	private void generateWithCoins() {
		
	}
	
	public void attackZombie(Position p, int damage) {
		
	}
	
	public void attackPlant(Position p, int damage) {
		
	}
	
	public boolean isEmpty(Position zombiePosition) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static Position newZombiePosition(int row) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isInsideBoard(Position p) {
		
		return false; // VOLVER
	}
}
