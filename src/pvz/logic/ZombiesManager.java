package pvz.logic;

import java.util.List;
import java.util.Random;

import pvz.control.Level;
import pvz.logic.gameobjects.Zombie;
import pvz.logic.gameobjects.Zombies;
import utils.Position;
import pvz.logic.ZombieFactory;

/**
 * Manages the full lifecycle of zombies for a game session.
 *
 * <p>
 * Responsibilities: deciding each cycle whether to spawn a new zombie
 * (probabilistically, subject to the remaining quota from {@link Level}),
 * delegating per-cycle updates and dead-removal to the underlying
 * {@link ZombieList}, and answering win/loss queries
 *
 */

public class ZombiesManager {

	private GameWorld game;

	private Level level;

	private Random rand;

	private int remainingZombies;
	
	private int zombiesOnGame;


	public ZombiesManager(GameWorld game, Level level, Random rand) {
		this.game = game;
		this.level = level;
		this.rand = rand;
		this.remainingZombies = level.getNumberOfZombies();

		zombiesOnGame=0;
	}

	/**
	 * Checks if the game should add (if possible) a zombie to the game.
	 * 
	 * @return <code>true</code> if a zombie should be added to the game.
	 */

	private boolean shouldAddZombie() {
		return rand.nextDouble() < level.getZombieFrequency();

	}

	

	/**
	 * Return a random row within the board limits.
	 * 
	 * @return a random row.
	 */

	private int randomZombieRow() {
		return rand.nextInt(Game.NUM_ROWS);
	}

	

	public boolean addZombie() {
		int row = randomZombieRow();
		return addZombie(row);
	}

	public boolean addZombie(int row) {
		Position zombiePosition = game.newZombiePosition(row);

		boolean spawnRequested = shouldAddZombie();
		boolean canAdd = this.remainingZombies > 0 && spawnRequested && game.isEmptyOfZombies(zombiePosition);

		if (canAdd) {
			List<Zombies> types = ZombieFactory.getAvailableZombies();
			Zombies randomType = types.get(rand.nextInt(types.size()));
			Zombies zombi = ZombieFactory.spawnZombie(randomType.getName(), game, zombiePosition);
			game.add(zombi);
			remainingZombies--;
		}
		return canAdd;
	}

	public int getRemainingZombies() {
		return remainingZombies;
	}



	public boolean isEmpty(Position pos) {
		return game.isEmpty(pos);
	}
	

	public boolean allZombiesWereKilled() {
		return (remainingZombies == 0 && zombiesOnGame==0);
	}


	public void zombieWasAdded() {
		zombiesOnGame++;
	}
	public void zombieWasKilled() {
		zombiesOnGame--;
	}
	public void addZombiePosition(Position pos) { // para debugear
		Zombie zombie;
		zombie = new Zombie(pos, game);
		game.add(zombie);
	}

	public void reset() {
		
		this.remainingZombies = level.getNumberOfZombies();

		zombiesOnGame=0;
		
	}

	public boolean noMoreZombies() {

		return zombiesOnGame==0;
	}
}
