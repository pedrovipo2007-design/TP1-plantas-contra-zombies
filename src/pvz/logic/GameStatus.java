package pvz.logic;

public interface GameStatus { // incluye los metodos que usa GamePrinter (no que implementa)

	String positionToString();

	boolean checkGameObject(String objectName);

	public int getCycles();

	public int getCoins();

	public int getRemainingZombies();

}
