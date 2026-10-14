package pvz.logic;

import java.util.Random;

import pvz.control.Level;
import pvz.logic.gameobjects.Peashooter;
import pvz.logic.gameobjects.PeashooterList;
import pvz.logic.gameobjects.Sunflower;
import pvz.logic.gameobjects.SunflowerList;
import utils.Position;

public class Game {

	public static final int NUM_ROWS = 4;
	public static final int NUM_COLS = 8;
	public static final int INITIAL_COINS = 50;

	private int cycles;
	private int coins;
	private long longSeed;
	private int seed;
	private Random rand;
	private boolean playerQuit;

	private Level tLevel;
	private ZombiesManager tZM;
	private PeashooterList tPL;
	private SunflowerList tSL;

	public Game(long seed, Level level) {

		playerQuit = false;
		longSeed = seed;
		coins = INITIAL_COINS;
		cycles = 0;
		rand = new Random(seed);
		tLevel = level;

		tZM = new ZombiesManager(this, tLevel, rand);
		tPL = new PeashooterList();
		tSL = new SunflowerList();
	}

	public String positionToString(Position position) {

		// tenemos los objetos en arrays de Game.NUM_COLS*Game.NUM_ROWS (x*y) y su
		// posicion sera (x * NUM_COLS) + y
		// hay que buscar en las 3 listas

		String res;

		if (!tZM.isEmpty(position)) {

			res = tZM.iconInPosition(position);

		} else if (!tPL.isEmpty(position)) {

			res = tPL.iconInPosition(position);

		} else if (!tSL.isEmpty(position)) {

			res = tSL.iconInPosition(position);

		} else
			res = " "; // devolvemos vacio si no hay nada

		return res;
	}

	public boolean checkGameObject(String objectName) {

		return (objectName.equals("sunflower") || objectName.equals("s") || objectName.equals("peashooter")
				|| objectName.equals("p"));
	}

	public int getCycles() {

		return cycles;
	}

	public int getCoins() {

		return coins;
	}

	public int getRemainingZombies() {

		return tLevel.getNumberOfZombies();
	}

	public boolean hasGameFinished() { // sustituimos a playerQuits

		return (playerQuit || this.haveZombiesReached() || (tZM.getRemainingZombies() == 0) && tZM.noMoreZombies());
	}

	public boolean playerWins() {

		return tLevel.getNumberOfZombies() == 0;
	}

	public void quit() {

		playerQuit = true;
	}

	public void update() {

		tZM.addZombie(); // tenemos dudas de que quieres que actualicemos primero
		tZM.update();
		tPL.update();
		tSL.update();
		tZM.removeDead();
		cycles++;
	}

	public boolean haveZombiesReached() {
		return tZM.doZombiesReachedTheHouse();
	}

	public void reset() {

		coins = INITIAL_COINS;
		cycles = 0;

		tZM.reset();
		tPL.reset();
		tSL.reset();
	}

	public void addGameObject(String plantType, Position position) {

		if (plantType.equals("peashooter") || plantType.equals("p")) {

			Peashooter p = new Peashooter(position, this);
			tPL.add(p);
			this.buyWithCoins(Peashooter.COST);
		}

		else if (plantType.equals("sunflower") || plantType.equals("s")) {

			Sunflower s = new Sunflower(position, this);
			tSL.add(s);
			this.buyWithCoins(Sunflower.COST);
		}
	}

	public void generateCoins(int cost) {

		coins += cost;
	}

	private void buyWithCoins(int cost) {

		coins -= cost;
	}

	public boolean isEmptyOfZombies(Position pos) {
		return tZM.isEmpty(pos);
	}

	public void attackZombie(Position p, int damage) { // position p es la planta

		int row = p.getRow();
		int col = p.getCol();

		int pos = col;
		pos++;

		while (pos < NUM_COLS) {

			if (!isEmptyOfZombies(new Position(row, pos))) {
				p = new Position(row, pos);
				tZM.damageZombie(p, damage);
				pos++;
				break;
			} else {

				pos++;
			}
		}
	}

	public void attackPlant(Position p, int damage) { // position p es la planta
		if (tPL.isEmpty(p))
			tSL.receiveDamage(damage, p);
		else
			tPL.receiveDamage(p, damage);
	}

	public boolean isEmpty(Position zombiePosition) {

		return (tPL.isEmpty(zombiePosition) && tSL.isEmpty(zombiePosition));
	}

	public static Position newZombiePosition(int row) { // devuelvo posicion en la col mas alejada

		Position p = new Position(row, NUM_COLS + 1);

		return p;
	}

	public boolean isInsideBoard(Position p) {

		return p.getCol() < NUM_COLS && p.getRow() < NUM_ROWS;
	}
}
