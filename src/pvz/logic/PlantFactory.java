package pvz.logic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import utils.Position;

public class PlantFactory { // patron para independizar la creación de la lógica, no se cual es su package

	private static final List<Plant> AVAILABLE_PLANTS = Arrays.asList(

	);

	public static Plant spawnPlant(String plantName, GameWorld game, Position position) {
		// ...
	}

	public static List<Plant> getAvailablePlants() {

		return Collections.unmodifiableList(AVAILABLE_PLANTS);
	}
}
