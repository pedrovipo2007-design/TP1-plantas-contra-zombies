package pvz.logic.gameobjects;

import pvz.logic.Game;
import pvz.view.Messages;
import utils.Position;

public class ZombieList {

	private Zombie[] zombies;
	private int numberOfZombies;

	public ZombieList() {
		zombies = new Zombie[(1 + Game.NUM_COLS) * (Game.NUM_ROWS)];
		numberOfZombies = 0;
	}

	public boolean noMoreZombies() {
		return numberOfZombies == 0;
	}

	public void reset() {
		numberOfZombies = 0;
		for(int i =0;i<Game.NUM_COLS*Game.NUM_ROWS;i++) {
			zombies[i]=null;
		}
	}

	public String iconInPosition(Position p) {
		return String.format(Messages.ZOMBIE_ICON, zombies[p.Zhashcode()].getHealth());
	}

	public boolean isEmpty(Position p) {
		return (zombies[p.Zhashcode()] == null);
	}

	public void damage(Position p, int damage) {
		zombies[p.Zhashcode()].receiveDamage(damage);
	}

	public void update() {
	    for (int i = 0; i < Game.NUM_COLS + 1; i++) {
	        for (int j = 0; j < Game.NUM_ROWS; j++) {
	            Position position = new Position(j, i);
	            if (!isEmpty(position)) {
	                int idx = position.Zhashcode();
	                Zombie z = zombies[idx];
	                z.update();
	                int newIdx = z.getPosition().Zhashcode();
	                if (newIdx != idx) {          // se ha movido
	                    zombies[newIdx] = z;
	                    zombies[idx] = null;
	                }
	            }
	        }
	    }
	    removeDead();
	}

	public void removeDead() {
		for (int i = 0; i < Game.NUM_COLS; i++) {
			for (int j = 0; j < Game.NUM_ROWS; j++) {
				Position position;
				position = new Position(j, i);
				if (!isEmpty(position)) {
					if (!zombies[position.Zhashcode()].isAlive()) {
						zombies[position.Zhashcode()] = null;
						numberOfZombies--;
					}
				}
			}
		}
	}

	public void add(Zombie z) {

		zombies[z.getPosition().Zhashcode()] = z;
		numberOfZombies++;
	}

	public Zombie firstZombieInRow(int row, int col) { // la columna desde la que dispara el lanzaguisantes

		while (col < Game.NUM_COLS) {
			Position position;
			position = new Position(row, col);
			if (!isEmpty(position)) {

				col++;
			} else

				return zombies[position.Zhashcode()];
		}
		return null;
	}
}
