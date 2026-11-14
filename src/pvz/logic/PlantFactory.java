package pvz.logic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import pvz.logic.gameobjects.Peashooter;
import pvz.logic.gameobjects.Plant;
import pvz.logic.gameobjects.Sunflower;
import pvz.view.Messages;
import utils.Position;

public class PlantFactory {
	private static final List<Plant> AVAILABLE_PLANTS = Arrays.asList(new Peashooter(), new Sunflower());
	// definimos constructora por defecto

	// HEMOS DEFINIDO CONSTRUCTORAS SIN ARGUMENTOS PERO PUEDEN GENERAR PROBLEMAS EN
	// UN FUTURO SI SE CUELAN
	// EN GOC Y LLAMAN A METODOS QUE REQUIERAN DE UN MODEL O UNA POS PUES
	// ACCEDERIAMOS A MEMORIA VACIA
	public static Plant spawnPlant(String plantName, GameWorld game, Position pos) {
		if (plantName.equalsIgnoreCase(Messages.SUNFLOWER_SYMBOL) || plantName.equals(Messages.SUNFLOWER_NAME)) {
			return new Sunflower(pos, game);
		}
		if (plantName.equalsIgnoreCase(Messages.PEASHOOTER_SYMBOL) || plantName.equals(Messages.PEASHOOTER_NAME)) {
			return new Peashooter(pos, game);
		} else
			return null;
	}

	public static List<Plant> getAvailablePlants() {
		return Collections.unmodifiableList(AVAILABLE_PLANTS);
	}

	// esto sireve para pasar una copia que no se puede modificar(CONST)
	// Collections viene en java.util
	// static porque lo llamamos desde command y no tenemos instancia

}
