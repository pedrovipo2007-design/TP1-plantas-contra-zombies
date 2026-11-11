package pvz.logic;

import utils.Position;

public interface GameModel {

	// actualizar estado de la partida (reiniciar, actualizar...)
	// interactua con comandos y ejecuta acciones del usuario

	void reset();//aqui no se si habra que utilizar el hecho de que el command lo ejecuto con un model 

	void update();

	void quit();//este va a ser equivalente a ejecutar exit?? lo he usado suponiendo que si

	void addPlant(GameModel model, String plantName, Position pos);

	boolean hasGameFinished();

}
