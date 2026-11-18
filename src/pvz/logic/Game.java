
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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import pvz.control.Level;

import pvz.logic.actions.GameAction;
import pvz.logic.gameobjects.Plant;

import pvz.logic.gameobjects.Zombie;
import pvz.logic.gameobjects.Zombies;
import pvz.view.Messages;
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
	private SunsManager sunsManager;
	private int lastCatchCycle;
    private boolean zombiesReachedHouse;
    private Deque<GameAction> actions;


	private GameObjectContainer GOC;

	public Game(long seed, Level level) {

		playerQuit = false;
		longSeed = seed;
		coins = INITIAL_COINS;
		cycles = 0;
		rand = new Random(seed);
		tLevel = level;

		tZM = new ZombiesManager(this, tLevel, rand);
		sunsManager = new SunsManager(rand);
		lastCatchCycle = -1;
        zombiesReachedHouse = false;
        actions = new ArrayDeque<>();


		GOC = new GameObjectContainer();
	}
	@Override
	public String positionToString(Position position) { // GS

		// te dice que objeto hay en "position"
		// creo que hay que refactorizar para que sea independiente de los objetos

		// tenemos los objetos en arrays de Game.NUM_COLS*Game.NUM_ROWS (x*y) y su
		// posicion sera (x * NUM_COLS) + y
		// hay que buscar en las 3 listas

		String objectIcon = GOC.isEmpty(position) ? "" : GOC.iconInPosition(position).trim();
		String sunIcon = sunsManager.iconInPosition(position);
		if (!objectIcon.isEmpty() && !sunIcon.isEmpty()) {
			return objectIcon + " " + sunIcon;
		}
		if (!objectIcon.isEmpty()) return objectIcon;
		if (!sunIcon.isEmpty()) return sunIcon;
		return " ";
	}

	public boolean checkGameObject(String objectName) { // GS
		return PlantFactory.isPlantName(objectName);
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

	@Override
	public int getGeneratedSuns() {
		return sunsManager.getGeneratedSuns();
	}

	@Override
	public int getCaughtSuns() {
		return sunsManager.getCaughtSuns();
	}

	public void quit() { // GM

		playerQuit = true;
	}

    @Override
    public void update() {
        executePendingActions();
        sunsManager.update(cycles);
        GOC.update();
        tZM.addZombie();

        boolean deadRemoved = true;
        while (deadRemoved || !actions.isEmpty()) {
            deadRemoved = GOC.removeDead();
            executePendingActions();
        }

        cycles++;
    }

	public boolean haveZombiesReached() { // no se en que interfaz ponerlo
		return zombiesReachedHouse;
	}

        @Override
    public void reset() {
        reset(tLevel, longSeed);
    }

    @Override
    public void reset(Level level, long seed) {
        tLevel = level;
        longSeed = seed;
        playerQuit = false;
        coins = INITIAL_COINS;
        cycles = 0;
        rand.setSeed(seed);
        tZM = new ZombiesManager(this, tLevel, rand);
        GOC.reset();
        sunsManager.reset();
        actions.clear();
        lastCatchCycle = -1;
        zombiesReachedHouse = false;
    }

	public void generateCoins(int cost) { // GW

		coins += cost;
	}

	@Override
	public void generateSun() {
		sunsManager.generateSun();
	}

	@Override
	public boolean catchSun(Position pos) {
		if (lastCatchCycle == cycles) {
			return false;
		}
		int caught = sunsManager.catchSuns(pos);
		if (caught == 0) {
			return false;
		}
		coins += caught * 10;
		lastCatchCycle = cycles;
		return true;
	}

	@Override
	public boolean hasCaughtSunThisCycle() {
		return lastCatchCycle == cycles;
	}

    @Override
    public void pushAction(GameAction action) {
        actions.addLast(action);
    }

    private void executePendingActions() {
        while (!actions.isEmpty()) {
            GameAction action = actions.removeLast();
            action.execute(this);
        }
    }

        private void buyWithCoins(int cost) {
        coins -= cost;
    }

	public boolean isEmptyOfZombies(Position pos) { // GW

		return tZM.isEmpty(pos);
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

	@Override
	public String getEndMessage() {
		if (playerQuit) return Messages.PLAYER_QUITS;
		if (playerWins()) return Messages.PLAYER_WINS;
		return Messages.ZOMBIES_WIN;
	}

	//ESTE REALMENTE SE PUEDE BORRAR< ES SOLO POR SI NO TE GUSTA COMO ESTA
	
	
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
//NO INDICA POR QUE NO SE HA PODIDO EJECUTAR EL COMANDO
	}

	@Override
	public boolean addCheatPlant( String plantName, Position pos) { // GM
		if (this.isInsideBoard(pos)) {
			if (this.isEmpty(pos)) {
				Plant miplanta = PlantFactory.spawnPlant(plantName, this, pos);
				if (miplanta != null) {
					GOC.add(miplanta);
					return true;
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
	public void zombieReachedHouse() {
		zombiesReachedHouse = true;
	}

	@Override
	public void zombieWasKilled() {
		tZM.zombieWasKilled();
	}
	public boolean plantAtackReceived(Position atackPos, int damage) { // GW

		return this.GOC.plantAtackReceived(atackPos, damage);
	}

	@Override
	public void add(Zombies zombie) {

		GOC.add(zombie);
	}

	@Override
	public boolean addZombie( String zombieName, Position pos) { // GM
		if (this.isInsideBoard(pos)) {
			if (this.isEmpty(pos)) {
				Zombies zombie = ZombieFactory.spawnZombie(zombieName, this, pos);
				if (zombie != null) {
					this.GOC.add(zombie);
					return true;
				}
			}
		}
		return false;

	}
	
	@Override
	public boolean zombieAtackReceived(Position p, int damage) {
		return GOC.zombieAtackReceived(p,damage);
	}
	@Override
	public void explodePlant(Position center, int damage) {
		Position p;
		for(int i=-1;i<=1;i++) {
			for(int j=-1;j<=1;j++) {
				p=new Position(center.getRow() + i, center.getCol() + j);
				if(this.isInsideBoard(p)) {
					GOC.plantAtackReceived(p, damage);
				}
				
			}
		}
		
	}
	@Override
	public void explodeZombie(Position center, int damage) {
		Position p;
		for(int i=-1;i<=1;i++) {
			for(int j=-1;j<=1;j++) {
				p=new Position(center.getRow() + i, center.getCol() + j);
				if(this.isInsideBoard(p)) {
					GOC.zombieAtackReceived(p, damage);
				}
				
			}
		}
		
	}
}
