package pvz.logic;

public interface GameItem {

	boolean plantAtackReceived(int damage); // encapsular metodos relacionados con las interacciones dentro del juego
	//LA PLANTA ATACA A UN ZOMBIE
	
	boolean zombieAtackReceived(int damage);
}
