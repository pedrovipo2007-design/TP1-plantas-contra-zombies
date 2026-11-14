package pvz.logic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import pvz.logic.gameobjects.Zombie;
import pvz.logic.gameobjects.Zombies;
import utils.Position;

public class ZombieFactory {

	private static final List<Zombies> AVAILABLE_ZOMBIES = Arrays.asList(new Zombie());

	// HEMOS DEFINIDO CONSTRUCTORAS SIN ARGUMENTOS PERO PUEDEN GENERAR PROBLEMAS EN
	// UN FUTURO SI SE CUELAN
	// EN GOC Y LLAMAN A METODOS QUE REQUIERAN DE UN MODEL O UNA POS PUES
	// ACCEDERIAMOS A MEMORIA VACIA
	// MISMO PROBLEMA QUE EN PLANTFACTORY FIXME

	public static Zombies spawnZombie(String ZombieName, GameWorld game, Position pos) {

		// adaptar spawnPlant TODO
		return null;
	}

	public static List<Zombies> getAvailableZombies() {

		return Collections.unmodifiableList(AVAILABLE_ZOMBIES);
	}
}
