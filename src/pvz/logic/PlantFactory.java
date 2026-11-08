package pvz.logic;
import utils.Position;
import utils.StringUtils;
import java.util.Arrays;
import pvz.logic.gameobjects.Plant;
import pvz.view.Messages;
import java.util.*;
import pvz.logic.gameobjects.Sunflower;
import pvz.logic.gameobjects.Peashooter;
import pvz.view.Messages;
public class PlantFactory {
	private static final List<Plant> AVAILABLE_PLANTS = Arrays.asList(
			 
			 );
	// como creo instancias de sunflower y peashooter sin tener un world asociado ni una posicion
	
	
	public static Plant spawnPlant(String plantName, GameWorld game, Position pos) {
		if(plantName.equals(Messages.SUNFLOWER_SYMBOL)|| plantName.equals(Messages.SUNFLOWER_NAME)) {
			return new Sunflower(pos,game);
		}
		if(plantName.equals(Messages.PEASHOOTER_SYMBOL)|| plantName.equals(Messages.PEASHOOTER_NAME) ) {
			return new Peashooter(pos,game);
		}
		else return null;
	}
	
	
	public static List<Plant> getAvailablePlants() {
		 return Collections.unmodifiableList(AVAILABLE_PLANTS);
		 }

	

}
