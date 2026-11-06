package pvz.logic;

import java.util.ArrayList;
import java.util.List;

public class GameObjectContainer { // clase donde delega game, almacen de objetos del juegos, actualiza, borra...

	private List<GameObject> gameObjects;

	public GameObjectContainer() {

		gameObjects = new ArrayList<>();
	}
}
