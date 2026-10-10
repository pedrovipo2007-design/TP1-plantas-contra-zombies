package pvz.logic.gameobjects;
import utils.Position;
public class Zombie {
	private Position position;
	public static final int INITIAL_HEALTH = 4;
	public static final int DAMAGE = 4;
	public static final int MOVE_AFTER_CYCLES = 4;
	private int health;
	private int cyclesNotMoving;
	
	
	
	public Zombie(Position p) {
		position=p;
		health= INITIAL_HEALTH;
		cyclesNotMoving=0;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public boolean mayor(Zombie zombie2) {//preguntarle a la profe si esto se puede hacer como interfaz o utilizando clases abstractas para no repetir en cada objeto que involucra la clase position
		if(position.getRow()>zombie2.getPosition().getRow())return true;
		else if (position.getCol()>zombie2.getPosition().getCol())return true;
		else return false;
	}
	public Zombie() {
		
	}
}

