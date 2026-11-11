package pvz.logic.gameobjects;
import pvz.logic.GameObject;
import pvz.logic.GameWorld;
import utils.Position;
public abstract class Plant extends GameObject {
	

	protected int price;
	

	Plant(GameWorld game, Position p) {
		super(game, p);
		// TODO Auto-generated constructor stub
	}



	
	
	
	
	public Plant() {
		super ();
		// TODO Auto-generated constructor stub
	}







	public int getPrice() {
		return price;
	}
	
	
	
	@Override
	public boolean plantAtackReceived(int damage) {
		return false;
	}
}
