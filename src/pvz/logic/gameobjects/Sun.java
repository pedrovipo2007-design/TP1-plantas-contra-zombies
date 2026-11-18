package pvz.logic.gameobjects;

import pvz.view.Messages;
import utils.Position;

/** No se si deberia extender gameObject, no encuentro ventajas fuera de que podriamos actualizarlo junto 
 * con el resto de objetos. Ademas esto podria llevar a interraciones y rompe la division 
 * pues GameObjectContainer guarda elementos que interactuan entre si y los soles no lo hacen*/
public class Sun {

	public static final int LIFETIME = 10;

	private final Position position;
	private int cyclesLeft;

	public Sun(Position position) {
		this.position = new Position(position.getRow(), position.getCol());
		this.cyclesLeft = LIFETIME;
	}

	public Position getPosition() {
		return new Position(position.getRow(), position.getCol());
	}

	public int getCyclesLeft() {
		return cyclesLeft;
	}

	public boolean isAlive() {
		return cyclesLeft > 0;
	}

	public void update() {
		cyclesLeft--;
	}

	public String getIcon() {
		return String.format(Messages.SUN_ICON, cyclesLeft);
	}
}
