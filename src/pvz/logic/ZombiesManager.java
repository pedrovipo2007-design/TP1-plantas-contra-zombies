package pvz.logic;

import java.util.Random;

import pvz.control.Level;
import pvz.logic.gameobjects.Zombie;
import pvz.logic.gameobjects.ZombieList;
import utils.Position;

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

	private Game game;

	private Level level;

	private Random rand;

	private int remainingZombies;

	private ZombieList zombies;

	public ZombiesManager(Game game, Level level, Random rand) {
		this.game = game;
		this.level = level;
		this.rand = rand;
		this.remainingZombies = level.getNumberOfZombies();
		this.zombies = new ZombieList();
	}

	/**
	 * Checks if the game should add (if possible) a zombie to the game.
	 * 
	 * @return <code>true</code> if a zombie should be added to the game.
	 */

	private boolean shouldAddZombie() {
		return rand.nextDouble() < level.getZombieFrequency();

	}

	public boolean noMoreZombies() {
		return zombies.noMoreZombies();
	}

	/**
	 * Return a random row within the board limits.
	 * 
	 * @return a random row.
	 */

	private int randomZombieRow() {
		return rand.nextInt(Game.NUM_ROWS);
	}

	public String iconInPosition(Position p) {
		return zombies.iconInPosition(p);
	}

	public boolean addZombie() {
		int row = randomZombieRow();
		if (addZombie(row))
			remainingZombies--;
		return true;
	}

	public boolean addZombie(int row) {

		Position zombiePosition = Game.newZombiePosition(row);

		boolean canAdd = shouldAddZombie() && this.remainingZombies > 0 && game.isEmpty(zombiePosition);

		if (canAdd) {
			Zombie zombie;
			zombie = new Zombie(zombiePosition, game);
			zombies.add(zombie);
		}
		return canAdd;
	}

	public int getRemainingZombies() {
		return remainingZombies;
	}

	public void reset() {

		this.remainingZombies = level.getNumberOfZombies();
		zombies.reset();
	}

	public boolean doZombiesReachedTheHouse() {
		
		    return zombies.hasReachedHouse();
		
	}

	public void damageZombie(Position pos, int damage) {

		int row = pos.getRow();
		int col = pos.getCol();

		int p = col;
		p++;

		while (p < game.NUM_COLS) {

			if (!isEmpty(new Position(row, p))) {
				Position aux = new Position(row, p);
				zombies.damage(aux, damage);
				break;

			} else
				p++;
		}
	}

	public boolean isEmpty(Position pos) {
		return zombies.isEmpty(pos);
	}

	public void update() {
		zombies.update();
	}

	public boolean allZombiesWereKilled() {
		return (remainingZombies == 0 && zombies.noMoreZombies());
	}

	public void removeDead() {
		zombies.removeDead();
	}

	public void addZombiePosition(Position pos) { // para debugear
		Zombie zombie;
		zombie = new Zombie(pos, game);
		zombies.add(zombie);
	}
}
