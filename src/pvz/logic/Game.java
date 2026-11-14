
/*
 * 
 * voy poniendo por aquí las interfaces completadas hasta el momento:
 * 
 * GameModel  -> NO
 * GameWorld  -> NO
 * GameStatus -> SI
 * GameView   -> SI
 * 
 */

package pvz.logic;

import java.util.Random;

import pvz.control.Level;
import pvz.logic.gameobjects.PeashooterList;
import pvz.logic.gameobjects.Plant;
import pvz.logic.gameobjects.SunflowerList;
import pvz.logic.gameobjects.Zombie;
import utils.Position;

public class Game implements GameStatus, GameWorld, GameModel {

	// GameStatus -> metodos que usa GamePrinter (estado del mundo)
	// GameWorld -> metodos para que los objetos interactuen
	// GameModel -> metodos para modificar el estado de la partida

	public static final int INITIAL_COINS = 50;
	private boolean playerQuit;

	// hasta aquí hay en los apuntes

	private int cycles;
	private int coins;
	private long longSeed;
	private int seed;
	private Random rand;

	private Level tLevel;
	private ZombiesManager tZM;
	private PeashooterList tPL;
	private SunflowerList tSL;

	private GameObjectContainer GOC;

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

		// eliminar las anteriores
		GOC = new GameObjectContainer();
	}
	@Override
	public String positionToString(Position position) { // GS

		// te dice que objeto hay en "position"
		// creo que hay que refactorizar para que sea independiente de los objetos

		// tenemos los objetos en arrays de Game.NUM_COLS*Game.NUM_ROWS (x*y) y su
		// posicion sera (x * NUM_COLS) + y
		// hay que buscar en las 3 listas

		String res;

		if (!GOC.isEmpty(position)) {

			res = GOC.iconInPosition(position);

		} 
		else
			res = " "; // devolvemos vacio si no hay nada

		return res;
	}

	public boolean checkGameObject(String objectName) { // GS

		return (objectName.equals("sunflower") || objectName.equals("s") || objectName.equals("peashooter")
				|| objectName.equals("p"));
	}

	public int getCycles() { // GS

		return cycles;
	}

	public int getCoins() { // GS

		return coins;
	}

	public int getRemainingZombies() { // GS

		return tZM.getRemainingZombies();
	}

	public void quit() { // GM

		playerQuit = true;
	}

	@Override
	public void update() { // GM
		tZM.addZombie();
		GOC.update();
		cycles++;
	}

	public boolean haveZombiesReached() { // no se en que interfaz ponerlo

		return tZM.doZombiesReachedTheHouse();
	}

	public void reset() { // GM

		coins = INITIAL_COINS;
		cycles = 0;

		tZM.reset();
		tPL.reset();
		tSL.reset();

		rand.setSeed(longSeed); // lo he buscado, daba error
	}

	public void generateCoins(int cost) { // GW

		coins += cost;
	}

	private void buyWithCoins(int cost) { // privada

		coins -= cost;
	}

	public boolean isEmptyOfZombies(Position pos) { // GW

		return tZM.isEmpty(pos);
	}

	public void attackZombie(Position p, int damage) { // position p es la planta - GW

		tZM.damageZombie(p, damage);
	}

	public void attackPlant(Position p, int damage) { // position p es la planta - GW
		if (tPL.isEmpty(p))
			tSL.receiveDamage(damage, p);
		else
			tPL.receiveDamage(p, damage);
	}

	public boolean isEmpty(Position pos) { // GW

		return GOC.isEmpty(pos);
	}

	public Position newZombiePosition(int row) { // devuelvo posicion en la col mas alejada - GW

		Position p = new Position(row, NUM_COLS );
		return p;
	}

	public boolean isInsideBoard(Position p) { // GW

		return p.getCol() < NUM_COLS && p.getRow() < NUM_ROWS && p.getCol() >= 0 && p.getRow() >= 0;
	}

	public boolean hasGameFinished() { // GM
		return playerQuit || haveZombiesReached() || playerWins();
	}

	public boolean playerWins() { // GM
		return tZM.getRemainingZombies() == 0 && tZM.noMoreZombies();
	}

	public boolean playerQuits() { // GM
		return playerQuit;
	}
	
	//ESTE REALMENTE SE PUEDE BORRAR< ES SOLO POR SI NO TE GUSTA COMO ESTA
	@Override
	public void addPlant(GameModel game, String plantName, Position pos) { // GM
		if (this.isInsideBoard(pos)) {
			if (this.isEmpty(pos)) {
				Plant miplanta = PlantFactory.spawnPlant(plantName, this, pos);
				if (miplanta != null) {
					if (this.coins >= miplanta.getPrice()) {
						this.GOC.add(miplanta);
						this.buyWithCoins(miplanta.getPrice());
					}
				}
			}
		}

	}
	
	@Override
	public boolean addPlant( String plantName, Position pos) { // GM
		if (this.isInsideBoard(pos)) {
			if (this.isEmpty(pos)) {
				Plant miplanta = PlantFactory.spawnPlant(plantName, this, pos);
				if (miplanta != null) {
					if (this.coins >= miplanta.getPrice()) {
						this.GOC.add(miplanta);
						this.buyWithCoins(miplanta.getPrice());
						return true;
					}
				}
			}
		}
		return false;

	}
	
	@Override
	public void zombieWasAdded() {
		tZM.zombieWasAdded();
	}

	@Override
	public void zombieWasKilled() {
		tZM.zombieWasKilled();
	}
	public boolean plantAtackReceived(Position atackPos, int damage) { // GW

		return this.GOC.plantAtackReceived(atackPos, damage);
	}
	
	@Override
	public void add(Zombie zombie) {
		// TODO Auto-generated method stub
		GOC.add(zombie);
	}
}
