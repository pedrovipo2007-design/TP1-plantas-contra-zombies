package pvz.logic;

import utils.Position;

public interface GameModel {

	// actualizar estado de la partida (reiniciar, actualizar...)
	// interactua con comandos y ejecuta acciones del usuario

	void reset();

	void update();

	void quit();

	void addPlant(GameModel model, String plantName, Position pos);

}
