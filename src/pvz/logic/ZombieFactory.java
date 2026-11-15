package pvz.logic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import pvz.logic.gameobjects.Peashooter;
import pvz.logic.gameobjects.Plant;
import pvz.logic.gameobjects.SportyZombie;
import pvz.logic.gameobjects.Sunflower;
import pvz.logic.gameobjects.Zombie;
import pvz.logic.gameobjects.Zombies;
import pvz.view.Messages;
import utils.Position;

public class ZombieFactory {

	private static final List<Zombies> AVAILABLE_ZOMBIES = Arrays.asList(new Zombie());

	// HEMOS DEFINIDO CONSTRUCTORAS SIN ARGUMENTOS PERO PUEDEN GENERAR PROBLEMAS EN
	// UN FUTURO SI SE CUELAN
	// EN GOC Y LLAMAN A METODOS QUE REQUIERAN DE UN MODEL O UNA POS PUES
	// ACCEDERIAMOS A MEMORIA VACIA
	// MISMO PROBLEMA QUE EN PLANTFACTORY FIXME

	public static Zombies spawnZombie(String zombieName, GameWorld game, Position pos) {
		
		if (zombieName.equalsIgnoreCase(Messages.ZOMBIE_SYMBOL) || zombieName.equals(Messages.ZOMBIE_NAME)) {
			return new Zombie(pos, game);
		}
		if (zombieName.equalsIgnoreCase(Messages.SPORTY_ZOMBIE_SYMBOL) || zombieName.equals(Messages.SPORTY_ZOMBIE_NAME)) {
			return new SportyZombie(pos, game);//hay que añadir el resto de zombies TODO
		} else
		return null;
	}

	public static List<Zombies> getAvailableZombies() {

		return Collections.unmodifiableList(AVAILABLE_ZOMBIES);
	}
}

