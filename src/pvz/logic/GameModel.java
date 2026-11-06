package pvz.logic;

public interface GameModel {

	// actualizar estado de la partida (reiniciar, actualizar...)
	// interactua con comandos y ejecuta acciones del usuario

	void reset();

	void update();

	void quit();

}
