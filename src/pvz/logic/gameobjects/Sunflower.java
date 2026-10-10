package pvz.logic.gameobjects;
import utils.Position;
public class Sunflower {
	
	private Position position;
	
	public static Object getDescription() {
		// TODO Auto-generated method stub
		return "[S]unflower: cost='20' suncoins, damage='0', endurance='1'";
	}
	
	
	public Position getPosition() {
		return position;
	}
	
	public boolean mayor(Sunflower sunflower2) {//preguntarle a la profe si esto se puede hacer como interfaz o utilizando clases abstractas para no repetir en cada objeto que involucra la clase position
		if(position.getRow()>sunflower2.getPosition().getRow())return true;
		else if (position.getCol()>sunflower2.getPosition().getCol())return true;
		else return false;
	}
}
