package pvz.logic;

import pvz.control.Level;
import utils.Position;

public interface GameModel {

	// actualizar estado de la partida (reiniciar, actualizar...)
	// interactua con comandos y ejecuta acciones del usuario

	void reset();

    void reset(Level level, long seed);

	// aqui no se si habra que utilizar el hecho de que el command lo ejecuto con un
	// model

	void update();

	void quit(); // este va a ser equivalente a ejecutar exit?? lo he usado suponiendo que si

	

	boolean addPlant(String plantName, Position pos);

	boolean isInsideBoard(Position position);

	boolean isEmpty(Position position);
	
	boolean addCheatPlant(String plantName, Position pos);

	boolean hasGameFinished();

	boolean playerWins();

	boolean playerQuits();

	boolean addZombie(String plantName, Position pos);

	boolean catchSun(Position pos);

	boolean hasCaughtSunThisCycle();

	//boolean addZombie(String s, Position p); // ver AddZombieCommand
}
