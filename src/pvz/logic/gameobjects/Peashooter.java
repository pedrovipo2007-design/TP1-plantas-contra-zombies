package pvz.logic.gameobjects;

import utils.Position;

public class Peashooter {
	private Position position;
	
	
	public static Object getDescription() {
		// TODO Auto-generated method stub
		return "[P]eashooter: cost='50' suncoins, damage='1', endurance='3'";
	}

	
	public Position getPosition() {
		return position;
	}
	
	public boolean mayor(Peashooter peashooter2) {//preguntarle a la profe si esto se puede hacer como interfaz o utilizando clases abstractas para no repetir en cada objeto que involucra la clase position
		if(position.getRow()>peashooter2.getPosition().getRow())return true;
		else if (position.getCol()>peashooter2.getPosition().getCol())return true;
		else return false;
	}
	
	
}
