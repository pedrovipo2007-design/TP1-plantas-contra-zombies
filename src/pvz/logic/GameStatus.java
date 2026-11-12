package pvz.logic;

import utils.Position;

public interface GameStatus { // incluye los metodos que usa GamePrinter (no que implementa)

	String positionToString(Position position);

	boolean checkGameObject(String objectName);

	public int getCycles();

	public int getCoins();

	public int getRemainingZombies();

}
