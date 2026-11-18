package pvz.logic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import pvz.logic.gameobjects.BucketHeadZombie;
import pvz.logic.gameobjects.ExplosiveZombie;
import pvz.logic.gameobjects.SportyZombie;
import pvz.logic.gameobjects.Zombie;
import pvz.logic.gameobjects.Zombies;
import pvz.view.Messages;
import utils.Position;

public class ZombieFactory {

	private static final List<Zombies> AVAILABLE_ZOMBIES = Arrays.asList(
			new Zombie(), new BucketHeadZombie(), new SportyZombie(), new ExplosiveZombie());

	// HEMOS DEFINIDO CONSTRUCTORAS SIN ARGUMENTOS PERO PUEDEN GENERAR PROBLEMAS EN
	// UN FUTURO SI SE CUELAN
	// EN GOC Y LLAMAN A METODOS QUE REQUIERAN DE UN MODEL O UNA POS PUES
	// ACCEDERIAMOS A MEMORIA VACIA
	// MISMO PROBLEMA QUE EN PLANTFACTORY FIXME

	public static Zombies spawnZombie(String zombieName, GameWorld game, Position pos) {
		if (zombieName == null) return null;
		if (zombieName.equalsIgnoreCase(Messages.ZOMBIE_SYMBOL)
				|| zombieName.equalsIgnoreCase(Messages.ZOMBIE_NAME)) {
			return new Zombie(pos, game);
		}
		if (zombieName.equalsIgnoreCase(Messages.BUCKET_HEAD_ZOMBIE_SYMBOL)
				|| zombieName.equalsIgnoreCase(Messages.BUCKET_HEAD_ZOMBIE_NAME)) {
			return new BucketHeadZombie(pos, game);
		}
		if (zombieName.equalsIgnoreCase(Messages.SPORTY_ZOMBIE_SYMBOL)
				|| zombieName.equalsIgnoreCase(Messages.SPORTY_ZOMBIE_NAME)) {
			return new SportyZombie(pos, game);
		}
		if (zombieName.equalsIgnoreCase(Messages.EXPLOSIVE_ZOMBIE_SYMBOL)
				|| zombieName.equalsIgnoreCase(Messages.EXPLOSIVE_ZOMBIE_NAME)) {
			return new ExplosiveZombie(pos, game);
		}
		return null;
	}

	public static boolean isZombieName(String zombieName) {
		return zombieName != null && (zombieName.equalsIgnoreCase(Messages.ZOMBIE_SYMBOL)
				|| zombieName.equalsIgnoreCase(Messages.ZOMBIE_NAME)
				|| zombieName.equalsIgnoreCase(Messages.BUCKET_HEAD_ZOMBIE_SYMBOL)
				|| zombieName.equalsIgnoreCase(Messages.BUCKET_HEAD_ZOMBIE_NAME)
				|| zombieName.equalsIgnoreCase(Messages.SPORTY_ZOMBIE_SYMBOL)
				|| zombieName.equalsIgnoreCase(Messages.SPORTY_ZOMBIE_NAME)
				|| zombieName.equalsIgnoreCase(Messages.EXPLOSIVE_ZOMBIE_SYMBOL)
				|| zombieName.equalsIgnoreCase(Messages.EXPLOSIVE_ZOMBIE_NAME));
	}

	public static List<Zombies> getAvailableZombies() {
		return Collections.unmodifiableList(AVAILABLE_ZOMBIES);
	}
}

